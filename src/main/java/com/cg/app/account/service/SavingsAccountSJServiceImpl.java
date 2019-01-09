package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.app.pojo.account.dao.SavingsAccountDao;
import com.cg.app.pojo.account.factory.AccountFactory;
import com.cg.app.pojo.account.savings.SavingsAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;
import com.cg.app.pojo.exception.InsufficientFundsException;
import com.cg.app.pojo.exception.InvalidInputException;
@Primary
@Service
public class SavingsAccountSJServiceImpl implements SavingsAccountService{
	@Autowired
	private SavingsAccountDao savingsAccountDao;

	@Override
	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		AccountFactory factory = AccountFactory.getInstance();
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDao.createNewAccount(account);
		return null;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDao.getAccountById(accountNumber);
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDao.getAllSavingsAccount();
	}
	@Transactional
	@Override
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		withdraw(sender, amount);
		deposit(receiver, amount);
		
	}
	@Transactional
	@Override
	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		if (amount > 0) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDao.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			
		}else {
			throw new InvalidInputException("Invalid Input Amount!");
	}
	}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName)throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDao.getAccountByName(accountHolderName);
	}

	@Override
	public List<SavingsAccount> getSortedAccounts(int sortBy) throws ClassNotFoundException, SQLException {
		return savingsAccountDao.getSortedAccounts(sortBy);
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount account) throws DataAccessException, ClassNotFoundException, SQLException, AccountNotFoundException {
		
		return savingsAccountDao.updateAccount(account);
	}

	@Override
	public boolean closeAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		return savingsAccountDao.closeAccount(accountNumber);
	}
	@Transactional
	@Override
	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		if (amount > 0 && currentBalance >= amount) {
			currentBalance -= amount;
			savingsAccountDao.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		} else {
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		}

		
	}

	

}
