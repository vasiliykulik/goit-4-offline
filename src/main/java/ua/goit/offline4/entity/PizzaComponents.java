package ua.goit.offline4.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ComponentAmount.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
@Entity
@Table(name = "pizza_components", schema = "pizzeria")
public class PizzaComponents
    implements Serializable {

    @Id
    @ManyToOne
    private Component component;
    @Id
    @ManyToOne
    private Pizza pizza;

    private BigDecimal amount;

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
