package com.kb.sort.impl;

import java.util.Arrays;

public class QuickSort {
//    private int number;

	public static void main(String[] args) {
		QuickSort s = new QuickSort();
		int[] input = { 1, 3, 2, 4, 5, 8, 7, 9, 6 };
		s.sort(input);
	}

	public void sort(int[] values) {
		// check for empty or null array
		if (values == null || values.length == 0) {
			return;
		}
		System.out.println(Arrays.toString(values));
		quicksort(values, 0, values.length - 1);
		System.out.println(Arrays.toString(values));
	}


	private void quicksort(int[] values, int low, int high) {
		int i = low, j = high;
		int pivot = values[ low + (high-low)/2];
		while(i <= j) {
			while(values[i]<pivot) {
				i++;
			}
			while(values[j]>pivot) {
				j--;
			}
			if(i <=j ) {
				exchange(values, i, j);
				i++;
				j--;
			}
		}
		if (j > low) {
			quicksort(values, low, j);
		}
		if (i < high) {
			quicksort(values, i, high);
		}
		
	}

	private void exchange(int[] values, int i, int j) {
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}
}