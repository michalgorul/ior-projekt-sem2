package pl.polsl.aei.ior.springdata.orders.dto;

import java.math.BigDecimal;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.aei.ior.springdata.customers.dto.CustomerDtoMapper;
import pl.polsl.aei.ior.springdata.orderitems.OrderItemsEntity;
import pl.polsl.aei.ior.springdata.orders.OrdersEntity;
import pl.polsl.aei.ior.springdata.products.dto.ProductsDtoMapper;

@Service
@AllArgsConstructor
public class OrdersDtoMapper implements Function<OrdersEntity, OrdersDto> {

  private final CustomerDtoMapper customerDtoMapper;
  private final ProductsDtoMapper productsDtoMapper;

  @Override
  public OrdersDto apply(OrdersEntity ordersEntity) {

    return new OrdersDto(
        ordersEntity.getOrderId(),
        customerDtoMapper.apply(ordersEntity.getCustomersByCustomerId()),
        ordersEntity.getOrderDate(),
        ordersEntity.getTotalPrice(),
        ordersEntity.getOrderItemsByOrderId().stream()
            .map(OrderItemsEntity::getProductsByProductId)
            .map(productsDtoMapper)
            .toList(),
        ordersEntity.getOrderItemsByOrderId().stream()
            .map(OrderItemsEntity::getQuantity)
            .mapToInt(Integer::intValue)
            .sum(),
        BigDecimal.valueOf(
            ordersEntity.getOrderItemsByOrderId().stream()
                .map(OrderItemsEntity::getPricePerUnit)
                .mapToDouble(BigDecimal::doubleValue)
                .sum()));
  }
}
