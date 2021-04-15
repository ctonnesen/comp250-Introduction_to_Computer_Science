public class Cyborg implements Human, Machine {

    public void move() {

        System.out.println("Cyborg is moving.");

    }
    
    public void think() {

        System.out.println("Cyborg is thinking.");

    }

    
    public static void main (String[]  args) {
    	Cyborg[] c = new Cyborg[0];
    	for (Human h : c)
    	    h.think();
    }
    
}