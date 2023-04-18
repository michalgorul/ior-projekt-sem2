package pl.polsl.aei.ior.springdata.productcategories;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "Product_Categories", schema = "store")
@IdClass(ProductCategoriesEntityPK.class)
public class ProductCategoriesEntity {
    @Id
    @Column(name = "product_id")
    private UUID productId;

    @Id
    @Column(name = "category_id")
    private UUID categoryId;

}
