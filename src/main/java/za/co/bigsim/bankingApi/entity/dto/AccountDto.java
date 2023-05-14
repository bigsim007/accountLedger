package za.co.bigsim.bankingApi.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import za.co.bigsim.bankingApi.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class AccountDto {

    private Long id;

    @NotEmpty
    private String branchNumber;

    @NotEmpty
    private String accountNumber;

    private BigDecimal currentBalance;

    @NotEmpty
    private String bankName;

    private AccountType accountType;

    private UserDto user;

    private List<TransactionDto> transactionDtos;

    public AccountDto() {
    }

    public AccountDto(Long id, String branchNumber, String accountNumber, BigDecimal currentBalance, String bankName, AccountType accountType, UserDto user) {
        this.id = id;
        this.branchNumber = branchNumber;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.bankName = bankName;
        this.accountType = accountType;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<TransactionDto> getTransactionDtos() {
        return transactionDtos;
    }

    public void setTransactionDtos(List<TransactionDto> transactionDtos) {
        this.transactionDtos = transactionDtos;
    }
}
