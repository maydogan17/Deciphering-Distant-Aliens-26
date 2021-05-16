package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Algorithm {

	protected String text;
	protected Map<String, ArrayList<Integer>> aliensMap = new HashMap<String, ArrayList<Integer>>();
	protected ArrayList<String> wordList;
	
	public Algorithm(String text, Map<String, ArrayList<Integer>> aliensMap, ArrayList<String> wordList) {
		this.aliensMap = aliensMap;
		this.text = text;
		this.wordList = wordList;
	}
	
	public abstract String run();

}
