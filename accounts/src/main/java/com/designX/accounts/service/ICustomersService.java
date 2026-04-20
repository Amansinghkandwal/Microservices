package com.designX.accounts.service;

import com.designX.accounts.dto.CustomerDetailDto;

public interface ICustomersService {

    /*
    *@param mobileNumber - Input Mobile Number
    * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailDto fetchCustomerDetails(String mobileNumber);
}
