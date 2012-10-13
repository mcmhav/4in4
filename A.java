package p;

import java.util.ArrayList;

public class A {

	private B br;
	private boolean op;
	private String d;
	private Tree<Boolean[][]> t;
	private static int count = 0;
	public static int nextVal = (int)(Math.random()*7);
	
	public A(String d, B b, boolean p){
		this.br = b;
		this.op = p;
		this.d = d;
	}
	
	public void setOP(boolean p){
		op = p;
	}
	
	public void setCount(int a){
		count = a;
	}
	
	public void comp(String d){
		if(d.equals("random")){
			mrRandom();
		}else if (d.equals("no1")){
			mrAgentno1();
			setCount(0);
			Tree.ab = true;
			Tree.ac = true;
			if(A.ab){
				mrRandom();
			}
			A.ab = true;
		}else if (d.equals("no2")){
			mrAgentno2();
		}
	}
	
	private void mrRandom(){
		int pl = (int)(Math.random()*7);
		while(!br.canPlace(pl)){
			pl = (int)(Math.random()*7);
		}
		br.place(op, pl);
	}
	
	private void mrAgentno2(){
		putBno2(br.getB(), op);
	}
	
	public synchronized void putBno2(Boolean[][] b, boolean p){
		try{
  			if(br.gotWinningMove(p, b)){
  				nextVal = br.findWinningMove(p, b).get(0);
  				br.place(op, nextVal);
  			}else if(br.gotWinningMove(S.changeT(p), b)){
  				nextVal = br.findWinningMove(S.changeT(p), b).get(0);
  				br.place(op, nextVal);
  			}else if(br.twoNextOpen(S.changeT(p),b)){
  				nextVal = br.stopTwoNextOpen(S.changeT(p), b).get(0);
  				br.place(op, nextVal);
  			}else if(br.leadToWin(p, b)){
  				nextVal = br.stopWinningPos(p, b).get(0);
  				br.place(op, nextVal);
  			}else{
  				mrRandom();
  			}
  		}catch (Exception e) {
  			System.err.println("err " + e.fillInStackTrace());
  		}	
	}
	
	private void mrAgentno1(){
//		Boolean[][] btemp = {{true,null,null,null,null,null, null},
//				{false,null,null,null,null,null, null},
//				{true,null,null,null,null,null, null},
//				{false,null,null,null,null,null,null},
//				{true,null,null,null,null,null,null},
//				{false,true,false,false,null,null,null}};
		t = new Tree<Boolean[][]>(br.getB(), op, this);
		putBno1(br.getB(), op, 0);
	}
	
    
  	public synchronized void putBno1(Boolean[][] b, boolean p, int a){
//  		Tree<Boolean[][]> t = new Tree<Boolean[][]>(b, op);
		try{
  			if(ab && br.gotWinningMove(p, b)){
  				arrAdd(b, a, p, t, br.findWinningMove(p, b));
  			}else if(ab && br.gotWinningMove(S.changeT(p), b)){
  				arrAdd(b, a, p, t, br.findWinningMove(S.changeT(p), b));
  			}else if(ab && br.leadToWin(p, b)){
  				arrAdd(b, a, p, t, br.stopWinningPos(p, b));
  			}else if(ab && br.twoNextOpen(S.changeT(p),b)){
  				arrAdd(b, a, p, t, br.stopTwoNextOpen(S.changeT(p), b));
  			}else if(ab){
  				standardAdd(b, a, p, t);
  			}
  		}catch (Exception e) {
  			System.err.println("err " + e.fillInStackTrace());
  		}	
//	  	System.out.println(t.printTree(0));
  	}
  	
  	public void no1Place(){
  		br.place(op, nextVal);
  	}
  	
  	private final int maxD = 100000;
  	private void standardAdd(Boolean[][] b, int a, boolean p, Tree<Boolean[][]> t){
  		for (int i = 0; i < b[0].length; i++) {
			Boolean[][] temp = new Boolean[6][7];
			temp = br.place2(p, i ,Tree.copyB(b, temp));
			if(ab && !Tree.doesExist(t,temp)){
				t.addLeaf(b, temp, p);
			}
			if(ab && br.canPlaceT(i, temp) && a<17 && !B.hasWonT(temp, i) && count<=maxD){
				if(count>=maxD && ab){
//					t.getWinningTree();
					ab=false;
					nextVal = Tree.be;
//					br.place(op, nextVal);
					count += maxD;
					break;
//					System.out.println(nextVal);
				}
				count ++;
				if(count<maxD){
					boolean te = S.changeT(p);
					putBno1(temp, te, a);
				}
			}
		}
  	}
	
  	private void arrAdd(Boolean[][] b, int a, boolean p, Tree<Boolean[][]> t, ArrayList<Integer> al){
  		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.addAll(al);
		for (int i = 0; i < ar.size(); i++) {
			Boolean[][] temp = new Boolean[6][7];
			temp = br.place2(p, ar.get(i) ,Tree.copyB(b, temp));
			if(ab && !Tree.doesExist(t,temp)){
				t.addLeaf(b, temp, p);
			}
			if(ab && br.canPlaceT(ar.get(i), temp) && a<17 && !B.hasWonT(temp, ar.get(i)) && count<=maxD){
				if(count>=maxD && ab){
//					t.getWinningTree();
					ab=false;
					nextVal = Tree.be;
//					br.place(op, nextVal);
					count += maxD;
					break;
//					System.out.println(nextVal);
				}
				count ++;
				if(count<maxD){
					boolean te = S.changeT(p);
					putBno1(temp, te, a);
				}
			}
		}
  	}
  	public static boolean ab = true;
}
