package com.krishnan.balaji;

import java.util.ArrayList;
import java.util.List;

import com.krishnan.balaji.service.SortService;
import com.krishnan.balaji.util.AlgorithmLoader;

public class TestHarness {

	public static void main(String[] args) {
		List<SortService> sortingAlgos = AlgorithmLoader.getInstance().getSortingAlgorithms();
		List<Integer> input = prepareInput(8);
		System.out.println("input: " + input);
		sortingAlgos.forEach(sortingAlgo -> {
			List cloned = new ArrayList<>();
			cloned.addAll(input);
			System.out.println("algorithm " + sortingAlgo.getClass().getCanonicalName() +", output: " + sortingAlgo.sort(cloned));
		});
		
	}

	public static List<Integer> prepareInput(int range) {
		List<Integer> ints = new ArrayList<>();
		for (int i = 0; i < range; i++) {
			ints.add((int) (100 * Math.random()));
		}
		return ints;
	}

}
