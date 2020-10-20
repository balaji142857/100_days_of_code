package com.kb.j8.fi;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

	public static void main(String[] args) {
		
		//function
		Function<String, Integer> stringToInt = s -> Integer.parseInt(s);
		Function<Integer, String> backToString = i -> String.valueOf(i); 
		Function<String,String> noOps = stringToInt.andThen(backToString);
		System.out.println(noOps.apply("123"));
		
		//supplier & consumer
		Supplier<String>  supplier = () -> "hello, world!";
		Consumer<String> consumer = s -> System.out.println("Consumed "+ s);
		consumer.accept(supplier.get());
		
		BiFunction<String, String, String> biFunc  = (a,b) -> a.concat(b);
		consumer.accept(biFunc.apply("Hello",", World!."));
		
		Predicate<String> filter = s -> null != s && !"".equals(s.trim());
		System.out.println(filter.test(null));
		System.out.println(filter.test(supplier.get()));
		
		Predicate<String> predicate = Predicate.isEqual("");
		predicate.test("");
	}
}
