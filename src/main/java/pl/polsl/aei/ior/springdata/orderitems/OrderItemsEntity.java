package pl.polsl.aei.ior.springdata.orderitems;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.polsl.aei.ior.springdata.orders.OrdersEntity;
import pl.polsl.aei.ior.springdata.products.ProductsEntity;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "order_items", schema = "store")
public class OrderItemsEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_item_id")
    private UUID orderItemId;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
    private OrdersEntity ordersByOrderId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productsByProductId;
}
