package org.example.model.persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateContext<T> {

    private static SessionFactory sessionFactory;
    private final Class<T> clazz;

    public HibernateContext(Class<T> clazz) {
        this(clazz, false);
    }
    
    public HibernateContext(Class<T> clazz, boolean test) {
        this.clazz = clazz;
        sessionFactory = buildSessionFactory(test ? "test.cfg.xml" : "hibernate.cfg.xml");
    }

    private static SessionFactory buildSessionFactory(String res) {
        try {
            return new Configuration()
                    .configure(res)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void dispose() {
        getSessionFactory().close();
    }

    public void save(T t) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
        }
    }

    public void update(T t) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
        }
    }

    public T load(long id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.find(clazz, id);
        }
    }

    public List<T> load() {
        try (Session session = getSessionFactory().openSession()) {
            return new ArrayList<>(session.createQuery("from " + clazz.getSimpleName(), clazz).list());
        }
    }
}

