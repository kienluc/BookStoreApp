package group2it81.BookServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import group2it81.service.BookService;

public class TestCase13 {
   @Test
   public void test1() {
        BookService bs = new BookService();
        boolean rsBooks = bs.addBook(null, 6, 7, 7);
        assertTrue(!rsBooks);
   } 
}
