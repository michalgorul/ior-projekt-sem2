package pl.polsl.aei.ior.springdata.customers;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersEntity, UUID> {
  @Modifying
  @Transactional
  @Query(
      value =
          "DELETE FROM orders WHERE customer_id IN (SELECT customer_id FROM customers);"
              + "DELETE FROM customers",
      nativeQuery = true)
  void deleteAll();
}
