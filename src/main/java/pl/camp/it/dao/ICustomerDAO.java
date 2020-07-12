package pl.camp.it.dao;

import pl.camp.it.model.Customer;
import java.util.List;

public interface ICustomerDAO {
    void saveCustomerToDataBase(Customer customer);
    void deleteCustomer(Customer customer);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();

}