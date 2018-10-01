/****************************************
 * Weather Program
 * 
 * Fetches weather from wanted city using openweathermap API (https://openweathermap.org)
 * HTML done by using the java.net library
 * Request parsed using JSON library called GSON
 * 
*/

/* Internal clases */
import networking.*;
import ui.*;
import data.*;
import java.util.*;
import java.io.*;

/* Libs */
import com.google.gson.*;

public final class App {
    public static void main(String[] args) {
        //Ui.launchUi(args);
        String q = API.getCurrentWeather("Oulu", "Fi");
        JsonObject jObj = new Gson().fromJson(q, JsonObject.class);
        System.out.println(jObj.get("main").getAsJsonObject().get("temp").getAsString());
    }
}
