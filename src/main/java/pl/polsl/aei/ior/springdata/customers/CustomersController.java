package pl.polsl.aei.ior.springdata.customers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.aei.ior.springdata.customers.dto.CustomerDto;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomersController {
  CustomersService customersService;

  //  http://localhost:8081/api/v1/customers
  @GetMapping
  public List<CustomerDto> getCustomers() {
    return customersService.getCustomers();
  }

  //  http://localhost:8081/api/v1/customers/{some UUID}
  @GetMapping("/{customerId}")
  public CustomerDto getCustomerById(@PathVariable String customerId) {
    return customersService.getCustomerById(customerId);
  }

  // DELETE  http://localhost:8081/api/v1/customers
  @DeleteMapping("/{customerId}")
  public String deleteCustomerById(@PathVariable String customerId) {
    return customersService.deleteCustomerById(customerId);
  }

  //
  // http://localhost:8081/api/v1/customers/first-last-name?firstName={firstName}&lastName={lastName}
  @GetMapping("/first-last-name")
  public List<CustomerDto> getCustomerByFirstNameAndLastName(
      @RequestParam String firstName, @RequestParam String lastName) {
    return customersService.getCustomerByFirstNameAndLastName(firstName, lastName);
  }

  //
  // http://localhost:8081/api/v1/customers/first-last-name-page?firstName={firstName}&lastName={lastName}&page={page}&size={size}
  @GetMapping("/first-last-name-page")
  public ResponseEntity<Page<CustomerDto>> getCustomerByFirstNameAndLastNamePageable(
      @RequestParam String firstName,
      @RequestParam String lastName,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "1") int size,
      @RequestParam(defaultValue = "lastName") String[] sort) {
    return customersService.getCustomerByFirstNameAndLastNamePageable(
        firstName, lastName, page, size, sort);
  }

  //  http://localhost:8081/api/v1/customers/phone/{phoneNumber}
  @GetMapping("/phone/{phoneNumber}")
  public List<CustomerDto> getCustomerByPhoneNumberLike(@PathVariable String phoneNumber) {
    return customersService.getCustomerByPhoneNumberLike(phoneNumber);
  }

  //  http://localhost:8081/api/v1/customers/address/{address}
  @GetMapping("/address/{address}")
  public List<CustomerDto> getCustomerByAddress(@PathVariable String address) {
    return customersService.getCustomerByAddress(address);
  }

  //
  // http://localhost:8081/api/v1/customers/name-city-address?firstName={firstName}&city={city}&address={address}
  @GetMapping("/name-city-address")
  public List<CustomerDto> getCustomerByFirstNameAndCityOrAddress(
      @RequestParam String firstName, @RequestParam String city, @RequestParam String address) {
    return customersService.getCustomerByFirstNameAndCityOrAddress(firstName, city, address);
  }

  //  http://localhost:8081/api/v1/customers/last-name/{lastName}
  @GetMapping("/last-name/{lastName}")
  public List<CustomerDto> getCustomerByLastName(@PathVariable String lastName) {
    return customersService.getCustomerByLastName(lastName);
  }
}
