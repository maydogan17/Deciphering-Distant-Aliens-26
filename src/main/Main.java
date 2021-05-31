package main;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
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
		
//		for(int i=0; i<5; i++) {
			System.out.println("SubsString Algorithm\n");
			runTests(SubStringAlgorithm.class, "SubString");
			System.out.println("Naive Algorithm\n");
			runTests(NaiveAlgorithm.class, "Naive");
			System.out.println("SkipList Algorithm\n");
			runTests(SkipListAlgorithm.class, "SkipList");
			System.out.println("KMP Algorithm\n");
			runTests(KMPAlgorithm.class, "KMP");
//			System.out.println("--------------------------");
//		}
		//runTestsv2(NaiveAlgorithm.class, SubStringAlgorithm.class, SkipListAlgorithm.class, KMPAlgorithm.class);
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
	
	public static void runTests(Class algo, String name) {
		Constructor constructor = null;
		FileWriter csvWriter;
		try {
			constructor = algo.getConstructor(String.class, Map.class, ArrayList.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.out.println("Constructor error.");
		}
		try {
			csvWriter = new FileWriter(name + ".csv");
			csvWriter.append("test no");
			csvWriter.append(";");
			csvWriter.append(name);
			csvWriter.append("\n");
		String finalStr = "\tsmall aliens\t\t\t\tlarge aliens\n";
		Algorithm algo1 = (Algorithm) constructor.newInstance(firstText, smallAliens, smallWordList);
		String res1 = execTime(algo1);
		csvWriter.append("test1");
		csvWriter.append(";");
		csvWriter.append(res1);
		csvWriter.append("\n");
		Algorithm algo2 = (Algorithm) constructor.newInstance(firstText, largeAliens, largeWordList);
		String res2 = execTime(algo2);
		csvWriter.append("test2");
		csvWriter.append(";");
		csvWriter.append(res2);
		csvWriter.append("\n");
		finalStr += String.format("text1\t%s\t\t%s\n", res1,res2);
		Algorithm algo3 = (Algorithm) constructor.newInstance(secondText, smallAliens, smallWordList);
		String res3 = execTime(algo3);
		csvWriter.append("test3");
		csvWriter.append(";");
		csvWriter.append(res3);
		csvWriter.append("\n");
		Algorithm algo4 = (Algorithm) constructor.newInstance(secondText, largeAliens, largeWordList);
		String res4 = execTime(algo4);
		csvWriter.append("test4");
		csvWriter.append(";");
		csvWriter.append(res4);
		csvWriter.append("\n");
		finalStr += String.format("text2\t%s\t%s\n", res3,res4);
		Algorithm algo5 = (Algorithm) constructor.newInstance(thirdText, smallAliens, smallWordList);
		String res5 = execTime(algo5);
		csvWriter.append("test5");
		csvWriter.append(";");
		csvWriter.append(res5);
		csvWriter.append("\n");
		Algorithm algo6 = (Algorithm) constructor.newInstance(thirdText, largeAliens, largeWordList);
		String res6 = execTime(algo6);
		csvWriter.append("test6");
		csvWriter.append(";");
		csvWriter.append(res6);
		csvWriter.append("\n");
		finalStr += String.format("text3\t%s\t%s\n", res5,res6);
		finalStr += "\ttest aliens\n";
		Algorithm algo7 = (Algorithm) constructor.newInstance(fourthText, testAliens, testWordList);
		String res7 = execTime(algo7);
		csvWriter.append("test7");
		csvWriter.append(";");
		csvWriter.append(res7);
		csvWriter.append("\n");
		finalStr += String.format("text4\t%s\t\n", res7);
		System.out.println(finalStr);
		csvWriter.flush();
		csvWriter.close();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IOException e) {
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
	private static String execTime(Algorithm algo) {
		long start = System.nanoTime();
		String result = algo.run();
		long finish = System.nanoTime();
		long timeElapsed = (finish - start)/1000;
		return String.valueOf(timeElapsed);
		//return (int) timeElapsed;
	}
	public static void runTestsv2(Class algor1, Class algor2, Class algor3, Class algor4) {
		Constructor constructor1 = null;
		Constructor constructor2 = null;
		Constructor constructor3 = null;
		Constructor constructor4 = null;
		ArrayList<Integer> bruteCount = new ArrayList<Integer>();
		ArrayList<Integer> subStringCount = new ArrayList<Integer>();
		ArrayList<Integer> skipListCount = new ArrayList<Integer>();
		ArrayList<Integer> KMPCount = new ArrayList<Integer>();
		for(int i=0; i<7; i++) {
			bruteCount.add(0);
			subStringCount.add(0);
			skipListCount.add(0);
			KMPCount.add(0);
		}
		FileWriter csvWriter;
		try {
			constructor1 = algor1.getConstructor(String.class, Map.class, ArrayList.class);
			constructor2 = algor2.getConstructor(String.class, Map.class, ArrayList.class);
			constructor3 = algor3.getConstructor(String.class, Map.class, ArrayList.class);
			constructor4 = algor4.getConstructor(String.class, Map.class, ArrayList.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.out.println("Constructor error.");
		}
		try {
			csvWriter = new FileWriter("new.csv");
			csvWriter.append("test no");
			csvWriter.append(";");
			csvWriter.append("Naive");
			csvWriter.append(";");
			csvWriter.append("SubString");
			csvWriter.append(";");
			csvWriter.append("SkipList");
			csvWriter.append(";");
			csvWriter.append("KMP");
			csvWriter.append("\n");
			
//			for(int i=0; i<30; i++) {
//				bruteCount.set(0, bruteCount.get(0) + execTime((Algorithm) constructor1.newInstance(firstText, smallAliens, smallWordList)));
//				subStringCount.set(0, subStringCount.get(0) + execTime((Algorithm) constructor2.newInstance(firstText, smallAliens, smallWordList)));
//				skipListCount.set(0, skipListCount.get(0) + execTime((Algorithm) constructor3.newInstance(firstText, smallAliens, smallWordList)));
//				KMPCount.set(0, KMPCount.get(0) + execTime((Algorithm) constructor4.newInstance(firstText, smallAliens, smallWordList)));
//			}
//			csvWriter.append("text1");
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(bruteCount.get(0) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(subStringCount.get(0) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(skipListCount.get(0) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(KMPCount.get(0) / 30));
//			csvWriter.append("\n");
			
			Algorithm algo1 = (Algorithm) constructor1.newInstance(firstText, smallAliens, smallWordList);
			Algorithm algo2 = (Algorithm) constructor1.newInstance(firstText, smallAliens, smallWordList);
			Algorithm algo3 = (Algorithm) constructor1.newInstance(firstText, smallAliens, smallWordList);
			Algorithm algo4 = (Algorithm) constructor1.newInstance(firstText, smallAliens, smallWordList);
			//for(int i=0; i<30; i++) {
				csvWriter.append("test1");
				csvWriter.append(";");
				String output = execTime(algo1);
				csvWriter.append(output);
				csvWriter.append(";");
				String output1 = execTime(algo2);
				csvWriter.append(output1);
				csvWriter.append(";");
				String output2 = execTime(algo3);
				csvWriter.append(output2);
				csvWriter.append(";");
				String output3 = execTime(algo4);
				csvWriter.append(output3);
				csvWriter.append("\n");
			//}
			
			
//			for(int i=0; i<30; i++) {
//				bruteCount.set(1, bruteCount.get(1) + execTime((Algorithm) constructor1.newInstance(firstText, largeAliens, largeWordList)));
//				subStringCount.set(1, subStringCount.get(1) + execTime((Algorithm) constructor2.newInstance(firstText, largeAliens, largeWordList)));
//				skipListCount.set(1, skipListCount.get(1) + execTime((Algorithm) constructor3.newInstance(firstText, largeAliens, largeWordList)));
//				KMPCount.set(1, KMPCount.get(1) + execTime((Algorithm) constructor4.newInstance(firstText, largeAliens, largeWordList)));
//			}
//			csvWriter.append("text2");
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(bruteCount.get(1) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(subStringCount.get(1) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(skipListCount.get(1) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(KMPCount.get(1) / 30));
//			csvWriter.append("\n");
			//for(int i=0; i<30; i++) {
				csvWriter.append("text2");
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor1.newInstance(firstText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor2.newInstance(firstText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor3.newInstance(firstText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor4.newInstance(firstText, largeAliens, largeWordList)));
				csvWriter.append("\n");
			//}
			
//			for(int i=0; i<30; i++) {
//				bruteCount.set(2, bruteCount.get(2) + execTime((Algorithm) constructor1.newInstance(secondText, smallAliens, smallWordList)));
//				subStringCount.set(2, subStringCount.get(2) + execTime((Algorithm) constructor2.newInstance(secondText, smallAliens, smallWordList)));
//				skipListCount.set(2, skipListCount.get(2) + execTime((Algorithm) constructor3.newInstance(secondText, smallAliens, smallWordList)));
//				KMPCount.set(2, KMPCount.get(2) + execTime((Algorithm) constructor4.newInstance(secondText, smallAliens, smallWordList)));
//			}
//			csvWriter.append("text3");
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(bruteCount.get(2) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(subStringCount.get(2) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(skipListCount.get(2) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(KMPCount.get(2) / 30));
//			csvWriter.append("\n");
			
			//for(int i=0; i<30; i++) {
				csvWriter.append("text3");
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor1.newInstance(secondText, smallAliens, smallWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor2.newInstance(secondText, smallAliens, smallWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor3.newInstance(secondText, smallAliens, smallWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor4.newInstance(secondText, smallAliens, smallWordList)));
				csvWriter.append("\n");
			//}
			
//			for(int i=0; i<30; i++) {
//				bruteCount.set(3, bruteCount.get(3) + execTime((Algorithm) constructor1.newInstance(secondText, largeAliens, largeWordList)));
//				subStringCount.set(3, subStringCount.get(3) + execTime((Algorithm) constructor2.newInstance(secondText, largeAliens, largeWordList)));
//				skipListCount.set(3, skipListCount.get(3) + execTime((Algorithm) constructor3.newInstance(secondText, largeAliens, largeWordList)));
//				KMPCount.set(3, KMPCount.get(3) + execTime((Algorithm) constructor4.newInstance(secondText, largeAliens, largeWordList)));
//			}
//			csvWriter.append("text4");
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(bruteCount.get(3) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(subStringCount.get(3) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(skipListCount.get(3) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(KMPCount.get(3) / 30));
//			csvWriter.append("\n");
			//for(int i=0; i<30; i++) {
				csvWriter.append("text4");
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor1.newInstance(secondText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor2.newInstance(secondText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor3.newInstance(secondText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor4.newInstance(secondText, largeAliens, largeWordList)));
				csvWriter.append("\n");
			//}
			
//			for(int i=0; i<30; i++) {
//				bruteCount.set(4, bruteCount.get(4) + execTime((Algorithm) constructor1.newInstance(thirdText, smallAliens, smallWordList)));
//				subStringCount.set(4, subStringCount.get(4) + execTime((Algorithm) constructor2.newInstance(thirdText, smallAliens, smallWordList)));
//				skipListCount.set(4, skipListCount.get(4) + execTime((Algorithm) constructor3.newInstance(thirdText, smallAliens, smallWordList)));
//				KMPCount.set(4, KMPCount.get(4) + execTime((Algorithm) constructor4.newInstance(thirdText, smallAliens, smallWordList)));
//			}
//			csvWriter.append("text5");
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(bruteCount.get(4) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(subStringCount.get(4) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(skipListCount.get(4) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(KMPCount.get(4) / 30));
//			csvWriter.append("\n");
			
//			for(int i=0; i<30; i++) {
				csvWriter.append("text5");
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor1.newInstance(thirdText, smallAliens, smallWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor2.newInstance(thirdText, smallAliens, smallWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor3.newInstance(thirdText, smallAliens, smallWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor4.newInstance(thirdText, smallAliens, smallWordList)));
				csvWriter.append("\n");
//			}
			
//			for(int i=0; i<30; i++) {
//				bruteCount.set(5, bruteCount.get(5) + execTime((Algorithm) constructor1.newInstance(thirdText, largeAliens, largeWordList)));
//				subStringCount.set(5, subStringCount.get(5) + execTime((Algorithm) constructor2.newInstance(thirdText, largeAliens, largeWordList)));
//				skipListCount.set(5, skipListCount.get(5) + execTime((Algorithm) constructor3.newInstance(thirdText, largeAliens, largeWordList)));
//				KMPCount.set(5, KMPCount.get(5) + execTime((Algorithm) constructor4.newInstance(thirdText, largeAliens, largeWordList)));
//			}
//			csvWriter.append("text6");
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(bruteCount.get(5) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(subStringCount.get(5) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(skipListCount.get(5) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(KMPCount.get(5) / 30));
//			csvWriter.append("\n");
			
//			for(int i=0; i<30; i++) {
				csvWriter.append("text6");
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor1.newInstance(thirdText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor2.newInstance(thirdText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor3.newInstance(thirdText, largeAliens, largeWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor4.newInstance(thirdText, largeAliens, largeWordList)));
				csvWriter.append("\n");
//			}
			
//			for(int i=0; i<30; i++) {
//				bruteCount.set(6, bruteCount.get(6) + execTime((Algorithm) constructor1.newInstance(fourthText, testAliens, testWordList)));
//				subStringCount.set(6, subStringCount.get(6) + execTime((Algorithm) constructor2.newInstance(fourthText, testAliens, testWordList)));
//				skipListCount.set(6, skipListCount.get(6) + execTime((Algorithm) constructor3.newInstance(fourthText, testAliens, testWordList)));
//				KMPCount.set(6, KMPCount.get(6) + execTime((Algorithm) constructor4.newInstance(fourthText, testAliens, testWordList)));
//			}
//			csvWriter.append("text7");
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(bruteCount.get(6) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(subStringCount.get(6) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(skipListCount.get(6) / 30));
//			csvWriter.append(";");
//			csvWriter.append(String.valueOf(KMPCount.get(6) / 30));
//			csvWriter.append("\n");
			
//			for(int i=0; i<30; i++) {
				csvWriter.append("text7");
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor1.newInstance(fourthText, testAliens, testWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor2.newInstance(fourthText, testAliens, testWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor3.newInstance(fourthText, testAliens, testWordList)));
				csvWriter.append(";");
				csvWriter.append(execTime((Algorithm) constructor4.newInstance(fourthText, testAliens, testWordList)));
				csvWriter.append("\n");
//			}
			
			csvWriter.flush();
			csvWriter.close();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IOException e) {
			e.printStackTrace();
		}
		
		
	}


}
