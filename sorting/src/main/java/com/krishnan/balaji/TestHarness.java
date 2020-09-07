package com.krishnan.balaji;

import java.util.ArrayList;
import java.util.List;

import com.krishnan.balaji.service.SortService;
import com.krishnan.balaji.util.AlgorithmLoader;

public class TestHarness {

	public static void main(String[] args) {
		List<SortService> sortingAlgos = AlgorithmLoader.getInstance().getSortingAlgorithms();
//		18, 80, 86, 73, 46, 8, 26, 66
		List<Integer> input = prepareInput(8);
		System.out.println("input: \t\t\t\t" + input);
		sortingAlgos.forEach(sortingAlgo -> {
			List cloned = new ArrayList<>();
			cloned.addAll(input);
			System.out.println(sortingAlgo.getClass().getSimpleName() +" output: \t\t" + sortingAlgo.sort(cloned));
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
