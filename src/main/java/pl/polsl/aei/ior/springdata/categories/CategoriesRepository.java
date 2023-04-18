package pl.polsl.aei.ior.springdata.categories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, UUID> {
  @Modifying
  @Transactional
  @Query(value = "delete from categories", nativeQuery = true)
  void deleteAll();
}
