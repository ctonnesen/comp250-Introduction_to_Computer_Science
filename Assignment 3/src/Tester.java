
public class Tester {
	
	
	public static void main(String arg[]) {
	CatInfo a = new CatInfo("A", 100, 40, 243, 20);
	CatInfo b = new CatInfo("B", 50, 60, 244, 30);
	CatInfo c = new CatInfo("C", 125, 75, 245, 10);
	CatInfo d = new CatInfo("D", 100, 15, 246, 50);
	CatInfo e = new CatInfo("E", 100, 50, 247, 27);
	CatInfo f = new CatInfo("F", 126, 60, 245, 10);
	CatInfo g = new CatInfo("G", 123, 75, 246, 10);
	CatInfo h = new CatInfo("H", 126, 80, 248, 10);
	CatInfo i = new CatInfo("I", 123, 30, 248, 10);
	CatInfo j = new CatInfo("J", 125, 60, 245, 10);

	CatTree tree = new CatTree(a); 
	tree.addCat(b);
	tree.addCat(c);
	tree.addCat(d);
	tree.addCat(e);
	tree.addCat(f);
	tree.addCat(g);
	tree.addCat(h);
	tree.addCat(i);
	tree.addCat(j);


	System.out.println(tree.root.junior.costPlanning(3)[2]);
	
	}
}
