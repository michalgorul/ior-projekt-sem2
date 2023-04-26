package pl.polsl.aei.ior.springdata.orders;

import java.math.BigDecimal;
import java.sql.Date;
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
public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID> {

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM orders", nativeQuery = true)
  void deleteAll();

  List<OrdersEntity> findByOrderDateAndTotalPriceIsLessThan(Date orderDate, BigDecimal price);

  Page<OrdersEntity> findByOrderDateAndTotalPriceIsLessThan(
      Date orderDate, BigDecimal price, Pageable pageable);

  List<OrdersEntity> findByCustomersByCustomerIdLastNameLike(String firstName);

  List<OrdersEntity>
      findDistinctByCustomersByCustomerIdLastNameAndCustomersByCustomerIdCityOrTotalPriceIsGreaterThanEqual(
          String lastName, String city, BigDecimal price);

  @Query(
      "select o from OrdersEntity o join OrderItemsEntity oi on o.orderId = oi.orderId where"
          + " oi.quantity < :quantity")
  List<OrdersEntity> findByQuantityIsLess(@Param("quantity") Integer quantity);
}
