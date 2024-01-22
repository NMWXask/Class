package org.klozevitz.classwork.db;

import jakarta.servlet.http.HttpServletRequest;
import org.klozevitz.classwork.model.Notepad;

import java.sql.SQLException;
import java.util.List;

public interface task5 {
    Notepad add(Notepad notepad);
    void remove(int id);
    List<String> covers() throws SQLException;
    List<String> pageTypes() throws SQLException;


    Notepad update(Notepad notepad, HttpServletRequest request);

    void closeConnection() throws SQLException;
}
