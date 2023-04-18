package pl.polsl.aei.ior.springdata;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.polsl.aei.ior.springdata.categories.CategoriesEntity;
import pl.polsl.aei.ior.springdata.categories.CategoriesRepository;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {

  private final CategoriesRepository categoriesRepository;

  @Override
  public void run(String... args) {
    categoriesRepository.deleteAll();
    CategoriesEntity fruits = new CategoriesEntity("Fruits");
    CategoriesEntity vegetables = new CategoriesEntity("Vegetables");
    CategoriesEntity dairy = new CategoriesEntity("Dairy");
    CategoriesEntity bakery = new CategoriesEntity("Bakery");
    CategoriesEntity meat = new CategoriesEntity("Meat");
    CategoriesEntity seafood = new CategoriesEntity("Seafood");
    categoriesRepository.saveAll(Arrays.asList(fruits, vegetables, dairy, bakery, meat, seafood));
  }
}
