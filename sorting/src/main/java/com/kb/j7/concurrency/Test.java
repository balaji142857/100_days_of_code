package com.kb.j7.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test {
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

		System.out.println("countDownLatch: ");
		testCountDownLatch();
		System.out.println("cyclicBarrier: ");
		testCyclicBarrier();
		System.out.println("phaser");
		testPhaser();
		System.out.println("semaphore");
		testSemaphore();
		System.out.println("exchanger");
		testExchanger();
		System.out.println("executors + fkJoin + shutdown/shutdownNow");
		testExecutors();
	}

	private static void testExchanger() throws InterruptedException {
		Exchanger<String> e = new Exchanger<>();
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(3 + Math.round(Math.random() * 10));
				System.out.println(e.exchange("from runnable"));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		es.shutdown();
		System.out.println("initiating exchange at " + System.currentTimeMillis());
		System.out.println(e.exchange("from main"));
		System.out.println("exchange completed at " + System.currentTimeMillis());
	}

	private static void testExecutors()  {
		ExecutorService es = Executors.newFixedThreadPool(4);
		/**
		 * One good way to shut down the ExecutorService 
		 * is to use both of these methods combined with the awaitTermination() method.
		 * 
		 * shutdown
		 * try 
		 * 		awaitTermination
		 * catch
		 * 		shutDownNow
		 *  
		 */
		es.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(5  + Math.round(Math.random() * 5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		 // shutdown - wont block calling thread, wont accept new requests - but ongoing tasks will NOT BE interrupted
		es.shutdown();
		/**
		 * await termination - block current thread until either the ES shuts down or time expires
 		 * whichever happens first. if time expired exception will be thrown
		 */
		try {
			es.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			 // shutdownNow same as shutdown - diff is this WILL interrupt the ongoing tasks
			es.shutdownNow();
		}
		CompletableFuture cf;
	}

	private static void testSemaphore() throws InterruptedException {
		Semaphore sem = new Semaphore(4);
		sem.acquire();//acquire 1 permit. if no permits are available calling thread is blocked
		sem.acquire(2);//acquire n permits
		sem.tryAcquire();//acquire - but if no available permits return boolean - will not block
		sem.release();// release permit
		sem.acquireUninterruptibly();//same as acquire - but interrupting the thread will not unblock
		sem.release(2);
		sem.release();
		System.out.println("semaphore test finished");
	}

	private static void testPhaser() {
		int threadCount = Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(threadCount);
		Thread.currentThread().setName("main-1-thread-1");
		Phaser p = new Phaser();
		System.out.println("after phaser construction");
		printPhaserDetails(Thread.currentThread().getName(), p);
		p.register();
		System.out.println("after phaser register");
		printPhaserDetails(Thread.currentThread().getName(), p);
		IntStream.range(0, threadCount).forEach(item -> es.submit(new MyPhaserCallable(p)));
		printPhaserDetails(Thread.currentThread().getName() + " arrived1", p);
		p.arriveAndAwaitAdvance();
		printPhaserDetails(Thread.currentThread().getName() + " Arrive&Advance", p);
		p.arriveAndDeregister();
		printPhaserDetails(Thread.currentThread().getName() + " deregistered", p);
		es.shutdown();
		Thread.currentThread().setName("main");
	}

	private static void testCyclicBarrier() throws InterruptedException, BrokenBarrierException {
		int threadCount = Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(threadCount);
		CyclicBarrier cb = new CyclicBarrier(threadCount, () -> {
			System.out.println(Thread.currentThread().getName() + " barrier completed action");
		});
		List<Future<String>> result = new ArrayList<>();
		IntStream.range(0, 4).forEach(item -> result.add(es.submit(new MyBarrierCallable(cb))));
		es.shutdown();
		result.stream().forEach(future -> {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		System.out.println("cyclic barrier resets once all threads have called await,");

	}

	private static void testCountDownLatch() throws InterruptedException {
		int threadCount = Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(threadCount);
		CountDownLatch cl = new CountDownLatch(threadCount);

		List<Future<String>> result = new ArrayList<>();
		IntStream.range(0, 4).forEach(item -> result.add(es.submit(new MyLatchCallable(cl))));
		// if timeout exceeds exception is not thrown, return value is false... await()
		// return type is void btw
		boolean waitResult = cl.await(10, TimeUnit.SECONDS);
		System.out.println("wait result is" + waitResult);
		System.out.println("additional await calls on the same latch has no effect"
				+ " - counter will reamain at zero and will not reset once all threads have called the countDown()");
		cl.await();
		cl.await();
		cl.await();
		cl.await();
		System.out.println("subsequent await calls completed");
		es.shutdown();
		result.stream().forEach(future -> {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		System.out.println(
				"countDownLatch will not reset even after all threads call caountDown, count:" + cl.getCount());
	}

	public static void printPhaserDetails(String threadName, Phaser p) {
		System.out.println(threadName + "\t" + System.currentTimeMillis() + "\t:::\t arrivedParties: "
				+ p.getArrivedParties() + ",unarrivedParties " + p.getUnarrivedParties() + ", phase: " + p.getPhase());
	}
}

class MyLatchCallable implements Callable<String> {

	private CountDownLatch latch = null;

	MyLatchCallable(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public String call() throws Exception {
		long sleepTime = Math.round(10 * Math.random());
		TimeUnit.SECONDS.sleep(sleepTime);
		latch.countDown();
		return Thread.currentThread().getName() + " slept for " + sleepTime;
	}
}

class MyBarrierCallable implements Callable<String> {

	private CyclicBarrier cb = null;

	MyBarrierCallable(CyclicBarrier cb) {
		this.cb = cb;
	}

	@Override
	public String call() throws Exception {
		long sleepTime = Math.round(10 * Math.random());
		TimeUnit.SECONDS.sleep(sleepTime);
		System.out.println(Thread.currentThread().getName() + " calling cb.await");
		cb.await();
		System.out.println(Thread.currentThread().getName() + " cb.await completed");
		return Thread.currentThread().getName() + " slept for " + sleepTime;
	}
}

class MyPhaserCallable implements Callable<String> {

	private Phaser p;

	MyPhaserCallable(Phaser p) {
		this.p = p;
	}

	@Override
	public String call() throws Exception {
		Test.printPhaserDetails(Thread.currentThread().getName() + " before register", p);
		p.register();
		Test.printPhaserDetails(Thread.currentThread().getName() + " after register", p);
		TimeUnit.SECONDS.sleep(Math.round(Math.random()*10));
		Test.printPhaserDetails(Thread.currentThread().getName() + " arrived1", p);
		p.arriveAndAwaitAdvance();
		TimeUnit.SECONDS.sleep(Math.round(Math.random()*10));
		p.arriveAndDeregister();
		Test.printPhaserDetails(Thread.currentThread().getName() + " after arr&dereg", p);
		return null;
	}

}


