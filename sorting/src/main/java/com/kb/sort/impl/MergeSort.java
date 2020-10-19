package com.kb.sort.impl;

import java.util.ArrayList;
import java.util.List;

import com.kb.sort.SortService;

public class MergeSort<T extends Comparable<T>> implements SortService<T>  {
	
	@Override
	public List<T> sort(List<T> input) {
		mergeSort(input, 0, input.size() -1);
		return input;
	}

//	public static void main(String[] args) {
//		MergeSort s = new MergeSort();
//		int[] input = { 1, 3, 2, 4, 5, 8, 7, 9, 6 };
//		System.out.println("merge sorting ip:" + Arrays.toString(input));
//		s.sort(input);
//		System.out.println("merge sorting op:" + Arrays.toString(input));
//	}
//
//	public void sort(int[] values) {
//		mergeSort(values, 0, values.length -1);
//	}

	private void merge(List<T> input, int low, int middle, int high) {
		int arr1Size = middle - low + 1;
		int arr2Size = high - middle;

		List<T> arr1 = new ArrayList<>(arr1Size);
		List<T> arr2 = new ArrayList<>(arr2Size);

		int i = 0, j = 0;

		while (i < arr1Size) {
			arr1.set(i, input.get(low + i));
			i++;
		}

		while (j < arr2Size) {
			arr2.set(j, input.get(middle + 1 + j));
			j++;
		}
		
		i =0;
		j =0;
		int k = low;
		while(i  < arr1Size && j < arr2Size) {
			if (arr1.get(i).compareTo(arr2.get(j)) < 0) {
				input.set(k, arr1.get(i));
				i++;
			} else {
				input.set(k, arr2.get(j));
				j++;
			}
			k++;
		}
		while (i < arr1Size) {
			input.set(k, arr1.get(i));
			k++;
			i++;
		}
		while (j < arr2Size) {
			input.set(k, arr2.get(j));
			k++;
			j++;
		}
	}

	private void mergeSort(List<T> input, int low, int high) {
		System.out.println("low: "+low+", high: "+high);
		if (low < high) {
			int middle = (high + low) / 2;
			if ( low != middle)
			mergeSort(input, low, middle );
			if (middle +1 != high)
			mergeSort(input, middle+1, high);
			merge(input, low, middle, high);
		}
	}


}