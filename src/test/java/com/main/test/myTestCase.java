package com.main.test;

import JDBC.ConnectionFactory;
import JDBC.hello;
import SQL.*;
import Threads.SimpleThreadFactory;
//import org.junit.Test;
import Cache.*;
import org.junit.jupiter.api.*;


import Cache.CacheHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class myTestCase {
    CacheHelper temp2 = null;
    //This tests the cache and attaches a thread to the cache.
    @Test
    @Order(1)
    public void printCache() throws Exception{
        SimpleThreadFactory temp = SimpleThreadFactory.getInstance();
        temp2 = new CacheHelper();
        Thread thread = temp.getThread(temp2);
        thread.run();
        System.out.println("Active threads: " + temp.getSize());
        //temp2.myCache.put(1, "da one!");
        //String value = temp2.myCache.get(1);
        //System.out.println(value);
        //System.out.println("Lets wait for 5 seconds to see if it left the cache.");
        //TimeUnit.SECONDS.sleep(6);
        //value = temp2.myCache.get(1L);
        //System.out.println(value);
        temp.releaseConnection(thread);
        System.out.println("Active threads: " + temp.getSize());

    }

    //This creates an empty table, check dbeaver
    @Test
    @Order (2)
    public void createEmptyTable(){
        System.out.println(Create.createTable("public.test"));
    }

    //This creates a row to a specified table, check dbeaver
    @Test
    @Order (3)
    public void addRowToTable(){
        Vector<String> names = new Vector<>();
        names.add("test1");
        Vector<String> row = new Vector<>();
        row.add("hi");
        System.out.println(Create.createRow("test", names, row));
    }

    //This tests the add column method to the specified table with a specified data type
    @Test
    @Order (4)
    public void addColumnToTable(){
        System.out.println(Create.createColumn("public.test", "test2", "VARCHAR"));
    }



    //Test deleting a row from a table
    @Test
    @Order(5)
    public void deleteRow(){
        System.out.println(Delete.dropRow("test", "test1", "hi"));
    }

    //Test deleting a column from a table
    @Test
    @Order(6)
    public void deleteColumn(){
        System.out.println(Delete.dropColumn("test", "test2"));
    }

    //Test renaming a table
    @Test
    @Order(7)
    public void renameTable(){
        System.out.println(Update.updateTableName("test", "testtwo"));
    }

    //Test updating a row
    @Test
    @Order(8)
    public void updateRow(){

        System.out.println(Update.updateTableName("testtwo", "test"));
        addRowToTable();
        System.out.println(Update.updateRowValue("test", "test1", "hi", "newhi"));
    }

    //Test updating a column name
    @Test
    @Order(9)
    public void updateColumnName(){
        System.out.println(Update.updateColumn("test", "test1", "test2"));
    }

    //Test reading a table from the cache that isnt in it yet.
//    @Test
//    @Order(10)
//    public void readTable(){
//        temp2 = new CacheHelper();
//        System.out.println(Read.getTable("test", temp2).toString());
//    }

    //Test reading a table from the cache that isnt in it yet.
//    @Test
//    @Order(11)
//    public void transact(){
//        Vector<String> temp3 = new Vector<>();
//        String SQL = "ALTER TABLE test RENAME TO test4";
//        temp3.add(SQL);
//        Transactions.start(temp3);
//        Transactions.end();
//    }

    //This tests the drop table method
    @Test
    @Order (12)
    public void dropTable(){
        System.out.println(Delete.dropTable("test4"));
    }


}
