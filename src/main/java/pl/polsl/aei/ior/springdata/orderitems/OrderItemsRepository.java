package pl.polsl.aei.ior.springdata.orderitems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, UUID> {
}
