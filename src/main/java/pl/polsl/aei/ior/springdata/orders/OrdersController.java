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

  @GetMapping
  public List<OrdersDto> getOrders() {
    return ordersService.getOrders();
  }

  @GetMapping("/{orderId}")
  public OrdersDto getOrderById(@PathVariable String orderId) {
    return ordersService.getOrderById(orderId);
  }

  @DeleteMapping("/{orderId}")
  public String deleteOrderById(@PathVariable String orderId) {
    return ordersService.deleteOrderById(orderId);
  }

  @GetMapping("/date-price")
  public List<OrdersDto> getOrderByOrderDateAndPriceIsLessThan(
      @RequestParam Date orderDate, @RequestParam Double price) {
    return ordersService.getOrderByOrderDateAndPriceIsLessThan(orderDate, price);
  }

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

  @GetMapping("/customer-name/{lastName}")
  public List<OrdersDto> getOrderByCustomersLastNameLike(@PathVariable String lastName) {
    return ordersService.getOrderByCustomersLastNameLike(lastName);
  }

  @GetMapping("/customer-name-city-price")
  public List<OrdersDto> getOrderByCustomersLastNameAndCityOrPrice(
      @RequestParam String lastName, @RequestParam String city, @RequestParam Double price) {
    return ordersService.getOrderByCustomersLastNameAndCityOrPrice(lastName, city, price);
  }

  @GetMapping("/quantity/{quantity}")
  public List<OrdersDto> getOrderByQuantityIsLess(@PathVariable Integer quantity) {
    return ordersService.getOrderByQuantityIsLess(quantity);
  }
}
