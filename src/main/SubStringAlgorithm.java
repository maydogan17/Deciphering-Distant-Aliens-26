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
			}
		}

		return maxConsec;
	}
		
	private String findAlienNameFromMap(ArrayList<Integer> wordOcc) {
		for(String key : aliensMap.keySet()) {
			ArrayList<Integer> temp = aliensMap.get(key);
			if(temp.equals(wordOcc)) {
				return key;
			}
		}
		return "No Match";
		
	}
	
}
