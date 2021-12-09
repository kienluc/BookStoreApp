package group2it81.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.io.IOException;
import group2it81.pojo.User;
import group2it81.service.DangNhapService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class DangNhapController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPass;

    SceneController switcher = new SceneController();

    public void login(ActionEvent event) throws IOException{
        DangNhapService q = new DangNhapService();
        List<User> users = q.getUser(txtUserName.getText(), txtPass.getText());
        
        
        if (!users.isEmpty()) {
            DangNhapService.ID_ROLE = users.get(0).getNhanVien().getRole().getId();
            DangNhapService.TEN_NV = users.get(0).getNhanVien().getHo() + " " + users.get(0).getNhanVien().getTen();
            switcher.switchScene("HomePage", event);
        } else {
            switcher.createAlert("Sai tên đăng nhập hoặc mật khẩu", "Đăng nhập");

        }        
    }
    
    public void exit() {
        Platform.exit();
    }

}
