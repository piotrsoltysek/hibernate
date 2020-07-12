package pl.camp.it.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.camp.it.App;
import pl.camp.it.model.Product;

public class ProductDAO {

    public static Product getProductById(int id) {
        Session session = App.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.camp.it.model.Product WHERE id = :identyfikator");
        query.setParameter("identyfikator", id);
        Product result = query.getSingleResult();
        session.close();
        return result;
    }
}
