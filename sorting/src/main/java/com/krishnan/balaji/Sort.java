package com.krishnan.balaji;

import java.util.List;

@FunctionalInterface
public interface Sort<T extends Comparable<T>> {
	
	public List<T> sort(List<T> input);

}
