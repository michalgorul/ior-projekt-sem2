package pl.polsl.aei.ior.springdata.orders.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import pl.polsl.aei.ior.springdata.customers.dto.CustomerDto;
import pl.polsl.aei.ior.springdata.products.dto.ProductDto;

public record OrdersDto(
    UUID orderId,
    CustomerDto customer,
    Date orderDate,
    BigDecimal totalPrice,
    List<ProductDto> products,
    int quantity,
    BigDecimal pricePerUnit) {}
