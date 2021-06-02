package main;

import java.util.ArrayList;

public class SkiplistPrepareData {
	//Prepares index arrays of each letter for a given text.
	ArrayList<Integer> poslistB = new ArrayList<Integer>();
	ArrayList<Integer> poslistD = new ArrayList<Integer>();
	ArrayList<Integer> poslistF = new ArrayList<Integer>();
	ArrayList<Integer> poslistZ = new ArrayList<Integer>();

	public void getSkiplists(String text) {
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
