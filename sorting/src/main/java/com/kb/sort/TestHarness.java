package com.kb.sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class TestHarness {

	public static void main(String[] args) {
		List<SortService> sortingAlgos = getSortingAlgorithms();
		int iterationCount = 4;
		while (iterationCount > 0 ) {
		List<Integer> input = prepareInput(8);
//		int[] values = { 1, 3, 2, 4, 5, 8, 7, 9, 6 };
//		List<Integer> input = Arrays.asList(values);
			System.out.println("input: \t\t\t\t" + input);
			sortingAlgos.forEach(sortingAlgo -> {
				List cloned = new ArrayList<>();
				cloned.addAll(input);
				System.out.println(sortingAlgo.getClass().getSimpleName() +" output: \t\t" + sortingAlgo.sort(cloned));
			});
			System.out.println("===============================");
			iterationCount--;
		}
		
	}

	
	public static List<Integer> prepareInput(int range) {
		//		18, 80, 86, 73, 46, 8, 26, 66
		List<Integer> ints = new ArrayList<>();
		for (int i = 0; i < range; i++) {
			ints.add((int) (100 * Math.random()));
		}
		return ints;
	}
	
	private static List<SortService> getSortingAlgorithms() {
		ServiceLoader<SortService> loader = ServiceLoader.load(SortService.class);
		Iterator<SortService> iterator =  loader.iterator();
		List<SortService> algorithms = new ArrayList<>();
		while (iterator.hasNext()) {
			algorithms.add(iterator.next());
		}
		return algorithms;
	}

}
