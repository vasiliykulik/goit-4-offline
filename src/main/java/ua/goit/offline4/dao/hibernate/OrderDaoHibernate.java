package ua.goit.offline4.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.offline4.dao.OrderDao;
import ua.goit.offline4.entity.Order;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Стрела on 23.12.2016.
 */
public class OrderDaoHibernate implements OrderDao {
    // Session, получаем Session c SessionFactory
    // (Если Spring - то можете туда передавать Factory)
    private SessionFactory factory;

//3. Конструктор
    public OrderDaoHibernate(SessionFactory factory) {
        this.factory = factory;
    }
// 6. E вариантах со Spring будет только getSession
    @Override
    public List<Order> getAll() {
        // 7. factory.getCurrentSession();
        //8. Хотим сделать подвызов другого метода другаго Dao
        /*
        9. CurrentSession - Transactional Manager, или Spring подготовит его. ОН closeable - поэтому try
         */
        try (Session session = factory.openSession()){
            return session.createQuery("from Order", Order.class).list(); // 11. Попытается заппить Order 1 к 1, также с помощи критерии

            /**/
            /* 10. return session.createQuery("select o.status, " +
                    " o.orderDate from Order o");// это HQL, это не очень*/

        }
    }
/*//4. Setter ли передаем через set - будет свойство у Hibernate - не надо беспокоится заинжектена ли запись
    public void setFactory (SessionFactory factory) {
        this.factory = factory;
    }

    //5. Supplier - появился в Java8 - по сути сплошной getter - и забыли
    private Supplier<Session> supplier;
    public OrderDaoHibernate(Supplier<Session> supplier){
        this.supplier = supplier;
    }*/


}
