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
	static String words[] = {"BFBZD", "BBZF", "ZBZD"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String initText = "BBFFZBBFZZZBFBBZBZBBBBFFZFBFZZBBBZBFBBZBFFZZBBBBZZBBBFFBFBZDBFBZDBFBZDBFBZDZBZDZBZDZBZDZBZDZBZDBFBBBBFBFZBBBZBFZZBBBFBFZBBFBZBZZFBBZZBBZFFBBBBZBZZFZZFFFFBBBFFBFFFBZBFBBFFBBZFBBZF";
		//System.out.println(Arrays.toString(firstTry(initText))); 
		String text = readText("1.txt");
		System.out.println(Arrays.toString(firstTry(text))); 
		readCSV("small_aliens.csv");
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
	
	public static void readCSV(String fileName) {
		Scanner sc;
		try {
			//this part reads the lines
			sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()) 
			{
				lines.add(sc.nextLine()); 
			}
			sc.close(); // closes the scanner
			
			//this part parses among the lines and fill the alien map
			Scanner lineScanner = null; 
			for(int i=0; i<lines.size(); i++) {
				lineScanner = new Scanner(lines.get(i));
				lineScanner.useDelimiter(";"); // sets the delimiter pattern
				if(i == 0) { //headers' line
					lineScanner.next(); //pass the first word
					while (lineScanner.hasNext()){
						wordArrayList.add(lineScanner.next()); //add the words
					}
				}else {
					String alienName = lineScanner.next();
					ArrayList<Integer> values = new ArrayList<Integer>();
					while (lineScanner.hasNext()) {
						values.add(Integer.parseInt(lineScanner.next())); //add the values
					}
					aliens.put(alienName, values); //add the values read into the map
				}
			}
			lineScanner.close();
			System.out.println(wordArrayList);
			System.out.println(aliens);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
