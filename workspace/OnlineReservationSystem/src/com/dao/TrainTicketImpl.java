package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import comm.DBconnector;
import com.functions.*;

public  class TrainTicketImpl implements TrainTicket {

	public String insertBusDetails( int TrainNo,String TrainName,String source, String destination,
			String TrainType, int Seats, String ArrivalTime, String DepartureTime)
	{
		String message = "Not inserted !";
		
		try (Connection conn = DBconnector.provideConnection()) {
			
			PreparedStatement ps =  conn.prepareStatement("insert into railwaydetails(TrainNo,TrainName,Source,Destination, "
					+ "TrainType, Seats, ArrivalTime, DepartureTime) values (?,?,?,?,?,?,?,?) ");
			
			ps.setInt(1, TrainNo);
			ps.setString(2, TrainName);
			ps.setString(3, source);
			ps.setString(4, destination);
			ps.setString(5, TrainType);
			ps.setInt(6,Seats);
			ps.setString(7, ArrivalTime);
			ps.setString(8, DepartureTime);
			
			int x = ps.executeUpdate();
			
			if(x>0)
			{
				message = "Train Details Inserted Sucessfully !";
			}
			
			
			
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		
		
		
		return message;
	}

	@Override
	public String confirmTicket(String source, String destination, String name, int age,int num) {
		
		String message = "Seat Not Available !";
		
		try (Connection conn = DBconnector.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select Seats,TrainNo from railwaydetails "
					+ "where source = ? and destination = ?");
			
			ps.setString(1, source);
			ps.setString(2, destination);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				int x = rs.getInt("Seats");
				
				if(x>0)
				{
					PreparedStatement ps2 = conn.prepareStatement("Update railwaydetails set Seats = Seats-? where source = ?");
					
					ps2.setInt(1,num);
					ps2.setString(2, source);
					
					ps2.executeUpdate();
					
					PreparedStatement ps3 = conn.prepareStatement("insert into customerdetails values(?,?,?)");
					
					ps3.setInt(1, rs.getInt("TrainNo"));
					ps3.setString(2, name);
					ps3.setInt(3, age);
					
					
					
					ps3.executeUpdate();
					
					PreparedStatement ps4  = conn.prepareStatement("select * from customerdetails where CustomerName = ?");
					
					ps4.setString(1, name);
					
					ResultSet rs2 = ps4.executeQuery();
					
					if(rs2.next())
					{
						System.out.println();
						System.out.println("TrainNo: "+ rs2.getInt("TrainNo"));
						System.out.println("Passenger Name: "+rs2.getString("CustomerName"));
						System.out.println("Passenger Age: "+rs2.getInt("CustomerAge"));						
					}
					message = "Ticked confirmed !";
				}
				else {
					System.out.println(message);
				}
				
				
			}
			else 
			{
				message = "Please Enter Valid Location !";
			}
			
			
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		
		
		
		
		
		
		return message;
	}

	@Override
	public String ticketCancel(int trainno, String name) {
		
		String message = "Ticket Canellation Unsucessful !";
		
		
		try (Connection conn = DBconnector.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("delete from customerdetails where CustomerName = ? and TrainNo = ?");
			
			ps.setString(1, name);
			ps.setInt(2, trainno);
			
			int x= ps.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement("Update railwaydetails set seats = seats+1 where TrainNo = ?");
			
			ps2.setInt(1, trainno);
			
			ps2.executeUpdate();
			
			if(x>0)
			{
				message = "Ticket Cancellation Sucessful !";
			}
			
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public String adminLogin(String username, String password) {
		
		String message = "Wrong Credentials!";
		
		try (Connection conn = DBconnector.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Select * from adminlogin where username = ? and password = ?");
			
			ps.setString(1, username);
			ps.setString(2,password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				System.out.println();
				System.out.println("Welcome "+rs.getString("username")+"!");
				message = "";
			}
			
			
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		return message;}
	
}
