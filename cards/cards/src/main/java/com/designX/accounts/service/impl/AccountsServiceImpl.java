package com.designX.accounts.service.impl;

import com.designX.accounts.constants.AccountsConstants;
import com.designX.accounts.dto.AccountsDto;
import com.designX.accounts.dto.CustomerDto;
import com.designX.accounts.entity.Accounts;
import com.designX.accounts.entity.Customer;
import com.designX.accounts.exception.CustomerAlreadyExistsException;
import com.designX.accounts.exception.ResourseNotFoundException;
import com.designX.accounts.mapper.AccountsMapper;
import com.designX.accounts.mapper.CustomerMapper;
import com.designX.accounts.repository.AccountsRepository;
import com.designX.accounts.repository.CustomerRepository;
import com.designX.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    private  AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent())
        {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber"
                    +customerDto.getMobileNumber());
        }
        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }



    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer)
    {
          Accounts newAccount=new Accounts();
          newAccount.setCustomerId(customer.getCustomerId());
          long randomAccNumber = 1000000000L+new Random().nextInt(900000000);
          newAccount.setAccountNumber(randomAccNumber);
          newAccount.setCustomerId(customer.getCustomerId());
          newAccount.setAccountType(AccountsConstants.SAVINGS);
          newAccount.setBranchAddress(AccountsConstants.ADDRESS);
          return newAccount;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourseNotFoundException("Customer","mobileNumber",mobileNumber)
        );

       Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourseNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

       CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;

    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(
                            () -> new ResourseNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
                    );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(
                            () -> new ResourseNotFoundException("Customer", "CustomerID", customerId.toString())
                    );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        ()->new ResourseNotFoundException("Customer","mobileNumber",mobileNumber)
                );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}

