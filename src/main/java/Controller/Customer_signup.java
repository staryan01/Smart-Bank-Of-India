package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CustomerDao;
import Dto.Customer;

@WebServlet("/customer_signup")
public class Customer_signup extends HttpServlet
{	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String mobile = req.getParameter("mob");
		long mob=Long.parseLong(mobile);  // change string to long

		String password = req.getParameter("pwd");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("date");
		
		
		Date date=Date.valueOf(dob);
		Period period = Period.between(date.toLocalDate(), LocalDate.now());
		int age= period.getYears();
		
		Customer customer = new Customer();
		CustomerDao customerDao = new CustomerDao();
		
		if(age>18)
		{
			
			if(customerDao.check1(email).isEmpty() && customerDao.check2(mob).isEmpty())
			{
//			resp.getWriter().print("<h1>He is Eligible to create a bank Account</h1>");
//			customer.setCid(age);    it willl get generated randomly
			customer.setCname(name);
			customer.setEmail(email);
			customer.setGender(gender);
			customer.setMob(mob);
			customer.setDate(date);
			customer.setPwd(password);
			
			customerDao.save(customer);
			
			
			
			//logic for printing sir and madam
			Customer customer2 = customerDao.check1(email).get(0);
//			Customer customer2 = list.get(0);
			if(customer2.getGender().equals("female"))
			{
				resp.getWriter().print("<h1>Hello Madam a</h1>");
			}
			else
			{
				resp.getWriter().print("<h1>Hello Sir</h1>");
			}
			resp.getWriter().print("<h1>Account is Created successfully your customer id is: "+customer2.getCid()+" </h1>");
			req.getRequestDispatcher("customerlogin.html").include(req, resp);
			
			}
			else
			{
				resp.getWriter().print("<h1>This email id: "+email+" and "+" this mobile number: "+mob+" is already exist</h1>");
			}
			
		}
		else
		{
			resp.getWriter().print("<h1>He is not Eligible to create a bank Account</h1>");

		}
		
//		resp.getWriter().print("<h1> "+name+" </h1>"
//				+ "<h1> "+mob+" </h1>"
//				+ "<h1> "+password+" </h1>"
//				+ "<h1> "+email+" </h1>"
//				+ "<h1> "+gender+" </h1>"
//				+ "<h1> "+dob+" </h1>");
//		resp.getWriter().print("<h1> "+mob+" </h1>");
//		resp.getWriter().print("<h1> "+password+" </h1>");
//		resp.getWriter().print("<h1> "+email+" </h1>");
//		resp.getWriter().print("<h1> "+gender+" </h1>");
//		resp.getWriter().print("<h1> "+dob+" </h1>");
		
		
		
		
	}
}
