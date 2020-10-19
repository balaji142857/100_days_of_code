package com.kb.sort.impl;

import java.util.ArrayList;
import java.util.List;

import com.kb.sort.SortService;

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
		int minIndex = 0;
		for (int i = 0; i < input.size(); i++) {
			if ( i == minIndex) {
				continue;
			}
			if(input.get(minIndex).compareTo(input.get(i))  > 0) {
				minIndex = i;
			}
		}
		return minIndex;
	}

}
