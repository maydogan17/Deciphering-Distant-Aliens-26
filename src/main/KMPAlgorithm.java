package main;

import java.util.ArrayList;
import java.util.Map;

public class KMPAlgorithm extends Algorithm{
	//https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
	ArrayList<Integer> kmpList = new ArrayList<Integer>();

	public KMPAlgorithm(String text, Map<String, ArrayList<Integer>> aliensMap, ArrayList<String> wordList) {
		super(text, aliensMap, wordList);
		// TODO Auto-generated constructor stub
	}
	
	void computeLPSArray(String pat, int M, int lps[])
    {
        int len = 0;
        int i = 1;
        lps[0] = 0;
  
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else
            {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
	
	void KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();

        int lps[] = new int[M];
        int j = 0;

        computeLPSArray(pat, M, lps);
  
        int i = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                kmpList.add(i - j);
                j = lps[j - 1];
            }
  
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

	@Override
	public String run() {
		// TODO Auto-generated method stub
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		for(String word : wordList) {
			KMPSearch(word, text);
			int consec = 0;
			int max = 0;
			for(int i = 0; i<kmpList.size()-1;i++) {
				if(kmpList.get(i+1)-kmpList.get(i) == word.length()) {
					consec++;
					if(consec > max) {
						max = consec;
					}
				}
				else {
					consec = 0;
				}
			}
			resultList.add(max+1);
			kmpList.clear();
		}
	     for (Map.Entry<String,ArrayList<Integer>> entry : aliensMap.entrySet()) {
	            if(entry.getValue().equals(resultList)) return entry.getKey();
	        }
		//System.out.println(resultList);
		return "No Match";
	}

}
