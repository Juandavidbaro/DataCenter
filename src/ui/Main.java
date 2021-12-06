package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Controller;
import model.Server;

public class Main {
    private Scanner sc;
    private Controller controller;

    public Main() {
        sc = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {

        Main obPpal = new Main();

        int option = 0;

        do {
            option = obPpal.showMenuAndGetOption();
            obPpal.answerOption(option);
        } while (option != 0);

    }

    /**
     * Description: menu options
	 *@return imput, opcion digitada por el usuario
     */
    public int showMenuAndGetOption() {
        System.out.println("\n\nMENU, Input a option\n" + 
		"(1) Show list of available mini-rooms\n" + 
		"(2) Rent a mini-room\n" +
		"(3)Cancel rent a mini room \n" + 
		"(4)Show status of mini -rooms \n" + 
		"(5)Simulate turn on the rooms\n" +
		"(6)Simulate turn off the rooms\n"+
		"(0) EXIT");
        int input = Integer.parseInt(sc.nextLine());
        return input;

    }

    public void answerOption(int userOption) {

        switch (userOption) {
        case 0:
            System.out.println("Close the app, bye.");
            break;
        case 1:
            infoAvailableRooms();
            break;
        case 2:
            rentAMiniroom();
            break;
        case 3:
			cancelRentAMiniRoom();

            break;
        case 4:
			System.out.println(controller.infoStatusRooms());
			
			break;
		case 5:
			changeStateMinirooms();
			
            break;
			
		case 6:
			turnOffMinirooms();
			
			break;
        }
    }
	
	
	
	/**
     * Descripcion: permite cancelar la renta del minicuarto correspondiente
     * 
     * 
     * 
     */
    private void cancelRentAMiniRoom() {

        System.out.println(" 1 To delete mini-rooms for company, 2 To delete mini-room for ID");
        int option = Integer.parseInt(sc.nextLine());

        if (option == 1) {
            System.out.println("input the name of company: ");
            String name = sc.nextLine();
            System.out.println(controller.cancelRentAMiniRoom(name));
        }else if (option == 2) {
            System.out.println("Input the ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println(controller.cancelRentAMiniRoom(id));
        }

    }
	
	/**
     * Descripcion: permite realizar la renta del minicuarto, con todos sus datos correspondientes
     * 
     * 
     * 
     */
    private void rentAMiniroom() {
        System.out.println("Input the number of miniroom to rent: ");
        int num= Integer.parseInt(sc.nextLine());

        System.out.println("Select 1 for private company or 2 for research project: ");
        int option= Integer.parseInt(sc.nextLine());

        if (option == 1  || option == 2){
            String name;
            String nit;
            if (option == 1){
                System.out.println("The name of company: ");
                name = sc.nextLine();
   
               System.out.println("The nit of company: ");
                nit = sc.nextLine();
            }else{
                name = "ICESI";
                System.out.println("Input the ID of research project: ");
                nit = sc.nextLine();
            }
            System.out.println("Input the amount of servers: ");
            int numServes = Integer.parseInt(sc.nextLine());
            
            ArrayList <Server> rack = new ArrayList<>();
            for (int i=0;i < numServes; i++){
                System.out.println("Input the cache: ");
                double cache = Double.parseDouble(sc.nextLine());

                System.out.println("Input the number if processors: ");
                int numProcessor = Integer.parseInt(sc.nextLine());

                System.out.println("Input 1 if is intel or 2 if is AMD: ");
                int MarkProcessor = Integer.parseInt(sc.nextLine());

                System.out.println("Input the ram: ");
                double ram = Double.parseDouble(sc.nextLine());

                System.out.println("Input the disc");
                int disc = Integer.parseInt(sc.nextLine());

                System.out.println("Input the capacity of disc: ");
                double CapacityDisc = Integer.parseInt(sc.nextLine());

                rack.add(new Server(cache, numProcessor, MarkProcessor, ram, disc, CapacityDisc));
            }

            System.out.println(controller.rentAMiniroom(num, name, nit, rack));

      
            }else {
                System.out.println("invalid option");
            }
    }
	
	/**
     * Descripcion: muestra al usuario un listado de la disponibilidad de las habitaciones
     * 
     * 
     * 
     */
    private void infoAvailableRooms() {
        System.out.println(controller.infoAvailableRooms());
    }
	
	private void changeStateMinirooms() {
        System.out.println(controller.changeStateMinirooms());
    }
	private void turnOffMinirooms() {
		String letter;
		
		System.out.println("\nShutdown system:\n"+
		"Letter L: turn off the first mini-quarters of all runners, along with the mini-quarters of the first runner.\n"+
		"Letter Z: turn off the mini-quarters of the first and last runner, along with the mini-quarters of the reverse diagonal.\n"+
		"Letter H: turn off the mini-rooms located in the odd-numbered corridors.\n"+
		"Letter O: turn off the mini-rooms located in the windows.\n"+
		"Letter M: turn off all mini-quarters in column N.\n"+
		"Letter P: turn off the mini-rooms of a corridor.\n"+
		"Letter X: to exit the simulator.\n"+
		"Type the corresponding letter in uppercase:");
		
		letter = sc.nextLine();
		
		switch(letter) {
		
		case "L":
			controller.turnOffL();
			break;
		case "Z":
			controller.turnOffZ();
			break;
	
		case "H":
			controller.turnOffH();
			break;

		case "O":
			controller.turnOffO();
			break;
			
		case "M":
			System.out.println("Enter the column:");
            int column = sc.nextInt();
            controller.turnOffM(column);
			break;
			
		case "P":
			System.out.println("Enter the corridor:");
                int corridor = sc.nextInt();
                controller.turnOffP(corridor);
			break;
			
		case "0":
                System.out.println("\nEnd of simulation");
                
                break;	
		}
		//System.out.println("Result:");
        //System.out.println(controller.simulatedMap());		
        //System.out.println(controller.turnOffMinirooms());
    }
}