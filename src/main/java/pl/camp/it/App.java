package pl.camp.it;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.camp.it.dao.CustomerDAO;
import pl.camp.it.dao.ProductDAO;
import pl.camp.it.model.Address;
import pl.camp.it.model.Customer;
import pl.camp.it.model.Invoice;
import pl.camp.it.model.Product;
import java.util.Date;


public class App {
    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        System.out.println("Hibernate !!");

        Customer customer = new Customer();
        customer.setName("Mateusz");
        customer.setSurname("Bereda");
        customer.setPesel(87050614441L);

        Address address = new Address();
        address.setCity("Kraków");
        address.setStreet("Ogrodowa");
        customer.setAddress(address);

        Invoice invoice = new Invoice();
        invoice.setDate(new Date());
        invoice.setSignature("FV/1/7/2020");
        customer.getInvoices().add(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setDate(new Date());
        invoice2.setSignature("FV/2/7/2020");
        customer.getInvoices().add(invoice2);

        Product product = new Product();
        product.setPrice(100.0);
        product.setName("Mikser");
        product.getCustomers().add(customer);
        customer.getProducts().add(product);

        CustomerDAO.saveCustomerToDataBase(customer);

        Customer customer2 = new Customer();
        customer2.setName("Jan");
        customer2.setSurname("Kowlaski");
        customer2.setPesel(87010454987L);

        CustomerDAO.saveCustomerToDataBase(customer2);

        System.out.println("Customer:");
        System.out.println(customer);
        System.out.println("Customer2:");
        System.out.println(customer2);

        System.out.println(ProductDAO.getProductById(1));

    }
}
