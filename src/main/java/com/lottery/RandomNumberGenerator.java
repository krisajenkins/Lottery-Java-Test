package com.lottery;

import static com.lottery.Game.DRAW_NUMBER_COUNT;
import static com.lottery.Game.DRAW_NUMBER_MAX;
import static com.lottery.Game.DRAW_NUMBER_MIN;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
	private Random randomnessSource;
	private List<Integer> range;

	public RandomNumberGenerator() {
		try {
			this.randomnessSource = SecureRandom.getInstance("SHA1PRNG", "SUN");
			this.randomnessSource.nextBytes(new byte[] {});
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}

		this.range = new ArrayList<Integer>();
		for (int i = DRAW_NUMBER_MIN; i <= DRAW_NUMBER_MAX; i++) {
			this.range.add(i);
		}
	}

	public List<Integer> draw() {
		Collections.shuffle(range, randomnessSource);

		return new ArrayList<Integer>(range.subList(0, DRAW_NUMBER_COUNT));
	}
}
