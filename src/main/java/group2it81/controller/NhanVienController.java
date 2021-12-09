package group2it81.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import group2it81.pojo.NhanVienDetails;
import group2it81.pojo.NhanVien;
import group2it81.pojo.User;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

import group2it81.service.NhanVienService;

public class NhanVienController implements Initializable {
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<NhanVienDetails> table;
    @FXML
    private TableColumn<NhanVienDetails, Integer> colId;
    @FXML
    private TableColumn<NhanVienDetails, String> colHo;
    @FXML
    private TableColumn<NhanVienDetails, String> colTen;
    @FXML
    private TableColumn<NhanVienDetails, String> colSDT;
    @FXML
    private TableColumn<NhanVienDetails, String> colQueQuan;
    @FXML
    private TableColumn<NhanVienDetails, Date> colNgaySinh;
    @FXML
    private TableColumn<NhanVienDetails, String> colChucVu;
    @FXML
    private TextField txtHo;
    @FXML
    private TextField txtTen;
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtqueQuan;
    @FXML
    private DatePicker txtngaySinh;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPass;

    SceneController switcher = new SceneController();
    NhanVienService nv = new NhanVienService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void getListNhanVien() {
        List<NhanVien> listNhanVien = nv.searchNhanVien(txtSearch.getText());

        ObservableList<NhanVienDetails> oblist = FXCollections.observableArrayList();

        for (NhanVien nhanVien : listNhanVien) {
            NhanVienDetails nvd = new NhanVienDetails();
            nvd.setId(nhanVien.getId());
            nvd.setHo(nhanVien.getHo());
            nvd.setTen(nhanVien.getTen());
            nvd.setSdt(nhanVien.getSdt());
            nvd.setQueQuan(nhanVien.getQueQuan());
            nvd.setNgaySinh(new Date(nhanVien.getNgaySinh().getTime()));
            nvd.setChucVu(nhanVien.getRole().getRoleName());

            oblist.add(nvd);
        }

        colId.setCellValueFactory(new PropertyValueFactory<NhanVienDetails, Integer>("id"));
        colHo.setCellValueFactory(new PropertyValueFactory<NhanVienDetails, String>("ho"));
        colTen.setCellValueFactory(new PropertyValueFactory<NhanVienDetails, String>("ten"));
        colSDT.setCellValueFactory(new PropertyValueFactory<NhanVienDetails, String>("sdt"));
        colQueQuan.setCellValueFactory(new PropertyValueFactory<NhanVienDetails, String>("queQuan"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<NhanVienDetails, Date>("ngaySinh"));
        colChucVu.setCellValueFactory(new PropertyValueFactory<NhanVienDetails, String>("chucVu"));

        table.setItems(oblist);

        txtSearch.clear();
    }

    public void themNhanVien() {
        String msg = "";
        try {
            NhanVien nhanVienMoi = new NhanVien();
            nhanVienMoi.setHo(txtHo.getText());
            nhanVienMoi.setTen(txtTen.getText());
            nhanVienMoi.setSdt(txtSDT.getText());
            nhanVienMoi.setQueQuan(txtqueQuan.getText());
            nhanVienMoi.setNgaySinh(Timestamp.valueOf(txtngaySinh.getValue().atStartOfDay()));

            User userMoi = new User();
            userMoi.setUsername(txtUsername.getText());
            userMoi.setPassword(txtPass.getText());

            nhanVienMoi.setUser(userMoi);

            if(nv.addNhanVien(nhanVienMoi)){
                msg = "Thêm nhân viên thành công";
            }

        } catch (Exception ex) {
                msg = "Phải điền đầy đủ thông tin";
        } finally {
            switcher.createAlert(msg, "Thêm Nhân Viên");

            txtHo.clear();
            txtTen.clear();
            txtngaySinh.setValue(null);
            txtSDT.clear();
            txtqueQuan.clear();
            txtUsername.clear();
            txtPass.clear();
        }

    }

    public void exit(ActionEvent event) throws IOException {
        switcher.switchScene("HomePage", event);
    }

    public void xoaNhanVien() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Bạn có chắc muốn xóa nhân viên này ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.setTitle("Thông báo");
        ButtonType result = alert.showAndWait().orElse(ButtonType.YES);
        
        if (ButtonType.YES.equals(result)) {
            if(nv.xoaNhanVien(table.getSelectionModel().getSelectedItem().getId())){
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            } 
        }
    }

}