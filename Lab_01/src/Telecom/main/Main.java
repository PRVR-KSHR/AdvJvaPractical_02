package Telecom.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import Telecom.pojo.Telecom;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/lab"; 
        String username = "root";
        String pwd = "praveer";

        Connection con = DriverManager.getConnection(url, username, pwd);
        Scanner sc = new Scanner(System.in);

        Telecom tel = new Telecom();

        int choice;

        do {
        	System.out.println("____________________________________");
        	System.out.println("---Welcome to InfraTel Insight---");
        	System.out.println("-------------------------------------");
            System.out.println("1. Insert Customer Information");
            System.out.println("2. Display Information");
            System.out.println("3. Update customer of expired phone number");
            System.out.println("4. Remove the customer record");
            System.out.println("5. Display customer, Telecompany with their phone number");
            System.out.println("6. Display the users of a specific telecompany");
            System.out.println("7. Search customer with the first letter of the name");
            System.out.println("8. Total customer present in telecom");
            System.out.println("9. Display data with the highest plan");
            System.out.println("10. Display data by rank (based on data)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    tel.insertTelecom(con, sc);
                    break;
                case 2:
                    tel.displayTelecom(con);
                    break;
                case 3:
                    tel.updateTelecom(con, sc);
                    break;
                case 4:
                    tel.removeTelecom(con, sc);
                    break;
                case 5:
                    tel.displayimportant(con);
                    break;
                case 6:
                    tel.displaytelecomp(con, sc);
                    break;
                case 7:
                    tel.displaycust(con, sc);
                    break;
                case 8:
                    tel.displayrows(con);
                    break;
                case 9:
                    tel.displayHighestPlan(con);
                    break;
                case 10:
                    tel.displayByRank(con, sc);
                    break;
                case 0:
                    System.out.println("Goodbye! Have a great day!");
                    System.out.println("___________________________________");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        while (choice != 0);

        sc.close();
        con.close();
    }
}
