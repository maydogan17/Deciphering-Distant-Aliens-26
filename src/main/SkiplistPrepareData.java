package main;

import java.util.ArrayList;
import java.util.Arrays;

public class SkiplistPrepareData {
	int[] skiplistB;
	int[] skiplistD;
	int[] skiplistF;
	int[] skiplistZ;
	
	ArrayList<Integer> poslistB= new ArrayList<Integer>();
	ArrayList<Integer> poslistD= new ArrayList<Integer>();
	ArrayList<Integer> poslistF= new ArrayList<Integer>();
	ArrayList<Integer> poslistZ= new ArrayList<Integer>();
	

	public void getSkiplists(String text) {
		skiplistB = new int[text.length()];
		skiplistD = new int[text.length()];
		skiplistF = new int[text.length()];
		skiplistZ = new int[text.length()];

		initializeSkipLists(text);

		for (int i = 0; i < text.length(); i++) {

			switch (text.charAt(i)) {
			case 'B':
				skiplistB[i] = 1;
				poslistB.add(i);
				break;
			case 'D':
				skiplistD[i] = 1;
				poslistD.add(i);
				break;
			case 'F':
				skiplistF[i] = 1;
				poslistF.add(i);
				break;
			case 'Z':
				skiplistZ[i] = 1;
				poslistZ.add(i);
				break;
			default:
				break;
			}

		}
	}

	public void initializeSkipLists(String text) {
		for (int i = 0; i < text.length(); i++) {
			skiplistB[i] = 0;
			skiplistD[i] = 0;
			skiplistF[i] = 0;
			skiplistZ[i] = 0;
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

	public int[] getSkiplistB() {
		return skiplistB;
	}

	public int[] getSkiplistD() {
		return skiplistD;
	}

	public int[] getSkiplistF() {
		return skiplistF;
	}

	public int[] getSkiplistZ() {
		return skiplistZ;
	}
}
