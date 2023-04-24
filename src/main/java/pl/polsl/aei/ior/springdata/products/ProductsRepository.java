package pl.polsl.aei.ior.springdata.products;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
}
