package com.krishnan.balaji;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort<T extends Comparable<T>> implements Sort<T>{

	@Override
	public List<T> sort(List<T> input) {
		List<T> output = new ArrayList<>(input.size());
		while (input.size() >  0) {
			T item = input.remove(0);
			int index= findIndexFor(output, item);
			if (index < 0 ) {
				output.add(item);
			} else {
				output.add(index, item);
			}
		}
		return output;
	}

	private int findIndexFor(List<T> list, T item) {
		for (int i =0; i < list.size(); i++) {
			if (list.get(i).compareTo(item) > 0) {
				return i;
			}
		}
		return -1;
	}

}
