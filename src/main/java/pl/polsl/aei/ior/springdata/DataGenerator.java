package pl.polsl.aei.ior.springdata;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.polsl.aei.ior.springdata.categories.CategoriesEntity;
import pl.polsl.aei.ior.springdata.categories.CategoriesRepository;
import pl.polsl.aei.ior.springdata.customers.CustomersEntity;
import pl.polsl.aei.ior.springdata.customers.CustomersRepository;
import pl.polsl.aei.ior.springdata.orderitems.OrderItemsEntity;
import pl.polsl.aei.ior.springdata.orderitems.OrderItemsRepository;
import pl.polsl.aei.ior.springdata.orders.OrdersEntity;
import pl.polsl.aei.ior.springdata.orders.OrdersRepository;
import pl.polsl.aei.ior.springdata.products.ProductsEntity;
import pl.polsl.aei.ior.springdata.products.ProductsRepository;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {

  private final CategoriesRepository categoriesRepository;
  private ProductsRepository productsRepository;
  private final CustomersRepository customersRepository;
  private OrdersRepository ordersRepository;
  private OrderItemsRepository orderItemsRepository;

  @Override
  public void run(String... args) {

    //    Clear out
    categoriesRepository.deleteAll();
    productsRepository.deleteAll();
    customersRepository.deleteAll();
    ordersRepository.deleteAll();
    orderItemsRepository.deleteAll();

    //    Categories
    CategoriesEntity fruits = new CategoriesEntity("Fruits");
    CategoriesEntity vegetables = new CategoriesEntity("Vegetables");
    CategoriesEntity dairy = new CategoriesEntity("Dairy");
    CategoriesEntity bakery = new CategoriesEntity("Bakery");
    CategoriesEntity meat = new CategoriesEntity("Meat");
    CategoriesEntity seafood = new CategoriesEntity("Seafood");
    categoriesRepository.saveAll(Arrays.asList(fruits, vegetables, dairy, bakery, meat, seafood));

    //    Products
    ProductsEntity product1 =
        new ProductsEntity("Apple", "Fresh and juicy apples", new BigDecimal("0.99"));
    product1.setCategoriesEntities(Collections.singleton(fruits));

    ProductsEntity product2 =
        new ProductsEntity("Carrots", "Fresh and crunchy carrots", new BigDecimal("1.49"));
    product2.setCategoriesEntities(Collections.singleton(vegetables));

    ProductsEntity product3 =
        new ProductsEntity("Milk", "Fresh milk from local dairy", new BigDecimal("2.49"));
    product3.setCategoriesEntities(Collections.singleton(dairy));

    ProductsEntity product4 =
        new ProductsEntity("Baguette", "Freshly baked French bread", new BigDecimal("3.99"));
    product4.setCategoriesEntities(Collections.singleton(bakery));

    ProductsEntity product5 =
        new ProductsEntity("Beef", "Fresh and tender beef", new BigDecimal("12.99"));
    product5.setCategoriesEntities(Collections.singleton(meat));
    productsRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

    //    Customers
    CustomersEntity customer1 =
        CustomersEntity.builder()
            .firstName("John")
            .lastName("Doe")
            .email("johndoe@example.com")
            .phoneNumber("555-1234")
            .address("123 Main St")
            .city("Anytown")
            .state("TX")
            .zipCode("12345")
            .build();
    CustomersEntity customer2 =
        CustomersEntity.builder()
            .firstName("Jane")
            .lastName("Doe")
            .email("janedoe@example.com")
            .phoneNumber("555-5678")
            .address("456 High St")
            .city("Othertown")
            .state("TX")
            .zipCode("67890")
            .build();
    CustomersEntity customer3 =
        CustomersEntity.builder()
            .firstName("Bob")
            .lastName("Smith")
            .email("bobsmith@example.com")
            .phoneNumber("555-2468")
            .address("789 Low St")
            .city("Smalltown")
            .state("TX")
            .zipCode("13579")
            .build();
    CustomersEntity customer4 =
        CustomersEntity.builder()
            .firstName("Alice")
            .lastName("Jones")
            .email("alicejones@example.com")
            .phoneNumber("555-3691")
            .address("321 Broad St")
            .city("Bigcity")
            .state("TX")
            .zipCode("97531")
            .build();
    CustomersEntity customer5 =
        CustomersEntity.builder()
            .firstName("Tom")
            .lastName("Brown")
            .email("tombrown@example.com")
            .phoneNumber("555-9876")
            .address("654 Narrow St")
            .city("Tighttown")
            .state("TX")
            .zipCode("24680")
            .build();

    List<CustomersEntity> customers =
        List.of(customer1, customer2, customer3, customer4, customer5);
    customersRepository.saveAll(customers);

    //    Orders
    OrdersEntity order1 =
        new OrdersEntity(
            customer1.getCustomerId(),
            new Date(System.currentTimeMillis()),
            new BigDecimal("25.00"));
    OrdersEntity order2 =
        new OrdersEntity(
            customer1.getCustomerId(),
            new Date(System.currentTimeMillis()),
            new BigDecimal("50.00"));
    OrdersEntity order3 =
        new OrdersEntity(
            customer2.getCustomerId(),
            new Date(System.currentTimeMillis()),
            new BigDecimal("75.00"));
    OrdersEntity order4 =
        new OrdersEntity(
            customer3.getCustomerId(),
            new Date(System.currentTimeMillis()),
            new BigDecimal("75.00"));
    OrdersEntity order5 =
        new OrdersEntity(
            customer4.getCustomerId(),
            new Date(System.currentTimeMillis()),
            new BigDecimal("15.00"));
    OrdersEntity order6 =
        new OrdersEntity(
            customer5.getCustomerId(),
            new Date(System.currentTimeMillis()),
            new BigDecimal("725.00"));
    ordersRepository.saveAll(Arrays.asList(order1, order2, order3, order4, order5, order6));

    //    OrderItems
    OrderItemsEntity item1 =
        new OrderItemsEntity(
            order1.getOrderId(), product1.getProductId(), 2, BigDecimal.valueOf(19.99));
    OrderItemsEntity item2 =
        new OrderItemsEntity(
            order2.getOrderId(), product3.getProductId(), 10, BigDecimal.valueOf(29.99));
    OrderItemsEntity item3 =
        new OrderItemsEntity(
            order3.getOrderId(), product2.getProductId(), 3, BigDecimal.valueOf(19.99));
    orderItemsRepository.saveAll(Arrays.asList(item1, item2, item3));
  }
}
