package component;
import java.util.Scanner;


public class InsertDetails {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter BusNo: ");
        int busno = sc.nextInt();

        System.out.println("Enter BusName: ");
        String busname = sc.next();

        System.out.println("Enter Source: ");
        String source = sc.next();

        System.out.println("Enter Destination: ");
        String destination = sc.next();

        System.out.println("Enter BusType: ");
        String bustype = sc.next();

        System.out.println("Enter Seats Avilable: ");
        int seats = sc.nextInt();

        System.out.println("Enter ArrivalTime: ");
        String arrivalTime = sc.next();

        System.out.println("Enter DepartureTime: ");
        String departureTime = sc.next();


        TrainTicket dao = new TrainTicketImpl();

        String result = dao.insertBusDetails(busno,busname, source, destination, bustype, seats, arrivalTime, departureTime);

        System.out.println(result);



    }

}
