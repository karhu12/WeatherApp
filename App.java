/****************************************
 * Weather Program
 * 
 * Fetches weather from wanted city using openweathermap API (https://openweathermap.org)
 * HTML done by using the java.net library
 * Request parsed using JSON library
 * 
*/

import networking.*;
import ui.*;
import com.fasterxml.jackson.databind.ObjectMapper;
//TODO: Fix library importing

public final class App {
    public static void main(String[] args) {
        //Ui.launchUi(args);
        String q = API.getCurrentWeather("Oulu", "Fi");
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(q, User.class);
        System.out.println(user);
    }
}
