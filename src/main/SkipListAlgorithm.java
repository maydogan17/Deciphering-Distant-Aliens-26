package main;

import java.util.ArrayList;
import java.util.Map;

public class SkipListAlgorithm extends Algorithm{
	
	
	public SkipListAlgorithm(String text, Map<String, ArrayList<Integer>> aliensMap, ArrayList<String> wordList) {
		super(text, aliensMap, wordList);
		// TODO Auto-generated constructor stub
		ArrayList<Integer> vars = new ArrayList<Integer>();
		
		SkiplistPrepareData data = new SkiplistPrepareData();
		data.getSkiplists(text);
		
		for(int a=0; a<wordList.size(); a++) {
			
			String word = wordList.get(a);
			int wordcount = 0;
			int incount=0;
			int max = 0;
			boolean hasBefore = false;
			for(int i=0; i<text.length(); i++) {
				
				for(int j=0; j<word.length(); j++) {
					if(i+j<text.length()) {
						if(data.getSkiplist(word.charAt(j))[j+i] == 1) {
							incount++;
						}
					}
				}
				if(incount==word.length()) {
					wordcount++;
					hasBefore = true;
					i = i+word.length()-1;
					if(wordcount > max) {
						max = wordcount;
					}
				}
				else {
					wordcount = 0;
					hasBefore = false;
				}
				incount = 0;
			}
			
		vars.add(max);
		}
		System.out.println(vars);
		// using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String,ArrayList<Integer>> entry : aliensMap.entrySet()) {
            if(entry.getValue().equals(vars)) System.out.println(entry.getKey());
        }
        
	}

	@Override
	public String run() {
		// TODO Auto-generated method stub
		return null;
	}

}
