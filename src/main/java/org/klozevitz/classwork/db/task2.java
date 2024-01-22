package org.klozevitz.classwork.db;

import org.klozevitz.classwork.model.Notepad;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface task2 {
    List<Notepad> all();
    List<String> countries() throws SQLException;
    Map<String, Integer> countriesSummary() throws SQLException;
    Map<String, Integer> brandSummary() throws SQLException;
    String maxSummaryCountry() throws SQLException;
    String minSummaryCountry() throws SQLException;
    String maxSummaryBrand() throws SQLException;
    String minSummaryBrand() throws SQLException;
    List<Notepad> getSoftCover();
    List<Notepad> gethardCover();
    void closeConnection() throws SQLException;
}
