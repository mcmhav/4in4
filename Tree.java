package p;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

@SuppressWarnings("hiding")
public class Tree<T> {

	private T head;
	private ArrayList<Tree<T>> leafs = new ArrayList<Tree<T>>();
	private Tree<T> parent = null;
	private HashMap<T, Tree<T>> locate = new HashMap<T, Tree<T>>();
	private A ai;
	
	private boolean op;
	private int v;

	public Tree(T head, boolean op,A ai) {
		this.head = head;
		this.op = op;
		this.ai = ai;
		locate.put(head, this);
	}
	
	public void addLeaf(T root, T leaf) {
	    if (locate.containsKey(root)) {
	      locate.get(root).addLeaf(leaf);
	    } else {
	      addLeaf(root).addLeaf(leaf);
	    }
	}

	public void addLeaf(T root, T leaf, boolean p) {
	    if (locate.containsKey(root)) {
	      locate.get(root).addLeaf(leaf,p);
	    } else {
	      addLeaf(root).addLeaf(leaf, p);
	    }
	}

	public Tree<T> addLeaf(T leaf, boolean p) {
	    Tree<T> t = new Tree<T>(leaf, op, ai);
	    leafs.add(t);
	    t.parent = this;
	    t.locate = this.locate;
	    locate.put(leaf, t);
	    if(B.hasWonT(((Boolean[][])t.head)) && p==op){
	    	t.setV(99);
	    	if(ab){
	    		getWinningVal(t);
	    		A.nextVal = be;
	    		System.out.println(be);
	    		A.ab = false;
	    		ai.no1Place();
	    	}
	    }else if(B.hasWonT(((Boolean[][])t.head)) && p!=op){
	    	t.setV(-99);
	    }else{
	    	t.setV(0);
	    }
	    return t;
  	}
	
  	public Tree<T> addLeaf(T leaf) {
	    Tree<T> t = new Tree<T>(leaf, op, ai);
	    leafs.add(t);
	    t.parent = this;
	    t.locate = this.locate;
	    locate.put(leaf, t);
	    return t;
  	}

  	public Tree<T> setAsParent(T parentRoot) {
	    Tree<T> t = new Tree<T>(parentRoot, op, ai);
	    t.leafs.add(this);
	    this.parent = t;
	    t.locate = this.locate;
	    t.locate.put(head, this);
	    t.locate.put(parentRoot, t);
	    return t;
  	}

  	public T getHead() {
  		return head;
  	}

  	public Tree<T> getTree(T element) {
  		return locate.get(element);
  	}

  	public Tree<T> getParent() {
  		return parent;
  	}

  	public Collection<T> getSuccessors(T root) {
	    Collection<T> successors = new ArrayList<T>();
	    Tree<T> tree = getTree(root);
	    if (null != tree) {
	      for (Tree<T> leaf : tree.leafs) {
	        successors.add(leaf.head);
	      }
	    }
	    return successors;
  	}

  	public Collection<Tree<T>> getSubTrees() {
  		return leafs;
 	 }

  	public static <T> Collection<T> getSuccessors(T of, Collection<Tree<T>> in) {
	    for (Tree<T> tree : in) {
	      if (tree.locate.containsKey(of)) {
	        return tree.getSuccessors(of);
	      }
	    }
	    return new ArrayList<T>();
  	}

  	@Override
  	public String toString() {
  		return printTree(0);
  	}
  
  	public String printB(Boolean[][] b){
  		String s = "";
  		for (int i = 0; i < b.length; i++) {
//			System.out.println();
  			s += System.getProperty("line.separator");
  			for (int j = 0; j < b[0].length; j++) {
  				String temp = "";
  				if(b[i][j] == null){
  					temp = ".";
  				}else if(b[i][j] == false){
  					temp = "B";
  				}else if(b[i][j] == true){
  					temp = "W";
  				}
//				System.out.print(temp + "	");
  				s += temp + "	";
  			}
  		}
//		System.out.println();
		return s;
	}
    
  	private static final int indent = 2;

  	public synchronized String printTree(int increment) {
  		String s = "";
  		String inc = "";
  		for (int i = 0; i < increment; ++i) {
  			inc = inc + " ";
  		}
//  		s = inc + head;
  		s = inc + printB((Boolean[][])head);
  		if(B.hasWonT((Boolean[][])head)){
  			s += "WUNNET---------  ";
  		}
  		for (Tree<T> child : leafs) {
  			s += "\n" + child.printTree(increment + indent);
  		}
  		return s;
  	}
  
  	public static Boolean[][] copyB(Boolean[][] b, Boolean[][] c){
  		for (int i = 0; i < 6; i++) {
		  for (int j = 0; j < 7; j++) {
			c[i][j] = b[i][j];
		  }
  		}
  		return c;
  	}
  
