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
                    return w.main;
                case "description":
                    return w.description;
                case "icon":
                    return w.icon;
                default:
                    return "";
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

    public static String degToDir(double degrees) {
        if (degrees > 337.5 && degrees <= 360 || degrees >= 0 && degrees <= 22.5) {
            return "North";
        }
        else if (degrees > 22.5 && degrees <= 67.5) {
            return "Northeast";
        }
        else if (degrees > 67.5 && degrees <= 112.5) {
            return "East";
        }
        else if (degrees > 112.5 && degrees <= 157.5) {
            return "Southeast";
        }
        else if (degrees > 157.5 && degrees <= 202.5) {
            return "South";
        }
        else if (degrees > 202.5 && degrees <= 247.5) {
            return "Southwest";
        }
        else if (degrees > 247.5 && degrees <= 292.5) {
            return "West";
        }
        else if (degrees > 292.5 && degrees <= 337.5) {
            return "Northwest";
        }
        return "";
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
        public double deg;
    }
}