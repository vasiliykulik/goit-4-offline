package ua.goit.offline4.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.goit.offline4.dao.hibernate.OrderDaoHibernate;
import ua.goit.offline4.entity.Order;
import ua.goit.offline4.entity.Pizza;

/**
 * Created by Стрела on 23.12.2016.
 */
public class OrderDaoTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Pizza.class)
                .configure("hibernate.cfg.xml");
        // 12. Мы посмотрели что sessionFactory не сложно руками сделать
        // 15. Мусор - убрать - можно изменив уровень логирования
                try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
                    OrderDaoHibernate dao = new OrderDaoHibernate(sessionFactory);
                    System.out.println(dao.getAll());
                }
    }
}
