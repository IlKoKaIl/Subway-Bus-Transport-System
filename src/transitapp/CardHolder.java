package transitapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class stores all data that may be relevant to a CardHolder
 * Creates new CardHolders and can remove old ones
 * 
 */
public class CardHolder {
	
	private String name;
	private String email;
	private Queue<Trip> trips = new LinkedList<Trip>();
	private ArrayList<Trip> totaltrips = new ArrayList<Trip>();
	private ArrayList<TravelCard> cards = new ArrayList<TravelCard>();
	private boolean isTravel = false;
	private String id;
	
	/**
	 * Constructs a new CardHolder storing their name, email, and a unique CardHolder id.
	 * 
	 */
	public CardHolder(String name, String email, String id) {
		this.name = name;
		this.email = email;
		this.id = id;
		System.out.println("CardHolderID " + this.id + " (Name: " + this.name
				+ " , email: "+ this.email + ") is succesfully created");
	}
	
	/**
	 * Allows a current CardHolder to change their name
	 * 
	 * @param newName: the new name the CardHolder wishes to change to
	 */
	public void changeName(String newName) {
		this.name = newName;
		System.out.println("Name has been successfully changed to " + newName);
	}
	
	/**
	 * returns the CardHolderID of a cardholder
	 * 
	 * @return the CardHolderID of a cardholder
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * returns the name of a cardholder
	 * 
	 * @return the name of a CardHolder
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the email of a CardHolder
	 * 
	 * @return the email of a CardHolder
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Returns if any TravelCard in cards has an id cardID
	 * 
	 * @param cardID to be checked
	 * @return returns if any TravelCard in cards has an id cardID
	 */
	public boolean checkUniqueCard(String cardID) {
		for (TravelCard card: cards) {
			if (card.getCardID().toLowerCase().equals(cardID.toLowerCase())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the TravelCard with ID cardID
	 * 
	 * @param cardID
	 * @return
	 */
	public TravelCard getCard(String cardID) {
		for (TravelCard card: cards) {
			if (card.getCardID().toLowerCase().equals(cardID.toLowerCase())){
				return card;
			}
		}
		return null;
	}
	
	/**
	 * Allows a CardHolder to create a new TravelCard
	 * 
	 * @param cardID: a string representing the ID for a new TravelCard
	 */
	public void addCard() {
		String id = "CARD";
		int idcount = 1;
		String tempid = id + String.valueOf(idcount);
		boolean uniqueid = checkUniqueCard(tempid);
		
		if (uniqueid == false) {
			while(uniqueid == false) {
				idcount += 1;
				tempid = id + String.valueOf(idcount);
				uniqueid = checkUniqueCard(tempid);
			}
		}
		id = tempid.toUpperCase();
		cards.add(new TravelCard(id));
		System.out.println("New TravelCard " + id + " has been created for " + this.id);
	}
	
	/**
	 * Allows for a CardHolder to remove an existing TravelCard
	 * 
	 * @param cardID: a string representing the ID for an existing TravelCard
	 */
	public void removeCard(String cardID) {
		boolean removed = false;
		for(Iterator<TravelCard> itr = cards.iterator(); itr.hasNext();) {
			TravelCard card = itr.next();
			if(card.getCardID().equals(cardID)) {
				itr.remove();
				removed = true;
				System.out.println("TravelCard " + cardID + " has been removed");
			}
		}
		
		if (removed == false) {
			System.out.println("No TravelCard with " + cardID + " was found in your account");
		}
	}
	
	/**
	 * Allows a CardHolder to suspend (deactivate) a TravelCard
	 * 
	 * @param cardID: a string representing the ID for a TravelCard
	 */
	public void suspendCard(String cardID) {
		for(TravelCard card: cards) {
			if(card.getCardID().equals(cardID)) {
				if (card.getStatus() == true) {
					card.changeStatus();
					System.out.println("TravelCard " + cardID + " has been successfully suspended");
				}
			}
		}
	}
	
	/**
	 * Allows a CardHolder to activate a TravelCard
	 * 
	 * @param cardID: a string representing the ID for a TravelCard
	 */
	public void activateCard(String cardID) {
		for(TravelCard card: cards) {
			if(card.getCardID().equals(cardID)) {
				if (card.getStatus() == false) {
					card.changeStatus();
					System.out.println("TravelCard " + cardID + " has been successfully reactivated");
				}
			}
		}
	}
	
	/**
	 * returns the travel status of a CardHolder
	 * 
	 * @return a boolean representing status of a CardHolder
	 */
	public boolean getStatus() {
		return this.isTravel;
	}
	
	/**
	 * Changes status of Cardholder to False if currently True or to True if currently False
	 */
	public void changeStatus() {
		this.isTravel = !this.isTravel;
	}
	
	/**
	 * Prints a message showing the past three trips that have been made
	 */
	public void viewTrips() {
		for(int i = 0; i < trips.size(); i++) {
			Trip temp = trips.poll();
			System.out.println(temp.toString());
			this.trips.add(temp);
		}
	}
	
	/**
	 * Find and returns the most recent trip
	 * @return the most recent trip made (the last trip made)
	 */
	public Trip mostRecentTrip() {
		Trip returntrip = null;
		for(int i = 0; i < trips.size(); i++) {
			Trip temp = trips.poll();
			returntrip = temp;
			this.trips.add(temp);
		}
		return returntrip;
	}
	
	/**
	 * Adds Trip trip to the ArrayList trips
	 * 
	 * @param trip to be added
	 */
	public void addTrips(Trip trip) {
		if(trips.size() >= 3) {
			trips.poll();
			trips.add(trip);
		}else {
			trips.add(trip);
		}
	}
	
	/**
	 * Adds a trip obejct into the arraylist of total trips
	 */
	public void addTotalTrips(Trip trip) {
		totaltrips.add(trip);
	}
	
	/**
	 * remove a trip obejct into the arraylist of total trips
	 */
	public void removeTotalTrips(Trip trip) {
		totaltrips.remove(trip);
	}
	/**
	 * Views all TravelCards owned by the CardHolder through print messages
	 */
	public void viewCards() {
		for(int i = 0; i < cards.size(); i++) {
			System.out.println(cards.get(i).toString());
		}
	}
	
	/**
	 * calculates the fees for all trips made and prints it
	 * 
	 */
	public void trackFees() {
		double result = 0.0;
		for(Trip trip: totaltrips) {
			result += trip.getFee();
		}
		System.out.println("The total fee of all trips by " + id + " is "+ result);
	}
	
	@Override
	public String toString() {
		return name + ". CardHolderID: " + id + " email: " + email;
	}

}
