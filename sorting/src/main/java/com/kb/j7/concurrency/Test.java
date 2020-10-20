package com.kb.j7.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test {
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

//		CyclicBarrier cb;
//		Semaphore s;
//		Phaser p;
//		Exchanger ex;
		System.out.println("countDownLatch: ");
		testCountDownLatch();
		System.out.println("cyclicBarrier: ");
		testCyclicBarrier();
		System.out.println("phaser");
		testPhaser();
		System.out.println("semaphore");
		testSemaphore();
		System.out.println("executors + fkJoin");
		testExecutors();

	}

	private static void testExecutors() {
		// TODO Auto-generated method stub
		
	}

	private static void testSemaphore() {
		// TODO Auto-generated method stub
		
	}

	private static void testPhaser() {
		// TODO Auto-generated method stub
		
	}

	private static void testCyclicBarrier() throws InterruptedException, BrokenBarrierException {
		int threadCount = Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(threadCount);
		CyclicBarrier cb = new CyclicBarrier(threadCount, () -> {
			System.out.println(Thread.currentThread().getName() + " barrier completed action");
		});
		List<Future<String>> result = new ArrayList<>();
		IntStream.range(0, 4).forEach(item -> result.add(es.submit(new MyDiffCallable(cb))) );
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
		IntStream.range(0, 4).forEach(item -> result.add(es.submit(new MyCallable(cl))) );
		// if timeout exceeds exception is not thrown, return value is false... await() return type is void btw
		boolean waitResult = cl.await(10, TimeUnit.SECONDS);
		System.out.println("wait result is"+ waitResult);
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
		System.out.println("countDownLatch will not reset even after all threads call caountDown, count:" +cl.getCount());
	}

}

class MyCallable implements Callable<String> {

	private CountDownLatch latch = null;

	MyCallable(CountDownLatch latch) {
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


class MyDiffCallable implements Callable<String> {

	private CyclicBarrier cb= null;

	MyDiffCallable(CyclicBarrier cb) {
		this.cb = cb;
	}

	@Override
	public String call() throws Exception {
		long sleepTime = Math.round(10 * Math.random());
		TimeUnit.SECONDS.sleep(sleepTime);
		System.out.println(Thread.currentThread().getName()+" calling cb.await");
		cb.await();
		System.out.println(Thread.currentThread().getName()+" cb.await completed");
		return Thread.currentThread().getName() + " slept for " + sleepTime;
	}
}