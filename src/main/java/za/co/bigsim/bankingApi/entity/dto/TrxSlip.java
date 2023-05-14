package za.co.bigsim.bankingApi.entity.dto;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public class TrxSlip {

    @NotEmpty
    private String accountNumber;

    private BigDecimal amount;


    public TrxSlip() {
    }

    public TrxSlip(String accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
