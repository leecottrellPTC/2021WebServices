package com.leecottrell.mysqldemo.Customer;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;

@RestController
public class CustomerController {
    @RequestMapping(value="/Customer", method=RequestMethod.GET)
    public ResponseEntity<List<Customer>> allCustomers(){
        List response = new ArrayList<Customer>();

        String SQL = "Select * from customer";
        Customer cust = new Customer();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
      
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel",
                 "username", "a,plain3");
            Statement stmt = conn.createStatement();
            ResultSet records = stmt.executeQuery(SQL);
            while(records.next()){
                cust = new Customer();
                cust.setCustID(records.getInt("custID"));
                cust.setFname(records.getString("fname"));
                cust.setLname(records.getString("lname"));
                cust.setPayDetails(records.getString("payDetails"));
                response.add(cust);

            }

        }
        catch(Exception ex){
            cust.setFname(ex.toString());

           return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity(response, HttpStatus.OK);
        
    }
}
