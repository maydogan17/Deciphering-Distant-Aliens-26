package main;

import java.util.ArrayList;
import java.util.Map;

public class BruteForceAlgorithm extends Algorithm {

	public BruteForceAlgorithm(String text, Map<String, ArrayList<Integer>> aliensMap, ArrayList<String> wordList) {
		super(text, aliensMap, wordList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run() {
		// TODO Auto-generated method stub
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (String s : wordList) {
			ArrayList<Integer> pos = new ArrayList<Integer>();

			for (int i = 0; i < text.length(); i++) {
				if (i + s.length() <= text.length()) {
					if (text.substring(i, i + s.length()).equals(s)) {
						pos.add(i);
					}

				}
			}
			if (pos.size() == 0) {
				result.add(0);
			} else if (pos.size() == 1) {
				result.add(1);
			} else {
				int consec = 0;
				int max = 0;
				for (int i = 0; i < pos.size() - 1; i++) {
					if (pos.get(i + 1) - pos.get(i) == s.length()) {
						consec++;
						if (consec > max) {
							max = consec;
						}
					} else {
						consec = 0;
					}
				}

				result.add(max + 1);
			}
		}
		for (Map.Entry<String, ArrayList<Integer>> entry : aliensMap.entrySet()) {
			if (entry.getValue().equals(result))
				return entry.getKey();
		}
		return "No Match";
	}

}
