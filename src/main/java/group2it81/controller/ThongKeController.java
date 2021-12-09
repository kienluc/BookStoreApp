package group2it81.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import group2it81.service.BillService;

public class ThongKeController implements Initializable {
    
    String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
    
    @FXML
    private BarChart<String, Integer> reportChart;

    @FXML
    private ChoiceBox<String> cbChoice;
    @FXML
    private ChoiceBox<Integer> cbFromMonth;
    @FXML
    private ChoiceBox<Integer> cbToMonth;
    @FXML
    private ChoiceBox<Integer> cbYear;

    BillService billService = new BillService();
    XYChart.Series<String, Integer> st = new XYChart.Series<String, Integer>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Test with loading data to Bar Chart                
        loadData();
    }
    
    
    public void loadData(){

        ObservableList<Integer> oblistMonth = FXCollections.observableArrayList();
        oblistMonth.addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        cbFromMonth.setItems(oblistMonth);
        cbToMonth.setItems(oblistMonth);
        
        ObservableList<Integer> oblistYear = FXCollections.observableArrayList();
        for(int i = 2018; i <= Integer.parseInt(timeStamp); i++){
            oblistYear.addAll(i);
        }
        cbYear.setItems(oblistYear);
    }
    
    public void revenue(){
        st.getData().clear();
        reportChart.getData().clear();
        
        st.setName(Integer.toString(cbYear.getValue()));
        
        for(int i = cbFromMonth.getValue(); i <= cbToMonth.getValue(); i++) {            
            String thang = String.format("Thang %d", i);
            st.getData().add(new XYChart.Data<String, Integer>(thang, billService.countRevenue(i, cbYear.getValue()).intValue()));          
        }
        reportChart.getData().add(st);
        
    }
    SceneController switcher = new SceneController();
    public void backHomePage(ActionEvent event) throws IOException{
        switcher.switchScene("HomePage", event);
    }
}
