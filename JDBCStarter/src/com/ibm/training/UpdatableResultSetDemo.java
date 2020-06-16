package com.ibm.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatableResultSetDemo {
	
	Connection dbCon;
	
	public UpdatableResultSetDemo() {
		try {
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/ibmtraining", "root", "");
		} catch (SQLException e) {
			System.out.println("Some issues while connecting : " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		
		new UpdatableResultSetDemo().fetchAndUpdate();

	}

	private void fetchAndUpdate() {
		
		String qryFetch = "select * from user_details";
		
		
		PreparedStatement pstmt;
		try {
			pstmt = dbCon.prepareStatement(qryFetch, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			//Execute the query
			ResultSet rs = pstmt.executeQuery();
			
			//Traverse through the data
			while(rs.next()) {
				if(rs.getString("userName").trim().equals("Suryanshu")) {
					
					//Update the resultset at this position
					rs.updateString(1, "Shashank Sharma");

					//Commit the changes
					rs.updateRow();
				}
			}
			
			System.out.println("Successfully updated the row...");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
