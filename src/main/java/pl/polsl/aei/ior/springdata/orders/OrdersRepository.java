package pl.polsl.aei.ior.springdata.orders;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID> {

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM orders", nativeQuery = true)
  void deleteAll();
}
