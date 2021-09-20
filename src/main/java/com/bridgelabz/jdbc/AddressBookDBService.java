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
                                resultSet.getString(6)+" "+
                                resultSet.getString(7));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



    public void readAllData(){
        String sql="SELECT contact.firstname,contact.lastname,address.city,address.state FROM contact inner join address on address.contact_id=contact.contact_id;";
        try {
            Connection connection=this.getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(
                        resultSet.getString(1)+" "+
                                resultSet.getString(2)+" "+
                                resultSet.getString(3)+" "+
                                resultSet.getString(4));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public int updateContactDataUsingStatement(String oldName, String newName) {
        String sql=String.format("update contact set firstname='%s' where firstname='%s';",newName,oldName);
        try(Connection connection=this.getConnection()) {
            Statement statement=connection.createStatement();
            //readAllData();
            int result=statement.executeUpdate(sql);
            readAllData();
            return statement.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }





    public void addNewContact(int id,String firstname,String lastName,String phoneno,String email,int typeId,String city,String state,String zip,int addId) throws SQLException {
        Connection connection = null;
        connection = this.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        String sql = String.format("insert into contact(contact_id,firstname,lastname,phone_no,email,insertDate,type_id) values (%d,'%s','%s','%s','%s',DATE(NOW()),%d);", id,firstname, lastName, phoneno, email,typeId);
        try{
            //Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);

           // readData();
           // return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            connection.rollback();
        }
        try {
            String sql1 = String.format("insert into address(address_id,city,state,zip,contact_id) values (%d,'%s','%s','%s',%d);", addId,city, state, zip,id);
            int result1=statement.executeUpdate(sql1);
        }
        catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
        }
        try {
            connection.commit();
            readAllData();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            if(connection!=null)
            {
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }



       // return 0;
    }


    public void retrieveAccordingToDate(String startdate, String enddate) {
        String sql=String.format("select * from contact where insertDate BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE);",startdate,enddate);
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
                                resultSet.getString(6)+" "+
                                resultSet.getString(7));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
