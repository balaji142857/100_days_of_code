package com.krishnan.balaji.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.krishnan.balaji.service.SortService;

public class SelectionSort<T extends Comparable<T>> implements SortService<T> {

	@Override
	public List<T> sort(List<T> input) {
		List<T> output = new ArrayList<>(input.size());
		while(input.size() > 0) {
			int index = findMinIndex(input);
			T item = input.remove(index);
			output.add(item);
			
		}
		return output;
	}

	private int findMinIndex(List<T> input) {
		T minSoFar = null;
		int minIndex = 0;
		for (int i = 0; i < input.size(); i++) {
			if ( null == minSoFar) {
				minSoFar = input.get(i);
				minIndex = i;
				continue;
			}
			if(minSoFar.compareTo(input.get(i))  > 0) {
				minSoFar = input.get(i);
				minIndex = i;
			}
		}
		return minIndex;
	}

}
