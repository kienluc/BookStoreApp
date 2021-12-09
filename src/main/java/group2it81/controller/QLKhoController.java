package group2it81.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



import group2it81.pojo.Author;
import group2it81.pojo.Book;
import group2it81.pojo.BookDetail;
import group2it81.pojo.BookType;
import group2it81.pojo.Producer;
import group2it81.service.AuthorService;
import group2it81.service.BookService;
import group2it81.service.BookTypeService;
import group2it81.service.ProducerService;


public class QLKhoController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    //Components
    AuthorService ats = new AuthorService();
    List<Author> rsAuthors = ats.getAuthor();
    BookTypeService bts = new BookTypeService();
    List<BookType> rsBookTypes = bts.getBookType();
    ProducerService pds = new ProducerService();
    List<Producer> rsProducers = pds.getProducer();
    BookService bs = new BookService();
    
    //Switch scene and create an alert
    SceneController switcher = new SceneController();
    
    //ID for searching
    private int id_author;
    private int id_bookType;
    private int id_producer;
    
    //Properties in Scene Builder
    @FXML
    private ChoiceBox<String> cbAuthor;
    @FXML
    private ChoiceBox<String> cbBookType;
    @FXML
    private ChoiceBox<String> cbProducer;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML 
    private TextField txtAmount;
    @FXML
    private TextField txtRepublish;

    @FXML
    private TableView<BookDetail> table;
    @FXML
    private TableColumn<BookDetail, Integer> colId;
    @FXML
    private TableColumn<BookDetail, String> colName;
    @FXML
    private TableColumn<BookDetail, String> colAuthor;
    @FXML
    private TableColumn<BookDetail, String> colBookType;
    @FXML
    private TableColumn<BookDetail, Integer> colPrice;
    @FXML
    private TableColumn<BookDetail, Integer> colAmount;
    @FXML
    private TableColumn<BookDetail, String> colProducer;

    //Add a book into tableview and database
    public void inputBook(ActionEvent event) throws IOException{
        try {
            
            Book sach = new Book();
        
            rsAuthors.forEach(auth -> {
                if(auth.getHoTen().equals(cbAuthor.getValue())) id_author = auth.getId();
            });
            rsBookTypes.forEach(type ->{
                if(type.getTenLoai().equals(cbBookType.getValue())) id_bookType = type.getId();
            });

            rsProducers.forEach(prod ->{
                if(prod.getTenNXB().equals(cbProducer.getValue())) id_producer = prod.getId();
            });

            sach.setTenSach(txtName.getText());
            sach.setSoLuongTon(Integer.parseInt(txtAmount.getText()));
            sach.setLanTaiBan(Integer.parseInt(txtRepublish.getText()));
            sach.setDonGia(Integer.parseInt(txtPrice.getText()));
            bs.addBook(sach, id_author, id_bookType, id_producer);
            switcher.createAlert("Thêm sách thành công !!!", "Thông báo");
            
            txtName.clear();
            txtAmount.clear();
            txtPrice.clear();
            txtRepublish.clear();
            cbAuthor.setValue(null);
            cbBookType.setValue(null);
            cbProducer.setValue(null);

            loadData();
        } catch (Exception ex) {
            switcher.createAlert("Phải nhập đầy đủ thông tin sách", "Thông báo");
        }
                    
    }
    //Clear input information of book   
    public void clearInfo(ActionEvent event) throws IOException{
            txtName.clear();
            txtAmount.clear();
            txtPrice.clear();
            txtRepublish.clear();
            cbAuthor.setValue(null);
            cbBookType.setValue(null);
            cbProducer.setValue(null);
    }
    private void loadData(){
        //load Author - Category - Publisher
        ObservableList<String> oblistAuth = FXCollections.observableArrayList();
        for(Author a: rsAuthors){
            oblistAuth.addAll(a.getHoTen());
        }
        cbAuthor.setItems(oblistAuth);

        
        ObservableList<String> oblistBookType = FXCollections.observableArrayList();
        for(BookType b: rsBookTypes){
            oblistBookType.addAll(b.getTenLoai());
        }
        cbBookType.setItems(oblistBookType);

        ObservableList<String> oblistProducer = FXCollections.observableArrayList();
        for(Producer p: rsProducers){
            oblistProducer.addAll(p.getTenNXB());
        }
        cbProducer.setItems(oblistProducer);

        //load list of Book into tableview
        List<Book> rsBooks = bs.loadBooks();
        ObservableList<BookDetail> oblistBook = FXCollections.observableArrayList();
        rsBooks.forEach(book ->{
            BookDetail b = new BookDetail();
            b.setId(book.getId());
            b.setTenSach(book.getTenSach());
            b.setLoaiSach(book.getLoaisach().getTenLoai());
            b.setNxb(book.getProducer().getTenNXB());
            b.setTenTG(book.getAuthor().getHoTen());
            b.setGia(book.getDonGia());
            b.setSoLuong(book.getSoLuongTon());
            
            oblistBook.add(b);
        });

        colId.setCellValueFactory(new PropertyValueFactory<BookDetail, Integer>("id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<BookDetail, Integer>("soLuong"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<BookDetail, String>("tenTG"));
        colBookType.setCellValueFactory(new PropertyValueFactory<BookDetail, String>("loaiSach"));
        colName.setCellValueFactory(new PropertyValueFactory<BookDetail, String>("tenSach"));
        colPrice.setCellValueFactory(new PropertyValueFactory<BookDetail, Integer>("gia"));
        colProducer.setCellValueFactory(new PropertyValueFactory<BookDetail, String>("nxb"));
        
        table.setItems(oblistBook);
    }
    
    //Delete book from tableview and database
    public void deleteBook(){       
        bs.deleteBook(table.getSelectionModel().getSelectedItem().getId());
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        switcher.createAlert("Xoa thanh cong", "Thong bao");
        loadData();
    }
    public void backHomePage(ActionEvent event) throws IOException{
        switcher.switchScene("HomePage", event);
    }
}
