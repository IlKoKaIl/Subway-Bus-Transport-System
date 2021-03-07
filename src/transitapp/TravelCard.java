package transitapp;

/**
 * This class creates a travel card and keeps track of its balance
 * 
 * It has been implemented such that a new travel card starts with a balance of $19 and an active
 * status. Balance and active status can be deducted, added and viewed.
 */
public class TravelCard {
	
	private float balance;
	private boolean status;
	private String cardID;
	
	/**
	 * Constructs a new travel card with a balance of $19, an active status, and a unique cardID.
	 */
	public TravelCard(String cardID) {
		this.balance = 19;
		this.status = true;
		this.cardID = cardID;
	}
	
	/**
	 * Returns the current balance of the travel card.
	 * 
	 * @return current balance of travel card
	 */
	public float getBalance() {
		return balance;
	}
	
	/**
	 * Returns the current active status of the travel card.
	 * 
	 * @return current status of travel card
	 */
	public boolean getStatus() {
		return status;
	}
	
	/**
	 * Returns the card ID of the travel card.
	 * 
	 * @return card ID of travel card
	 */
	public String getCardID() {
		return this.cardID;
	}
	
	/**
	 * Changes the current active status of the travel card to either inactive or active depending
	 * on the current status.
	 */
	public void changeStatus() {
		if (status == true){
			status = false;
			System.out.println("TravelCard " + cardID + " is now inactive.");
		}
		else {
			status = true;
			System.out.println("TravelCard " + cardID + "is now active.");
		}
	}
	
	/**
	 * Adds the balance of the travel card if the amount to be added is either $10, $20 or $50.
	 * Else, balance is not added and prints a message explaining why balance is not added.
	 * 
	 * @param amount: the amount to be added to the travel card
	 */
	public void addBalance(float amount) {
		if ((amount == 10) || (amount == 20) || (amount == 50)) {
			balance += amount;
			System.out.println("Successfully added $" + amount + " to TravelCard " + cardID);
		}
		else {
			System.out.println("Invalid balance to add. Add only $10, $20 and $50 notes");
		}
	}
	
	/**
	 * Deducts the balance of the travel card to pay for a trip. If the initial balance of the card
	 * is positive, deducts the card's balance. Else, prints an error message stating insufficient
	 * funds in the card and suggesting to refill the card's balance.
	 * 
	 * @param amount: the amount to be payed by the travel card
	 */
	public void pay(float amount) {
		if (balance > 0) {
			balance -= amount;
			System.out.println("Payment Successful! Deducted $" + amount + "from TravelCard" + cardID);
		}
		else {
			System.out.println("Payment not processed."
					+ "You have insufficient funds on your card. Please add your balance.");
		}
	}

	@Override
	public String toString() {
		return "TravelCard [balance=" + balance + ", status=" + status + ", cardID=" + cardID + "]";
	}
	
}
