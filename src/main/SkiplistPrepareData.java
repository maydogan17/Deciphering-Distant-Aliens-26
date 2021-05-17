package main;

import java.util.Arrays;

public class SkiplistPrepareData {
		int[] skiplistB;
		int[] skiplistD;
		int[] skiplistF;
		int[] skiplistZ;
		
		public void getSkiplists(String sampleText) {
			ReadData readData = new ReadData();
			String text = readData.readText(sampleText);
			skiplistB = new int[text.length()];
			skiplistD = new int[text.length()];
			skiplistF = new int[text.length()];
			skiplistZ = new int[text.length()];
			
			initializeSkipLists(text);
			
			for (int i = 0; i < text.length(); i++) {
				
				switch(text.charAt(i)) {
				case 'B':
					skiplistB[i] = 1;
					break;
				case 'D':
					skiplistD[i] = 1;
					break;
				case 'F':
					skiplistF[i] = 1;
					break;
				case 'Z':
					skiplistZ[i] = 1;
					break;
				}
				
			}
			
			//System.out.println(Arrays.toString(skiplistB));
		}
		
		
		public void initializeSkipLists(String text) {
			for (int i = 0; i < text.length(); i++) {
				skiplistB[i] = 0;
				skiplistD[i] = 0;
				skiplistF[i] = 0;
				skiplistZ[i] = 0;				
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
