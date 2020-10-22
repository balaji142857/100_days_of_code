package com.kb.j5.generics;

@FunctionalInterface
public interface MyFunction<T, R> {

	R apply(T arg);

	default <V> MyFunction<T, V> andThen(MyFunction<? super R, V> after) {
		return t -> after.apply(apply(t));
	}

	default <V> MyFunction<V, R> compose(MyFunction<V, ? extends T> before) {
		return t -> apply(before.apply(t));
	}

	default MyFunction<T, T> identity() {
		return t -> t;
	}
}