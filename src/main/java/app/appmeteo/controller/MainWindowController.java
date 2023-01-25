package app.appmeteo.controller;

import app.appmeteo.model.*;
import app.appmeteo.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    public MainWindowController(DataManager dataManager, ViewFactory viewFactory, String fxmlName) {
        super(dataManager, viewFactory, fxmlName);
    }

    @FXML
    private Label nomVille;


    @FXML
    private Label date;

    @FXML
    private Label heure;

    @FXML
    private Label temperature;


    @FXML
    private Label temperatureMin;

    @FXML
    private Label temperatureMax;

    @FXML
    private Label valeurVent;

    @FXML
    private Label valeurHumidite;

    @FXML
    private Label heureLever;

    @FXML
    private Label heureCoucher;

    @FXML
    private ChoiceBox<String> listeFavoris;

    @FXML
    private TextField chercherVille;
    @FXML
    private ImageView imagemeteo;
    @FXML
    private Label tempdemain;
    @FXML
    private ImageView imagedemain;
    @FXML
    private Label demain;
    @FXML
    private Label tempvendredi;
    @FXML
    private ImageView imagevendredi;
    @FXML
    private Label vendredi;
    @FXML
    private Label tempsamedi;
    @FXML
    private ImageView imagesamedi;
    @FXML
    private Label samedi;
    @FXML
    private Label tempdim;
    @FXML
    private ImageView imagedim;
    @FXML
    private Label dim;
    @FXML
    private Label templundi;
    @FXML
    private ImageView imagelundi;
    @FXML
    private Label lundi;

    @FXML
    private Label tempmardi;
    @FXML
    private ImageView imagemardi;
    @FXML
    private Label mardi;

    @FXML
    void addToFavorites() throws IOException {
        boolean alreadyExisting = false;
        for (String ville : listeFavoris.getItems()) {
            if (ville.equals(dataManager.getCityName())) {
                alreadyExisting = true;
            }
        }
        if (!alreadyExisting) {
        listeFavoris.getItems().add(dataManager.getCityName());
        FileOperation.addCitytoFavorites(dataManager.getCityName());
        dataManager.addToFavorites(dataManager.getCityName());}
    }

    @FXML
    void deleteFromFavorites() throws IOException {
        boolean alreadyExisting = false;
        for (String ville: listeFavoris.getItems()){
            if (ville.equals(dataManager.getCityName())){
                alreadyExisting = true;
            }
        }
        if (alreadyExisting) {
        dataManager.removeFromFavorites(dataManager.getCityName());
        listeFavoris.getItems().remove(dataManager.getCityName());
        FileOperation.deleteCityfromFavorites(dataManager.getCityName());}
    }

    @FXML
    void searchCity() throws IOException {

        APIManager.HttpResponse(chercherVille.getText().toLowerCase());
        dataManager = new DataManager(new File("src/main/resources/test.json"));
        APiManagerDaily.HttpResponse(dataManager.getLongitude(), dataManager.getLaltitude());
        DataManagerDaily dataManagerDaily = new DataManagerDaily(new File("src/main/resources/Daily.json"));
        Date dateD = new Date(System.currentTimeMillis());
        Date dateH = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        date.setText(dateFormatter.format(dateD));
        heure.setText(timeFormatter.format(dateH));
        nomVille.setText(chercherVille.getText().toLowerCase());
        DisplayInformation(dataManager);
    }
    @FXML
    void favoriteSelected() throws IOException {
        String city =  listeFavoris.getValue();
        System.out.println(city);
        APIManager.HttpResponse(city);

        dataManager = new DataManager(new File("src/main/resources/test.json"));
        APiManagerDaily.HttpResponse(dataManager.getLongitude(), dataManager.getLaltitude());
        Date dateD = new Date(System.currentTimeMillis());
        Date dateH = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter= new SimpleDateFormat("HH:mm");
        date.setText(dateFormatter.format(dateD));
        heure.setText(timeFormatter.format(dateH));
        nomVille.setText(city);
        DisplayInformation(dataManager);
    }

    private void initialiazeFavorites() {
        try {
            for (String city: FileOperation.getFavorites().getItems()){
                listeFavoris.getItems().add(city);
                dataManager.addToFavorites(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialiazeFavorites();

        listeFavoris.setItems(FXCollections.observableArrayList(listeFavoris.getItems()));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        Date dateD = new Date(System.currentTimeMillis());
        Date dateH = new Date(System.currentTimeMillis());
        date.setText(dateFormatter.format(dateD));
        heure.setText(timeFormatter.format(dateH));
        DisplayInformation(dataManager);

    }

    public void DisplayDayofweek() {
        Calendar maintenant = Calendar.getInstance();
        String[] nomDesJours = new String[]{"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        int i = maintenant.get(Calendar.DAY_OF_WEEK);
        i = i - 1;
        System.out.println(i);
        if (i + 1 == 7) i = -1;
        demain.setText(nomDesJours[i + 1]);
        i = i + 1;
        if (i + 1 == 7) i = -1;
        vendredi.setText(nomDesJours[i + 1]);
        i = i + 1;
        if (i + 1 == 7) i = -1;
        samedi.setText(nomDesJours[i + 1]);
        i = i + 1;
        if (i + 1 == 7) i = -1;
        dim.setText(nomDesJours[i + 1]);
        i = i + 1;
        if (i + 1 == 7) i = -1;
        lundi.setText(nomDesJours[i + 1]);
        i = i + 1;
        if (i + 1 == 7) i = -1;
        mardi.setText(nomDesJours[i + 1]);
        i = i + 1;
    }

    public void DisplayInformation(DataManager dataManager) {
        temperature.setText(dataManager.getTemp());
        temperatureMin.setText(dataManager.getTempmin());
        temperatureMax.setText(dataManager.getTempmax());
        valeurVent.setText(dataManager.getSpeed());
        valeurHumidite.setText(dataManager.getHumidity());
        heureLever.setText(dataManager.getSunrise());
        heureCoucher.setText(dataManager.getSunset());
        String s = "http://openweathermap.org/img/wn/" + dataManager.getIcon() + "@2x.png";
        imagemeteo.setImage(new Image(s));

        DataManagerDaily dataManagerDaily = new DataManagerDaily(new File("src/main/resources/Daily.json"));
        s = dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(1).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        String icondemain = "http://openweathermap.org/img/wn/" + s + "@2x.png";
        imagedemain.setImage(new Image(icondemain));
        tempdemain.setText(String.valueOf(dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(1).getAsJsonObject().get("temp").getAsJsonObject().get("day")));

        s = dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(2).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        icondemain = "http://openweathermap.org/img/wn/" + s + "@2x.png";
        imagevendredi.setImage(new Image(icondemain));
        tempvendredi.setText(String.valueOf(dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(2).getAsJsonObject().get("temp").getAsJsonObject().get("day")));

        s = dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(3).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        icondemain = "http://openweathermap.org/img/wn/" + s + "@2x.png";
        imagesamedi.setImage(new Image(icondemain));
        tempsamedi.setText(String.valueOf(dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(3).getAsJsonObject().get("temp").getAsJsonObject().get("day")));

        s = dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(4).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        icondemain = "http://openweathermap.org/img/wn/" + s + "@2x.png";
        imagedim.setImage(new Image(icondemain));
        tempdim.setText(String.valueOf(dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(4).getAsJsonObject().get("temp").getAsJsonObject().get("day")));

        s = dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(5).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        icondemain = "http://openweathermap.org/img/wn/" + s + "@2x.png";
        imagelundi.setImage(new Image(icondemain));
        templundi.setText(String.valueOf(dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(5).getAsJsonObject().get("temp").getAsJsonObject().get("day")));

        s = dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(6).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        icondemain = "http://openweathermap.org/img/wn/" + s + "@2x.png";
        imagemardi.setImage(new Image(icondemain));
        tempmardi.setText(String.valueOf(dataManagerDaily.getJsonElement("daily").getAsJsonArray().get(6).getAsJsonObject().get("temp").getAsJsonObject().get("day")));

        DisplayDayofweek();
    }
}
