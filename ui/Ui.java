package ui;

/* Internal */
import networking.API;

/* JAVAFX */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ui extends Application {
    public static void launchUi(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather Program");
        BorderPane border = new BorderPane();

        /* Center grid */
        GridPane centerGrid = new GridPane();
        Label cityLabel = new Label("City: ");
        TextField cityName = new TextField();
        Button weatherRequest = new Button("Search");
        Label weatherLabel = new Label();
        weatherRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    weatherLabel.setText(API.getCurrentWeather("Oulu", "FI"));
                }
                catch (Exception exc) {
                    weatherLabel.setText("Could not fetch weather");
                }
            }
        });
        centerGrid.add(cityLabel,1,0);
        centerGrid.add(cityName,2,0);
        centerGrid.add(weatherRequest,3,0);
        centerGrid.add(weatherLabel,1,1);
        border.setCenter(centerGrid);

        /* Show */
        primaryStage.setScene(new Scene(border, 1080, 720));
        primaryStage.show();
    }
}