package pl.polsl.aei.ior.springdata.products.dto;

import java.util.function.Function;
import org.springframework.stereotype.Service;
import pl.polsl.aei.ior.springdata.categories.CategoriesEntity;
import pl.polsl.aei.ior.springdata.products.ProductsEntity;

@Service
public class ProductsDtoMapper implements Function<ProductsEntity, ProductDto> {
  @Override
  public ProductDto apply(ProductsEntity productsEntity) {
    return new ProductDto(
        productsEntity.getProductName(),
        productsEntity.getDescription(),
        productsEntity.getPrice(),
        productsEntity.getCategoriesEntities().stream()
            .map(CategoriesEntity::getCategoryName)
            .toList());
  }
}
