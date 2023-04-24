package pl.polsl.aei.ior.springdata.customers.dto;

import java.util.UUID;

public record CustomerDto(
    UUID customerId,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address,
    String city,
    String state,
    String zipCode) {}
