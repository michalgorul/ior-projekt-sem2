package pl.polsl.aei.ior.springdata.customers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.polsl.aei.ior.springdata.customers.dto.CustomerDto;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomersController {
  CustomersService customersService;

  @GetMapping
  public List<CustomerDto> getCustomers() {
    return customersService.getCustomers();
  }

  @GetMapping("/{customerId}")
  public CustomerDto getCustomerById(@PathVariable String customerId) {
    return customersService.getCustomerById(customerId);
  }

  @DeleteMapping("/{customerId}")
  public String deleteCustomerById(@PathVariable String customerId) {
    return customersService.deleteCustomerById(customerId);
  }

  @GetMapping("/first-last-name")
  public List<CustomerDto> getCustomerByFirstNameAndLastName(
      @RequestParam String firstName, @RequestParam String lastName) {
    return customersService.getCustomerByFirstNameAndLastName(firstName, lastName);
  }

  @GetMapping("/phone/{phoneNumber}")
  public List<CustomerDto> getCustomerByPhoneNumberLike(@PathVariable String phoneNumber) {
    return customersService.getCustomerByPhoneNumberLike(phoneNumber);
  }

  @GetMapping("/address/{address}")
  public List<CustomerDto> getCustomerByAddress(@PathVariable String address) {
    return customersService.getCustomerByAddress(address);
  }

  @GetMapping("/address")
  public List<CustomerDto> getCustomerByFirstNameAndCityOrAddress(
      @RequestParam String firstName, @RequestParam String city, @RequestParam String address) {
    return customersService.getCustomerByFirstNameAndCityOrAddress(firstName, city, address);
  }

  @GetMapping("/address/{lastName}")
  public List<CustomerDto> getCustomerByLastName(@PathVariable String lastName) {
    return customersService.getCustomerByLastName(lastName);
  }
}
