package pl.camp.it.services;

public interface IRandomDataService {
    String generateCity();
    String generateStreet();
    String generateInvoiceSingature();
    String generateProductName();
    double generateProductPrice();
}