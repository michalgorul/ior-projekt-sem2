package pl.polsl.aei.ior.springdata.customers;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.aei.ior.springdata.customers.dto.CustomerDto;
import pl.polsl.aei.ior.springdata.customers.dto.CustomerDtoMapper;

@AllArgsConstructor
@Service
public class CustomersService {
  private final CustomersRepository customersRepository;
  private final CustomerDtoMapper customerDtoMapper;

  public List<CustomerDto> getCustomers() {
    return customersRepository.findAll().stream().map(customerDtoMapper).toList();
  }

  public CustomerDto getCustomerById(String customerId) {
    return customersRepository
        .findById(UUID.fromString(customerId))
        .map(customerDtoMapper)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
  }

  public List<CustomerDto> getCustomerByFirstNameAndLastName(String firstName, String lastName) {
    return customersRepository.findByFirstNameAndLastName(firstName, lastName).stream()
        .map(customerDtoMapper)
        .toList();
  }

  public List<CustomerDto> getCustomerByPhoneNumberLike(String phoneNumber) {
    return customersRepository.findByPhoneNumberLike("%" + phoneNumber + "%").stream()
        .map(customerDtoMapper)
        .toList();
  }

  public List<CustomerDto> getCustomerByAddress(String address) {
    return customersRepository.findByAddressIgnoreCase(address).stream()
        .map(customerDtoMapper)
        .toList();
  }

  public List<CustomerDto> getCustomerByFirstNameAndCityOrAddress(
      String firstName, String city, String address) {
    return customersRepository
        .findDistinctByFirstNameAndCityOrAddress(firstName, city, address)
        .stream()
        .map(customerDtoMapper)
        .toList();
  }

  public List<CustomerDto> getCustomerByLastName(String lastName) {
    return customersRepository.findByLastname(lastName).stream().map(customerDtoMapper).toList();
  }

  public String deleteCustomerById(String customerId) {
    CustomersEntity customerEntity =
        customersRepository
            .findById(UUID.fromString(customerId))
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    customersRepository.delete(customerEntity);
    return customerId;
  }
}
