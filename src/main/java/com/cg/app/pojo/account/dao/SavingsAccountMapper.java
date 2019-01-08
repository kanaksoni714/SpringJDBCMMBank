package com.cg.app.pojo.account.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cg.app.pojo.account.savings.SavingsAccount;

public class SavingsAccountMapper implements RowMapper<SavingsAccount> {

	@Override
	public SavingsAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		int accountNumber= rs.getInt(1);
		String account_hn= rs.getString(2);
		double accountBalance= rs.getDouble(3);
		boolean Salaried=rs.getBoolean(4);
		SavingsAccount savingsAccount =new SavingsAccount(accountNumber,account_hn, accountBalance,Salaried);
		return savingsAccount;
		
		
		
				
	}

}
