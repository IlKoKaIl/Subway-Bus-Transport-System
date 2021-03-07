// These is a placeholder package and placeholder class	
// Feel free to rename or remove these when you add in your own code (just make sure to add/commit/push any changes made,
//		and let your teammates know to pull the changes. Follow the workflow in the a2 instructions)

package transitapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;	
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import javafx.util.Pair;

public class Main {
	public static void main(String[] args) {

		int date = 0;
		TransitStaff admin = TransitStaff.getInstance();

		// Creating subway stations

		ArrayList<Pair<String, Integer>> stations = new ArrayList<Pair<String, Integer>>();
		stations.add(new Pair<>("StGeorge", 0));
		stations.add(new Pair<>("Museum", 5));
		stations.add(new Pair<>("Queen'sPark", 12));
		stations.add(new Pair<>("StPatrick", 20));
		stations.add(new Pair<>("Osgoode", 25));
		stations.add(new Pair<>("StAndrew", 32));
		stations.add(new Pair<>("Union", 40));
		stations.add(new Pair<>("King", 45));

		// Creating bus stations
		ArrayList<Pair<String, Integer>> busRoute1 = new ArrayList<Pair<String, Integer>>();
		ArrayList<Pair<String, Integer>> busRoute2 = new ArrayList<Pair<String, Integer>>();
		ArrayList<Pair<String, Integer>> busRoute3 = new ArrayList<Pair<String, Integer>>();
		ArrayList<Pair<String, Integer>> busRoute4 = new ArrayList<Pair<String, Integer>>();

		ArrayList<ArrayList<Pair<String, Integer>>> busRoutes = new ArrayList<ArrayList<Pair<String, Integer>>>();

		busRoute1.add(new Pair<>("Koreatown", 0));
		busRoute1.add(new Pair<>("Chinatown", 10));
		busRoute1.add(new Pair<>("Museum", 20)); // Intersects with Subway Station
		busRoute1.add(new Pair<>("Robarts", 30));
		busRoute1.add(new Pair<>("StPatrick", 40)); // Intersects with Subway Station
		busRoute1.add(new Pair<>("OldDistrict", 50));
		busRoute1.add(new Pair<>("Harbourfront", 60));

		busRoute2.add(new Pair<>("PearsonAirport", 0));
		busRoute2.add(new Pair<>("SquareOne", 30));
		busRoute2.add(new Pair<>("EtibicokeTownHall", 60));
		busRoute2.add(new Pair<>("StPatrick", 90)); // Intersects with Subway Station
		busRoute2.add(new Pair<>("UniversityofToronto,Scarborough", 120));

		busRoute3.add(new Pair<>("StGeorge", 0)); // Intersects with Subway Station
		busRoute3.add(new Pair<>("Wellesley", 7));
		busRoute3.add(new Pair<>("RyersonUniversity", 15));
		busRoute3.add(new Pair<>("EatonCentre", 20));
		busRoute3.add(new Pair<>("Osgoode", 30)); // Intersects with Subway Station
		busRoute3.add(new Pair<>("CNTower", 36));
		busRoute3.add(new Pair<>("CityHall", 41));
		busRoute3.add(new Pair<>("StanleyPark", 55));

		busRoute4.add(new Pair<>("LibertyVillage", 0));
		busRoute4.add(new Pair<>("FashionDistrict", 20));
		busRoute4.add(new Pair<>("King", 30)); // Intersects with Subway Station
		busRoute4.add(new Pair<>("OldTown", 45));
		busRoute4.add(new Pair<>("Corktown", 60));

		busRoutes.add(0, busRoute1);
		busRoutes.add(0, busRoute2);
		busRoutes.add(0, busRoute3);
		busRoutes.add(0, busRoute4);	
		String[] inputarr;
		
		try {
			  FileReader file = new FileReader("events.txt");
			  BufferedReader in = new BufferedReader(file);
		    Scanner input = new Scanner(in);
		    String line = input.nextLine();
	
		    while(input.hasNextLine()) {
				inputarr = line.split(" ");
				CardHolder currentuser = null;
				
				if (inputarr[0].equals("admin")) {
					
					switch (inputarr[1].toLowerCase()) {
					case "newcardholder":
						String name = inputarr[2] + " " + inputarr[3];
						String email = inputarr[4];
						String id = inputarr[2].substring(0,1) + inputarr[3].substring(0,1);
						int idcount = 1;
						String tempid = id + String.valueOf(idcount);
						boolean uniqueid = admin.checkUniqueCardHolder(tempid);
						if (uniqueid == false) {
							while(uniqueid == false) {
								idcount += 1;
								tempid = id + String.valueOf(idcount);
								uniqueid = admin.checkUniqueCardHolder(tempid);
							}
						}
						id = tempid.toUpperCase();
						admin.addCardHolder(new CardHolder(name, email, id));
						admin.getCardHolders();
						break;
					case "viewcardholders":
						for (CardHolder i: admin.getCardHolders()) {
							System.out.println(i);
						}
						break;
					case "nextday":
						date++;
						System.out.println("Moved to the next day");
					case "showdailyreport":
						System.out.println(admin.toString());
					}
					line = input.nextLine();
				}else {
					if (inputarr.length >=2) {
						for (CardHolder i: admin.getCardHolders()) {
							if (i.getID().toLowerCase().equals(inputarr[0].toLowerCase())) {
								currentuser = i;
							}
						}
					if (currentuser != null) {
						switch (inputarr[1].toLowerCase()) {
						case "remove":
							currentuser.removeCard(inputarr[2]);
							break;
						case "addcard":
							currentuser.addCard();
							break;
						case "addbalance":
							TravelCard card = currentuser.getCard(inputarr[2]);
							int add = Integer.valueOf(inputarr[3]);
							card.addBalance(add);
							break;
						case "suspend":
							currentuser.suspendCard(inputarr[2]);
							break;
						case "reactivate":
							currentuser.activateCard(inputarr[2]);
							break;
						case "changename":
							currentuser.changeName(inputarr[2] + " " + inputarr[3]);
							break;
						case "viewcards":
							currentuser.viewCards();
							break;
						case "viewtrips":
							currentuser.viewTrips();
							break;
						case "track":
							currentuser.trackFees();
							break;
						case "enter":
							if(currentuser.getCard(inputarr[2]).getBalance() > 0) {
								if (currentuser.getStatus() == false) {
									Trip trip = new Trip();
									trip.setStart(inputarr[3]);
									if (inputarr[4].toLowerCase().equals("stop")) {
										trip.setFee(2);
										currentuser.getCard(inputarr[2]).pay(2);
									} else if (inputarr[4].toLowerCase().equals("station")) {
										trip.setTriptype(true);
									}
									currentuser.addTrips(trip);
									currentuser.changeStatus();
								} else {
									Trip currtrip = currentuser.mostRecentTrip();
									if (currtrip.getEnd().equals(inputarr[3])) {
										admin.removetrip(currtrip);
										currentuser.removeTotalTrips(currtrip);
										currtrip.setEnd("");
										if (inputarr[4].toLowerCase().equals("stop")) {
											currtrip.setFee(2);
											currentuser.getCard(inputarr[2]).pay(2);
											currtrip.setTriptype(false);
										} else if (inputarr[4].toLowerCase().equals("station")) {
											currtrip.setTriptype(true);
										}
									} else {
										Trip trip = new Trip();
										trip.setStart(inputarr[3]);
										if (inputarr[4].toLowerCase().equals("stop")) {
											trip.setFee(2);
											currentuser.getCard(inputarr[2]).pay(2);
											trip.setTriptype(false);
										} else if (inputarr[4].toLowerCase().equals("station")) {
											trip.setTriptype(true);
										}
										currentuser.addTrips(trip);
									}
								}
								System.out.println("Successfully tapped into " + inputarr[3] + " " + inputarr[4]);
							}else {
								System.out.println("Insufficient fund");
								line = input.nextLine();
							}
							break;
						case "exit":
							Trip trip = currentuser.mostRecentTrip();

							int stationstopnum = 0;
							int totaltime = 0;
							trip.setEnd(inputarr[3]);

							int compare1 = 0;
							int compare2 = 0;

							int endindex = 0;
							int startindex = 0;

							if (trip.getTriptype() == true) {
								for (Pair pair : stations) {
									if (pair.getKey().equals(trip.getEnd())) {
										compare1 = (int) pair.getValue();
										endindex = stations.indexOf(pair);
									}
									if (pair.getKey().equals(trip.getStart())) {
										compare2 = (int) pair.getValue();
										startindex = stations.indexOf(pair);
									}
								}
								totaltime = Math.abs(compare1 - compare2);
								stationstopnum = Math.abs(endindex - startindex);
							} else {
								for(ArrayList<Pair<String, Integer>> busroute: busRoutes) {
									for(Pair pair: busroute) {
										if(pair.getKey().equals(trip.getEnd())) {
											compare1 = (int) pair.getValue();
										}
										if(pair.getKey().equals(trip.getStart())) {
											compare2 = (int) pair.getValue();
										}
									}
									if(compare1 != 0 && compare2 != 0) {
										totaltime = Math.abs(compare1-compare2);
									}
								}
							}
							if (trip.getTriptype()) {
								trip.setTime(totaltime);
								trip.setFee((stationstopnum-1) * 0.5);
								currentuser.getCard(inputarr[2]).pay((float) trip.getFee());
								System.out.println("station trip updated");
							} else {
								trip.setTime(totaltime);
							}

							if (trip.getTime() >= 120) {
								currentuser.changeStatus();
								System.out.println("maxed out a single trip time limit");
							}
							if (trip.getFee() >6) {
								trip.setFee(-(trip.getFee()-6));
								currentuser.getCard(inputarr[2]).addBalance((float) (trip.getFee()-6));;
							}
							System.out.println("Successfully exited " + inputarr[3] + " " + inputarr[4]);
							admin.updatedata(trip.getFee(), trip);
							currentuser.addTotalTrips(trip);
							break;
						default:
								System.out.println("Invalid input. Try again");
								line = input.nextLine();
					}
							line = input.nextLine();
						}else {
							System.out.println("Invalid input. Try again");
							line = input.nextLine();
						}
					}
				}
			}
					
		    input.close();
		} catch(FileNotFoundException e) {
			System.out.println("events.txt is not found");
		}
	}//method
}//Main
