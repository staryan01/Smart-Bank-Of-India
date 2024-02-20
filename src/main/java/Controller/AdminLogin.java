package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.Bank_account;

@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name= req.getParameter("name");
		String email = req.getParameter("email");
		
		BankDao bankDao = new BankDao();		
		if(email.equals("admin@gmail.com") && name.equals("admin"))
		{
			resp.getWriter().print("<h1>Admin login successful</h1>");
			List<Bank_account> list = bankDao.fetchAll();
		
			//session tracking
			req.getSession().setAttribute("list", list);
			req.getRequestDispatcher("adminhome.jsp").include(req, resp);
		}
		else
		{
			resp.getWriter().print("<h1>Invalid credential </h1>");
		}
		
	}

}
