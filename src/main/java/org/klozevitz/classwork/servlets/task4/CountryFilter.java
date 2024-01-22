package org.klozevitz.classwork.servlets.task4;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.db.task4;
import org.klozevitz.classwork.model.Notepad;


import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/countryFilter")
public class CountryFilter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            completeRequest(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/templates/countryFilter.jsp");
        dispatcher.forward(request, response);
    }

    private void completeRequest(HttpServletRequest request) throws SQLException {
        task4 dao = new DbDao();
        List<String> countries = dao.countrySelect();
        request.setAttribute("countries", countries);
        if (request.getParameter("country") != null) {
            request.setAttribute("filtered", dao.countryFilter(request.getParameter("country")));
            request.setAttribute("headers",
                    Arrays.stream(Notepad.class.getDeclaredFields()).map(Field::getName).toList());
        }
        dao.closeConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
