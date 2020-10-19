package com.krishnan.balaji.service.impl;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		MergeSort s = new MergeSort();
		int[] input = { 1, 3, 2, 4, 5, 8, 7, 9, 6 };
		System.out.println("merge sorting ip:" + Arrays.toString(input));
		s.sort(input);
		System.out.println("merge sorting op:" + Arrays.toString(input));
	}

    public void sort(int[] values) {
        mergesort(values, new int[values.length], 0, values.length - 1);
    }

    private void mergesort(int[] values, int[] helper, int low, int high) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(values, helper, low, middle);
            // Sort the right side of the array
            mergesort(values, helper, middle + 1, high);
            // Combine them both
            merge(values, helper, low, middle, high);
        }
    }

    private void merge(int[] values, int[] helper, int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = values[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                values[k] = helper[i];
                i++;
            } else {
                values[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            values[k] = helper[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.

    }
}