package za.co.bigsim.bankingApi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import za.co.bigsim.bankingApi.entity.Account;
import za.co.bigsim.bankingApi.entity.dto.AccountDto;
import za.co.bigsim.bankingApi.entity.dto.AccountType;
import za.co.bigsim.bankingApi.entity.User;
import za.co.bigsim.bankingApi.entity.dto.UserDto;
import za.co.bigsim.bankingApi.service.AccountService;
import za.co.bigsim.bankingApi.service.TransactionService;
import za.co.bigsim.bankingApi.service.UserService;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class CustomerController {

    private AccountService accountService;

    private TransactionService transactionService;

    private UserService userService;

    CustomerController(AccountService accountService,
        TransactionService transactionService, UserService userService){
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    // handler method to handle list of users
    @GetMapping("/account")
    public String account(Model model){

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

       // User user = (User) authentication == null ? null :  authentication.getPrincipal();

        User user = userService.findUserByEmail("test@gmail.com");

        UserDto userDto = userService.mapToUserDto(user);
        AccountDto account = accountService.findAccount(user);
       // Optional<Account> account1 = accountService.findByAccountNumber("8888");
       // account1.get().setUser();
        if (account == null){
            account = new AccountDto(0l,"999999","8888", BigDecimal.ONE,"SomeBank", AccountType.CURRENT, userDto);
            transactionService.debitAccount(BigDecimal.valueOf(9999l), account.getAccountNumber());
            transactionService.creditAccount(BigDecimal.valueOf(455l), account.getAccountNumber());
            }

        account.setTransactionDtos(
                transactionService.findTransactionsByAccountNumber(account.getAccountNumber()));

        account.setUser(userDto);
        model.addAttribute("account", account);
        return "account";
    }
}
