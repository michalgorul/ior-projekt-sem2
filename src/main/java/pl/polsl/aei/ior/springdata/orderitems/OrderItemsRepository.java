package pl.polsl.aei.ior.springdata.orderitems;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, UUID> {
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM order_items", nativeQuery = true)
  void deleteAll();
}
