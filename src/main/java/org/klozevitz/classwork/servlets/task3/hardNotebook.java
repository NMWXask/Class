package org.klozevitz.classwork.servlets.task3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.db.task2;
import org.klozevitz.classwork.model.Notepad;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
@WebServlet("/hard_notebook")
public class hardNotebook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        completeRequest(req);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/templates/hardNotebook.jsp");
        dispatcher.forward(req, resp);
    }
    private void completeRequest(HttpServletRequest request) {
        task2 dao = new DbDao();
        List<Notepad> hard = dao.gethardCover();
        try {
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("headers",
                Arrays.stream(Notepad.class.getDeclaredFields()).map(Field::getName).toList());
        request.setAttribute("hard", hard);
    }

    public void destroy() {
    }
}
