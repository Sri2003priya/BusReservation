package s1;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Booking {
String passengerName;
int busNo;
Date date;
 Booking(){
	 Scanner reader = new Scanner(System.in);
	 System.out.println("Enter name of passenger:");
	 passengerName = reader.next();
	 System.out.println("Enter Bus No:");
	 busNo = reader.nextInt();
	 System.out.println("Enter date dd-mm-yyyy");
	 String dateInput = reader.next();
	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 
	 try {
		 date = dateFormat.parse(dateInput);
	 }
	 catch(ParseException e){
		 e.printStackTrace();
	 }
 }
 public boolean isAvailable() throws SQLException {//ArrayList<Booking> bookings ,ArrayList<Bus> buses
	 BusDAO busdao = new BusDAO();
	 BookingDAO bookingdao = new BookingDAO();
	 int capacity = busdao.getCapacity(busNo);
//	 for(Bus bus:buses) {
//		 if(bus.getBusNo() == busNo) {
//			 capacity = bus.getCapacity();
//		 }
//	 }
	 int booked = bookingdao.getBookedCount(busNo,date);
//	 for(Booking b:bookings) {
//		 if(b.busNo == busNo && b.date.equals(date)) {
//			 booked++;
//		 }
//	 }
	 return booked<capacity;
 }
}
