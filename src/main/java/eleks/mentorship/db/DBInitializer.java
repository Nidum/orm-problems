package eleks.mentorship.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBInitializer {
    private final SessionFactory sessionFactory;

    public DBInitializer() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        getSessionFactory().close();
    }
}
