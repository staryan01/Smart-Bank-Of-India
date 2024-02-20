package Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.BankTransaction;
import Dto.Bank_account;

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		String amt = req.getParameter("amt");
		double amount = Double.parseDouble(amt);
		long acno = (long) req.getSession().getAttribute("acno");
		
		BankDao bankDao = new BankDao();
		Bank_account bank_account=bankDao.find(acno);
		
		if(bank_account.getAmount()<amount)
		{
			resp.getWriter().print("<h1>Insufficient balance your available balance is:"+bank_account.getAmount()+"<h1>");
		}
		else
		{
			if(amount>bank_account.getAcc_limit())
			{
				resp.getWriter().print("<h1> You are exceeding your account limit your actual limit is:"+bank_account.getAcc_limit()+"</h1>");
			}
			else
			{
				bank_account.setAmount(bank_account.getAmount()-amount);
				
				BankTransaction bankTransaction = new BankTransaction();
				bankTransaction.setBalance(bank_account.getAmount());
				bankTransaction.setDateTime(LocalDateTime.now());
				bankTransaction.setDeposit(0);
				bankTransaction.setWithdraw(amount);
				
				List<BankTransaction> list=bank_account.getList();
				list.add(bankTransaction);
				bank_account.setList(list);
				
				bankDao.update_the_details(bank_account);
				resp.getWriter().print("<h1>Amount has been withdraw  successfully</h1>");
				req.getRequestDispatcher("accounthome.html").include(req, resp);
			}
		}
		
		
	}
}
