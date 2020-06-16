package com.ibm.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementDemo {
	
	Connection dbCon;
	PreparedStatement pstmt;
	
	

	public PreparedStatementDemo() {
		try {
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/ibmtraining", "root", "");
			
		} catch (SQLException e) {
			System.out.println("Some issues while connecting : " + e.getMessage());
		}
	}

	
	void createOurStatement(String qry) {
		try {
			pstmt = dbCon.prepareStatement(qry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		PreparedStatementDemo demo = new PreparedStatementDemo();
		
//		demo.queryTable();
		
		demo.updateData();
		
		
	}



	private void queryTable() {
		//Write the query to fetch details from table
		String fetchQry = "select * from userDetails where userID=?";
		
		try {
//			pstmt = dbCon.prepareStatement(fetchQry);
			this.createOurStatement(fetchQry);
			
			//Put in the values for the placeholder
			pstmt.setInt(1, 4);
			
			//Execute the query
			ResultSet rs = pstmt.executeQuery();
			
			//Traverse through the result
			while(rs.next()) {
				System.out.println("Name: " + rs.getString("userName"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("Issues while creating the prepared statement : " + e.getMessage());
		}
		
		//Close the connection
		try {
			dbCon.close();
		} catch (SQLException e) {
			System.out.println("Issues while closing the connection : " + e.getMessage());
		}
		
	}
	
	
//	Update table: userDetails
	void updateData() {
		//Write the query to update Table
		String updateQry = "update userDetails set userName= ? where userID = ?";
		
		try {
		pstmt = dbCon.prepareStatement(updateQry);
		
		//Put values into the pstmt
		pstmt.setString(1, "Mayank Awasthi");
		pstmt.setInt(2, 2);
		
		//Execute the query
		if(pstmt.executeUpdate() > 0)
				System.out.println("Updated successfully...");
		else
			System.out.println("Some issues while updating the row...");
		} catch (SQLException e) {
			System.out.println("Issues while creating the prepared statement : " + e.getMessage());
		}
	}
	
	
	
	
	
	
	

}
