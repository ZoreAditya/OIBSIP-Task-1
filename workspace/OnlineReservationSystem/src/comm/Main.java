package comm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.dao.TrainTicket;
import com.dao.TrainTicketImpl;
import comm.DBconnector;

public class Main {
	
	public static void main(String[] args) {
		
		int check = 1;
		
		while(check !=2)
		{
			
			System.out.println("             ------------------------------------------------------------------------         ");
			System.out.print(" ********** |  Welcome To  Maharashtra Railway Catering and Tourism Corporation Limited    | **********\n");
			System.out.println("             ------------------------------------------------------------------------            ");
			
			Scanner sc = new Scanner(System.in);
			TrainTicket train = new TrainTicketImpl();
			
			System.out.println("Select one of the option:");
			System.out.println();
			System.out.println("1. Co-ordinator");
			System.out.println("2. Passenger");
			System.out.println();
			
			System.out.println("Enter your choice:");
			int choice = sc.nextInt();
			
			if(choice==1)
			{
				System.out.println();
				System.out.println("Enter Username: ");
				String username = sc.next();
				
				System.out.println("Enter Password: ");
				String password = sc.next();
				
				
				String login = train.adminLogin(username, password);
				
				System.out.println(login);
				
				if(login.equals("Wrong Credentials!"))
				{
					check = 1;
					continue;
				}
				
				System.out.println("1. Insert Train Details");
				System.out.println("2. Go back");
				System.out.println("3. Exit");
				
				System.out.println();
				System.out.println("Enter your choice:");
				int insertorNot = sc.nextInt();
				
				if(insertorNot==1)
					
				{
					System.out.println("Enter TrainNo: ");
					int busno = sc.nextInt();
			
					System.out.println("Enter TrainName: ");
					String trainname = sc.next();
					
					System.out.println("Enter Source: ");
					String source = sc.next();
					
					System.out.println("Enter Destination: ");
					String destination = sc.next();
					
					System.out.println("Enter TrainType: ");
					String traintype = sc.next();
					
					System.out.println("Enter Seats Avilable: ");
					int seats = sc.nextInt();
					
					System.out.println("Enter ArrivalTime: ");
					String arrivalTime = sc.next();
					
					System.out.println("Enter DepartureTime: ");
					String departureTime = sc.next();
					
					
					String result = train.insertBusDetails(busno,trainname, source, destination, traintype, seats, arrivalTime, departureTime);
					
					System.out.println(result);
					
				}
				else if(insertorNot==2)
				{
					check = 1;
				}
				else if(insertorNot==3)
				{
					check = 2;
				}
				else
				{
					System.out.println("Please Enter Correction Information Next Time!");
				}
			}
			else if(choice ==2)
			{
				System.out.println();
				System.out.println("1. Reserve Ticket");
				System.out.println("2. Cancel Ticket");
				System.out.println("3. Go back");
				System.out.println("4. Exit");
				
				
				System.out.println();
				System.out.println("Enter your choice: ");
				int ticketStatus = sc.nextInt();
				
				if(ticketStatus==1)
				{
					System.out.println();
					
					try(Connection conn =DBconnector.provideConnection()) {
						
						
						PreparedStatement ps = conn.prepareStatement("Select * from railwaydetails");
						
						ResultSet rs = ps.executeQuery();
						System.out.println("=========================================");
						System.out.println("TRAIN-NO SEATS SOURCE  DESTINATION");
						while(rs.next())
						{
							
							System.out.print("  "+rs.getInt("TrainNo")+"     ");
							
							System.out.print(rs.getInt("Seats")+"   ");
							
							System.out.print(rs.getString("source")+"  ");
							System.out.print(rs.getString("destination"));
							
							
							
							System.out.println();
						}
						System.out.println("==========================================");
						
					} catch (Exception e) {
						System.out.println(e.getMessage());
					} 
					
					System.out.println();
					System.out.println("Enter Source Location: ");
					String source = sc.next();
					
					System.out.println("Enter Destination Location: ");
					String destination = sc.next();
					
					System.out.println("Enter Your Name: ");
					String name = sc.next();
					
					System.out.println("Enter Your Age: ");
					int age = sc.nextInt();
					
					System.out.println("Enter no.of seats: ");
					int num = sc.nextInt();
					
					
					String res = train.confirmTicket(source, destination, name, age,num);
					
					System.out.println();
					System.out.println(res);
					check = 1;
					
					
				}
				else if(ticketStatus==2)
				{
					System.out.println("Enter TrainNo: ");
					int trainno = sc.nextInt();
					
					System.out.println("Enter Name: ");
					String name = sc.next();
					
					
					String result = train.ticketCancel(trainno, name);
					
					
					System.out.println();
					
					System.out.println(result);
					
				}
				else if(ticketStatus==3)
				{
					check = 1;
				}
				else if(ticketStatus==4)
				{
					check = 2;
				}
				else
				{
					System.out.println("Please Enter Correction Information Next Time!");
				}
				
			}
			else
			{
				System.out.println("Please Enter Correction Information Next Time!");
			}
			
		}
		
	    System.out.println("============================");
	    System.out.println("Thank you for visiting us !");
	    System.out.println("============================");
		
		
	}
}