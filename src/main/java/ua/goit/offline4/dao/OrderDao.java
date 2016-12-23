package ua.goit.offline4.dao;

import ua.goit.offline4.entity.Order;

import java.util.List;

/**
 * Created by Стрела on 23.12.2016.
 */
public interface OrderDao {
    List<Order> getAll();
}
