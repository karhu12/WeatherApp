package data;
import java.util.*;
import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    @SerializedName("name")
    private String cityName;
    private String visibility;

    @SerializedName("weather")
    private ArrayList<WeatherInfo> weatherInfo;

    @SerializedName("main")
    private TemperatureInfo temperatureInfo;

    @SerializedName("coord")
    private CoordinateInfo coordinateInfo;

    @SerializedName("wind")
    private WindInfo windInfo;

    /* Functions */
    public double getTemperatureInfo(String fieldName) {
        switch (fieldName) {
            case "temp":
                return this.temperatureInfo.temp;
            case "pressure":
                return this.temperatureInfo.pressure;
            case "humidity":
                return this.temperatureInfo.humidity;
            case "temp_min":
                return this.temperatureInfo.temp_min;
            case "temp_max":
                return this.temperatureInfo.temp_max;
            default:
                return 0.0;
        }
    }

    public String getWeatherInfo(String fieldName) {
        String returnStr = new String();
        for (WeatherInfo w : this.weatherInfo) {
            switch (fieldName) {
                case "main":
                    returnStr += w.main;
                case "description":
                    returnStr += w.description;
                case "icon":
                    returnStr += w.icon;
                default:
                    returnStr += "";
            }
        }
        return returnStr;
    }

    public Double getCoordinateInfo(String fieldName) {
        switch (fieldName) {
            case "lat":
                return this.coordinateInfo.lat;
            case "lon":
                return this.coordinateInfo.lon;
            default:
                return 0.0;
        }
    }

    public Double getWindInfo(String fieldName) {
        switch (fieldName) {
            case "speed":
                return this.windInfo.speed;
            case "deg":
                return (double)this.windInfo.deg;
            default:
                return 0.0;
        }
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getVisiblity() {
        return this.visibility;
    }




    /* Storage classes for JSON */
    public class WeatherInfo {
        public String main;
        public String description;
        public String icon;
    };

    public class TemperatureInfo {
        public double temp;
        public double pressure;
        public double humidity;
        public double temp_min;
        public double temp_max;
    }

    public class CoordinateInfo {
        public double lon;
        public double lat;
    }

    public class WindInfo {
        public double speed;
        public int deg;
    }
}