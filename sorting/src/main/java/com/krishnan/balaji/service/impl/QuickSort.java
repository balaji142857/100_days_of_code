package com.krishnan.balaji.service.impl;

import java.util.List;

import com.krishnan.balaji.service.SortService;

public class QuickSort<T extends Comparable<T>> implements SortService<T> {

	@Override
	public List<T> sort(List<T> input) {
		quickSort(input, 0, input.size() - 1);
		return input;
	}

	private void quickSort(List<T> input, int start, int end) {
		if (start == end) {
			return;
		}
		int left = start;
		int right = end;
		int index = 1;
		if (end - start == 1 ) {
			if (input.get(start).compareTo(input.get(end)) > 0) {
				swap(input, start, end);
			}
			return;
		}
		if (end-start == 2) {
			if (input.get(start).compareTo(input.get(start+1)) > 0) {
				swap(input, start, start +1);
			}
			if (input.get(start+1).compareTo(input.get(start+2)) > 0) {
				swap(input, start+1, start +2);
			}
			return;
		}
//		System.out.println("processing " + input + " for start: " + start + ", end: " + end + " pivot: "
//				+ input.get((end - start) / 2));
		int pivotIndex = 1 + (end - start) / 2;
		while (left < pivotIndex) {
//		System.out.println("sorting iteration " + index);
			if (pivotIndex == start || pivotIndex == end) {
				return;
			}
			left = getLeftSwapIndex(input, start, pivotIndex);
			right = getRightSwapIndex(input, pivotIndex, end);
			if (input.get(left).compareTo(input.get(right)) > 0) {
//				System.out.println("Swapping " + input.get(left) + " and " + input.get(right) + " with pivot "
//						+ input.get(pivotIndex) + " in iteration: " + index);
				swap(input, left, right);
			}
//			if (left == start && right == end) {
//				break;
//			}
			index++;
		}
		if (left != 0) {
			quickSort(input, 0, left);
		}
		if (left + 1 != end) {
			quickSort(input, left + 1, end);
		}
	}

	private void swap(List<T> input, int left, int right) {
		T temp = input.get(left);
		input.set(left, input.get(right));
		input.set(right, temp);

	}

	private int getRightSwapIndex(List<T> input, int pivotIndex, int end) {
		int right = end;
		for (; right > pivotIndex; right--) {
			if (input.get(right).compareTo(input.get(pivotIndex)) < 0) {
				break;
			}
		}
		return right;
	}

	private int getLeftSwapIndex(List<T> input, int start, int pivotIndex) {
		int left = 0;
		for (; left < pivotIndex; left++) {
			if (input.get(left).compareTo(input.get(pivotIndex)) > 0) {
//				System.out.println(input.get(left) + " is greater than " + input.get(pivotIndex));
				break;
			}
		}
		return left;
	}
}
