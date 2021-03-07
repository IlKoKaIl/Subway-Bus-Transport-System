package transitapp;

import java.util.ArrayList;

/**
 * This class observes CardHolder and TravelCard classes to update trip data 
 *
 */
public class TransitStaff{
	
	private ArrayList<CardHolder> cardholders = new ArrayList<CardHolder>();
	private static TransitStaff transitstaff = new TransitStaff();
	private double revenue = 0.0;
	private ArrayList<Trip> trips = new ArrayList<Trip>();
	
	/**
	 * Returns a list of CardHolders in the system
	 * 
	 * @return a list of CardHolders in the system
	 */
	public ArrayList<CardHolder> getCardHolders(){
		return cardholders;
	}
	
	/**
	 * Adds a CardHolder cardholder to the ArrayList cardholders.
	 * 
	 * @param cardholder to be added
	 */
	public void addCardHolder(CardHolder cardholder) {
		cardholders.add(cardholder);
	}
	
	/**
	 * Checks whether id is not a unique CardHolderID in the ArrayList cardholders.
	 * 
	 * @param id to be checked whether it is unique.
	 * @return Returns false if there is a CardHolder in cardholders with CardHolderID id.
	 * else, returns true
	 */
	public boolean checkUniqueCardHolder(String id) {
		for (CardHolder cardholder: cardholders) {
			if (cardholder.getID().toLowerCase().equals(id.toLowerCase())) {
				return false;
			}
		}
		return true;
	}
	
	public void removetrip(Trip trip) {
		trips.remove(trip);
	}
	
	/**
	 * Returns an instance of transitstaff
	 * @return a instance of transitstaff
	 */
	public static TransitStaff getInstance() {
		return transitstaff;
	}
	
	/**
	 * 
	 * @param tripfee: a double representing the fee for a trip
	 * @param trip: a Trip data type
	 * 
	 * Adds the fees collected from a trip fees to revenue
	 */
	public void updatedata(double tripfee, Trip trip) {
		this.revenue += tripfee;
		trips.add(trip);
	}
	
	/**
	 * return the total revenue made from all trips
	 * 
	 * @return the total revenue made from all the trip fees
	 */
	public String toString() {
		return "total revenue " + revenue + " total " + trips.size() + " number of trips";
	}

}
