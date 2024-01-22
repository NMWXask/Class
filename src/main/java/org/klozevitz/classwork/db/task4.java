package org.klozevitz.classwork.db;

import org.klozevitz.classwork.model.Notepad;

import java.sql.SQLException;
import java.util.List;

public interface task4 {
    List<Notepad> pageTypeFilter(String type) throws SQLException;

    List<String> pageType() throws SQLException;
    List<String> countrySelect() throws SQLException;
    List<Notepad> countryFilter(String country) throws SQLException;
    void closeConnection() throws SQLException;
}
