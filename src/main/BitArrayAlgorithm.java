package main;

import java.util.ArrayList;
import java.util.Map;

public class BitArrayAlgorithm extends Algorithm {
	
	private ArrayList<Integer> wordOccurences = new ArrayList<>();
	
	public BitArrayAlgorithm(String text, Map<String, ArrayList<Integer>> aliensMap, ArrayList<String> wordList) {
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
			int maxOccurenceForWord = 0;
			for(int i=0; i<word.length(); i++) {
				String bitArray = extractCombinationsFromIndex(word, i);
				int bitParse = Integer.parseInt(bitArray, 2);
				int maxConsecutive = maxConsecutiveOnes(bitParse);
				if(maxConsecutive > maxOccurenceForWord) {
					maxOccurenceForWord = maxConsecutive;
				}		
			}
			occurList.add(maxOccurenceForWord);
		}
		String result = findAlienNameFromMap(wordOccurences);
		return result;
		
	}
	
	private String extractCombinationsFromIndex(String word, int index) {
		String bitString = "";
		for(int i=index; i<text.length(); i=i+word.length()) {
			String str = text.substring(i, i + word.length());
			if(str.equals(word)) {
				bitString += "1";
			}else {
				bitString += "0";
			}
		}
		return bitString;
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
