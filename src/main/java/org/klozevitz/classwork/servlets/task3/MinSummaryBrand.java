package org.klozevitz.classwork.servlets.task3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.db.task2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/min_summary_brand")
public class MinSummaryBrand extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            completeRequest(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/templates/MinSumNoteBrand.jsp");
        dispatcher.forward(request, response);
    }


    private void completeRequest(HttpServletRequest request) throws SQLException {
        task2 dao = new DbDao();
        String minNoteOfBrand = dao.minSummaryBrand();
        try {
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("minNoteOfBrand", minNoteOfBrand);
    }

    public void destroy() {
    }
}
