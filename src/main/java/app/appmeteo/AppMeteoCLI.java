package app.appmeteo;



import app.appmeteo.model.APIManager;
import app.appmeteo.model.DataManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AppMeteoCLI {
    public static void main(String[] args) throws IOException {


        System.out.println("Welcome to the weather app");

        System.out.println("Entrez la ville ");
        Scanner scanner = new Scanner(System.in);
        String ville = scanner.nextLine();
        APIManager.HttpResponse(ville);
        DataManager dataManager = new DataManager(new File("src/main/resources/test.json"));
        System.out.println("Altitude :" + dataManager.getLaltitude());
        System.out.println("longitude :" + dataManager.getLongitude());
        System.out.println("Temperature :" + dataManager.getTemp());
        System.out.println("Humidité : " + dataManager.getHumidity());
        System.out.println("Levée du soleil :" + dataManager.getSunrise());
        System.out.println("couché de soleil " + dataManager.getSunset());


        scanner.close();
    }
}
