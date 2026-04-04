package com.designX.loans.service;

import com.designX.loans.dto.LoanDto;


public interface ILoanService {

    /**
     *
     * @param mobileNumber - Mobile Number of the customer
     */
    void createLoan(String mobileNumber);


    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    LoanDto fetchLoan(String mobileNumber);


    /**
     *
     * @param loanDto - LoanDto object
     * @return boolean indicating if update of loan details is successful or not
     */
    boolean updateLoan(LoanDto loanDto);

    /**
     *
     * @param mobileNumber
     * @return boolean indicating if loan details are deleted successfully or not
     */
    boolean deleteLoan(String mobileNumber);

}
