package servlets;

import oracleTransaction.SaveToDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 5/13/2020.
 */
public class Insert extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
        SaveToDB register = new SaveToDB();
        String name=request.getParameter("username");
        String email=request.getParameter("email");
        String password=request.getParameter("password");

        register.insert(name,email,password);
        response.sendRedirect("welcome.jsp");



    }
}
