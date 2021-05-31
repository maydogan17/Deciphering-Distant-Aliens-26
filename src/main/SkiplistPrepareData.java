package main;

import java.util.ArrayList;

public class SkiplistPrepareData {
	//Prepares index arrays of each letter for a given text.
	int[] skiplistB;
	int[] skiplistD;
	int[] skiplistF;
	int[] skiplistZ;

	ArrayList<Integer> poslistB = new ArrayList<Integer>();
	ArrayList<Integer> poslistD = new ArrayList<Integer>();
	ArrayList<Integer> poslistF = new ArrayList<Integer>();
	ArrayList<Integer> poslistZ = new ArrayList<Integer>();

	public void getSkiplists(String text) {
		skiplistB = new int[text.length()];
		skiplistD = new int[text.length()];
		skiplistF = new int[text.length()];
		skiplistZ = new int[text.length()];

		for (int i = 0; i < text.length(); i++) {

			switch (text.charAt(i)) {
			case 'B':
				poslistB.add(i);
				break;
			case 'D':
				poslistD.add(i);
				break;
			case 'F':
				poslistF.add(i);
				break;
			case 'Z':
				poslistZ.add(i);
				break;
			default:
				break;
			}
		}
	}

	public int[] getSkiplist(char a) {
		switch (a) {
		case 'B':
			return skiplistB;
		case 'D':
			return skiplistD;
		case 'F':
			return skiplistF;
		case 'Z':
			return skiplistZ;
		default:
			return null;
		}
	}

	public ArrayList<Integer> getPosList(char a) {
		switch (a) {
		case 'B':
			return poslistB;
		case 'D':
			return poslistD;
		case 'F':
			return poslistF;
		case 'Z':
			return poslistZ;
		default:
			return null;
		}
	}
}
