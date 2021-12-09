package group2it81.DangNhapServiceTester;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import group2it81.pojo.User;
import group2it81.service.DangNhapService;

public class TestCase1 {
    @Test
    public void test1(){
        DangNhapService login = new DangNhapService();
        List<User> rsUsers = login.getUser("kienluc", "123");
        assertTrue(rsUsers.get(0).getUsername().equals("kienluc"));
    }
    
    @Test
    public void test2(){
        DangNhapService login = new DangNhapService();
        List<User> rsUsers = login.getUser("kien", "123");
        assertTrue(rsUsers.get(0).getUsername().equals("kien"));
    }
}
