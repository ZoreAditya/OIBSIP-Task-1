package com.dao;
import comm.DBconnector;
import com.functions.*;

public  interface TrainTicket {
	
	public String insertBusDetails(int trainNo,String TrainName,String source, String destination,
			String TrainType, int Seats, String ArrivalTime, String DepartureTime);
	
	public String confirmTicket(String source, String destination,String name, int age,int num);
	
	public String ticketCancel(int trainno, String name);
	
	public String adminLogin(String username, String password);
	

}
