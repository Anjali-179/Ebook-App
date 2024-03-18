package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@WebServlet("/register")
public  class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);

		String name = req.getParameter("fname");
		String email = req.getParameter("email");
		String phoneNo = req.getParameter("phoneNo");
		String password = req.getParameter("password");
		String check = req.getParameter("check");

		boolean accountExists = checkAccountExists(email, phoneNo);


		if (accountExists) {
			System.out.println("account exist");

		}
		else {
			try {
				System.out.println(name);
				Configuration config=new Configuration().configure("hibernate.cfg.xml");
				//create Session Factory
				SessionFactory factory= config.buildSessionFactory();
				// create a session object
				Session s1=factory.openSession();
				System.out.println(s1);
				Transaction t1=s1.beginTransaction();
				User u1=new User();

				u1.setEmail(email);
				u1.setName(name);
				u1.setPhoneNo(phoneNo);
				u1.setPassword(password);
				s1.save(u1);
				t1.commit();
				s1.close();
				resp.sendRedirect(req.getContextPath() + "/login.jsp");



			}

			catch(Exception e) {
				System.out.println("not register sucessfully");
				e.printStackTrace();

			}

		}
	}
	 boolean checkAccountExists(String email, String phoneNo) {

		Configuration  config= new Configuration().configure();
		SessionFactory factory=config.buildSessionFactory();
		 Session session = factory.openSession();
		 System.out.println("hjwdva"+session);
		 Transaction tx = null;
		 boolean exists = false;
		 try {
		   tx = session.beginTransaction();
			 Long count = (Long) session.createQuery("select count(*) from User where email = :email or phoneNo = :phoneNo")
					 .setParameter("email", email)
					 .setParameter("phoneNo", phoneNo)
					 .uniqueResult();
			 exists = count > 0;
		System.out.println(exists);
		    tx.commit();
		} catch (Exception e) {
		     if (tx != null) {
		         tx.rollback();
		     }
		     e.printStackTrace();
		 } finally {
		     session.close();
		}

		return exists;

	}
}






