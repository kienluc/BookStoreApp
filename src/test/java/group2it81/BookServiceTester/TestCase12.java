package group2it81.BookServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import group2it81.service.BookService;

public class TestCase12 {
   @Test
   public void test1() {
        BookService bs = new BookService();
        boolean rsBooks = bs.deleteBook(15);
        assertTrue(rsBooks );
   } 
}
