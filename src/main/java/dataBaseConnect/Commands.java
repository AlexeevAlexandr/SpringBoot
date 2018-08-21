package dataBaseConnect;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Commands {
    private Transaction transaction = null;
    public void add(String firstName, String lastName, String email) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            DataBaseConnect dataBaseConnect = new DataBaseConnect(firstName, lastName, email);
            session.save(dataBaseConnect);
            transaction.commit();
        } catch(Exception e){
            if(transaction != null){transaction.rollback();}
            e.getMessage();}
    }
}
