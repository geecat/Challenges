package com.geecat.random;

import java.util.Random;

/**
 * Use this to generate random number and program that question This will
 * generate as many number as you want and from the range you specify. First you
 * will select problem min 5 and max 10. Then run this.
 * 
 * @author Geecat
 * 
 */
public class RandomGenerator {

	private static int range = 11;// number of problem since random number
									// excludes n.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generateRandom();
	}

	private static void generateRandom() {
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		Random rand = new Random();
		a1 = rand.nextInt(range);
		while (a1 == 0) {
			a1 = rand.nextInt(range);
		}
		a2 = rand.nextInt(range);
		while (a1 == a2 || a2 == 0) {
			a2 = rand.nextInt(range);
		}
		a3 = rand.nextInt(range);
		while (a1 == a3 || a2 == a3 || a3 == 0) {
			a3 = rand.nextInt(range);
		}

		System.out.println("Today you will be programming following three problems: " + a1 + ", " + a2 + ", and " + a3);

	}

}
