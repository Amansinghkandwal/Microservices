package com.designX.loans.mapper;

import com.designX.loans.dto.LoanDto;
import com.designX.loans.entity.Loans;

public class LoanMapper{

    public static LoanDto mapToLoanDto(Loans loan, LoanDto loandto)
    {
            loandto.setLoanType(loan.getLoanType());
            loandto.setTotalLoan(loan.getTotalLoan());
            loandto.setAmountPaid(loan.getAmountPaid());
            loandto.setMobileNumber(loan.getMobileNumber());
            loandto.setOutstandingAmount(loan.getOutstandingAmount());
            loandto.setLoanNumber(loan.getLoanNumber());

            return loandto;
    }

    public static Loans mapToLoan(LoanDto loanDto, Loans loan)
    {
        loan.setLoanType(loanDto.getLoanType());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        loan.setLoanNumber(loanDto.getLoanNumber());
        return loan;
    }
}
