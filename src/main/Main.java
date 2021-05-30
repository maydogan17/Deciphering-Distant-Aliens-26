package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	protected static Map<String, ArrayList<Integer>> smallAliens = new HashMap<String, ArrayList<Integer>>();
	protected static Map<String, ArrayList<Integer>> largeAliens = new HashMap<String, ArrayList<Integer>>();
	protected static ArrayList<String> smallWordList = new ArrayList<String>();
	protected static ArrayList<String> largeWordList = new ArrayList<String>();
	protected static String firstText;
	protected static String secondText;
	protected static String thirdText;
	
	public static void main(String[] args) {

		initValues();
		runTests();
	}
	
	public static void initValues() {
		ReadData readData = new ReadData();
		smallAliens = readData.readCSV("small_aliens.csv", smallWordList);
		largeAliens = readData.readCSV("large_aliens.csv", largeWordList);
		firstText = readData.readText("1.txt");
		secondText = readData.readText("5.txt");
		thirdText = readData.readText("10.txt");
	
	}	
	
	public static void runTests() {
//		SkiplistPrepareData p = new SkiplistPrepareData();
//		p.getSkiplists(secondText);
		
		Algorithm alg = new SkipListAlgorithm(thirdText, largeAliens, largeWordList);
		String res7 = runAlgorithm(alg);
		System.out.println(res7);
		//System.out.println(alg.run());

		String finalStr = "\tsmall aliens\t\t\t\tlarge aliens\n";
		Algorithm algo1 = new BruteForceAlgorithm(firstText, smallAliens, smallWordList);
		String res1 = runAlgorithm(algo1);
		Algorithm algo2 = new BruteForceAlgorithm(firstText, largeAliens, largeWordList);
		String res2 = runAlgorithm(algo2);
		finalStr += String.format("text1\t%s\t\t%s\n", res1,res2);
		Algorithm algo3 = new BruteForceAlgorithm(secondText, smallAliens, smallWordList);
		String res3 = runAlgorithm(algo3);
		Algorithm algo4 = new BruteForceAlgorithm(secondText, largeAliens, largeWordList);
		String res4 = runAlgorithm(algo4);
		finalStr += String.format("text2\t%s\t%s\n", res3,res4);
		Algorithm algo5 = new BruteForceAlgorithm(thirdText, smallAliens, smallWordList);
		String res5 = runAlgorithm(algo5);
		Algorithm algo6 = new BruteForceAlgorithm(thirdText, largeAliens, largeWordList);
		String res6 = runAlgorithm(algo6);
		finalStr += String.format("text3\t%s\t%s\n", res5,res6);
		System.out.println(finalStr);
	}
	
	public static String runAlgorithm(Algorithm algo) {
		long start = System.nanoTime();
		String result = algo.run();
		long finish = System.nanoTime();
		long timeElapsed = (finish - start)/1000;
		return "Match: " + result + ", Elapsed Time(us): " + timeElapsed;
	}



}
