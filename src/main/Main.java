package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	int ozer[] = {4, 1, 5};
	int furkan[] = {3, 2, 5};
	int gulay[] = {4, 8, 3};
	static String words[] = {"BFBZD", "BBZF", "ZBZD"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String initText = "BBFFZBBFZZZBFBBZBZBBBBFFZFBFZZBBBZBFBBZBFFZZBBBBZZBBBFFBFBZDBFBZDBFBZDBFBZDZBZDZBZDZBZDZBZDZBZDBFBBBBFBFZBBBZBFZZBBBFBFZBBFBZBZZFBBZZBBZFFBBBBZBZZFZZFFFFBBBFFBFFFBZBFBBFFBBZFBBZF";
		//System.out.println(Arrays.toString(firstTry(initText))); 
		String text = readText("1.txt");
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
	
	public static String readText(String fileName) {
		String output = "";
		try {
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String data = scan.nextLine();
				output += data;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return output;
	}

}
