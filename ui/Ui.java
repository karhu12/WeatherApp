package ui;

import networking.API;
import data.CurrentWeather;
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

/* libs */
import com.google.gson.*;

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
                String json = API.getCurrentWeather(cityName.getText());
                try {
                    if (API.isJsonValid(json)) {
                        CurrentWeather weather = new Gson().fromJson(json, CurrentWeather.class);
                        cityLabel.setText(weather.getTemperatureInfo("temp") + weather.getWeatherInfo("description"));
                    }
                    else {
                        cityLabel.setText("Invalid search");
                    }
                }
                catch (Exception err) {
                    err.printStackTrace();
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