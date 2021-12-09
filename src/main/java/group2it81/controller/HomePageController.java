package group2it81.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import group2it81.service.DangNhapService;

public class HomePageController implements Initializable {

    @FXML
    private Label lblTenNv;

    @FXML
    private Button btnQuanLyNV;

    @FXML
    private Button btnQuanLyKho;

    @FXML
    private Button btnBanSach;

    @FXML
    private Button btnThongKe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTenNv.setText(DangNhapService.TEN_NV);

        switch (DangNhapService.ID_ROLE) {
            case 0:
                break;
            case 1:
                btnQuanLyNV.setDisable(true);
                break;
            case 2:
                btnQuanLyNV.setDisable(true);
                btnThongKe.setDisable(true);
                break;
            default:
                btnQuanLyNV.setDisable(true);
                btnThongKe.setDisable(true);
                btnQuanLyKho.setDisable(true);
                break;
        }
    }

    SceneController switcher = new SceneController();

    public void logout(ActionEvent event) throws IOException {
        switcher.switchScene("DangNhap", event);
    }

    public void quanLyNhanVien(ActionEvent event) throws IOException {
        switcher.switchScene("QLNV", event);
    }
    public void quanLyKho(ActionEvent event) throws IOException{
        switcher.switchScene("QLKho", event);
    }
    public void banSach(ActionEvent event) throws IOException{
        switcher.switchScene("BanSach", event);
    }
    public void thongKe(ActionEvent event) throws IOException{
        switcher.switchScene("ThongKe", event);
    }
}