  	public boolean doesExist(T t){
  		return locate.containsKey(t);
  	}
  	
  	public Set<T> getSet(){
  		return locate.keySet();
  	}
  	
  	public static boolean doesExist(Tree<Boolean[][]> t, Boolean[][] b){
  		boolean a = false;
  		for (int i = 0; i < t.getSubTrees().toArray().length; i++) {
  			Object obj = t.getSubTrees().toArray()[i];
  			Tree<Boolean[][]> tree = (Tree<Boolean[][]>)obj;
  			a = true;
			for (int j = 0; j < b.length; j++) {
				for (int j2 = 0; j2 < b[0].length; j2++) {
					if(tree.getHead()[j][j2]!=b[j][j2]){
						a=false;
						break;
					}
				}
				if(!a){
					break;
				}
			}
			if(a){
				return true;
			}
			a = doesExist(tree, b);
		}
  		return a;
  	}
  	
  	public static boolean gotWin(int a){
  		if(a>100) return true;
  		return false;
  	}
  	
  	public static boolean gotLos(int a){
  		if(a<0) return true;
  		return false;
  	}
  	public int getV() {
  		return v;
  	}
  	
  	public void setV(int v) {
  		this.v = v;
  	}
  	
  	public static boolean ac = true;
  	public void getWinningTree() {
  		for (Tree<T> child : leafs) {
  			if(child.getV()==99 && ac){
  				getWinningVal(child);
  				ac = false;
  				break;
  			}
  			child.getWinningTree();
  		}
  	}
  	public static boolean ab = true;
  	public static int be = (int)(Math.random()*7);
  	public void getWinningVal(Tree<T> t){
  		if(t.parent.parent!=null){
  			getWinningVal(t.parent);
  		}
  		if(ab){
  			be = bDiffers((Boolean[][])t.parent.head, (Boolean[][])t.head);
  			ab = false;
  		}
  	}
  	
  	private int bDiffers(Boolean[][] p, Boolean[][] b){
  		int w = (int)Math.random()*7;
  		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				if(p[i][j]!=b[i][j]){
					w = j;
				}
			}
		}
  		return w;
  	}
  	
  public static void main(String[] args) {
	  B b = new B();
	  A a = new A("no1", b, false);
//	  Tree<Boolean[][]> t = new Tree<Boolean[][]>(b.getB(), false);
	  a.comp("no1");
	  
//	  Boolean[][] r = {{false,false},{false, false}};
//	  Boolean[][] r2 = {{true,false},{true, true}};
//	  Boolean[][] r3 = {{false,true},{true, true}};
//	  Boolean[][] r4 = {{true,true},{true, true}};
//	  Boolean[][] r5 = {{false,false},{false, false}};
//	  
//	  Boolean[][] r6 = {{true,false},{true, true}};
//	  
//	  Boolean[][] r7 = {{true,false},{false, true}};
//	  Boolean[][] r8 = {{false,true},{false, false}};
//	  
//	  Boolean[][] r9 = {{false,true},{true, true}};
//	  
//	
////t.putB(b.getB(), false, 0);
//	  Tree<Boolean[][]> t = new Tree<Boolean[][]>(r);



//	for (int i = 0; i < 7; i++) {
//		Boolean[][] temp = new Boolean[6][7];
//		
////		t.printB3(t.copyB(b.getB(), temp));
//		t.addLeaf(b.place2(true, i, t.copyB(b.getB(), temp)));
//	}

//	t.addLeaf(r2);
//	t.addLeaf(r3);
//	t.addLeaf(r4);
//	t.addLeaf(r5);
//	t.addLeaf(r7, r8);
//
//	System.out.println(t.printTree(0));
//	System.out.println();
//	System.out.println(t.getSubTrees());
//	System.out.println();
//	System.out.println(t.test(t, r9));

	  
//	  Tree<Integer> t = new Tree<Integer>(5);
//	  
//	  t.addLeaf(3);
//	  if(Tree.gotLos(3)){
//		  
//	  }
//	  t.addLeaf(2);
//	  t.addLeaf(7);
//	  
//	  t.addLeaf(7, 9);
//	  t.addLeaf(2, 6);
//	  t.addLeaf(43, 23);
//	  t.addLeaf(43, 25);
//	  t.addLeaf(43, 99);
//	  t.addLeaf(43, 65);
//	  t.addLeaf(43, 87);
//	  t.addLeaf(34);
//	  
//	  t.addLeaf(99, 101);
//	  
//	  t.addLeaf(25,-10);
//	  
//	  System.out.println(t.printTree(0));
//	  System.out.println();
//	  System.out.println(t.getParent());
//	  System.out.println();
//	  System.out.println(t.getSuccessors(43));
//	  System.out.println();
//	  System.out.println(t.doesExist(101));
  }

}