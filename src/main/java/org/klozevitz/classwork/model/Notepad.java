package org.klozevitz.classwork.model;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Notepad {
    private Integer id;
    private String brand;
    private String model;
    private  int pagesAmount;
    private String cover;
    private String country;
    private String type;

    public Notepad(String brand, String model, int pagesAmount, String cover, String country, String type) {
        this.id = null;
        this.brand = brand;
        this.model = model;
        this.pagesAmount = pagesAmount;
        this.cover = cover;
        this.country = country;
        this.type = type;
    }

    public Notepad(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.brand = result.getString("brand");
        this.model = result.getString("model");
        this.pagesAmount = result.getInt("pagesAmount");
        this.cover = result.getString("cover");
        this.country = result.getString("country");
        this.type = result.getString("type");
    }

    public Notepad(HttpServletRequest request) {
        this.brand = request.getParameter("brand");
        this.model = request.getParameter("model");
        this.pagesAmount = Integer.parseInt(request.getParameter("pagesAmount"));
        this.cover = request.getParameter("cover");
        this.country = request.getParameter("country");
        this.type = request.getParameter("type");
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPagesAmount() {
        return pagesAmount;
    }

    public void setPagesAmount(int pagesAmount) {
        this.pagesAmount = pagesAmount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Notepad{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", name='" + model + '\'' +
                ", pageAmount=" + pagesAmount +
                ", cover='" + cover + '\'' +
                ", country='" + country + '\'' +
                ", pageType='" + type + '\'' +
                '}';
    }

}
