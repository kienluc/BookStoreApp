package group2it81.BookServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

import org.junit.jupiter.api.Test;

import group2it81.pojo.Book;
import group2it81.service.BookService;

public class TestCase6 {
    @Test
    public void test1(){
        BookService bs = new BookService();
        List<Book> rsBooks = bs.searchBookByKeyWord("");
        assertTrue(rsBooks.size() == 0);
    }
}
