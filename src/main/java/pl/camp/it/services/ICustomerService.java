package pl.camp.it.services;

public interface ICustomerService {
    void generateAndSaveCustomer(String name, String surname, String pesel);
}