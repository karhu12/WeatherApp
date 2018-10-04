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
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import java.io.*;

/* libs */
import com.google.gson.*;

public class Ui extends Application {

    private BorderPane border;
    private GridPane centerGrid;
    private Label cityLabel;
    private Label temperatureLabel;
    private TextField cityName; 
    private Button weatherRequest;
    private ImageView weatherImageView;
    private final String imgPath = "/home/riku/Documents/Opiskelumateriaali/V2/P1/Java/Harjoitukset/WeatherApp/images/";

    public static void launchUi(String[] args) {
        launch(args);
    }

    private void searchWeather() {
        String json = API.getCurrentWeather(cityName.getText());
        try {
            if (API.isJsonValid(json)) {
                CurrentWeather weather = new Gson().fromJson(json, CurrentWeather.class);
                File file = new File(imgPath + weather.getWeatherInfo("icon") + ".png");
                Image image = new Image(file.toURI().toString());
                weatherImageView.setImage(image);
                temperatureLabel.setText(String.valueOf(weather.getTemperatureInfo("temp")) + " °C");
            }
            else {

            }
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather Program");
        border = new BorderPane();

        /* Center grid */
        centerGrid = new GridPane();
        weatherImageView = new ImageView();
        temperatureLabel = new Label("");
        cityLabel = new Label("Search weather by City");
        cityName = new TextField();
        weatherRequest = new Button("Search");

        cityName.setFont(new Font("Arial", 22));
        cityLabel.setFont(new Font("Arial", 32));
        temperatureLabel.setFont(new Font("Arial", 32));
        weatherRequest.setFont(new Font("Arial", 22));

        weatherRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                searchWeather();
            }
        });

        
        centerGrid.add(cityLabel,0,0);
        centerGrid.add(cityName,0,1);
        centerGrid.add(weatherRequest,0,2);
        centerGrid.add(weatherImageView,0,3);
        centerGrid.add(temperatureLabel,1,3);

        centerGrid.setHalignment(cityLabel, HPos.CENTER);
        centerGrid.setHalignment(weatherRequest, HPos.CENTER);
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setVgap(15);

        border.setTop(centerGrid);
        border.setMargin(centerGrid, new Insets(12,12,12,12));

        /* Show */
        primaryStage.setScene(new Scene(border, 640, 320));
        primaryStage.show();
    }
}