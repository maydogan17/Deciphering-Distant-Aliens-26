package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	int ozer[] = {4, 1, 5};
	int furkan[] = {3, 2, 5};
	int gulay[] = {4, 8, 3};
	static Map<String, ArrayList<Integer>> aliens = new HashMap<String, ArrayList<Integer>>();
	static ArrayList<String> wordArrayList = new ArrayList<String>();
	static ArrayList<String> lines = new ArrayList<String>();
	//static String words[] = {"BFBZD", "BBZF", "ZBZD"};
	static String words[] = {};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String initText = "BBFFZBBFZZZBFBBZBZBBBBFFZFBFZZBBBZBFBBZBFFZZBBBBZZBBBFFBFBZDBFBZDBFBZDBFBZDZBZDZBZDZBZDZBZDZBZDBFBBBBFBFZBBBZBFZZBBBFBFZBBFBZBZZFBBZZBBZFFBBBBZBZZFZZFFFFBBBFFBFFFBZBFBBFFBBZFBBZF";
		//System.out.println(Arrays.toString(firstTry(initText))); 
		
		aliens = ReadData.readCSV("small_aliens.csv", wordArrayList);
		words = wordArrayList.toArray(new String[0]);
		System.out.println("The Alien Map:\n" + aliens);
		
		String text = ReadData.readText("1.txt");
		System.out.println(Arrays.toString(firstTry(text))); 
	}
	
	public static int[] firstTry(String in) {
		int res[] = {0, 0, 0};

		for(int j = 0; j<words.length;j++) {
			String word = words[j];
			int maxConsecCounter = 0;
			int consecutiveCounter = 0;
			
			for(int i = 0; i<in.length();i++) {
				if(checkIfNameMatch(in,i,word)) {
					consecutiveCounter++;
					if(consecutiveCounter>maxConsecCounter) {
						maxConsecCounter = consecutiveCounter;
					}
					//ABCDHS 0 ABC 3
					i += word.length() - 1;
					
				}else {
					consecutiveCounter = 0;
				}
			}
			res[j] = maxConsecCounter;
		}
		
		
		
		return res;
	}
	
	public static boolean checkIfNameMatch(String in, int begin, String word) {
		int end = word.length();
		boolean res = true;
		
		for(int i = 0;i < end;i++) {
			if(begin+i<in.length() && (in.charAt(begin + i) != word.charAt(i))) {
				res = false;
			}

		}
		
		return res;
	}
	


}
