package za.co.bigsim.bankingApi.entity;

import jakarta.persistence.*;
import za.co.bigsim.bankingApi.entity.dto.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String accountNumber;

    private BigDecimal amount;

    private Date initiationDate;

    private Date trxDate;

    private String reference;

    private TransactionType transactionType;


    public Transaction() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Date getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(Date initiationDate) {
        this.initiationDate = initiationDate;
    }

    public Date getTrxDate() {
        return trxDate;
    }
    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", amount=" + amount +
                ", initiationDate=" + initiationDate +
                ", trxDate=" + trxDate +
                ", reference='" + reference + '\'' +
                '}';
    }
}
