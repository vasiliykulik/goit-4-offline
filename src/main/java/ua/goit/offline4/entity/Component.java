package ua.goit.offline4.entity;

import java.math.BigDecimal;

/**
 * Component.
 *
 * @author Andrey Minov
 * @since 2016.12
 */
public class Component {

    private long id;
    private String name;
    private BigDecimal prize;

    public Component(long id, String name, BigDecimal prize) {
        this.id = id;
        this.name = name;
        this.prize = prize;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Component component = (Component) o;

        if (id != component.id) {
            return false;
        }
        if (name != null ? !name.equals(component.name) : component.name != null) {
            return false;
        }
        return prize != null ? prize.equals(component.prize) : component.prize == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (prize != null ? prize.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Component{" + "id=" + id + ", name='" + name + '\'' + ", prize=" + prize + '}';
    }
}
