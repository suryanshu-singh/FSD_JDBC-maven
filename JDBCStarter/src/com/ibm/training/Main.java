package com.ibm.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		

		try {
			//Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver"); //Optional now
			
			//Connect to the db
			Connection dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/ibmtraining", "root", "");
			
//			System.out.println("Successfully Connected to DataBase...");
			
//			new Main().getAllData(dbCon);
//			new Main().insertData(dbCon);
			new Main().insertFromRuntime(dbCon);
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Exception while loading driver" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Exception while connecting to db : " + e.getMessage());
		}

	}
	
//	Gets all rows from the table : userDetails
	void getAllData(Connection dbCon) {
		// Create a Statement
		try {
			Statement stmt = dbCon.createStatement();
			
			//Write the query to fetch data from table: userDetails
			String fetchQry = "select * from userDetails";
			
			//Execute the query
			ResultSet rs = stmt.executeQuery(fetchQry);
			
			//Traverse through the ResultSet
			while(rs.next()) {
				System.out.print("User ID : " + rs.getInt("userID"));
				System.out.println(", User Name : " + rs.getString("userName"));
			}
			
			//Close the connection
			dbCon.close();
			
			
		} catch (SQLException e) {
			System.out.println("Issues creating the statement :" + e.getMessage());
		}
	}
	
	
	
	
	// Inserts a new row into the table: userDetails
	void insertData(Connection dbCon) {
		//Write the query to insert a new row in table
		String insertQry = "insert into userDetails values(78, 'Yukti', 'Bangalore')";
		
		//Create the Statement
		try {
			Statement stmt = dbCon.createStatement();
			
			//Execute the query
			if(stmt.executeUpdate(insertQry) > 0) {
				System.out.println("Successfully inserted a new row...");
			}
			else {
				System.out.println("Some issues while inserting...");
			}
			
		} catch (SQLException e) {
			System.out.println("Issues creating the statement :" + e.getMessage());
		}
	}
	
	
	
	void insertFromRuntime(Connection dbCon) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter your name and address");
		
		String userName = scan.nextLine();
		String userAddress = scan.nextLine();
		
		//Write the query to insert values in table: userDetails
		String insertQry = "insert into userDetails(userID, userName, userAddress) values"
				+ "(78,"
				+ "'" + userName + "'"
				+ ", '"+ userAddress + "'"
				+ ")";
		
		//Execute the query
		Statement stmt;
		try {
			stmt = dbCon.createStatement();
			if(stmt.executeUpdate(insertQry) > 0)
				System.out.println("Inserted ...");
			else
				System.out.println("Some issues");
		} catch (SQLException e) {
			System.out.println("Some issues while inserting from runtime : " + e.getMessage());
		}
		
		
		
		
		
		
		
		
	}
	

}
