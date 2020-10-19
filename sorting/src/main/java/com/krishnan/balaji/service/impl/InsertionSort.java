package com.krishnan.balaji.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.krishnan.balaji.service.SortService;

public class InsertionSort<T extends Comparable<T>> implements SortService<T> {

	@Override
	public List<T> sort(List<T> input) {
		List<T> output = new ArrayList<>(input.size());
		while (input.size() > 0) {
			T item = input.remove(0);
			output.add(findIndexFor(output, item), item);
		}
		return output;
	}

	private int findIndexFor(List<T> list, T item) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).compareTo(item) > 0) {
				return i;
			}
		}
		return 0;
	}
}
