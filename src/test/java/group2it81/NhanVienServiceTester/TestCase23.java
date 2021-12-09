package group2it81.NhanVienServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import java.util.Date;

import org.junit.jupiter.api.Test;

import group2it81.pojo.NhanVien;

import group2it81.pojo.User;

import group2it81.service.NhanVienService;

public class TestCase23 {
   @Test
   public void test1() {
        NhanVienService nv = new NhanVienService();
        NhanVien a = new NhanVien();
        User u = new User();
        u.setUsername("a");
        u.setPassword("");
        a.setUser(u);
        a.setId(20);
        a.setHo("a");
        a.setTen("a");
        a.setQueQuan("HCM");
        Date d = new Date();
        a.setNgaySinh(new Timestamp(d.getTime()));
        boolean rsNV = nv.addNhanVien(a);
        assertTrue(rsNV);
   } 
}
