package pl.camp.it.services;

import pl.camp.it.dao.CustomerDAO;
import pl.camp.it.dao.ICustomerDAO;
import pl.camp.it.model.Address;
import pl.camp.it.model.Customer;
import pl.camp.it.model.Invoice;
import pl.camp.it.model.Product;

import java.util.Date;

public class CustomerService implements ICustomerService {
    static IRandomDataService randomDataService = new RandomDataService();
    static ICustomerDAO customerDAO = new CustomerDAO();

    public void generateAndSaveCustomer(String name, String surname, String pesel) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPesel(Long.parseLong(pesel));

        Address address = new Address();
        address.setCity(randomDataService.generateCity());
        address.setStreet(randomDataService.generateStreet());
        customer.setAddress(address);

        Invoice invoice = new Invoice();
        invoice.setDate(new Date());
        invoice.setSignature(randomDataService.generateInvoiceSingature());
        customer.getInvoices().add(invoice);

        Product product = new Product();
        product.setName(randomDataService.generateProductName());
        product.setPrice(randomDataService.generateProductPrice());
        product.getCustomers().add(customer);
        customer.getProducts().add(product);

        customerDAO.saveCustomerToDataBase(customer);

    }
}