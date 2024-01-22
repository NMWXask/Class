package org.klozevitz.classwork.db;

import jakarta.servlet.http.HttpServletRequest;
import org.klozevitz.classwork.model.Notepad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DbDao implements task2, task4, task5{
    private final String DB_URL = "jdbc:postgresql://localhost:5432/notes";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";
    private final String DB_DRIVER = "org.postgresql.Driver";
     private final Connection connection;

    public DbDao() {
        try {
            Class.forName(DB_DRIVER);
            this.connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("подключение невозможно");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не распакован");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notepad> all() {
        try {
            return getNotepadsListFromResultSet(
                    connection.createStatement().executeQuery("SELECT * FROM note;"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private List<Notepad> getNotepadsListFromResultSet(ResultSet result) throws SQLException {
        List<Notepad> all = new ArrayList<>();
        while (result.next()) {
            all.add(new Notepad(result));
        }
        return all;
    }

    @Override
    public List<String> countries() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT Distinct country FROM note");
       return getStringListFromResultSet(resultSet,"country");
    }

    @Override
    public Map<String, Integer> countriesSummary() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT country, count(id) as quantity from note group by country;");
        return getMapFromResult(resultSet,"country","quantity");
    }

    @Override
    public Map<String, Integer> brandSummary() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT brand, count(id) as quantity from note group by brand;");
        return getMapFromResult(resultSet,"brand","quantity");
    }

    @Override
    public String maxSummaryCountry() throws SQLException {
        String country = null;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT country\n" +
                "FROM note\n" +
                "GROUP BY country\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 1;");
        while (resultSet.next()){
          country =resultSet.getString("country");
        }
        return country;
    }

    // TODO: 18.01.2024  Исправить запрос на минимум
    @Override
    public String minSummaryCountry() throws SQLException {
        String country = null;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT country\n" +
                "FROM note\n" +
                "GROUP BY country\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 1;");
        while (resultSet.next()){
            country =resultSet.getString("country");
        }
        return country;
    }

    @Override
    public String maxSummaryBrand() throws SQLException {
        String brand = null;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT brand\n" +
                "FROM note\n" +
                "GROUP BY brand\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 1;");
        while (resultSet.next()){
            brand =resultSet.getString("brand");
        }
        return brand;
    }

    // TODO: 18.01.2024 Изменить запрос на мин
    @Override
    public String minSummaryBrand() throws SQLException {
        String brand = null;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT brand\n" +
                "FROM note\n" +
                "GROUP BY brand\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 1;");
        while (resultSet.next()){
            brand =resultSet.getString("brand");
        }
        return brand;
    }

    @Override
    public List<Notepad> getSoftCover() {
        try {
            return getNotepadsListFromResultSet(
                    connection.createStatement().executeQuery("SELECT * FROM note WHERE cover='soft'"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notepad> gethardCover() {
        try {
            return getNotepadsListFromResultSet(
                    connection.createStatement().executeQuery("SELECT * FROM note WHERE cover='hard'"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notepad> pageTypeFilter(String type) throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery(String.format("SELECT * FROM note WHERE type = '%s'", type));
        return getNotepadsListFromResultSet(resultSet);
    }

    @Override
    public List<String> pageType() throws SQLException {
        String query = "SELECT DISTINCT type FROM note;";
        ResultSet result = connection.createStatement().executeQuery(query);
        return getStringListFromResultSet(result, "type");
    }

    @Override
    public List<String> countrySelect() throws SQLException {
        String query = "SELECT DISTINCT country FROM note;";
        ResultSet result = connection.createStatement().executeQuery(query);
        return getStringListFromResultSet(result, "country");
    }




    @Override
    public List<Notepad> countryFilter(String country) throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery(String.format("SELECT * FROM note WHERE country = '%s'", country));
        return getNotepadsListFromResultSet(resultSet);
    }


    private Map<String,Integer> getMapFromResult(ResultSet result,String column1,String column2) throws SQLException {
        Map<String,Integer> resultMup = new HashMap<>();
        while (result.next()){
            resultMup.put(result.getString(column1),result.getInt(column2));
        }
        return resultMup;
    }
    private List<String> getStringListFromResultSet(ResultSet result, String column) throws SQLException {
        List<String> resultAsString = new ArrayList<>();
        while (result.next()) {
            resultAsString.add(result.getString(column));
        }
        return resultAsString;
    }

    @Override
    public Notepad add(Notepad notepad) {
        try {
            connection.createStatement().executeUpdate(getQueryFromNotepad(notepad));
            return notepad;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int idNote) {
        try {
            connection.createStatement().executeUpdate(String.format("DELETE FROM note WHERE id = %d",idNote));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private String getQueryFromNotepad(Notepad notepad) throws SQLException {

        return String.format("INSERT INTO note (brand,model,pagesAmount,cover,country,type)  VALUES ( '%s', '%s', %d, '%s', '%s', '%s')",
                 notepad.getBrand(), notepad.getModel(),
                notepad.getPagesAmount(), notepad.getCover(),
                notepad.getCountry(), notepad.getType());
    }

    private String getQueryFromUpdateNotepad(HttpServletRequest request,Notepad notepad) throws SQLException {
        Enumeration<String> c = request.getParameterNames();
        StringBuilder builder = new StringBuilder();
        while (c.hasMoreElements())
            builder.append(c.nextElement() + ",");
        builder.replace(builder.length() - 1, builder.length(), "");


        return String.format("UPDATE note SET %s = ('%s', '%s', %d, '%s', '%s', '%s') WHERE id = %d",builder.toString(),notepad.getBrand(), notepad.getModel(),
                notepad.getPagesAmount(), notepad.getCover(),
                notepad.getCountry(), notepad.getType(),request.getParameter("id"));
    }

    private int getCurrentId() throws SQLException {
        // в резалтсете получаем текущий id последней строки
        ResultSet result = connection.createStatement().executeQuery("SELECT max(id) FROM note;");
        result.next();
        //возвращаем "автоинкремент"
        return result.getInt("id") + 1;
    }

    @Override
    public List<String> covers() throws SQLException {
        String query = "SELECT DISTINCT cover FROM note;";
        ResultSet result = connection.createStatement().executeQuery(query);
        return getStringListFromResultSet(result, "cover");
    }

    @Override
    public List<String> pageTypes() throws SQLException {
        String query = "SELECT DISTINCT type FROM note;";
        ResultSet result = connection.createStatement().executeQuery(query);
        return getStringListFromResultSet(result, "type");
    }

    @Override
    public Notepad update(Notepad notepad,HttpServletRequest request) {
        try {
            connection.createStatement().executeUpdate(getQueryFromUpdateNotepad(request,notepad));
            return notepad;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
/* TODO: 19.01.2024 -> with */