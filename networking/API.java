package networking;
import java.io.*;
import java.lang.*;
import networking.HTML;

/* Libs */
import com.google.gson.Gson;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;


public class API {
    private static final String apiKey = "&appid=b0d127b76f3ac4cc264091bc7fdf1cdc";

    /* Returns a JSON object as String from the OpenWeatherMap API based on city and country code */
    public static String getCurrentWeather(String city, String countryCode) {
        String newQuery = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + countryCode + "&units=metric" + API.apiKey;
        try {
            return HTML.getRequest(newQuery);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* Overloaded version only uses city as parameter */
    public static String getCurrentWeather(String city) {
        String newQuery = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric" + API.apiKey;
        try {
            return HTML.getRequest(newQuery);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* Verifies JSON string by creating a JsonReader object from it and calling each of the
    characters value and see if it throw exceptions. If exceptions are not thrown the JSON string
    is valid */
    public static boolean isJsonValid(final String json) throws IOException {
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        try {
            JsonToken token;
            loop:
            while ((token = jsonReader.peek()) != JsonToken.END_DOCUMENT && token != null) {
                switch (token) {
                case BEGIN_ARRAY:
                    jsonReader.beginArray();
                    break;
                case END_ARRAY:
                    jsonReader.endArray();
                    break;
                case BEGIN_OBJECT:
                    jsonReader.beginObject();
                    break;
                case END_OBJECT:
                    jsonReader.endObject();
                    break;
                case NAME:
                    jsonReader.nextName();
                    break;
                case STRING:
                case NUMBER:
                case BOOLEAN:
                case NULL:
                    jsonReader.skipValue();
                    break;
                case END_DOCUMENT:
                    break loop;
                default:
                    throw new AssertionError(token);
                }
            }
            return true;
        } catch ( Exception e) {
            return false;
        }
    }
}