package pl.polsl.aei.ior.springdata.customers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.polsl.aei.ior.springdata.customers.dto.CustomerDto;

class CustomersControllerTest {
  private CustomersService customersService;
  private CustomersController customersController;
  private static List<CustomerDto> customerDtos;

  private static final UUID uuidString = UUID.fromString("2e5f36d9-f529-4b80-bc8b-28a19cc22db9");

  @BeforeAll
  static void init() {
    CustomerDto customer1 =
        new CustomerDto(
            uuidString,
            "John",
            "Doe",
            "johndoe@example.com",
            "1234567890",
            "123 Main St",
            "Anytown",
            "CA",
            "12345");

    CustomerDto customer2 =
        new CustomerDto(
            UUID.fromString("c988aefd-d9a7-4d24-b22f-6d5cadb6257b"),
            "Jane",
            "Doe",
            "janedoe@example.com",
            "0987654321",
            "456 Elm St",
            "Otherville",
            "NY",
            "67890");

    CustomerDto customer3 =
        new CustomerDto(
            UUID.fromString("9982ed22-13b9-4370-881d-0635fcfa999b"),
            "Bob",
            "Smith",
            "bobsmith@example.com",
            "5555555555",
            "789 Oak St",
            "Smalltown",
            "TX",
            "54321");

    customerDtos = Arrays.asList(customer1, customer2, customer3);
  }

  @BeforeEach
  void setUp() {
    customersService = mock(CustomersService.class);
    customersController = new CustomersController(customersService);
  }

  @Test
  void getCustomers_shouldReturnListOfCustomers() {
    // Arrange
    when(customersService.getCustomers()).thenReturn(customerDtos);

    // Act
    List<CustomerDto> actualCustomers = customersController.getCustomers();

    // Assert
    assertThat(actualCustomers).isEqualTo(customerDtos);
  }

  @Test
  void getCustomerById_shouldReturnCustomerWithMatchingId() {
    // Arrange
    when(customersService.getCustomerById(uuidString.toString())).thenReturn(customerDtos.get(0));

    // Act
    CustomerDto actualCustomer = customersController.getCustomerById(uuidString.toString());

    // Assert
    assertThat(actualCustomer).isEqualTo(customerDtos.get(0));
  }

  @Test
  void deleteCustomerById_shouldReturnSuccessMessage() {
    // Arrange
    UUID customerId = UUID.randomUUID();
    when(customersService.deleteCustomerById(customerId.toString()))
        .thenReturn(customerId.toString());

    // Act
    String actualMessage = customersController.deleteCustomerById(customerId.toString());

    // Assert
    assertThat(actualMessage).isEqualTo(customerId.toString());
  }

  @Test
  void getCustomerByFirstNameAndLastName_shouldReturnListOfMatchingCustomers() {
    // Arrange
    String firstName = "John";
    String lastName = "Doe";
    when(customersService.getCustomerByFirstNameAndLastName(firstName, lastName))
        .thenReturn(customerDtos);

    // Act
    List<CustomerDto> actualCustomers =
        customersController.getCustomerByFirstNameAndLastName(firstName, lastName);

    // Assert
    assertThat(actualCustomers).isEqualTo(customerDtos);
  }
}
