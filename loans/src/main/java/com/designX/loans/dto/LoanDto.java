package com.designX.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Data
public class LoanDto {


    @NotEmpty(message = "Mobile nummber cannot be empty")
    @Pattern(regexp="^$|[0-9]{10}]",message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile Number of Customer",example = "XXXXXXXXXXX"
    )
    private String mobileNumber;

    @NotEmpty(message = "LoanType cannot be empty")
    @Schema(
            description = "Type of the loan",example = "Home Loan"
    )
    private String loanType;

    @NotEmpty(message = "Loan nummber cannot be empty")
    @Pattern(regexp="^$|[0-9]{12}]",message = "LoanNumber must be 12 digits")
    @Schema(
            description = "Loan Number of Customer",example = "XXXXXXXXXXXXX"
    )
    private String loanNumber;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(
            description = "Total loan amount",example = "100000"
    )
    private int totalLoan;


    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(
            description = "Total loan amount paid",example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount against a loan",example = "99000"
    )
    private int outstandingAmount;
}
