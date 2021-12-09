package group2it81.DangNhapServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import group2it81.pojo.User;
import group2it81.service.DangNhapService;

public class TestCase3 {
    @Test
    public void test1(){
        DangNhapService login = new DangNhapService();
        List<User> rsUsers = login.getUser("asdeq", "123");
        assertTrue(rsUsers.size() == 0);
    }
    @Test
    public void test2(){
        DangNhapService login = new DangNhapService();
        List<User> rsUsers = login.getUser("", "123");
        assertTrue(rsUsers.size() == 0);
    }
}
