package Telecom.pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Telecom {

    public Telecom() {
        super();
    }

    // Inserting information in the table
    public void insertTelecom(Connection con, Scanner sc) throws SQLException {
        Statement st = con.createStatement();
        System.out.println("Enter Customer Id: ");
        int id = sc.nextInt();

        System.out.println("Enter Customer Name: ");
        String name = sc.next();

        System.out.println("Enter Phone Number: ");
        String phoneStr = sc.next();
        long phone = Long.parseLong(phoneStr);

        System.out.println("Enter Tel-Company Name: ");
        String telecom_name = sc.next();

        System.out.println("Enter Plan price: ");
        int plan = sc.nextInt();

        System.out.println("Enter Data(gb): ");
        double data = sc.nextDouble();

        String query = String.format("INSERT INTO telecom VALUES(%d, '%s', %d, '%s', %d, %f)", id, name, phone, telecom_name, plan, data);

        int rowsAffected = st.executeUpdate(query);
        System.out.println(rowsAffected + " Information successfully stored...");
    }

    // Display the information of telecom
    public void displayTelecom(Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM telecom");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getLong(3) + "  " + rs.getString(4) + "  " + rs.getInt(5) + "  " + rs.getDouble(6));
        }
    }

    // Update the customer of expired or blocked phone number
    public void updateTelecom(Connection con, Scanner sc) throws SQLException {
        Statement st = con.createStatement();
        System.out.println("Enter the Phone number: ");
        String phoneStr = sc.next();
        long phone = Long.parseLong(phoneStr);
        System.out.println("Enter transferred-Customer Name: ");
        String name = sc.next();
        String query = String.format("UPDATE telecom SET name='%s' WHERE phone = %d", name, phone);
        int rowsAffected = st.executeUpdate(query);
        System.out.println(rowsAffected + " Information updated...");
    }

    // Remove the customer record from table with the help of their id
    public void removeTelecom(Connection con, Scanner sc) throws SQLException {
        Statement st = con.createStatement();
        System.out.println("Enter Customer Id: ");
        int id = sc.nextInt();
        int rowsAffected = st.executeUpdate(String.format("DELETE FROM telecom WHERE id = %d", id));
        System.out.println(rowsAffected + " Record deleted...");
    }

    // Display the column of customer name, phone, and their telecompany
    public void displayimportant(Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rt = st.executeQuery("SELECT name, phone, telecom_name FROM telecom");
        while (rt.next()) {
            System.out.println(rt.getString(1) + "  " + rt.getLong(2) + "  " + rt.getString(3));
        }
    }

    // Displaying the data based on telecompany name
    public void displaytelecomp(Connection con, Scanner sc) throws SQLException {
        Statement st = con.createStatement();
        System.out.println("Enter Telecom Name: ");
        String telname = sc.next();
        String query = String.format("SELECT * FROM telecom WHERE telecom_name = '%s'", telname);
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getLong(3) + "  " + rs.getString(4) + "  " + rs.getInt(5) + "  " + rs.getDouble(6));
        }
    }

    // Displaying the data of the customer based on their name alphabet or character
    public void displaycust(Connection con, Scanner sc) throws SQLException {
        Statement st = con.createStatement();
        System.out.println("Enter the first letter: ");
        String custname = sc.next();
        String query = String.format("SELECT * FROM telecom WHERE name LIKE '%s%%'", custname);
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getLong(3) + "  " + rs.getString(4) + "  " + rs.getInt(5) + "  " + rs.getDouble(6));
        }
    }

    // Displaying the number of customers present in telecom data
    public void displayrows(Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM telecom");
        if (rs.next()) {
            System.out.println(rs.getInt(1));
        }
    }
    
 // Displaying the data of the customer with the highest plan
    public void displayHighestPlan(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String query = "SELECT * FROM telecom WHERE data IN (SELECT MAX(data) FROM telecom)";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getLong(3) + "  " + rs.getString(4) + "  " + rs.getInt(5) + "  " + rs.getDouble(6));
        }
    }
    
 // Displaying the data based on rank order of data
    public void displayByRank(Connection con, Scanner sc) throws SQLException {
        Statement st = con.createStatement();

        System.out.println("Enter the rank (n): ");
        int n = sc.nextInt();
        
        String query = String.format("SELECT * FROM (SELECT id, name, phone, plan, data, RANK() OVER (ORDER BY data DESC) AS ranking FROM telecom) AS ranked WHERE ranking = %d", n);

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getLong(3) + "  " + rs.getInt(4) + "  " + rs.getDouble(5));
        }
    }
}
