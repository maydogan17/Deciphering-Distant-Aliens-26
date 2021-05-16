package main;

import java.util.ArrayList;
import java.util.Map;

public class BruteForceAlgorithm extends Algorithm{

	private ArrayList<Integer> wordOccurences = new ArrayList<>();
	public BruteForceAlgorithm(String text, Map<String, ArrayList<Integer>> aliensMap, ArrayList<String> wordList) {
		super(text,aliensMap,wordList);
	}



	
	private void initValues() {
		for(int i = 0; i < wordList.size(); i++) {
			wordOccurences.add(0);
		}
		
	}

	// Time complexity is max(O(n.m),O(z.m))
	@Override
	public String run() {
		initValues();
		// O(z.m) z is length of text and m is number of words
		for (int j = 0; j < wordList.size(); j++) {
			String word = wordList.get(j);
			int maxConsecCounter = 0;
			int consecutiveCounter = 0;

			for (int i = 0; i < text.length(); i++) {
				// z is the length of text
				if (checkIfNameMatch(text, i, word)) {
					consecutiveCounter++;
					if (consecutiveCounter > maxConsecCounter) {
						maxConsecCounter = consecutiveCounter;
					}
					i += word.length() - 1;

				} else {
					consecutiveCounter = 0;
				}
			}
			wordOccurences.set(j, maxConsecCounter) ;
		}
		String result = findAlienNameFromMap(wordOccurences);
		return result;
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
	private boolean checkIfNameMatch(String sampleText, int begin, String word) {
		int end = word.length();
		boolean res = true;
		for (int i = 0; i < end; i++) {
			if (begin + i < sampleText.length() && (sampleText.charAt(begin + i) != word.charAt(i))) {
				res = false;
			}
		}
		return res;
	}

}
