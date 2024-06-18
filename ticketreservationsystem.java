package railwayreserv;

import java.util.ArrayList;
import java.util.Scanner;

public class ticketreservationsystem {
	
	 private int total_berths = 3;
     private int Lower_berths = 1;
     private int Middle_berths = 1;
     private int Upper_berths = 1;
     private int total_RAC_berths = 1;
     private int total_waiting_list_tickets = 1;
     
     private ArrayList<passenger> booked_tickets = new ArrayList<>();
     private ArrayList<passenger> RAC_tickets = new ArrayList<>();
     private ArrayList<passenger> waiting_list_tickets = new ArrayList<>();
	
	
     public void bookticket() {
 		Scanner sc = new Scanner(System.in);
 		
 		if(total_waiting_list_tickets==0) {
 			  System.out.println("No Tickets Available");
 			  return;
 		}
 		
 		
 		   System.out.println("Enter passenger name : ");
 	        String name = sc.nextLine();
 	        System.out.println("Enter passenger age : ");
 	        int age = sc.nextInt();
 	        sc.nextLine();
 	        System.out.println("Enter passenger Gender (M/F) : ");
 	        String gender = sc.nextLine().toUpperCase();
 	        System.out.println("Enter berth preference (lower/middle/upper) : ");
 	        String berthpreference = sc.nextLine().toLowerCase();
 		
 	        
 	        if(berthpreference.equals("lower")) {
 	        	if(Lower_berths > 0) {
 	        		Lower_berths--;
 	        	}
 	        	else {
 	        		 System.out.println("-----------------------------");
 	                System.out.println("Lower Berth not available suggesting available berths");
 	                suggest_available_berths(name,age,gender);
 	                return;
 	        	}
 	        }
 	        	else if(berthpreference.equals("middle")) {
 	        		if(Middle_berths > 0) {
 	        			Middle_berths--;
 	 	        	}
 	        		else {System.out.println("-----------------------------");
 	                		System.out.println("Middle Berth not available suggesting available berths");
 	                		suggest_available_berths(name,age,gender);
 	                		return;
 	        			
 	        		}
 	        	}
 	        	else if(berthpreference.equals("upper")) {
 	        		if(Upper_berths > 0) {
 	        			Upper_berths--;
 	 	        	}
 	        		else {System.out.println("-----------------------------");
 	                		System.out.println("Upper Berth not available suggesting available berths");
 	                		suggest_available_berths(name,age,gender);
 	                		return;
 	        			
 	        		}
 	        	}
 	        	else {
 	        		System.out.println("Invalid Input");
 	        		return;
 	        	}
 	        
 	        
 	        if(age>5) {
 	        	passenger pass = new passenger(name,age,gender,berthpreference);
 	        	
 	        	if(total_berths > 0) {
 	        		booked_tickets.add(pass);
 	        		total_berths--;
 	        		System.out.println("Ticket booked successfully");
 	        		System.out.println("--------------------------");
 	        	}else if (total_RAC_berths > 0) {
 	        		RAC_tickets.add(pass);
 	        		total_RAC_berths--;
 	        		System.out.println("Ticket booked successfully");
 	        		System.out.println("--------------------------");
 	        		
 	        	}
 	        	else {
 	        		waiting_list_tickets.add(pass);
 	        		total_waiting_list_tickets--;
 	        		System.out.println("Ticket booked successfully");
 	        		System.out.println("--------------------------");
 	        		
 	        	}
 	        	
 	        }
 	        
 	        
 	        
 	        
 	        
 	        }
 	        
 	        
 	        
