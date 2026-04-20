package com.designX.accounts.mapper;


import com.designX.accounts.dto.CustomerDetailDto;
import com.designX.accounts.dto.CustomerDto;
import com.designX.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerDetailDto mapToCustomerDetailsDto(Customer customer, CustomerDetailDto customerDetailDto) {
        customerDetailDto.setName(customer.getName());
        customerDetailDto.setEmail(customer.getEmail());
        customerDetailDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}