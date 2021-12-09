package group2it81.NhanVienServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import group2it81.service.NhanVienService;

public class TestCase21 {
   @Test
   public void test1() {
        NhanVienService nv = new NhanVienService();
        boolean rsNV = nv.xoaNhanVien(6);
        assertTrue(rsNV);
   } 
}
