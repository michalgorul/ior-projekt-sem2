package pl.polsl.aei.ior.springdata.products;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, UUID> {
  @Modifying
  @Transactional
  @Query(
      value =
          "DELETE FROM product_categories WHERE product_id IN (SELECT product_id FROM products); "
              + "DELETE FROM order_items WHERE product_id IN (SELECT product_id FROM products);"
              + "DELETE FROM products",
      nativeQuery = true)
  void deleteAll();

  List<ProductsEntity> findByProductNameAndPrice(String productName, BigDecimal price);

  Page<ProductsEntity> findByProductNameAndPrice(
      String productName, BigDecimal price, Pageable pageable);

  List<ProductsEntity> findByDescriptionLike(String description);

  List<ProductsEntity> findByCategoriesEntitiesCategoryNameIgnoreCase(String categoryName);

  List<ProductsEntity> findDistinctByProductNameAndPriceOrDescription(
      String productName, BigDecimal price, String description);

  @Query("select p from ProductsEntity p where p.productName = :productName")
  List<ProductsEntity> findByProductName(@Param("productName") String productName);
}
