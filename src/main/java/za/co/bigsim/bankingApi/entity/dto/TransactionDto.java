package za.co.bigsim.bankingApi.entity.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDto {

    private String accountNumber;

    private Date trxDate;

    private TransactionType transactionType;

    private BigDecimal amount;

    public TransactionDto() {
    }

    public TransactionDto(String accountNumber, Date trxDate, TransactionType transactionType, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.trxDate = trxDate;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
