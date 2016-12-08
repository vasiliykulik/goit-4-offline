package ua.goit.offline4.dao.hibernate;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ua.goit.offline4.dao.ComponentDao;
import ua.goit.offline4.entity.Component;

/**
 * ComponentsDaoHibernate.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class ComponentsDaoHibernate
    implements ComponentDao {

    private SessionFactory sessionFactory;

    public ComponentsDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Component get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Component.class, id);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Component> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return (List<Component>) session.createQuery("from Component").list();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Component> gtByPrize(BigDecimal prize) {
        try (Session session = sessionFactory.openSession()) {
            return (List<Component>) session.createQuery("from Component where prize > :prize").setParameter("prize", prize).list();
        }
    }

    @Override
    public Component add(String name, BigDecimal prize) {
        try (Session session = sessionFactory.openSession()) {
            Component component = new Component();
            component.setName(name);
            component.setPrize(prize);
            session.save(component);
            return component;
        }
    }

    @Override
    public boolean update(Component component) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(component);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public boolean delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Component component = session.load(Component.class, id);
                session.delete(component);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }

        }
    }
}
