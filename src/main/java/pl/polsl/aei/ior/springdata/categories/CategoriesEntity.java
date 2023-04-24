package pl.polsl.aei.ior.springdata.categories;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.polsl.aei.ior.springdata.products.ProductsEntity;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories", schema = "store")
public class CategoriesEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "category_id")
  private UUID categoryId;

  @Column(name = "category_name")
  private String categoryName;

  @ManyToMany(mappedBy = "categoriesEntities", cascade = CascadeType.ALL)
  private Collection<ProductsEntity> productsEntities;

  public CategoriesEntity(String categoryName) {
    this.categoryName = categoryName;
  }
}
