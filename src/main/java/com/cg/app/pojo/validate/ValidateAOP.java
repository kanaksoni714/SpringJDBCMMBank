package com.cg.app.pojo.validate;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Service;

@Service
public class ValidateAOP {
	
	private Logger logger = Logger.getLogger(ValidateAOP.class.getName());

	@Around("execution(*com.cg.app.account.service.SavingsAccountSJServiceImpl.deposit(..)")
	public void log3(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Before Logging");
		pjp.proceed();
		logger.info("Function name is" + pjp.getSignature());
		logger.info("Successfull");
		logger.info("Parameter is");
		Object[] params = pjp.getArgs();
		Double amount = (Double) params[1];
		if (amount > 0) {
			logger.info("Remaining Balance Is" + params[1]);
		}
	}

	@Around("execution(*com.cg.app.account.service.SavingsAccountSJServiceImpl.withdraw(..)")
	public void log5(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Before Logging");
		pjp.proceed();
		logger.info("Function name is" + pjp.getSignature());
		logger.info("Successfull");
		logger.info("Parameter is");
		Object[] params = pjp.getArgs();
		Double amount = (Double) params[1];
		Double currentBalance = (Double) params[0];
		if (amount < currentBalance) {
			logger.info("Remaining Balance Is" + params[1]);
		}
	}

	@Around("execution(*com.cg.app.account.service.SavingsAccountSJServiceImpl.fundTransfer(..)")
	public void log6(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Before Logging");
		pjp.proceed();
		logger.info("Function name is" + pjp.getSignature());
		logger.info("Successfull");
		logger.info("Parameter is");
		Object[] params = pjp.getArgs();
		Double amount = (Double) params[2];
		Integer sender = (Integer) params[0];
		if (amount > sender) {
			logger.info("Updated Balance Of Sender" + params[0]);
			logger.info("Updated Balance Of Receiver" + params[1]);
		}
	}

//	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
//			throws ClassNotFoundException, SQLException {
//		withdraw(sender, amount);
//		deposit(receiver, amount);
//		
////	}
//	@AfterReturning(pointcut = ("execution(*com.cg.app.account.service.(..))"))
//	public void log4(Integer retVal) {
//		logger.info("" + retVal);
//	}
	

}
