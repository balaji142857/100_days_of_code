package com.kb.j8.optionals;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test {
	
	
	public static void main(String[] args) {
		Optional.ofNullable(null);
		Optional.empty();
		
		Optional<User> b  = Optional.of(new User());
		Optional<Object> mapValue = b.map(u -> u.getEmail());
		System.out.println("map: "+ mapValue);
		Optional<String> flatMapValue = b.flatMap(u -> u.getEmail());
		System.out.println("flatMap: "+ flatMapValue);
		b.isPresent();
		b.ifPresent(val -> {
			
		});
		Supplier<String> supplier = () -> "hello world";
		Optional<String> a = Optional.ofNullable(null);
		a.orElse(someStringReturningMethod());
		a.orElseGet(supplier);
	}

	private static String someStringReturningMethod() {
		System.out.println("Method invoked");
		return "yikes, not a lazy call";
	}

}


class User {
	String email;
	User() {
		this.email = "abcd@gmail.com";
	}
	public Optional<String> getEmail() {
		return Optional.ofNullable(email);
	}
}