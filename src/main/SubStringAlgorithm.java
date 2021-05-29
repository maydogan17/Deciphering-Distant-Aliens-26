package main;

import java.util.ArrayList;
import java.util.Map;

public class SubStringAlgorithm extends Algorithm {
	
	private ArrayList<Integer> wordOccurences = new ArrayList<>();
	
	public SubStringAlgorithm(String text, Map<String, ArrayList<Integer>> aliensMap, ArrayList<String> wordList) {
		super(text, aliensMap, wordList);
		// TODO Auto-generated constructor stub
	}
	private void initValues() {
		for(int i = 0; i < wordList.size(); i++) {
			wordOccurences.add(0);
		}
		
	}
	@Override
	public String run() {
		// TODO Auto-generated method stub
		initValues();
		ArrayList<Integer> occurList = new ArrayList<Integer>();
		for(String word : wordList) {
			int maxConsecForWord = 0;
			for(int i=0; i<word.length(); i++) {
				int maxConsecForCombination = extractCombinationsFromIndex(word, i);
				if(maxConsecForCombination > maxConsecForWord) maxConsecForWord = maxConsecForCombination;
//				String bitArray = extractCombinationsFromIndex(word, i);
//				int bitParse = Integer.parseInt(bitArray, 2);
//				int maxConsecutive = maxConsecutiveOnes(bitParse);
//				if(maxConsecutive > maxOccurenceForWord) {
//					maxOccurenceForWord = maxConsecutive;
//				}		
			}
			occurList.add(maxConsecForWord);
		}
		String result = findAlienNameFromMap(occurList);
		return result;
		
	}
	
	private int extractCombinationsFromIndex(String word, int index) {
		int count = 0, maxConsec = 0;
		for(int i=index; i<text.length(); i=i+word.length()) {
			if(i+word.length() <= text.length()) {
				String str = text.substring(i, i + word.length());
				if (str.equals(word)) {
					count++;
					if(maxConsec < count) maxConsec = count;
				}else {
					count = 0;
				}
//				if (str.equals(word)) {
//					bitString += "1";
//				} else {
//					if(bitString.equals("") || bitString.endsWith("1")) {
//						bitString += "0";
//					}
//				}
			}
			
		}

		return maxConsec;
	}
	
	private static int maxConsecutiveOnes(int x) {
		// Initialize result
		int count = 0;

		// Count the number of iterations to
		// reach x = 0.
		while (x != 0) {
			// This operation reduces length
			// of every sequence of 1s by one.
			x = (x & (x << 1));

			count++;
		}

		return count;
	}
	
	private String findAlienNameFromMap(ArrayList<Integer> wordOcc) {
		// O(n.m) if n is number of aliens and m is number of words
		for(String key : aliensMap.keySet()) {
			//O(m)
			ArrayList<Integer> temp = aliensMap.get(key);
			if(temp.equals(wordOcc)) {
				return key;
			}
		}
		return "No Match";
		
	}
	
}
