package pl.camp.it.services;

import com.github.javafaker.Faker;

public class FakerDataService implements IRandomDataService {
    Faker faker = new Faker();

    @Override
    public String generateCity() {
        return faker.address().city();
    }

    @Override
    public String generateStreet() {
        return faker.address().streetName();
    }

    @Override
    public String generateInvoiceSingature() {
        return faker.number().digit();
    }

    @Override
    public String generateProductName() {
        return faker.beer().name();
    }

    @Override
    public double generateProductPrice() {
        return faker.number().randomDouble(2, 1, 20);
    }
}