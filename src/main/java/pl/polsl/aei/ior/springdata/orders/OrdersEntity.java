package pl.polsl.aei.ior.springdata.orders;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.polsl.aei.ior.springdata.customers.CustomersEntity;
import pl.polsl.aei.ior.springdata.orderitems.OrderItemsEntity;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "orders", schema = "store")
public class OrdersEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "order_id", nullable = false)
  private UUID orderId;

  @Column(name = "customer_id")
  private UUID customerId;

  @Column(name = "order_date")
  private Date orderDate;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  @OneToMany(mappedBy = "ordersByOrderId", cascade = CascadeType.ALL)
  private Collection<OrderItemsEntity> orderItemsByOrderId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(
      name = "customer_id",
      referencedColumnName = "customer_id",
      nullable = false,
      insertable = false,
      updatable = false)
  private CustomersEntity customersByCustomerId;

  public OrdersEntity(UUID customerId, Date orderDate, BigDecimal totalPrice) {
    this.customerId = customerId;
    this.orderDate = orderDate;
    this.totalPrice = totalPrice;
  }
}
