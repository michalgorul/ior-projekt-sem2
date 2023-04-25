package pl.polsl.aei.ior.springdata.products.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductDto(
    UUID productId,
    String productName,
    String description,
    BigDecimal price,
    List<String> categories) {}
