package com.krishnan.balaji.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.krishnan.balaji.service.SortService;

public class MergeSort<T extends Comparable<T>> implements SortService<T>{

	@Override
	public List<T> sort(List<T> input) {
		return input;
	}
	
	private void split(List<T> input, List<List<T>> output) {
		//recursive
		//split list into two until size is 1
	};
	
	private List<T> merge(List<T> input1, List<T> input2) {
		return new ArrayList<>();
	}

	
}
