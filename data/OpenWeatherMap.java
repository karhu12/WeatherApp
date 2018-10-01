package data;
import data.weather;

public class OpenWeatherMap {
    private String name;
    private String temp;
    private List<weather> weather = new List<weather>();
    public String getTemp() {
        return temp;
    }
}