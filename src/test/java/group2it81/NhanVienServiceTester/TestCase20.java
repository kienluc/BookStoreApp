package group2it81.NhanVienServiceTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import group2it81.pojo.NhanVien;
import group2it81.service.NhanVienService;

public class TestCase20 {
   @Test
   public void test1() {
        NhanVienService nv = new NhanVienService();
        List<NhanVien> rsNV = nv.searchNhanVien("");
        assertTrue(rsNV.size() == 0);
   } 
}
