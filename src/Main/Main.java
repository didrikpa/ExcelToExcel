package Main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.Parent;

public class Main extends Application {
    FXMLLoader fxmlLoader;
    Parent root;
    public Stage ps;

    @Override
    public void start(Stage primaryStage) throws IOException {
        fxmlLoader = new FXMLLoader();
        root = (Parent) fxmlLoader.load(this.getClass().getResource("/views/ExcelToExcel.fxml"));
        ps = primaryStage;
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ExcelToExcel");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
