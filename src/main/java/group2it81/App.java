package group2it81;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../DangNhap.fxml"));

        scene = new Scene(root);
        stage.setTitle("BookStore Management");
        Image icon = new Image("/Pic/bookstore-icon.jpg");  
        stage.getIcons().add(icon);  
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(false);   
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
