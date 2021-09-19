package com.bridgelabz.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
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
}
