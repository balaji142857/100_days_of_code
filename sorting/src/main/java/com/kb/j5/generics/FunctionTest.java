package com.kb.j5.generics;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionTest {

	public static void main(String[] args) {
		MyFunction<Integer, Integer> f1 = null;
		MyFunction<Integer, Integer> f2 = null;
		MyFunction<Integer, Number> f3 = f1.andThen(f2);
		Stream s;
		s.collect(Collectors.toList())
		MyFunction<Integer, Double> third = x -> x / 3.0;
		f3 = f1.andThen(third);
		
		
		Function<Integer, Integer> ff1 = null;
		Function<Integer, Integer> ff2 = null;
		Function<Integer, Number> ff3 = ff1.andThen(ff2);
		MyFunction<Integer, Double> ff4 = x -> x / 3.0;
		ff3 = f1.andThen(ff4);
	}

}



class Cell { }
class Organism extends Cell { }
class LivingBeing extends Organism { }
class Animal extends LivingBeing { }
class Dog extends Animal { }
class Puppy extends Dog { }