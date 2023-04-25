package pl.polsl.aei.ior.springdata.products;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.aei.ior.springdata.products.dto.ProductDto;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductsController {
  private final ProductsService productsService;

  @GetMapping
  public List<ProductDto> getProducts() {
    return productsService.getProducts();
  }

  @GetMapping("/{productId}")
  public ProductDto getProductById(@PathVariable String productId) {
    return productsService.getProductById(productId);
  }

  @DeleteMapping("/{productId}")
  public String deleteProductById(@PathVariable String productId) {
    return productsService.deleteProductById(productId);
  }

  @GetMapping("/name-price")
  public List<ProductDto> getProductByProductNameAndPrice(
      @RequestParam String productName, @RequestParam Double price) {
    return productsService.getProductByProductNameAndPrice(productName, price);
  }

  @GetMapping("/name-price-page")
  public ResponseEntity<Page<ProductDto>> getProductByProductNameAndPricePage(
      @RequestParam String productName,
      @RequestParam Double price,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "1") int size,
      @RequestParam(defaultValue = "productName") String[] sort) {
    return productsService.getProductByProductNameAndPricePage(
        productName, price, page, size, sort);
  }

  @GetMapping("/description/{description}")
  public List<ProductDto> getProductByDescriptionLike(@PathVariable String description) {
    return productsService.getProductByDescriptionLike(description);
  }

  @GetMapping("/name/{productName}")
  public List<ProductDto> findByProductNameIgnoreCase(@PathVariable String productName) {
    return productsService.findByProductNameIgnoreCase(productName);
  }

  @GetMapping("/name-price-description")
  public List<ProductDto> getProductByProductNameAndPriceOrDescription(
      @RequestParam String productName,
      @RequestParam Double price,
      @RequestParam String description) {
    return productsService.getProductByProductNameAndPriceOrDescription(
        productName, price, description);
  }

  @GetMapping("/name-jpql/{productName}")
  public List<ProductDto> getProductByProductNameJpql(@PathVariable String productName) {
    return productsService.getProductByProductNameJpql(productName);
  }
}
