package org.klozevitz.classwork;

import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.model.Notepad;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.stream(Notepad.class.getDeclaredFields()).map(Field::getName).toList());

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/notes", "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT country, sum(pagesamount) as pagesAmount from note group by country");
            Map<String, Integer> resultMup = new HashMap<>();
            while (result.next()) {
                resultMup.put(result.getString("country"), result.getInt("pagesAmount"));
            }

            Set<String> country = resultMup.keySet();
            List<Integer> valueList = new ArrayList<>(resultMup.values());

            System.out.println(country);
            System.out.println(valueList);


            System.out.println(resultMup);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
