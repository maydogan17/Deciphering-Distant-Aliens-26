package main;

import java.util.ArrayList;
import java.util.Map;

public class SkipListAlgorithm extends Algorithm{
	private SkiplistPrepareData data = new SkiplistPrepareData();
	
	public SkipListAlgorithm(String text, Map<String, ArrayList<Integer>> aliensMap, ArrayList<String> wordList) {
		super(text, aliensMap, wordList);
		// TODO Auto-generated constructor stub
		data.getSkiplists(text);
	}
	
	@Override
	public String run() {
		//z is the length of text
		//m is the number of words
		//n is the number of aliens
		//t 
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(String s : wordList) { //O(m * z/4) + O(m * z/)
			ArrayList<Integer> lst =  data.getPosList(s.charAt(0));
			ArrayList<Integer> pos = new ArrayList<Integer>();
			
			for(int i : lst) {
				if(i+s.length()<=text.length()) {
					if(text.substring(i, i+s.length()).equals(s)) {
						pos.add(i);
					}
				}
			}
			
			if(pos.size() == 0) {
				result.add(0);
			}
			else if (pos.size() == 1) {
				result.add(1);
			}
			else {
				int consec = 0;
				int max = 0;
				for(int i = 0; i<pos.size()-1;i++) {
					if(pos.get(i+1)-pos.get(i) == s.length()) {
						consec++;
						if(consec > max) {
							max = consec;
						}
					}
					else {
						consec = 0;
					}
				}
				
				result.add(max+1);
			}

		}
		 for (Map.Entry<String,ArrayList<Integer>> entry : aliensMap.entrySet()) {
	            if(entry.getValue().equals(result)) return entry.getKey(); 
	        }
		return "No Match";
	}

}
