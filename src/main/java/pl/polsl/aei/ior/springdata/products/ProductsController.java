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

  //  http://localhost:8081/api/v1/products
  @GetMapping
  public List<ProductDto> getProducts() {
    return productsService.getProducts();
  }

  //  http://localhost:8081/api/v1/products/{productId}
  @GetMapping("/{productId}")
  public ProductDto getProductById(@PathVariable String productId) {
    return productsService.getProductById(productId);
  }

  // DELETE http://localhost:8081/api/v1/products/{productId}
  @DeleteMapping("/{productId}")
  public String deleteProductById(@PathVariable String productId) {
    return productsService.deleteProductById(productId);
  }

  //  http://localhost:8081/api/v1/products/name-price?productName={productName}&price={price}
  @GetMapping("/name-price")
  public List<ProductDto> getProductByProductNameAndPrice(
      @RequestParam String productName, @RequestParam Double price) {
    return productsService.getProductByProductNameAndPrice(productName, price);
  }

  //
  // http://localhost:8081/api/v1/products/name-price-page?productName={productName}&price={price}&page={page}&size={size}
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

  //  http://localhost:8081/api/v1/products/description/{description}
  @GetMapping("/description/{description}")
  public List<ProductDto> getProductByDescriptionLike(@PathVariable String description) {
    return productsService.getProductByDescriptionLike(description);
  }

  //  http://localhost:8081/api/v1/products/category-name/{categoryName}
  @GetMapping("/category-name/{categoryName}")
  public List<ProductDto> findByCategoryNameIgnoreCase(@PathVariable String categoryName) {
    return productsService.findByCategoryNameIgnoreCase(categoryName);
  }

  //
  // http://localhost:8081/api/v1/products/name-price-description?productName={productName}&price={price}&description={description}
  @GetMapping("/name-price-description")
  public List<ProductDto> getProductByProductNameAndPriceOrDescription(
      @RequestParam String productName,
      @RequestParam Double price,
      @RequestParam String description) {
    return productsService.getProductByProductNameAndPriceOrDescription(
        productName, price, description);
  }

  //  http://localhost:8081/api/v1/products/name-jpql/{productName}
  @GetMapping("/name-jpql/{productName}")
  public List<ProductDto> getProductByProductNameJpql(@PathVariable String productName) {
    return productsService.getProductByProductNameJpql(productName);
  }
}
