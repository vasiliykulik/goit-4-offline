package ua.goit.offline4.entity;



import javax.persistence.*;
import javax.persistence.criteria.Join;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by Стрела on 23.12.2016.
 */
@Entity
@Table(name = "orders", schema = "public")// указываем schema
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "adress")
    private String address;
    private String phone;
    private int status;
    private String comment;
    @Column (name="time")
    private Timestamp orderDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_orders",
            schema = "offline",
    joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn (name = "order_id"))
    private List<Pizza> pizzas;// 17. Отношение Ордер  к пицам - Many to Many

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", status=").append(status);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", orderDate=").append(orderDate);
        sb.append(", pizzas=").append(pizzas);
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
