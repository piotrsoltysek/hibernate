package pl.camp.it.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.camp.it.App;
import pl.camp.it.model.Customer;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    @Override
    public void saveCustomerToDataBase(Customer customer) {
        Session session = App.sessionFactory.openSession();

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

    @Override
    public void deleteCustomer(Customer customer) {
        Session session = App.sessionFactory.openSession();

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

    @Override
    public Customer getCustomerById(int id) {
        Session session = App.sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.camp.it.model.Customer WHERE id = " + id);
        Customer customer = query.getSingleResult();
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        Session session = App.sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.camp.it.model.Customer");
        List<Customer> customerList = query.getResultList();
        session.close();
        return customerList;
    }
}