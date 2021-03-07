package transitapp;
/**
 * This class creates and stores details of each trip
 * 
 */

public class Trip {
	
	private String start;
	private String end;
	private double fee;
	private boolean triptype; // false: Bus True: Train
	private int totaltime;
	
	/**
	 * initialize an empty trip of type bus
	 */
	public Trip() {
		this.start = "";
		this.end = "";
		this.fee = 0;
		triptype = false;
		totaltime = 0;
	}
	
	/**
	 * Returns the starting location of a trip
	 * 
	 * @return the starting location of a trip
	 */
	public String getStart() {
		return this.start;
	}
	
	/**
	 * Returns ending location of a trip
	 * 
	 * @return the ending location of a trip
	 */
	public String getEnd() {
		return this.end;
	}
	
	/**
	 * Returns the fee charged for a trip
	 * 
	 * @return the fee charged for a trip
	 */
	public double getFee() {
		return this.fee;
	}
	
	/**
	 * Returns the type of trip (Subway or Bus)
	 * 
	 * @return the type of trip 
	 */
	public boolean getTriptype() {
		return this.triptype;
	}
	
	/**
	 * Returns the total time of the trip
	 * 
	 * @return the total time of the trip
	 */
	public int getTime() {
		return this.totaltime;
	}
	
	/**
	 * Set a trips starting location to a starting location
	 * 
	 * @param start: a valid start location for a trip
	 */
	public void setStart(String start) {
		this.start= start;
	}
	
	/**
	 * Sets a trips end location to an end location
	 * 
	 * @param end: a valid end location for a trip
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	
	/**
	 * Sets/adds to the fee of a trip
	 * 
	 * @param fee: a fee charged for a trip that can be added to
	 */
	public void setFee(double fee) {
		this.fee += fee;
	}
	
	/**
	 * Sets/adds to the time of a trip
	 * 
	 * @param time: a time for the trip to be added to
	 */
	public void setTime(int time) {
		this.totaltime += time;
	}
	
	/**
	 * Sets the type of trip it is (bus or subway)
	 * 
	 * @param triptype: a boolean representing the type of trip false being a bus trip and true a subway
	 * 
	 */
	public void setTriptype(boolean triptype) {
		this.triptype = triptype;
	}
	@Override
	public String toString() {
		return "Travel from " + start + " to " + end + " time elapsed " + totaltime + " total fee of " + fee;
	}
}
