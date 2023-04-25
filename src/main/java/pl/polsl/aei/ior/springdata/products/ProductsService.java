package pl.polsl.aei.ior.springdata.products;

import java.math.BigDecimal;
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
import pl.polsl.aei.ior.springdata.products.dto.ProductDto;
import pl.polsl.aei.ior.springdata.products.dto.ProductsDtoMapper;

@AllArgsConstructor
@Service
public class ProductsService {
  private final ProductsRepository productsRepository;
  private final ProductsDtoMapper productsDtoMapper;

  public List<ProductDto> getProducts() {
    return productsRepository.findAll().stream().map(productsDtoMapper).toList();
  }

  public ProductDto getProductById(String productId) {
    return productsRepository
        .findById(UUID.fromString(productId))
        .map(productsDtoMapper)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
  }

  public String deleteProductById(String productId) {
    ProductsEntity productEntity =
        productsRepository
            .findById(UUID.fromString(productId))
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    productsRepository.delete(productEntity);
    return productId;
  }

  public List<ProductDto> getProductByProductNameAndPrice(String productName, Double price) {
    return productsRepository
        .findByProductNameAndPrice(productName, BigDecimal.valueOf(price))
        .stream()
        .map(productsDtoMapper)
        .toList();
  }

  public ResponseEntity<Page<ProductDto>> getProductByProductNameAndPricePage(
      String productName, Double price, int page, int size, String[] sort) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    Page<ProductsEntity> productsEntityPage =
        productsRepository.findByProductNameAndPrice(
            productName, BigDecimal.valueOf(price), pageable);
    return ResponseEntity.ok(productsEntityPage.map(productsDtoMapper));
  }

  public List<ProductDto> getProductByDescriptionLike(String description) {
    return productsRepository.findByDescriptionLike("%" + description + "%").stream()
        .map(productsDtoMapper)
        .toList();
  }

  public List<ProductDto> findByProductNameIgnoreCase(String productName) {
    return productsRepository.findByProductNameIgnoreCase(productName).stream()
        .map(productsDtoMapper)
        .toList();
  }

  public List<ProductDto> getProductByProductNameAndPriceOrDescription(
      String productName, Double price, String description) {
    return productsRepository
        .findDistinctByProductNameAndPriceOrDescription(
            productName, BigDecimal.valueOf(price), description)
        .stream()
        .map(productsDtoMapper)
        .toList();
  }

  public List<ProductDto> getProductByProductNameJpql(String productName) {
    return productsRepository.findByProductName(productName).stream()
        .map(productsDtoMapper)
        .toList();
  }
}
