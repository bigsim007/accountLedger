package za.co.bigsim.bankingApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.co.bigsim.bankingApi.entity.dto.AccountDto;
import za.co.bigsim.bankingApi.entity.dto.AccountType;
import za.co.bigsim.bankingApi.entity.dto.UserDto;
import za.co.bigsim.bankingApi.entity.Role;
import za.co.bigsim.bankingApi.entity.User;
import za.co.bigsim.bankingApi.repository.RoleRepository;
import za.co.bigsim.bankingApi.service.AccountService;
import za.co.bigsim.bankingApi.service.TransactionService;
import za.co.bigsim.bankingApi.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BankingApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BankingApiApplication.class, args);
	}

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionService transactionService;


	@Override
	public void run(String... args) throws Exception {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role(null, "ADMIN"));
		roles.add(new Role(null, "ROLE_ADMIN"));
		roleRepository.saveAll(roles);
		UserDto user = new UserDto(null, "TestName", "Testsurname", "test@gmail.com", "password");
		userService.saveUser(user);
		AccountDto account = new AccountDto(0l,"999999","8888", BigDecimal.ONE,"SomeBank", AccountType.CURRENT, user);
		accountService.saveAccount(account);
		transactionService.debitAccount(BigDecimal.valueOf(10000l), account.getAccountNumber());
		transactionService.debitAccount(BigDecimal.valueOf(10000l), account.getAccountNumber());
		transactionService.debitAccount(BigDecimal.valueOf(10000l), account.getAccountNumber());
		transactionService.debitAccount(BigDecimal.valueOf(10000l), account.getAccountNumber());
		transactionService.debitAccount(BigDecimal.valueOf(10000l), account.getAccountNumber());
		transactionService.creditAccount(BigDecimal.valueOf(450l), account.getAccountNumber());
		transactionService.creditAccount(BigDecimal.valueOf(450l), account.getAccountNumber());
		transactionService.creditAccount(BigDecimal.valueOf(450l), account.getAccountNumber());
		transactionService.creditAccount(BigDecimal.valueOf(450l), account.getAccountNumber());
		transactionService.creditAccount(BigDecimal.valueOf(450l), account.getAccountNumber());



	}
}
