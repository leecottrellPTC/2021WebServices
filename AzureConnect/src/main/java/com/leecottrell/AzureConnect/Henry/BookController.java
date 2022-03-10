package com.leecottrell.AzureConnect.Henry;

import java.sql.*;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @RequestMapping(value="/Henry", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> allBooks(){
        List response = new ArrayList<Book>();
        Book book = new Book();

        String dbConnectionString = "your connection string";
        try{
            Connection con = DriverManager.getConnection(dbConnectionString);
            Statement stmt = con.createStatement();
            String sql = "select * from book order by title";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                book = new Book();
                book.setTitle(rs.getString("Title"));
                book.setBookCode(rs.getString("bookCode"));
                book.setPaperback(rs.getString("paperback"));
                book.setStuff(rs.getString("stuff"));
                book.Type(rs.getString("type"));
                book.setPublisherCode(rs.getString("publisherCode"));
                response.add(book);
            }
        }
        catch(Exception ex){
            book.setTitle(ex.toString());
            response.add(book);
            return new ResponseEntity(book, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
