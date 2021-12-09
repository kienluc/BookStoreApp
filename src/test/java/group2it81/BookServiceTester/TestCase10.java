package group2it81.BookServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import group2it81.pojo.Book;
import group2it81.service.BookService;

public class TestCase10 {
   @Test
   public void test1() {
        BookService bs = new BookService();
        Book b = new Book();
        b.setTenSach("abc");
        b.setLanTaiBan(99);
        b.setDonGia(90000);
        b.setSoLuongTon(100);
        boolean rsBooks = bs.addBook(b, 6, 7, 7);
        assertTrue(rsBooks);
   } 
}
