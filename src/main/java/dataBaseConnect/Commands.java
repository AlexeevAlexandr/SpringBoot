package dataBaseConnect;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Commands {
    private Transaction transaction = null;

    public void add(String firstName, String lastName, String email, String date) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            DataBaseConnect dataBaseConnect = new DataBaseConnect(firstName, lastName, email, date);
            session.save(dataBaseConnect);
            transaction.commit();
        } catch(Exception e){
            if(transaction != null){transaction.rollback();}
            e.getMessage();}
    }

    public List list() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM DataBaseConnect").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public void clear() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
             transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE data_base").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.getMessage();
        }
    }
}
