package com.bridgelabz.jdbc;

import java.sql.*;

public class AddressBookDBService {
    private String url = "jdbc:mysql://localhost:3306/addressbook_system?useSSL=false";
    private String username = "root";
    private String password = "ruchi";
    Connection con;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println(Class.forName("com.mysql.cj.jdbc.Driver"));

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            System.out.println("Connecting to database"+url);
            con= DriverManager.getConnection(url,username,password);
            System.out.println("Connection is successful!!"+con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }

    public void readData(){
        String sql="SELECT * FROM contact;";
        try {
            Connection connection=this.getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(
                        resultSet.getString(1)+" "+
                                resultSet.getString(2)+" "+
                                resultSet.getString(3)+" "+
                                resultSet.getString(4)+" "+
                                resultSet.getString(5)+" "+
                                resultSet.getString(6));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
