package group2it81.BookServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

import org.junit.jupiter.api.Test;

import group2it81.pojo.Book;
import group2it81.service.BookService;

public class TestCase7 {
    @Test
    public void test7(){
        BookService bs = new BookService();
        List<Book> rsBooks = bs.searchBookByName("Anabel");
        assertTrue(rsBooks.size() > 0);
    }
}
