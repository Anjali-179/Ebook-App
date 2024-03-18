package servlets;

import com.entity.newbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddBook")
public class ADDBOOKSERVLET extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String book_Name = req.getParameter("bookName");
        String author = req.getParameter("author");
        String bookPriceStr = req.getParameter("bookPrice");
        String imageUrl = req.getParameter("imageUrl");
        String bookDescription = req.getParameter("bookDescription");

        int bookPrice = 0;
        try {
            bookPrice = Integer.parseInt(bookPriceStr);
        } catch (NumberFormatException e) {
            // Handle the case where bookPrice cannot be parsed as an integer
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: Invalid book price format");
            return;
        }

        try {
            Configuration config = new Configuration().configure("hibernate.cfg.xml");
            SessionFactory factory = config.buildSessionFactory();
            Session session = factory.openSession();

            Transaction transaction = session.beginTransaction();
            newbook book1 = new newbook();
            book1.setBookname(book_Name);
            book1.setAuthor(author);
            book1.setBook_price(bookPrice);
            book1.setBookImage(imageUrl);
            book1.setBook_description(bookDescription);
            session.save(book1);
            transaction.commit();
            session.close();
            factory.close();
        } catch (Exception e) {
            // Handle other exceptions like Hibernate configuration errors, database connection issues, etc.
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Error: Failed to add book");
            e.printStackTrace(); // This will print the exception trace to the console for debugging
            return;
        }

        // Respond with success if everything went well
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Book added successfully");
    }
}
