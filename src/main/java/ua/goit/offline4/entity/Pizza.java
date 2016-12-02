package ua.goit.offline4.entity;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Pizza.
 *
 * @author Andrey Minov
 * @since 2016.12
 */
public class Pizza {

    private long id;
    private String name;
    private BigDecimal prize;
    private Collection<ComponentAmount> components;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public Collection<ComponentAmount> getComponents() {
        return components;
    }

    public void setComponents(Collection<ComponentAmount> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", name='" + name + '\'' + ", prize=" + prize + ", components=" + components + '}';
    }
}
