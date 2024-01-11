package com.functions;

import java.util.Scanner;
import com.dao.*;
import comm.*;

public class adminLogin {

public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter username: ");
		String username = sc.next();
		
		System.out.println("Enter Password: ");
		String password = sc.next();
		
		TrainTicket dao = new TrainTicketImpl();
		
		String result = dao.adminLogin(username, password);
		
		System.out.println();
		System.out.println(result);
	}
	
}
