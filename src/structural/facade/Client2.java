package structural.facade;

import java.util.ArrayList;
import java.util.Date;

/**
 * Let's take a travel agent site for example, that allows you to book hotels and flights. 
 * We have a HotelBooker And a FlightBooker.
 * Both of these have Hotel and Flight data types, which the client has knowledge about. 
 * They could be provided in the same package as the Facade for example.
 * The TravelFacade class allows the user to get their Hotel and Flight information in one call.
 * All that the client needs to worry about is the Facade class.
 * As you can see, it's just a simple approach to encapsulating data.
 */
public class Client2
{

	public static void main(String[] args)
	{
		// Client should input the to and from dates.
		Date from = null;
		Date to = null;

		TravelFacade facade = new TravelFacade(); 
		facade.getFlightsAndHotels(from, to);
	}
}

class TravelFacade
{

	private HotelBooker hotelBooker;
	private FlightBooker flightBooker; 

	public void getFlightsAndHotels(Date from, Date to)
	{
		ArrayList<Flight> flights = flightBooker.getFlightsFor(from, to);
		ArrayList<Hotel> hotels = hotelBooker.getHotelNamesFor(from, to);

		//process and return

	}

}

class FlightBooker
{
	public ArrayList<Flight> getFlightsFor(Date from, Date to) 
	{
		//returns flights available in the particular date range
		return null;
	}

}

class HotelBooker
{

	public ArrayList<Hotel> getHotelNamesFor(Date from, Date to) 
	{
		//returns hotels available in the particular date range
		return null;

	}

}

class Flight{
	
}

class Hotel{
	
}