package kitis.bakeryapp.bakeryapp;

import java.sql.*;
import java.util.ArrayList;

public class WorkDB {

    public static ArrayList getProductsList() {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakeryapp?serverTimezone=UTC", "root", "ZXCtiurin");
            Statement s = c.createStatement();
            ResultSet list = s.executeQuery("select * from catalog;");
            ArrayList<Product> productsArray = new ArrayList<>();

            while (list.next()) {
                Product product = new Product(
                  list.getString("name"),
                  list.getInt("count"),
                  list.getInt("price")
                );
                productsArray.add(product);
            }
            c.close();
            return productsArray;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void add(Product product) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakeryapp?serverTimezone=UTC", "root", "ZXCtiurin");
            Statement s = c.createStatement();
            String name = product.getName();
            int count = product.getCount();
            int price = product.getPrice();
            s.executeUpdate("insert into catalog (name,count,price) " +
                    "value ('"+name+"', "+count+", "+price+");");
            c.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Product product) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakeryapp?serverTimezone=UTC", "root", "ZXCtiurin");
            Statement s = c.createStatement();
            String name = product.getName();
            s.executeUpdate("delete from catalog where name='"+name+"';");
            c.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void change(Product product, String oldName) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakeryapp?serverTimezone=UTC", "root", "ZXCtiurin");
            Statement s = c.createStatement();
            String name = product.getName();
            int count = product.getCount();
            int price = product.getPrice();
            s.executeUpdate("update catalog set name='"+name+"', count='"+count+"', price='"+price+"' where name='"+oldName+"';");
            c.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
