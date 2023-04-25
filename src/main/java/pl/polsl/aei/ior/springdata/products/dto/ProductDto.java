package pl.polsl.aei.ior.springdata.products.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
    String productName, String description, BigDecimal price, List<String> categories) {}