 		private void  suggest_available_berths(String name, int age,String gender) {
 			 System.out.println("-----------------------------");
 			if(Lower_berths > 0) {
 				System.out.println("1. Lower");
	        	}
 			if(Middle_berths > 0) {
 				System.out.println("2. Middle");
	        	}
 			if(Upper_berths > 0) {
 				System.out.println("3. Upper");
	        	}
 			if(Lower_berths == 0 && Middle_berths==0 && Upper_berths==0) {
 				System.out.println("4. Book on RAC");
	        	}
 			 
 			
 			System.out.println("Enter your choice : ");
 			Scanner sc = new Scanner(System.in);
 			int option = sc.nextInt();
 			sc.nextLine();
 			
 			
 			
 			switch(option) {
 			
 			case 1 : Lower_berths--;
 						booked_tickets.add(new passenger(name,age,gender,"lower"));
 						total_berths--;
 						System.out.println("Ticket booked successfully");
 						System.out.println("--------------------------------");
 						break;
 			case 2 : Middle_berths--;
						booked_tickets.add(new passenger(name,age,gender,"middle"));
						total_berths--;
						System.out.println("Ticket booked successfully");
						System.out.println("--------------------------------");
						break;
 			case 3: Upper_berths--;
 						booked_tickets.add(new passenger(name,age,gender,"upper"));
 						total_berths--;
 						System.out.println("Ticket booked successfully");
 						System.out.println("--------------------------------");
 						break;
 			case 4: if(total_RAC_berths > 0) {
 					RAC_tickets.add(new passenger(name,age,gender,"side lower"));
	        		total_RAC_berths--;
	        		System.out.println("Ticket booked successfully  (RAC)");
	        		System.out.println("--------------------------");
	        		break;	
 			}
 			else {
 				System.out.println("RAC not available you can book on waiting list");
 				System.out.println("--------------------------");
 				System.out.println("say yes to book on waiting list");
 				String reply = sc.next().toLowerCase();
 				if(reply.equals("yes")) {
 					waiting_list_tickets.add(new passenger(name,age,gender,"NA"));
 	        		total_waiting_list_tickets--;
 	        		System.out.println("Ticket booked successfully  (Waiting List)");
 	        		System.out.println("--------------------------");
 				}
 				else if(reply.equals("no")) {
 					break;
 				}
 				break;	
 			}
 			}	
 		}
 	
	
	

	
 		//CANCEL TICKET
 		public void cancelticket() {
 			Scanner sc = new Scanner(System.in);
 			if(booked_tickets.isEmpty()) {
 				System.out.println("NO booked tickets to cancel.");
 				return;
 			}
 			System.out.println("Enter passenger name to cancel the ticket : ");
 	        String cancel_name = sc.nextLine();
 			
 	        passenger canceled_ticket = null;
 	        for(passenger tic :booked_tickets) {
 	        	if(tic.name.equalsIgnoreCase(cancel_name)) {
 	        		canceled_ticket =tic;
 	        		break;
 	        	}
 	        }
 			if(canceled_ticket == null) {
 				System.out.println("Ticket with the provided name not found");
 				return;
 			}
 			booked_tickets.remove(canceled_ticket);
 		    total_berths++;
 			
 			//move rac to place of the cancelled tickets
 		    
 		    if(!RAC_tickets.isEmpty()) {
 		    	passenger confirmed_ticket = RAC_tickets.remove(0);
 		    	total_RAC_berths++;
 		    	booked_tickets.add(new passenger(confirmed_ticket.name,confirmed_ticket.age,confirmed_ticket.gender,confirmed_ticket.berthpreference));
 		    	  System.out.println("ticket cancelled sucessfully for "+canceled_ticket.name);
 		    	  total_berths--;	
 		    }
 		    else {
 		    	 System.out.println("ticket cancelled sucessfully for "+canceled_ticket.name+"NO RAC passenger available to move");
 		    }
 		    
 		    
 		    //move waiting list to rac
 		   if(!waiting_list_tickets.isEmpty()) {
		    	passenger rac_candidate = waiting_list_tickets.remove(0);
		    	RAC_tickets.add(new passenger(rac_candidate.name,rac_candidate.age,rac_candidate.gender,"side lower"));
		    	  System.out.println("Waiting list passenger moved to RAC");
		    	  total_RAC_berths--;
		    	  total_waiting_list_tickets++;
		    }
 		     System.out.println("-----------------------------");
 		    
 			
 		}

