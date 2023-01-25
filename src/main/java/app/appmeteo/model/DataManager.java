package app.appmeteo.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class DataManager {
    JsonObject obj;
    private JsonParser jsonParse;
    private JsonElement coord;
    private String longitude;
    private String laltitude;
    private JsonElement weather;
    private String weatherid;
    private String main;
    private String description;
    private String icon;
    private JsonElement visibility;
    private JsonElement base;
    private JsonElement secondmain;
    private String temp;
    private String feelslike;
    private String tempmin;
    private String tempmax;
    private String pressure;
    private String humidity;
    private JsonElement wind;
    private String speed;
    private String deg;
    private String gust;
    private JsonElement dt;
    private JsonElement clouds;
    private String all;
    private JsonElement sys;
    private JsonElement timezone;
    private JsonElement id;
    private JsonElement name;
    private JsonElement cod;
    private String type;
    private String sysid;
    private String country;
    private String sunrise;
    private String sunset;
    private String cityName;
    private ArrayList favoris = new ArrayList<String>();

 

    public DataManager(File file) {
        this.jsonParse = new JsonParser();
        try (FileReader reader = new FileReader(file)) {
            this.obj = jsonParse.parse(reader).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.exists()) {
            coord = getJsonElement("coord");
            longitude = getElementOfJsonElement("coord", "lon");
            laltitude = getElementOfJsonElement("coord", "lat");
            weather = getJsonElement("weather");
            weatherid = getWeatherElements("id");
            main = getWeatherElements("main");
            description = getWeatherElements("description");
            icon = getWeatherElements("icon");
            visibility = getJsonElement("visibility");
            wind = getJsonElement("wind");
            speed = getElementOfJsonElement("wind", "speed");
            deg = getElementOfJsonElement("wind", "deg");
            //gust = getElementOfJsonElement("wind", "gust");
            base = getJsonElement("base");
            secondmain = getJsonElement("main");
            temp = getElementOfJsonElement("main", "temp");
            feelslike = getElementOfJsonElement("main", "feels_like");
            tempmin = getElementOfJsonElement("main", "temp_min");
            tempmax = getElementOfJsonElement("main", "temp_max");
            pressure = getElementOfJsonElement("main", "pressure");
            humidity = getElementOfJsonElement("main", "humidity") + "%";
            clouds = getJsonElement("clouds");
            all = getElementOfJsonElement("clouds", "all");
            dt = getJsonElement("dt");
            timezone = getJsonElement("timezone");
            id = getJsonElement("id");
            name = getJsonElement("name");
            cod = getJsonElement("cod");
            sys = getJsonElement("sys");
            //type = getElementOfJsonElement("sys", "type");
            country = getElementOfJsonElement("sys", "country");
            cityName = getJsonElement("name").toString();
            int sunriseTimeStamp = Integer.parseInt(getElementOfJsonElement("sys", "sunrise"));
            Date time = new Date((long) sunriseTimeStamp * 1000);
            sunrise = time.toString().split(" ")[3];
            int sunsetTimeStamp = Integer.parseInt(getElementOfJsonElement("sys", "sunset"));
            Date time2 = new Date((long) sunsetTimeStamp * 1000);
            sunset = time2.toString().split(" ")[3];
        }
    }

    public DataManager() {
    }


    //avoir les elements de weather qui est une list
    public String getWeatherElements(String description) {
        try {
            String element = weather.getAsJsonArray().get(0).getAsJsonObject().get(description).getAsString();
            return element;
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("hna tnaket");
            return null;
        }
    }

    //avoir (l'en tete ) Jasonelement
    public JsonElement getJsonElement(String description) {
        JsonElement element = obj.getAsJsonObject().get(description);
        return element;
    }

    //avoir l'element de la liste de Jason element
    public String getElementOfJsonElement(String description, String secondescription) {
            try {
                return getJsonElement(description).getAsJsonObject().get(secondescription).getAsString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }


    public String getCityName() { return cityName.replaceAll("\\u0022", ""); }

    public String getLongitude() {
        return longitude;
    }

    public String getLaltitude() {
        return laltitude;
    }

    public String getWeatherid() {
        return weatherid;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getTemp() {
        return temp;
    }

    public String getFeelslike() {
        return feelslike;
    }

    public String getTempmin() {
        return tempmin;
    }

    public String getTempmax() {
        return tempmax;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSpeed() {
        return speed;
    }

    public String getDeg() {
        return deg;
    }

    public String getGust() {
        return gust;
    }

    public String getAll() {
        return all;
    }

    public String getType() {
        return type;
    }

    public String getSysid() {
        return sysid;
    }

    public String getCountry() {
        return country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void addToFavorites(String ville){
        favoris.add(ville);
    }
    public void removeFromFavorites(String ville){
        favoris.remove(ville);
    }
}



