package servlets;
import com.entity.newbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class indexservlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

              String islogin=req.getParameter("login");
              String name=req.getParameter("username");
              System.out.println(name);
              System.out.println(islogin);
              req.setAttribute("islogin",islogin);
              req.setAttribute("name",name);
              Configuration config = new Configuration().configure("hibernate.cfg.xml");
              SessionFactory factory = config.buildSessionFactory();
              Session session = factory.openSession();
              List<newbook> books = session.createQuery("FROM newbook", newbook.class).getResultList();
              ArrayList<newbook> bookList = new ArrayList<>(books);

              req.setAttribute("myList", bookList);

              // Forward the request to the JSP page
              RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
              dispatcher.forward(req, resp);
              session.close();
              factory.close();


    }
}
