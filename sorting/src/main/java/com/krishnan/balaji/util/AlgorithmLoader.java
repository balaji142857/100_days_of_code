package com.krishnan.balaji.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import com.krishnan.balaji.service.SortService;

public class AlgorithmLoader {
	private static AlgorithmLoader provider;
	private ServiceLoader<SortService> loader;

	private AlgorithmLoader() {
		loader = ServiceLoader.load(SortService.class);
	}

	public static AlgorithmLoader getInstance() {
		if (provider == null) {
			provider = new AlgorithmLoader();
		}
		return provider;
	}

	public List<SortService> getSortingAlgorithms() {
		Iterator<SortService> iterator =  loader.iterator();
		List<SortService> algorithms = new ArrayList<>();
		while (iterator.hasNext()) {
			algorithms.add(iterator.next());
		}
		return algorithms;
	}
}