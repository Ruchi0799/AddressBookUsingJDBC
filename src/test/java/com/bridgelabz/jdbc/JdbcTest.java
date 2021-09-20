package com.bridgelabz.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcTest {
    @Test
    public void getDB_Connection() {
        Connection dbConnection=new AddressBookDBService().getConnection();
        System.out.println(dbConnection);
    }

    @Test
    public void givenEmployeePayrollInDb_WhenRetrieved_ShouldMatchEmployeeCount() {
        AddressBookDBService addressBookDBService=new AddressBookDBService();
        addressBookDBService.readData();
    }

    @Test
    public void abilityToAddNewContact() throws SQLException {
        AddressBookDBService addressBookDBService=new AddressBookDBService();
        addressBookDBService.addNewContact(5,"xyz","yzh","9987364545","xyz07@gmail,com",2,"Hyd","ddc","400502",5);
    }


    @Test
    public void abilityToEditFirstName() {
        AddressBookDBService addressBookDBService=new AddressBookDBService();
        addressBookDBService.updateContactDataUsingStatement("Teju","Pratibha");
    }
}
