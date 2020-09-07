package com.krishnan.balaji.service;

import java.util.List;

@FunctionalInterface
public interface SortService<T extends Comparable<T>> {
	
	public List<T> sort(List<T> input);

}
