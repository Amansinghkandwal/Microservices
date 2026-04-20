package com.designX.accounts.service.impl;

import com.designX.accounts.dto.*;
import com.designX.accounts.entity.Accounts;
import com.designX.accounts.entity.Customer;
import com.designX.accounts.exception.ResourseNotFoundException;
import com.designX.accounts.mapper.AccountsMapper;
import com.designX.accounts.mapper.CustomerMapper;
import com.designX.accounts.repository.AccountsRepository;
import com.designX.accounts.repository.CustomerRepository;
import com.designX.accounts.service.ICustomersService;
import com.designX.accounts.service.client.CardsFeignClient;
import com.designX.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService{

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailDto fetchCustomerDetails(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourseNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourseNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );


        CustomerDetailDto customerDetailDto=CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailDto());
        customerDetailDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        ResponseEntity<LoanDto> loanDtoResponseEntity=loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailDto.setLoanDto(loanDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailDto.setCardsDto(cardsDtoResponseEntity.getBody());
        return customerDetailDto;
    }
}
