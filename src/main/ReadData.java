package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadData {
	static ArrayList<String> lines = new ArrayList<String>();
	static Map<String, ArrayList<Integer>> aliens = new HashMap<String, ArrayList<Integer>>();
	//static ArrayList<String> wordArrayList = new ArrayList<String>();
	public ReadData() {
		
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
	
	public static Map<String, ArrayList<Integer>> readCSV(String fileName, ArrayList<String> wordArrayList) {
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
		return aliens;
	}
}
