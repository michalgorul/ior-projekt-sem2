package pl.polsl.aei.ior.springdata.orders;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.aei.ior.springdata.orders.dto.OrdersDto;
import pl.polsl.aei.ior.springdata.orders.dto.OrdersDtoMapper;

@AllArgsConstructor
@Service
public class OrdersService {
  private final OrdersRepository ordersRepository;
  private final OrdersDtoMapper ordersDtoMapper;

  public List<OrdersDto> getOrders() {
    return ordersRepository.findAll().stream().map(ordersDtoMapper).toList();
  }

  public OrdersDto getOrderById(String orderId) {
    return ordersRepository
        .findById(UUID.fromString(orderId))
        .map(ordersDtoMapper)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
  }

  public String deleteOrderById(String orderId) {
    OrdersEntity ordersEntity =
        ordersRepository
            .findById(UUID.fromString(orderId))
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

    ordersRepository.delete(ordersEntity);
    return orderId;
  }

  public List<OrdersDto> getOrderByOrderDateAndPriceIsLessThan(Date orderDate, Double price) {
    return ordersRepository
        .findByOrderDateAndTotalPriceIsLessThan(orderDate, BigDecimal.valueOf(price))
        .stream()
        .map(ordersDtoMapper)
        .toList();
  }

  public ResponseEntity<Page<OrdersDto>> getOrderByOrderDateAndPriceIsLessThanPage(
      Date orderDate, Double price, int page, int size, String[] sort) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    Page<OrdersEntity> ordersEntityPage =
        ordersRepository.findByOrderDateAndTotalPriceIsLessThan(
            orderDate, BigDecimal.valueOf(price), pageable);
    return ResponseEntity.ok(ordersEntityPage.map(ordersDtoMapper));
  }

  public List<OrdersDto> getOrderByCustomersLastNameLike(String lastName) {
    return ordersRepository.findByCustomersByCustomerIdLastNameLike("%" + lastName + "%").stream()
        .map(ordersDtoMapper)
        .toList();
  }

  public List<OrdersDto> getOrderByCustomersLastNameAndCityOrPrice(
      String lastName, String city, Double price) {

    return ordersRepository
        .findDistinctByCustomersByCustomerIdLastNameAndCustomersByCustomerIdCityOrTotalPriceIsGreaterThanEqual(
            lastName, city, BigDecimal.valueOf(price))
        .stream()
        .map(ordersDtoMapper)
        .toList();
  }

  public List<OrdersDto> getOrderByQuantityIsLess(Integer quantity) {
    return ordersRepository.findByQuantityIsLess(quantity).stream().map(ordersDtoMapper).toList();
  }
}
