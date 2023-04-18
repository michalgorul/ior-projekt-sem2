package pl.polsl.aei.ior.springdata.products;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.polsl.aei.ior.springdata.categories.CategoriesEntity;
import pl.polsl.aei.ior.springdata.orderitems.OrderItemsEntity;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "store")
public class ProductsEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "product_id")
  private UUID productId;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private BigDecimal price;

  @OneToMany(mappedBy = "productsByProductId")
  private Collection<OrderItemsEntity> orderItemsByProductId;

  @ManyToMany(mappedBy = "productsEntities")
  private Collection<CategoriesEntity> categoriesEntities;

  public ProductsEntity(String productName, String description, BigDecimal price) {
    this.productName = productName;
    this.description = description;
    this.price = price;
  }
}
