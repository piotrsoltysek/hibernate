package pl.camp.it.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FakerDataService implements IRandomDataService {

    @Override
    public String generateCity() {
        List<String> cities = Arrays.asList("Kraków", "Warszawa", "Poznań",
                "Katowice", "Gliwice", "New York", "Paris", "Berlin", "London", "Tokyo");
        return cities.get(new Random().nextInt(cities.size()));
    }

    @Override
    public String generateStreet() {
        List<String> streets = Arrays.asList("Polna", "Ogrodowa", "Malinowa",
                "Szewska", "Topolowa", "Floriańska", "Wall Street", "Krzywa", "Prosta");
        return streets.get(new Random().nextInt(streets.size()));
    }

    @Override
    public String generateInvoiceSingature() {
        Random random = new Random();
        int no = random.nextInt(10) + 1;
        int month = random.nextInt(12) + 1;
        int year = random.nextInt(6) + 2015;

        return new StringBuilder().append("FV/").append(no).append("/")
                .append(month).append("/").append(year).toString();
    }

    @Override
    public String generateProductName() {
        List<String> names = Arrays.asList("Woda", "Wino", "Kiełbasa", "Piwo", "Salceson", "Bułka", "Czipsy", "Cola");
        return names.get(new Random().nextInt(names.size()));
    }

    @Override
    public double generateProductPrice() {
        int generatedNumber = new Random().nextInt(1901) + 100;
        return ((double) generatedNumber) / 100.00;
    }
}