package ua.goit.offline4.entity;

import java.math.BigDecimal;

/**
 * ComponentAmount.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class ComponentAmount {

    private Component component;
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

    @Override
    public String toString() {
        return "ComponentAmount{" + "component=" + component + ", amount=" + amount + '}';
    }
}
