package group2it81.DangNhapServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import group2it81.pojo.User;
import group2it81.service.DangNhapService;

public class TestCase4 {
    @Test
    public void test1(){
        DangNhapService login = new DangNhapService();
        List<User> rsUsers = login.getUser("waece", "ef123");
        assertTrue(rsUsers.size() == 0);
    }

    @Test
    public void test2(){
        DangNhapService login = new DangNhapService();
        List<User> rsUsers = login.getUser("", "");
        assertTrue(rsUsers.size() == 0);
    }
}
