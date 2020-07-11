package pl.camp.it;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.camp.it.model.Customer;

import java.util.List;


public class App {
    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();




    public static void main(String[] args) {
        System.out.println("Hibernate !!");

        Customer customer = new Customer();
        customer.setName("Mateusz");
        customer.setSurname("Bereda");
        customer.setPesel(87050614441L);

        saveCustomerToDataBase(customer);

        Customer customer2 = new Customer();
        customer2.setName("Jan");
        customer2.setSurname("Kowlaski");
        customer2.setPesel(87010454987L);

        saveCustomerToDataBase(customer2);


        System.out.println(customer2);

        Customer customer3 = new Customer();
        customer3.setId(2);
        customer3.setName("Piotr");
        customer3.setSurname("Sołtysek");

        //deleteCustomer(customer3);

        //System.out.println(getCustomerById(2));

        System.out.println(getAllCustomers());



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
        return customer;
    }

    public static List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.camp.it.model.Customer");
        return query.getResultList();
    }
}
