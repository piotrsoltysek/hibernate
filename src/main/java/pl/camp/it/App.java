package pl.camp.it;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.camp.it.model.Address;
import pl.camp.it.model.Customer;
import pl.camp.it.model.Invoice;
import pl.camp.it.model.Product;

import java.util.Date;
import java.util.List;


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

        saveCustomerToDataBase(customer);

        Invoice invoice2 = new Invoice();
        invoice2.setDate(new Date());
        invoice2.setSignature("FV/2/7/2020");
        customer.getInvoices().add(invoice2);

        Product product = new Product();
        product.setPrice(100.0);
        product.setName("Mikser");
        product.getCustomers().add(customer);
        customer.getProducts().add(product);

        saveCustomerToDataBase(customer);

        Customer customer2 = new Customer();
        customer2.setName("Jan");
        customer2.setSurname("Kowlaski");
        customer2.setPesel(87010454987L);

        saveCustomerToDataBase(customer2);


        System.out.println("Customer:");
        System.out.println(customer);
        System.out.println("Customer2:");
        System.out.println(customer2);

        System.out.println(getProductById(1));


    }

    public static void saveCustomerToDataBase(Customer customer) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(customer);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteCustomer(Customer customer) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static Customer getCustomerById(int id) {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.camp.it.model.Customer WHERE id = " + id);
        Customer customer = query.getSingleResult();
        session.close();
        return customer;
    }

    public static List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.camp.it.model.Customer");
        List<Customer> customerList = query.getResultList();
        session.close();
        return customerList;
    }

    public static Product getProductById(int id) {
        Session session = sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.camp.it.model.Product WHERE id = :identyfikator");
        query.setParameter("identyfikator", id);
        Product result = query.getSingleResult();
        session.close();
        return result;
    }
}
