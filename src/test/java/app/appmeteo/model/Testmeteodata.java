package app.appmeteo.model;


import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testmeteodata  {


    DataManager meteomarseille = new DataManager(new File("src/main/resources/weather_marseille.json"));

    @Test
    void testGetelementOfJson () {
        assertEquals("272.87", meteomarseille.getElementOfJsonElement("main", "feels_like"));
    }
    @Test
    void testGetweatherElements () {
        assertEquals("4.92", meteomarseille.getElementOfJsonElement("wind", "speed"));
    }


}
