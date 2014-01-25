package com.lottery;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

// TODO Consider hard-codingness.
public class RandomNumberMachine {
	private Random randomnessSource;
	private List<Integer> range;

	public RandomNumberMachine() throws NoSuchAlgorithmException,
			NoSuchProviderException {
		this.randomnessSource = SecureRandom.getInstance("SHA1PRNG", "SUN");
		this.randomnessSource.nextBytes(new byte[] {});

		this.range = new ArrayList<Integer>();
		for (int i = 1; i <= 60; i++) {
			this.range.add(i);
		}
	}

	public Set<Integer> draw() {
		Collections.shuffle(range, randomnessSource);

		return new HashSet<Integer>(range.subList(0, 6));
	}
}
