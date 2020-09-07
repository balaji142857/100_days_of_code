package com.krishnan.balaji.service.impl;

import java.util.List;

import com.krishnan.balaji.service.SortService;

public class BubbleSort<T extends Comparable<T>> implements SortService<T> {

	@Override
	public List<T> sort(List<T> input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.size() - 1; j++) {
				int result = input.get(j).compareTo(input.get(j + 1));
				if (result > 0) {
					swap(input, j, j + 1);
				}
			}
		}
		return input;
	}

	private void swap(List<T> input, int i, int j) {
		T iValue = input.get(i);
		input.set(i, input.get(j));
		input.set(j, iValue);
	}

}
