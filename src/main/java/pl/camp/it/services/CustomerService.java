package pl.camp.it.services;

import pl.camp.it.dao.CustomerDAO;
import pl.camp.it.model.Address;
import pl.camp.it.model.Customer;
import pl.camp.it.model.Invoice;
import pl.camp.it.model.Product;

import java.util.Date;

public class CustomerService {

    public static void generateAndSaveCustomer(String name, String surname, String pesel) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPesel(Long.parseLong(pesel));

        Address address = new Address();
        address.setCity(RandomDataService.generateCity());
        address.setStreet(RandomDataService.generateStreet());
        customer.setAddress(address);

        Invoice invoice = new Invoice();
        invoice.setDate(new Date());
        invoice.setSignature(RandomDataService.generateInvoiceSingature());
        customer.getInvoices().add(invoice);

        Product product = new Product();
        product.setName(RandomDataService.generateProductName());
        product.setPrice(RandomDataService.generateProductPrice());
        product.getCustomers().add(customer);
        customer.getProducts().add(product);

        CustomerDAO.saveCustomerToDataBase(customer);

    }
}