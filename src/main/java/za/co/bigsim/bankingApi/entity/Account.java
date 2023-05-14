package za.co.bigsim.bankingApi.entity;

import jakarta.persistence.*;
import za.co.bigsim.bankingApi.entity.dto.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String branchNumber;

    private String accountNumber;

    private BigDecimal currentBalance;

    private String bankName;

    private transient List<Transaction> transactions;

    @Temporal(TemporalType.TIME)
    private Date createDateTime;

    @Temporal(TemporalType.TIME)
    private Date updateDateTime;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    private AccountType accountType;

    public Account() {}
    public Account(String bankName, User user, String branchNumber, String generateAccountNumber, BigDecimal currentBalance, AccountType accountType) {
        this.branchNumber = branchNumber;
        this.accountNumber = generateAccountNumber;
        this.currentBalance = currentBalance;
        this.bankName = bankName;
        this.user = user;
        this.accountType = accountType;
    }
    public Account(long id, String branchNumber, String accountNumber, BigDecimal currentBalance, String bankName, User user, AccountType accountType) {
        this.id = id;
        this.branchNumber = branchNumber;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.bankName = bankName;
        this.user = user;
        this.accountType = accountType;
    }

    public Account(long id, String branchNumber, String accountNumber, BigDecimal currentBalance, String bankName, User user, List<Transaction> transactions) {
        this.id = id;
        this.branchNumber = branchNumber;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.bankName = bankName;
        this.user = user;
        this.transactions = transactions;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBranchNumber() {
        return branchNumber;
    }
    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", branchNumber='" + branchNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", currentBalance=" + currentBalance +
                ", bankName='" + bankName + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
