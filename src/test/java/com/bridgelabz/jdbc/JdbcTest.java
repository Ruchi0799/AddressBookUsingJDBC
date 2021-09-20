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
    public void abilityToAddNewContactWithInsertDate() throws SQLException {
        AddressBookDBService addressBookDBService=new AddressBookDBService();
        addressBookDBService.addNewContact(7,"Karan","Kanchan","998736865","kk13@gmail,com",1,"Nashik","Maharashtra","400002",6);
    }



    @Test
    public void abilityToEditFirstName() {
        AddressBookDBService addressBookDBService=new AddressBookDBService();
        addressBookDBService.updateContactDataUsingStatement("Teju","Pratibha");
    }

    @Test
    public void findContact_AddedBetweenSpecificDateRange(){
        AddressBookDBService addressBookDBService=new AddressBookDBService();
        addressBookDBService.retrieveAccordingToDate("2018-01-01","2020-12-12");
    }

    @Test
    public void findContact_ByCityOrState(){
        AddressBookDBService addressBookDBService=new AddressBookDBService();
        addressBookDBService.retrieveByCityOrState("Navi Mumbai","Maharashtra");
    }
}
