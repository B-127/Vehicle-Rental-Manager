package GUI;

import ManagerConsole.WestminsterVehicleRentalManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchGUI extends Application {
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
        primaryStage.setTitle("Westminster Vehicle Rental Service");
        primaryStage.setScene(new Scene((root),1220,620));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
