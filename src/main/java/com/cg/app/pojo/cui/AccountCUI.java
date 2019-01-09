package com.cg.app.pojo.cui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.app.account.service.SavingsAccountService;
import com.cg.app.pojo.account.savings.SavingsAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;


@Component
public class AccountCUI {
//	java.util.logging.Logger
	private Logger logger = Logger.getLogger(AccountCUI.class.getName());
	private Scanner scanner = new Scanner(System.in);

	@Autowired
	private SavingsAccountService savingsAccountService;

	public void start() throws ClassNotFoundException, SQLException, AccountNotFoundException {

		do {
			logger.info("****** Welcome to Money Money Bank********");
			logger.info("1. Open New Savings Account");
			logger.info("2. Update Account");
			logger.info("3. Close Account");
			logger.info("4. Search Account");
			logger.info("5. Withdraw");
			logger.info("6. Deposit");
			logger.info("7. FundTransfer");
			logger.info("8. Check Current Balance");
			logger.info("9. Get All Savings Account Details");
			logger.info("10. Sort Accounts");
			logger.info("11. Exit");
			logger.info("Make your choice: ");

			int choice = scanner.nextInt();

			performOperation(choice);

		} while (true);
	}

	private void performOperation(int choice) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		switch (choice) {
		case 1:
			acceptInput("SA");
			break;
		case 2:
			updateAccount();
			break;
		case 3:
			closeAccount();
			break;
		case 4:
			searchAccount();
			break;
		case 5:
			withdraw();
			break;
		case 6:
			deposit();
			break;
		case 7:
			fundTransfer();
			break;
		case 9:
			showAllAccounts();
			break;
		case 10:
			sortMenu();
			break;
		case 11:
			closeAccount();
			break;
		default:
			System.out.println("Invalid Choice!");
			break;
		}

	}

	private void searchAccount() {
		logger.info("Enter the Account Number you wish to search");

	}

	private void updateAccount() throws ClassNotFoundException, SQLException, AccountNotFoundException {
		logger.info("Enter your account number");
		int accountNumber = scanner.nextInt();
		
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		
		String accountHolderName = account.getBankAccount().getAccountHolderName();
		boolean salary = account.isSalary();
		double accountBalance = account.getBankAccount().getAccountBalance();
		
		if (account != null)
		{
			System.out.println("choose what you want to update:\n 1).Name \n 2).Salaried \n 3).Both");
			int choice = scanner.nextInt();
			
			switch (choice) {
			case 1:
				String newAccountHolderName=scanner.toString();
				logger.info("Enter the updated Account Holder Name");
				break;
			case 2:
				boolean salaryType=scanner.hasNext();
				logger.info("Enter the updated Salary Type");
				break;
   			case 3:
   				String newAccountHolderName1=scanner.toString();
				logger.info("Enter the updated Account Holder Name");
				boolean salaryType1=scanner.hasNext();
				logger.info("Enter the updated Salary Type");
   				break;
   				
			default:
				logger.info("Invalid Choice");
				break;
			}
		account = new SavingsAccount(accountNumber, accountHolderName, accountBalance, salary);
		savingsAccountService.updateAccount(account);
		}else
		{
			logger.info("Invalid Account Number");
		}
	}



	private void fundTransfer() throws ClassNotFoundException, SQLException, AccountNotFoundException {
		logger.info("Enter Account Sender's Number: ");
		int senderAccountNumber = scanner.nextInt();
		logger.info("Enter Account Receiver's Number: ");
		int receiverAccountNumber = scanner.nextInt();
		logger.info("Enter Amount: ");
		double amount = scanner.nextDouble();

		SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
		SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
		savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);

	}

	private void deposit() throws ClassNotFoundException, SQLException, AccountNotFoundException {
		logger.info("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		logger.info("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;

		savingsAccount = savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.deposit(savingsAccount, amount);

	}

	private void withdraw() throws ClassNotFoundException, SQLException, AccountNotFoundException {
		logger.info("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		logger.info("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;

		savingsAccount = savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.withdraw(savingsAccount, amount);
	}

	private void closeAccount() throws ClassNotFoundException, SQLException {
		logger.info("Enter Account Number: ");
		int accountNumber = scanner.nextInt();

		boolean savingsAccount = savingsAccountService.closeAccount(accountNumber);
		System.out.println(savingsAccount);

	}

	private void sortMenu() throws ClassNotFoundException, SQLException {

		do {
			logger.info("+++++Ways of Sorting+++++++");
			logger.info("1. Account Number");
			logger.info("2. Account Holder Name");
			logger.info("3. Account Balance");
			logger.info("4. Exit from Sorting");

			int choice = scanner.nextInt();
			showSortedAccount(choice);

		} while (true);

	}

	private void showSortedAccount(int choice) throws ClassNotFoundException, SQLException {
		List<SavingsAccount> sortedAccounts;

		sortedAccounts = savingsAccountService.getSortedAccounts(choice);

		for (SavingsAccount savingsAccount : sortedAccounts) {
			System.out.println(savingsAccount);
		}

	}

	private void showAllAccounts() throws ClassNotFoundException, SQLException {
		List<SavingsAccount> savingsAccounts;

		savingsAccounts = savingsAccountService.getAllSavingsAccount();
		for (SavingsAccount savingsAccount : savingsAccounts) {
			System.out.println(savingsAccount);

		}

	}

	private void acceptInput(String type) throws ClassNotFoundException, SQLException {
		if (type.equalsIgnoreCase("SA")) {
			logger.info("Enter your Full Name: ");
			String accountHolderName = scanner.nextLine();
			accountHolderName = scanner.nextLine();
			logger.info("Enter Initial Balance(type na for Zero Balance): ");
			String accountBalanceStr = scanner.next();
			double accountBalance = 0.0;
			if (!accountBalanceStr.equalsIgnoreCase("na")) {
				accountBalance = Double.parseDouble(accountBalanceStr);
			}
			logger.info("Salaried?(y/n): ");
			boolean salary = scanner.next().equalsIgnoreCase("n") ? false : true;
			createSavingsAccount(accountHolderName, accountBalance, salary);
		}
	}

	private void createSavingsAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {

		savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);

	}

}
