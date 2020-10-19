package com.kb.sort.impl;

import java.util.List;

import com.kb.sort.SortService;

public class QuickSort1<T extends Comparable<T>> implements SortService<T> {

//	public static void main(String[] args) {
//		QuickSort1 s = new QuickSort1();
//		int[] input = { 1, 3, 2, 4, 5, 8, 7, 9, 6 }; 
//		s.sort(input);
//	}
//
//	private void sort(int[] input) {
//		System.out.println("before " + Arrays.toString(input));
//		quickSort(input, 0, input.length - 1);
//		System.out.println("after " + Arrays.toString(input));
//	}
	
	@Override
	public List<T> sort(List<T> input) {
		quickSort(input, 0, input.size() -1);
		return input;
	}

	private void quickSort(List<T> input, int start, int end) {
		if(start >= end)
			return;
		int low = start;
		int high = start;
		T pivot = input.get(end);
		while (low < end && high < end) {
			while (high < end) {
				if (input.get(high).compareTo(pivot) < 0) {
					high++;
					continue;
				}
				break;
			}
			low = high + 1;
			while (low < end) {
				if (input.get(low).compareTo(pivot) > 0) {
					low++;
					continue;
				}
				break;
			}
			if (high < input.size() && low< input.size() &&  input.get(low).compareTo(input.get(high)) < 0) {
				swap(input, low, high);
//				System.out.println("Swapped values: "+  input.get(low) +"," +input.get(high) +" for pivot:"+ input.get(high)+", result: " + input);
			}
		}
		if (input.get(end).compareTo(input.get(high)) < 0) {
			swap(input, end, high);
		}
		if (high -1 != start) {
			quickSort(input, start, high - 1);
		}
		if (end != high +1) {
			quickSort(input, high + 1, end);
		}
	}

	private void swap(List<T> input, int low, int high) {
		T temp = input.get(low);
		input.set(low, input.get(high));
		input.set(high, temp);
	}



}
