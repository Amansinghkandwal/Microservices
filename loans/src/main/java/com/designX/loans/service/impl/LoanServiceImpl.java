package com.designX.loans.service.impl;

import com.designX.loans.constants.LoanConstants;
import com.designX.loans.dto.LoanDto;
import com.designX.loans.entity.Loans;
import com.designX.loans.exception.LoanAlreadyExsistsException;
import com.designX.loans.exception.NoResourceFoundException;
import com.designX.loans.mapper.LoanMapper;
import com.designX.loans.repository.LoanRepository;
import com.designX.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;


    /**
     * @param mobileNumber - MobileNumber
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoan= loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent())
        {

            throw new LoanAlreadyExsistsException("Loans already registered with given mobileNumber"
                    +mobileNumber);
        }

        loanRepository.save(createNewLoan(mobileNumber));
    }


    /**
     *
     * @param mobileNumber - Mobile number of the customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber)
    {
        Loans newLoan= new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }



    /**
     * @param mobileNumber - Long mobileNumber
     * @return LoanDto Object
     */
    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loans loan=loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new NoResourceFoundException("Loan","mobileNumber",mobileNumber)
        );

         return LoanMapper.mapToLoanDto(loan,new LoanDto());
    }

    /**
     * @param loanDto - LoanDto object
     * @return true if updated else false
     */
    @Override
    public boolean updateLoan(LoanDto loanDto) {
       String mobileNumber=loanDto.getMobileNumber();
        Loans loan=loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new NoResourceFoundException("Loan","mobileNumber",mobileNumber)
        );

        LoanMapper.mapToLoan(loanDto,loan);

        loanRepository.save(loan);

        return true;
    }

    /**
     * @param mobileNumber
     * @return true if deleted successfully else false
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loan=loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new NoResourceFoundException("Loan","mobileNumber",mobileNumber)
        );

        loanRepository.deleteById(loan.getLoanId());
        return true;
    }



}
