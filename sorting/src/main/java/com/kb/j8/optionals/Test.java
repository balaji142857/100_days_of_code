package com.kb.j8.optionals;

import java.util.Optional;
import java.util.function.Supplier;

public class Test {
	
	
	public static void main(String[] args) {
		Optional.ofNullable(null);
		Optional.empty();
		Optional<String> a = null;
		a.isPresent();
		a.ifPresent(val -> {
			
		});
		Supplier<String> supplier = () -> "hello world";
		a.orElse(someStringReturningMethod());
		a.orElseGet(supplier);
	}

	private static String someStringReturningMethod() {
		System.out.println("Method invoked");
		return "yikes, not a lazy call";
	}

}
