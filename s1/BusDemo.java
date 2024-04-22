package s1;
import java.sql.SQLException;
import java.util.*;
public class BusDemo {

	public static void main(String args[]) {
		
//		ArrayList<Bus> buses = new ArrayList<Bus>();
//		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
//		buses.add(new Bus(1,true,2));
//		buses.add(new Bus(2,false,50));
//		buses.add(new Bus(3,true,48));
		
		BusDAO busdao = new BusDAO();
		try {
			
		
		busdao.displayBusInfo();
		
		
	 int userOpt = 1;
		Scanner reader = new Scanner (System.in);
//		for(Bus b:buses) {
//		b.displayBusInfo();
//	}
	
		while(userOpt == 1) {
			System.out.println("Enter 1 to Book and 2 to exit");
			userOpt = reader.nextInt();
			if(userOpt == 1) {
				Booking booking = new Booking();
				if(booking.isAvailable()) {
					BookingDAO bookingdao = new BookingDAO();
					bookingdao.addBooking(booking);
//					bookings.add(booking);
					System.out.println("Your Booking is confirmed");
				}
				else {
					System.out.println("Sorry. Bus is full. Try another bus or date.");
				}
			}
			}
		reader.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
