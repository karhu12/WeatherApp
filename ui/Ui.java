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
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import java.io.*;

/* libs */
import com.google.gson.*;

public class Ui extends Application {

    /* Containers */
    private BorderPane border;
    private HBox header;
    private GridPane centerGrid;
    private GridPane leftGrid;

    /* Border Top */
    private Label headerLabel;

    /* Border Left */
    private Label cityLabel;
    private Label countryCodeLabel;
    private TextField cityName;
    private TextField countryCodeName;
    private Button weatherRequest;

    /* Border Center */
    private Label temperatureLabel;
    private Label requestCityLabel;
    private Label descriptionLabel;
    private Label windLabel;
    private ImageView weatherImageView;

    private final String imgPath = "/home/riku/Documents/Opiskelumateriaali/V2/P1/Java/Harjoitukset/WeatherApp/images/";

    public static void launchUi(String[] args) {
        launch(args);
    }

    private void searchWeather() {
        String json = new String();
        if (!cityName.getText().trim().isEmpty() && !countryCodeName.getText().trim().isEmpty()) {
            json = API.getCurrentWeather(cityName.getText(), countryCodeName.getText());
        }
        else if (!cityName.getText().trim().isEmpty() && countryCodeName.getText().trim().isEmpty()) {
            json = API.getCurrentWeather(cityName.getText());
        }
        try {
            if (API.isJsonValid(json)) {
                CurrentWeather weather = new Gson().fromJson(json, CurrentWeather.class);
                File file = new File(imgPath + weather.getWeatherInfo("icon") + ".png");
                Image image = new Image(file.toURI().toString());
                descriptionLabel.setText(weather.getWeatherInfo("description"));
                requestCityLabel.setText(weather.getCityName());
                weatherImageView.setImage(image);
                temperatureLabel.setText(String.valueOf(weather.getTemperatureInfo("temp")) + " °C");
                windLabel.setText(String.valueOf("Wind " + weather.getWindInfo("speed")) + "m/s to " + CurrentWeather.degToDir(weather.getWindInfo("deg")));
            }
            else {
                requestCityLabel.setText("Search failed...");
                descriptionLabel.setText("");
                weatherImageView.imageProperty().set(null);
                temperatureLabel.setText("");
            }
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather Program");

        /* Containers */
        border = new BorderPane();
        centerGrid = new GridPane();
        leftGrid = new GridPane();
        header = new HBox();

        /* Border Top */
        headerLabel = new Label("Search weather by City and Country code");
        header.setPrefHeight(50);
        header.setStyle("-fx-background-color: #336699");
        headerLabel.setFont(new Font("Arial", 32));
        headerLabel.setTextFill(Color.web("#F5F5F5"));

        header.getChildren().add(headerLabel);
        header.setAlignment(Pos.CENTER);

        /* Border Left */
        cityLabel = new Label("City name");
        countryCodeLabel = new Label("Country code");
        cityName = new TextField();
        countryCodeName = new TextField();
        weatherRequest = new Button("Search");
        cityName.setPrefWidth(75);
        countryCodeName.setPrefWidth(75);
        cityName.setFont(new Font("Arial", 18));
        cityLabel.setFont(new Font("Arial", 24));
        countryCodeName.setFont(new Font("Arial", 18));
        countryCodeLabel.setFont(new Font("Arial", 24));
        weatherRequest.setFont(new Font("Arial", 22));
        weatherRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                searchWeather();
            }
        });

        leftGrid.add(cityLabel,0,0);
        leftGrid.add(cityName,0,1);
        leftGrid.add(countryCodeLabel,0,2);
        leftGrid.add(countryCodeName,0,3);
        leftGrid.add(weatherRequest,0,4);
        leftGrid.setHalignment(cityLabel, HPos.CENTER);
        leftGrid.setHalignment(weatherRequest, HPos.CENTER);
        leftGrid.setAlignment(Pos.CENTER);
        leftGrid.setVgap(15);
        leftGrid.setHgap(10);

        /* Border Center */ 
        weatherImageView = new ImageView();
        temperatureLabel = new Label("");
        descriptionLabel = new Label("");
        requestCityLabel = new Label("");
        windLabel = new Label("");
        temperatureLabel.setFont(new Font("Arial", 32));
        requestCityLabel.setFont(new Font("Arial", 28));
        descriptionLabel.setFont(new Font("Arial", 22));
        weatherImageView.setFitWidth(80);
        weatherImageView.setFitHeight(80);

        centerGrid.add(requestCityLabel, 1, 0);
        centerGrid.add(descriptionLabel, 1, 1);
        centerGrid.add(weatherImageView, 0, 2);
        centerGrid.add(temperatureLabel, 1, 2);
        centerGrid.add(windLabel, 1, 3);
        centerGrid.setAlignment(Pos.CENTER);

        /* Border */
        border.setCenter(centerGrid);
        border.setLeft(leftGrid);
        border.setTop(header);
        border.setMargin(leftGrid, new Insets(12,12,12,12));
    
        /* Show */
        primaryStage.setScene(new Scene(border, 640, 320));
        primaryStage.show();
    }
}