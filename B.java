package p;

import java.util.ArrayList;

public class B {
	
	private Boolean[][] b;
//	= {{null,null,null,null,null,null, null},
//			{null,null,null,null,null,null, null},
//			{null,null,false,null,null,null, null},
//			{null,null,true,true,null,null,null},
//			{null,null,true,false,false,null,null},
//			{false,true,true,true,false,null,false}};
	
	public B (){
		b = new Boolean[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				b[i][j] = null;
			}
		}
	}
	
	public Boolean[][] getB(){
		return b;
	}
	
	public boolean canPlace(int r){
		return b[0][r]==null;
	}
	
	public boolean canPlaceT(int r, Boolean[][] b){
		return b[0][r]==null;
	}
	
	public boolean hasWon(boolean p, int r){
		int temp = 0;
		for (int i = 0; i < 6; i++) {
			if(b[i][r] != null && b[i][r] == p){
				temp++;
			}else{
				temp = 0;
			}
			if(temp>=4){
				return true;
			}
		}
		
		temp = 0;
		int h = findH(r);
		for (int i = 0; i < 7; i++) {
			if(b[h][i] != null && b[h][i] == p){
				temp++;
			}else{
				temp = 0;
			}
			if(temp>=4){
				return true;
			}
		}
		
		temp = 0;
		int tr = r;
		while(h<5&&tr<6){
			h++;
			tr++;
		}
		while(h>0&&tr>0){
			if(b[h][tr] != null && b[h][tr] == p){
				temp++;
			}else{
				temp = 0;
			}
			h--;
			tr--;
			if(temp>=4){
				return true;
			}
		}
		
		temp = 0;
		tr=r;
		h = findH(r);
		while(h<5&&tr>0){
			h++;
			tr--;
		}
		while(h>0&&tr<6) {
			if(b[h][tr] != null && b[h][tr] == p){
				temp++;
			}else{
				temp = 0;
			}
			h--;
			tr++;
			if(temp>=4){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean twoNextOpen(boolean p, Boolean[][] b){
		return stopTwoNextOpen(p, b).size() > 0;
	}
	
	public ArrayList<Integer> stopTwoNextOpen(boolean p, Boolean[][] b){
		ArrayList<Integer> a = new ArrayList<Integer>();
		int cou = 0;
		boolean em = false;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				if(em && cou==2 && b[i][j]==null && (i==b.length-1 || b[i+1][j]!=null && b[i+1][j-3]!=null)){
					a.add(j);
					a.add(j-3);
					em = false;
					cou = 0;
				}
				if(b[i][j]==null){
					em = true;
				}else if(em && b[i][j]==p){
					cou++;
				}else{
					em = false;
					cou = 0;
				}
			}
			cou = 0;
			em = false;
		}
		return a;
	}
	
	public boolean gotWinningMove(boolean p, Boolean[][] b){
		boolean a = false;
		for (int i = 0; i < 7; i++) {
			Boolean[][] temp = new Boolean[6][7];
			temp = place2(p, i, Tree.copyB(b, temp));
			if(hasWonT(temp)){
				a = true;
			}
		}
		return a;
	}
	
	public ArrayList<Integer> findWinningMove(boolean p, Boolean[][] b){
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < 7; i++) {
			Boolean[][] temp = new Boolean[6][7];
			temp = place2(p, i, Tree.copyB(b, temp));
			if(hasWonT(temp)){
				a.add(i);
			}
		}
		return a;
	}
	
	public boolean leadToWin(boolean p, Boolean[][] b){
		return stopWinningPos(p, b).size()<b[0].length;
	}
	
	public ArrayList<Integer> stopWinningPos(boolean p, Boolean[][] b){
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < b[0].length; i++) {
			Boolean[][] temp = new Boolean[6][7];
			temp = place2(p, i, Tree.copyB(b, temp));
			if(!gotWinningMove(S.changeT(p), temp) && canPlaceT(i, b)){
				a.add(i);
			}
		}
		return a;
	}
	
	public static boolean hasWonT(Boolean[][] b){
		boolean a = false;
		for (int i = 0; i < 7; i++) {
			if(hasWon(true, i, b)){
				a = true;
			}
			if(hasWon(false, i, b)){
				a = true;
			}
		}
		return a;
	}
	
	public static boolean hasWonT(Boolean[][] b, int r){
		boolean a = false;
			if(hasWon(true, r, b)){
				a = true;
			}
			if(hasWon(false, r, b)){
				a = true;
			}
		return a;
	}
	
	
	public static boolean hasWon(boolean p, int r, Boolean[][] b){
		int temp = 0;
		for (int i = 0; i < 6; i++) {
			if(b[i][r] != null && b[i][r] == p){
				temp++;
			}else{
				temp = 0;
			}
			if(temp>=4){
//				System.out.println("loddrett");
				return true;
			}
		}
		
		temp = 0;
		int h = findH(r, b);
		for (int i = 0; i < 7; i++) {
			if(b[h][i] != null && b[h][i] == p){
				temp++;
			}else{
				temp = 0;
			}
			if(temp>=4){
//				System.out.println("vannrett");
				return true;
			}
		}
		
		temp = 0;
		int tr = r;
		while(h<5&&tr<6){
			h++;
			tr++;
		}
		while(h>=0&&tr>=0){
			if(b[h][tr] != null && b[h][tr] == p){
				temp++;
			}else{
				temp = 0;
			}
			h--;
			tr--;
			if(temp>=4){
//				System.out.println("venstre");
				return true;
			}
		}
		
		temp = 0;
		tr=r;
		h = findH(r, b);
		while(h<5&&tr>0){
			h++;
			tr--;
		}
		while(h>=0&&tr<6) {
			if(b[h][tr] != null && b[h][tr] == p){
				temp++;
			}else{
				temp = 0;
			}
			h--;
			tr++;
			if(temp>=4){
//				System.out.println("høyre");
				return true;
			}
		}
		
		return false;
	}
	
	public static int findH(int r, Boolean[][] b){
		for (int i = 0; i < 6; i++) {
			if(b[i][r] != null){
				return i;
			}
		}
		return 0;
	}
	
	public int findH(int r){
		for (int i = 0; i < 6; i++) {
			if(b[i][r] != null){
				return i;
			}
		}
		return 0;
	}
	
	public void place(boolean p, int r){
		if(canPlace(r)){
			for (int i = 0; i < 6; i++) {
				if(b[i][r]!=null){
					b[i-1][r] = p;
					break;
				}
				if(i==5){
					b[i][r] = p;
				}
			}
		}
	}
	
	public Boolean[][] place3(boolean p, int r){
		if(canPlace(r)){
			for (int i = 0; i < 6; i++) {
				if(b[i][r]!=null){
					b[i-1][r] = p;
					break;
				}
				if(i==5){
					b[i][r] = p;
				}
			}
		}
		return b;
	}
	
	public Boolean[][] place2(boolean p, int r, Boolean[][] b){
		if(canPlaceT(r, b)){
			for (int i = 0; i < 6; i++) {
				if(b[i][r]!=null && i>0){
					b[i-1][r] = p;
					break;
				}
				if(i==5){
					b[i][r] = p;
				}
			}
		}
		return b;
	}
	
	public void printB(){
		for (int i = 0; i < 6; i++) {
			System.out.println();
			for (int j = 0; j < 7; j++) {
				String temp = "";
				if(b[i][j] == null){
					temp = ".";
				}else if(b[i][j] == false){
					temp = "B";
				}else if(b[i][j] == true){
					temp = "W";
				}
				System.out.print(temp + "	");
			}
		}
		System.out.println();
	}
	
//	public static void main(String[] args) {
//		B b = new B();
//		b.printB();
//		b.place(false, 2);
//		b.printB();
//		b.place(false, 2);
//		b.printB();
//		b.place(false, 2);
//		b.printB();
//		b.place(false, 2);
//		b.printB();
//		b.place(false, 2);
//		b.printB();
//		b.place(false, 2);
//		b.printB();
//		b.place(false, 2);
//		b.printB();
//	}
}
