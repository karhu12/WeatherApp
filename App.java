/****************************************
 * Weather Program
 * 
 * Fetches weather from wanted city using openweathermap API (https://openweathermap.org)
 * HTML done by using the java.net library
 * Request parsed using JSON library
 * 
*/
/* Internal clases */
import networking.*;
import ui.*;
import data.*;
import java.util.*;
import java.io.*;

import com.google.gson.*;

public final class App {
    public static void main(String[] args) {
        //Ui.launchUi(args);
        String q = API.getCurrentWeather("Oulu", "Fi");
        Gson g = new Gson();
        OpenWeatherMap map =
    }
}