 		//print the booked tickets
 		public void	printbookedticket(){
 			if(booked_tickets.isEmpty()) {
 				System.out.println("NO booked tickets available.");
 				
 			}
 			else {
 				 System.out.println("Booked Tickets");
 				 System.out.println();
 				 for(int i=0;i<booked_tickets.size();i++) {
 					 passenger ticket = booked_tickets.get(i);
 					System.out.println( (i+1)+"  Name:  "+ticket.name +"\n    Age:  "+ticket.age+"\n    Gender:  "+ticket.gender+"\n    Berth Preference:  "+ticket.berthpreference);
 					 
 				 }
 				 System.out.println();
 				 System.out.println("RAC Tickets:");
 				 System.out.println();
 				for(int i=0;i<RAC_tickets.size();i++) {
					 passenger ticket = RAC_tickets.get(i);
					System.out.println( (i+1)+"  Name:  "+ticket.name +"\n    Age:  "+ticket.age+"\n    Gender:  "+ticket.gender+"\n    Berth Preference:  "+ticket.berthpreference);
					 
				 }
 				 System.out.println();
 				 System.out.println("Waiting list Tickets:");
 				 System.out.println();
 				for(int i=0;i<waiting_list_tickets.size();i++) {
					 passenger ticket = waiting_list_tickets.get(i);
					System.out.println( (i+1)+"  Name:  "+ticket.name +"\n    Age:  "+ticket.age+"\n    Gender:  "+ticket.gender+"\n    Berth Preference:  "+ticket.berthpreference);
					 
				 }
 				 System.out.println("----------------------------");
 				 System.out.println("Total Booked tickets : 		"+booked_tickets.size());
 				 System.out.println("Total RAC tickets : 			"+RAC_tickets.size());
 				 System.out.println("Total Witing list tickets :	"+waiting_list_tickets.size());
 				 System.out.println("----------------------------");
 				
 		}
 
 	}
 		
 		
 		
 		//case 4 print available tickets
 		public void printavailableticket() {
 				 System.out.println("----------------------------");
 				 System.out.println("AVAILABLE TICKETS");
 				 System.out.println("----------------------------");
				 System.out.println("Available tickets : 						"+(total_berths));
				 System.out.println("RAC  Available tickets : 			"+(total_RAC_berths));
				 System.out.println("Witing list Available tickets :		"+(total_waiting_list_tickets));
				 System.out.println("Total Available tickets : 				"+(total_berths+total_RAC_berths+total_waiting_list_tickets));
				 System.out.println("----------------------------");
				
 		}
 		
 		
 		
 		
 		
 		
 		
 		
 		
	public static void main(String[] args) {
		
		
		ticketreservationsystem ticket = new ticketreservationsystem();
		Scanner sc = new Scanner(System.in);
		
		
		
		while(true) {
			

	        System.out.println("Railway  Ticket Reservation System");
	        System.out.println("1.  Book Ticket");
	        System.out.println("2.  Cancel Ticket");
	        System.out.println("3.  Print Booked Tickets");
	        System.out.println("4.  Print Available  Ticket");
	        System.out.println("5.  Exit");


	        System.out.println("Enter your choice (1-5) : ");
	        int choice = sc.nextInt();
	        sc.nextLine();

	        System.out.println("--------------------------");

	        switch(choice){
	            case 1 : ticket.bookticket();
	                     break;
	            case 2 : ticket.cancelticket();
	                     break;
	            case 3 : ticket.printbookedticket();
	                     break;
	            case 4 : ticket.printavailableticket();
	                     break;
	            case 5 : System.out.println("Exiting the application Thank You");
	                     System.exit(0);
	            default :System.out.println("Enter a valid choice");

	        }

		}

	}

	

}
