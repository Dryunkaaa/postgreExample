package storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AbstractDAO<T> {

    private final static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private Class<T> tClass;

    public AbstractDAO(Class<T> tClass) {
        this.tClass = tClass;
    }

    public void save(T object) {
        Session session = openTransactSession();
        session.save(object);
        closeTransactSession(session);
    }

    public void delete(T object) {
        Session session = openTransactSession();
        session.delete(object);
        closeTransactSession(session);
    }

    public void update(T object) {
        Session session = openTransactSession();
        session.update(object);
        session.close();
    }

    public T getDaoById(long id) {
        Session session = sessionFactory.openSession();
        String query = "from " + tClass.getSimpleName() + " where id = " + id;
        T result = (T) session.createQuery(query);
        session.close();
        return result;
    }

    public List<T> getAll(){
        Session session = sessionFactory.openSession();
        String query = "from " + tClass.getSimpleName();
        List<T> objects = session.createQuery(query, tClass).list();
        session.close();
        return objects;
    }

    public Session openTransactSession() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public void closeTransactSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}
