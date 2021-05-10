package main;

public class Main {
	
	int ozer[] = {4, 1, 5};
	int furkan[] = {3, 2, 5};
	int gulay[] = {4, 8, 3};
	
	String words[] = {"BFBZD", "BBZF", "ZBZD"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello, world!");
	}
	
	public int[] firstTry(String in) {
		int res[] = {0, 0, 0};
		
		String frs = words[0];
		String sec = words[1];
		String thr = words[2];
		
		for(int i = 0; i<in.length();i++) {
			if(subs(in,i,frs)) {
				
			}
		}
		
		
		
		return furkan;
	}
	
	public boolean subs(String in, int begin, String compare) {
		int end = compare.length();
		boolean res = true;
		
		for(int i = 0;i < end;i++) {
			if(in.charAt(begin + i) != compare.charAt(i)) {
				res = false;
			}

		}
		
		return res;
	}

}
