package com.cg.app.pojo.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.app.pojo.account.savings.SavingsAccount;
import com.cg.app.pojo.cui.AccountCUI;
import com.cg.app.pojo.exception.AccountNotFoundException;

public class Bootstrap {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapp_db", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ACCOUNT2");
//			preparedStatement.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, AccountNotFoundException {

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		AccountCUI accountCUI = context.getBean(AccountCUI.class);
		accountCUI.start();
		BasicConfigurator.configure();

	}

}
//