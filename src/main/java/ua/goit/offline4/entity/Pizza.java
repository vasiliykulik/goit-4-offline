package ua.goit.offline4.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pizza.
 *
 * @author Andrey Minov
 * @since 2016.12
 */
@Entity
@Table(name = "pizza", schema = "pizzeria")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal prize;

    // 20. (В одной пицее много компонент,
    // по сути мы разворачиваем 1кN, если перемножаем то получаем N к N )
    @OneToMany (mappedBy = "pizza"),
    cascade = CascadeType.All,
    orphanRemoval = true,
    fetch = FetchType



    @OneToMany(mappedBy = "pizza", // Bidirectional One to many
            cascade = CascadeType.ALL, // 16. что делать с коллекцией, когда что то меняются в родительском. в агрегирующем классе
            // Если мы добавляем в пиццу какую то компоненту  - это гарантирует что добавится
            orphanRemoval = true, // 17. что то тоже самое
            fetch = FetchType.EAGER)  // 18. fetch важный параметр Eager - также наполнит таблицу Components. Если Lazy -
    // 18. то только в случае если мы вызовем get Components. Collection Mapping - на сайте Hibernste
    private List<PizzaComponents> components;
    // 20. Если нам нужно достать дополнительное поле

    /* 15. На уровне Hibernate мапится на уровне Коллекции
    * mappedBy - говорит */

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

/*    public List<PizzaComponents> getComponents() {
        return components;
    }

    public void setComponents(List<PizzaComponents> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", name='" + name + '\'' + ", prize=" + prize + ", components=" + components + '}';
    }*/
}
