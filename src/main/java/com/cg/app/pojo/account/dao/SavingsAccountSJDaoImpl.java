package com.cg.app.pojo.account.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.pojo.account.savings.SavingsAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;

@Repository
@Primary
public class SavingsAccountSJDaoImpl implements SavingsAccountDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("INSERT INTO ACCOUNT2 VALUES(?,?,?,?,?,?)",
				new Object[] { account.getBankAccount().getAccountNumber(),
						account.getBankAccount().getAccountHolderName(), account.getBankAccount().getAccountBalance(),
						account.isSalary(), null, "SA" });
		return account;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount sa =jdbcTemplate.queryForObject("SELECT * FROM ACCOUNT2 where  accountNumber=?",new Object[] {accountNumber},new SavingsAccountMapper());
		return sa;
	}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		jdbcTemplate.update("SELECT * FROM ACCOUNT2", getAccountByName(accountHolderName));
		return null;
	}

	@Override
	public boolean closeAccount(int accountNumber) throws SQLException, ClassNotFoundException {

		return jdbcTemplate.update("DELETE from ACCOUNT2 where accountNumber=?", new Object[] { accountNumber }) > 0;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {

		return jdbcTemplate.query("select * from account2", new SavingsAccountMapper());
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount account) {
		jdbcTemplate.update("INSERT INTO ACCOUNT2 VALUES(?,?,?,?,?)",
				new Object[] { 
						account.getBankAccount().getAccountHolderName(), 
						account.isSalary(), null, "SA" } );
		return account;
	}

	public void updateBalance(int accountNumber, double accountBalance) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("UPDATE ACCOUNT2 SET accountBalance=? where accountNumber=?", new Object[] {accountBalance,accountNumber});
	}

	@Override
	public List<SavingsAccount> getSortedAccounts(int choice) throws SQLException, ClassNotFoundException {

		String query = "";
		switch (choice) {
		case 1:
			query = "SELECT * FROM ACCOUNT2 ORDER BY accountNumber";
			break;
		case 2:
			query = "SELECT * FROM ACCOUNT2 ORDER BY account_hn";
			break;
		case 3:
			query = "SELECT * FROM ACCOUNT2 ORDER BY accountBalance";
			break;
		default:

			break;
		}

		System.out.println(query);
		return jdbcTemplate.query(query, new SavingsAccountMapper());

	}

	
}
