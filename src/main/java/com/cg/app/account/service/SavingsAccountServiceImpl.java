//package com.cg.app.account.service;
//
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.cg.app.pojo.account.dao.SavingsAccountDaoImpl;
//import com.cg.app.pojo.account.dao.SavingsAccountDao;
//import com.cg.app.pojo.account.factory.AccountFactory;
//import com.cg.app.pojo.account.savings.SavingsAccount;
//import com.cg.app.pojo.exception.AccountNotFoundException;
//import com.cg.app.pojo.exception.InsufficientFundsException;
//import com.cg.app.pojo.exception.InvalidInputException;
//import com.cg.app.pojo.util.DBUtil;
//
//@Service
//public class SavingsAccountServiceImpl implements SavingsAccountService {
//
//	private AccountFactory factory;
//	
//	@Autowired
//	private SavingsAccountDao savingsAccountDao;
//
//	public SavingsAccountServiceImpl() {
//		factory = AccountFactory.getInstance();
////		savingsAccountDao = new SavingsAccountDaoImpl();
//	}
//
//	@Override
//	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
//			throws ClassNotFoundException, SQLException {
//		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
//		savingsAccountDao.createNewAccount(account);
//		return null;
//	}
//
//	@Override
//	public List <SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
//		return savingsAccountDao.getAllSavingsAccount();
//	}
//
//	@Override
//	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
//		if (amount > 0) {
//			double currentBalance = account.getBankAccount().getAccountBalance();
//			currentBalance += amount;
//			savingsAccountDao.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
//			//savingsAccountDao.commit();
//		}else {
//			throw new InvalidInputException("Invalid Input Amount!");
//		}
//	}
//	@Override
//	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
//		double currentBalance = account.getBankAccount().getAccountBalance();
//		if (amount > 0 && currentBalance >= amount) {
//			currentBalance -= amount;
//			savingsAccountDao.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
//			//savingsAccountDao.commit();
//		} else {
//			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
//		}
//	}
//
//	@Override
//	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
//			throws ClassNotFoundException, SQLException {
//		try {
//			withdraw(sender, amount);
//			deposit(receiver, amount);
//			DBUtil.commit();
//		} catch (InvalidInputException | InsufficientFundsException e) {
//			e.printStackTrace();
//			DBUtil.rollback();
//		} catch(Exception e) {
//			e.printStackTrace();
//			DBUtil.rollback();
//		}
//	}
//
//	
//
//	@Override
//	public SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
//		return savingsAccountDao.getAccountById(accountNumber);
//	}
//
//	@Override
//	public List<SavingsAccount> getSortedAccounts(int choice) throws ClassNotFoundException, SQLException  {
//		return savingsAccountDao.getSortedAccounts(choice);
//	}
//
//	@Override
//	public boolean closeAccount(int accountNumber) throws ClassNotFoundException, SQLException {
//		return savingsAccountDao.closeAccount(accountNumber);
//	}
//
//	@Override
//	public SavingsAccount getAccountByName(int accountHolderName)throws ClassNotFoundException, SQLException,AccountNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	0
//	
//
//	@Override
//	public SavingsAccount updateAccount(SavingsAccount account) {
//		// TODO Auto-generated method stub
//		return null;
//
//	}
//
//	
//
//}
