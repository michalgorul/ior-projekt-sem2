package pl.polsl.aei.ior.springdata.orders;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.aei.ior.springdata.orders.dto.OrdersDto;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrdersController {
  private final OrdersService ordersService;

  //  http://localhost:8081/api/v1/orders
  @GetMapping
  public List<OrdersDto> getOrders() {
    return ordersService.getOrders();
  }

  //  http://localhost:8081/api/v1/orders/{orderId}
  @GetMapping("/{orderId}")
  public OrdersDto getOrderById(@PathVariable String orderId) {
    return ordersService.getOrderById(orderId);
  }

  // DELETE http://localhost:8081/api/v1/orders/{orderId}
  @DeleteMapping("/{orderId}")
  public String deleteOrderById(@PathVariable String orderId) {
    return ordersService.deleteOrderById(orderId);
  }

  //  http://localhost:8081/api/v1/orders/date-price?orderDate={orderDate}&price={price}
  @GetMapping("/date-price")
  public List<OrdersDto> getOrderByOrderDateAndPriceIsLessThan(
      @RequestParam Date orderDate, @RequestParam Double price) {
    return ordersService.getOrderByOrderDateAndPriceIsLessThan(orderDate, price);
  }

  //
  // http://localhost:8081/api/v1/orders/date-price-page?orderDate={orderDate}&price={price}&page={page}&size={size}
  @GetMapping("/date-price-page")
  public ResponseEntity<Page<OrdersDto>> getOrderByOrderDateAndPriceIsLessThanPage(
      @RequestParam Date orderDate,
      @RequestParam Double price,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "1") int size,
      @RequestParam(defaultValue = "orderDate") String[] sort) {
    return ordersService.getOrderByOrderDateAndPriceIsLessThanPage(
        orderDate, price, page, size, sort);
  }

  //  http://localhost:8081/api/v1/orders/customer-name/{lastName}
  @GetMapping("/customer-name/{lastName}")
  public List<OrdersDto> getOrderByCustomersLastNameLike(@PathVariable String lastName) {
    return ordersService.getOrderByCustomersLastNameLike(lastName);
  }

  //
  // http://localhost:8081/api/v1/orders/customer-name-city-price?lastName={lastName}&city={city}&price={price}
  @GetMapping("/customer-name-city-price")
  public List<OrdersDto> getOrderByCustomersLastNameAndCityOrPrice(
      @RequestParam String lastName, @RequestParam String city, @RequestParam Double price) {
    return ordersService.getOrderByCustomersLastNameAndCityOrPrice(lastName, city, price);
  }

  //  http://localhost:8081/api/v1/orders/quantity/{quantity}
  @GetMapping("/quantity/{quantity}")
  public List<OrdersDto> getOrderByQuantityIsLess(@PathVariable Integer quantity) {
    return ordersService.getOrderByQuantityIsLess(quantity);
  }
}
