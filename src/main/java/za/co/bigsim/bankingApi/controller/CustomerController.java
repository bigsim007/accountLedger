package za.co.bigsim.bankingApi.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import za.co.bigsim.bankingApi.entity.Account;
import za.co.bigsim.bankingApi.entity.dto.*;
import za.co.bigsim.bankingApi.entity.User;
import za.co.bigsim.bankingApi.service.AccountService;
import za.co.bigsim.bankingApi.service.TransactionService;
import za.co.bigsim.bankingApi.service.UserService;

import java.math.BigDecimal;
import java.util.List;
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

       org.springframework.security.core.userdetails.User principal =
               (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        User user = userService.findUserByEmail(principal.getUsername());

        UserDto userDto = userService.mapToUserDto(user);
        AccountDto account = accountService.findAccount(user);

        if (account == null){

            // Create an dummy account for test purposes
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

    //Handle debit/credit into the account
    @PostMapping("/account/transaction/{transactionType}")
    //public ResponseEntity<Object>
    public String debitAccount(@Valid @ModelAttribute("trxSlip") TrxSlip trxSlip,
                                               BindingResult result,
                                               Model model,  @PathVariable String transactionType){
        if(TransactionType.DEBIT.name().equals(transactionType)){

            //return new ResponseEntity<>(transactionService.debitAccount(trxSlip.getAmount(),trxSlip.getAccountNumber()),
           //         HttpStatus.CREATED);
            ResponseEntity responseEntity = (ResponseEntity)transactionService.debitAccount(trxSlip.getAmount(),trxSlip.getAccountNumber());

            if (HttpStatus.OK == responseEntity.getStatusCode())
                return "account";
        }else if (TransactionType.CREDIT.name().equals(transactionType)){
            ResponseEntity responseEntity = (ResponseEntity)transactionService.creditAccount(trxSlip.getAmount(),trxSlip.getAccountNumber());

            if (HttpStatus.OK == responseEntity.getStatusCode())
                return "account";

        }
        return "error";
    }


    //Get transaction by type
    @GetMapping("/account/{accountNumber}/{type}")
    public ResponseEntity<Object> getTransactionForType(@PathVariable String accountNumber,
                                                        @PathVariable String transactionType){

        List<TransactionDto> transactionDtoList = transactionService.findTransactionsByAccountNumberAndAccountType(accountNumber,transactionType);

        if (!transactionDtoList.isEmpty()){
            return new ResponseEntity<>(transactionDtoList,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid parameter supplied", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/deposit")
    public String deposit(){
        return "deposit";
    }

    @GetMapping("/credit")
    public String credit(){
        return "credit";
    }

}
