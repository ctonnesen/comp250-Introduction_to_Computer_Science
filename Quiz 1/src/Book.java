public class Book{

   public static String title;

   public static String author;

   public Book(String title, String author){

       title = title;

       author = author;

   }

 

   public static void main(String[] args) {

       Book b1 = new Book("Matilda", "Roald Dahl");

       Book b2 = new Book("Alice's Adventures in Wonderland", "Lewis Carroll");

       System.out.println(b1.title);

   }

}