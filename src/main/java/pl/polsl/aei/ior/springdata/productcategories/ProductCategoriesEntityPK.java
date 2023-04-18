package pl.polsl.aei.ior.springdata.productcategories;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
public class ProductCategoriesEntityPK implements Serializable {
    private UUID productId;

    private UUID categoryId;
}
