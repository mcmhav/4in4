package p;

import java.util.Scanner;

public class S {

	private boolean compP = false;
	private A a;
	
	public static boolean changeT(boolean p){
		if(p == true) return false;
		return true;
	}
	
	
	
	public void play(){
		B b = new B();
		
		boolean t = true;
		
		String diff = "no2";
		a = new A(diff, b, false);
		compP = true;
		
		Scanner sc = new Scanner(System.in);
		
		while(true){
			
			int temp = sc.nextInt();
//			a.setOP(t);
			b.place(t, temp);
//			a.comp(diff);
			b.printB();
			
			if(b.hasWon(t, a.nextVal)){
				System.out.println(t + " won!");
				break;
			}
			
			
			t = changeT(t);
//			a.setOP(t);
			if(compP){
				
				a.comp(diff);
				b.printB();
				if(b.hasWon(t, a.nextVal)){
					System.out.println(t + " won!");
					break;
				}
				t = changeT(t);
			}
		}
	}
	
	public static void main(String[] args) {
		S s = new S();
		s.play();
//		B b = new B();
//		
//		boolean t = true;
//		
//		Scanner sc = new Scanner(System.in);
//		
//		while(sc.hasNext()){
//			
//			int temp = sc.nextInt();
//			
//			b.place(t, temp);
//			
//			b.printB();
//			
//			if(b.hasWon(t, temp)){
//				System.out.println(t + " won!");
//				break;
//			}
//			
//			
//			t = changeT(t);
//		}
	}
}
