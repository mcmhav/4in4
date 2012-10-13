package p;

public class T {

	public static void main(String[] args) {
//		B br = new B();
//		
//		Boolean[][] b = {{null,null,null,null,null,null, null},
//							{null,null,null,null,null,null, null},
//							{null,null,false,null,null,null, null},
//							{null,null,true,true,null,null,null},
//							{null,null,true,false,false,null,null},
//							{false,true,true,true,false,null,false}};
//		
//		System.out.println(br.stopTwoNextOpen(true, b));
//		
//		boolean a = false;
//		for (int i = 0; i < 7; i++) {
//			System.out.println(br.hasWonT(b));
//			int temp = 0;
//			int tr = 3;
//			int h = br.findH(3, b);
//			System.out.println(h);
//			while(h<5&&tr<6){
//				h++;
//				tr++;
//			}
//			while(h>=0&&tr>=0){
//				if(b[h][tr] != null && b[h][tr] == true){
//					temp++;
//				}else{
//					temp = 0;
//				}
//				h--;
//				tr--;
//				System.out.println(temp);
//				if(temp>=4){
//					a = true;
//				}
//			}
//		}
//		System.out.println(a);
//		System.out.println(b.length);
//		System.out.println(b[0].length);
		
		
		double count = 0;
		String m = " ";
		String am = "a";
	
		int num = (int)(((Math.sin(count++/10))*100)/5);
		for (int i = 0; i < 45; i++) {
//			System.out.println((int)(((Math.sin(count++/10))*100)/10));
			po((int)(((Math.sin(count++/10))*100)/17));
		}
//		while(true){
//			am = m + am;
//			System.out.println(am);
//			count++;
//			if(count==5){
//				count = 0;
//				am = "a";
//			}
//		}
	}
	public static void po(int a){
		String m = " ";
		for (int i = 0; i < a; i++) {
			m += m;
		}
		m = m + ".";
		System.out.println(m);
	}
}
