package pl.polsl.aei.ior.springdata.customers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

  List<CustomersEntity> findByFirstNameAndLastName(String firstName, String lastName);

  //  Page<CustomersEntity> findByFirstNameAndLastName(
  //      String firstName, String lastName, Pageable pageable, Sort sort);

  List<CustomersEntity> findByPhoneNumberLike(String phoneNumber);

  Optional<CustomersEntity> findByAddressIgnoreCase(String address);

  Optional<CustomersEntity> findDistinctByFirstNameAndCityOrAddress(
      String firstName, String city, String address);

  @Query("select c from CustomersEntity c where c.lastName = :lastName")
  Optional<CustomersEntity> findByLastname(@Param("lastName") String lastName);
}
