package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	protected static Map<String, ArrayList<Integer>> smallAliens = new HashMap<String, ArrayList<Integer>>();
	protected static Map<String, ArrayList<Integer>> largeAliens = new HashMap<String, ArrayList<Integer>>();
	protected static Map<String, ArrayList<Integer>> testAliens = new HashMap<String, ArrayList<Integer>>();
	protected static ArrayList<String> smallWordList = new ArrayList<String>();
	protected static ArrayList<String> largeWordList = new ArrayList<String>();
	protected static ArrayList<String> testWordList = new ArrayList<String>();
	protected static String firstText;
	protected static String secondText;
	protected static String thirdText;

	protected static String fourthText;
	public static void main(String[] args) {

		initValues();
		System.out.println("SubsString Algorithm\n");
		runTests(SubStringAlgorithm.class);
		System.out.println("BruteForce Algorithm\n");
		runTests(BruteForceAlgorithm.class);
		System.out.println("SkipList Algorithm\n");
		runTests(SkipListAlgorithm.class);
		System.out.println("KMP Algorithm\n");
		runTests(KMPAlgorithm.class);
		System.out.println("Naive Algorithm\n");
		runTests(NaiveAlgorithm.class);
	}
	
	public static void initValues() {
		ReadData readData = new ReadData();
		smallAliens = readData.readCSV("small_aliens.csv", smallWordList);
		largeAliens = readData.readCSV("large_aliens.csv", largeWordList);
		testAliens = readData.readCSV("test_aliens.csv", testWordList);
		firstText = readData.readText("1.txt");
		secondText = readData.readText("5.txt");
		thirdText = readData.readText("10.txt");
		fourthText = readData.readText("2.txt"); //the one with colliding word example
	}	
	
	public static void runTests(Class algo) {
		Constructor constructor = null;
		try {
			constructor = algo.getConstructor(String.class, Map.class, ArrayList.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.out.println("Constructor error.");
		}
		try {
		String finalStr = "\tsmall aliens\t\t\t\tlarge aliens\n";
		Algorithm algo1 = (Algorithm) constructor.newInstance(firstText, smallAliens, smallWordList);
		String res1 = runAlgorithm(algo1);
		Algorithm algo2 = (Algorithm) constructor.newInstance(firstText, largeAliens, largeWordList);
		String res2 = runAlgorithm(algo2);
		finalStr += String.format("text1\t%s\t\t%s\n", res1,res2);
		Algorithm algo3 = (Algorithm) constructor.newInstance(secondText, smallAliens, smallWordList);
		String res3 = runAlgorithm(algo3);
		Algorithm algo4 = (Algorithm) constructor.newInstance(secondText, largeAliens, largeWordList);
		String res4 = runAlgorithm(algo4);
		finalStr += String.format("text2\t%s\t%s\n", res3,res4);
		Algorithm algo5 = (Algorithm) constructor.newInstance(thirdText, smallAliens, smallWordList);
		String res5 = runAlgorithm(algo5);
		Algorithm algo6 = (Algorithm) constructor.newInstance(thirdText, largeAliens, largeWordList);
		String res6 = runAlgorithm(algo6);
		finalStr += String.format("text3\t%s\t%s\n", res5,res6);
		finalStr += "\ttest aliens\n";
		Algorithm algo7 = (Algorithm) constructor.newInstance(fourthText, testAliens, testWordList);
		String res7 = runAlgorithm(algo7);
		finalStr += String.format("text4\t%s\t\n", res7);
		System.out.println(finalStr);
		
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static String runAlgorithm(Algorithm algo) {
		long start = System.nanoTime();
		String result = algo.run();
		long finish = System.nanoTime();
		long timeElapsed = (finish - start)/1000;
		return "Match: " + result + ", Elapsed Time(us): " + timeElapsed;
	}
	
	

}
