package networking;
import networking.HTML;

public class API {
    public static final String apiKey = "&appid=b0d127b76f3ac4cc264091bc7fdf1cdc";

    public static String getCurrentWeather(String city, String countryCode) {
        String newQuery = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + countryCode + API.apiKey;
        try {
            return HTML.getRequest(newQuery);
        }
        catch (Exception e) {
            return "Exception caught while getting a request";
        }
    }
}