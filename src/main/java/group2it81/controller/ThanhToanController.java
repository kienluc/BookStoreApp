package group2it81.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import group2it81.pojo.Customer;
import group2it81.service.BillService;
import group2it81.service.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ThanhToanController implements Initializable {
    private int id_customer;
    private Date date;
    @FXML
    private Label lbTotalPay;
    @FXML
    private Label lbBack;
    @FXML 
    private TextField txtMoney;
    @Override
    public void initialize (URL url, ResourceBundle rb){
        lbTotalPay.setText(Integer.toString(BanSachController.getTotalPrice()));
    }
    SceneController switcher = new SceneController();
    
    public void back(ActionEvent event) throws IOException{
        switcher.switchScene("BanSach", event);
    }
    
    public void acceptPay(ActionEvent event) throws IOException, NumberFormatException, ParseException{
        switcher.createAlert("Thanh toán thành công", "Thông báo");
        CustomerService cService = new CustomerService();
        cService.addCustomer(BanSachController.getCustomerName(), BanSachController.getCustomerPhone());   

        BillService billService = new BillService();
        List<Customer> rsCustomers = cService.getCustomer();
        
        rsCustomers.forEach(customer ->{
            if(customer.getSdt().equals(BanSachController.getCustomerPhone())) id_customer = customer.getId();
        });
        billService.addBill(id_customer, Integer.parseInt(lbTotalPay.getText()), date);
    }
    public void cashier(){
        try {
            int txt = Integer.parseInt(txtMoney.getText()) - Integer.parseInt(lbTotalPay.getText());
            if(txt < 0) {lbBack.setText("Khong du tien");}
            else {lbBack.setText(Integer.toString(txt));}
        }
        catch (Exception ex) {
            lbBack.setText("Khong hop le");
        }
    }
}
