package pl.polsl.aei.ior.springdata.customers.dto;

import java.util.function.Function;
import org.springframework.stereotype.Service;
import pl.polsl.aei.ior.springdata.customers.CustomersEntity;

@Service
public class CustomerDtoMapper implements Function<CustomersEntity, CustomerDto> {
  @Override
  public CustomerDto apply(CustomersEntity customersEntity) {
    return new CustomerDto(
        customersEntity.getCustomerId(),
        customersEntity.getFirstName(),
        customersEntity.getLastName(),
        customersEntity.getEmail(),
        customersEntity.getPhoneNumber(),
        customersEntity.getAddress(),
        customersEntity.getCity(),
        customersEntity.getState(),
        customersEntity.getZipCode());
  }
}
