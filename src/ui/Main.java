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
        System.out.println("\n\nMENU, Enter a option\n" + 
		"(1) Show list of available mini-rooms\n" + 
		"(2) Rent a mini-room\n" +
		"(3)Cancel rent a mini room \n" + 
		"(4)Show status of mini -rooms \n" + 
		"(5)Simulate turn on the rooms\n" +
		"(6)Simulate turn off the rooms\n"+
		"(0) EXIT");
        int Enter = sc.nextInt();
        return Enter;

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
			//System.out.println(controller.infoStatusRooms());
			 System.out.println(controller.getCenterMap());
			
			break;
		case 5:
			changeStateOnMinirooms();
			
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

        System.out.println("1. To delete mini-rooms for company\n2. To delete mini-room for ID");
        int option = sc.nextInt();

        if (option == 1) {
            System.out.println("Enter the name of company: ");
            String name = sc.next();
            System.out.println(controller.cancelRentAMiniRoom(name));
        }else if (option == 2) {
            System.out.println("Enter the ID: ");
            int id = sc.nextInt();
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
        System.out.println("Enter the number of miniroom to rent: ");
        int number= sc.nextInt();

        System.out.println("Select 1 for private company or 2 for research project: ");
        int option= sc.nextInt();

        if (option == 1  || option == 2){
            String name;
            String nit;
            if (option == 1){
                System.out.println("The name of company: ");
                name = sc.next();
   
               System.out.println("The nit of company: ");
                nit = sc.next();
            }else{
                name = "ICESI";
                System.out.println("Enter the number of the mini room: ");
                nit = sc.next();
            }
            System.out.println("Enter the amount of servers: ");
            int numberServes = sc.nextInt();
            
            ArrayList <Server> rack = new ArrayList<>();
            for (int i=0;i < numberServes; i++){
                System.out.println("Enter the cache: ");
                double cache = sc.nextDouble();

                System.out.println("Enter the number if processors: ");
                int numberProcessor = sc.nextInt();

                System.out.println("Enter 1 if is intel or 2 if is AMD: ");
                int markProcessor = sc.nextInt();

                System.out.println("Enter the ram: ");
                double ram = sc.nextDouble();

                System.out.println("Enter the disc");
                int disc = sc.nextInt();

                System.out.println("Enter the capacity of disc: ");
                double capacityDisc = sc.nextDouble();

                rack.add(new Server(cache, numberProcessor, markProcessor, ram, disc, capacityDisc));
            }

            System.out.println(controller.rentAMiniroom(number, name, nit, rack));

      
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
	/**
     * Descripcion: muestra el mensaje del exito o fracaso del encendido de los cuartos
     * 
     * 
     * 
     */
	private void changeStateOnMinirooms() {
        System.out.println(controller.changeStateOnMinirooms());
    }
	/**
     * Descripcion: muestra el menu de apagado, y segun lo que digite el usuario, apagará de la forma correspondiente
     * 
     * 
     * 
     */
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
		
		letter = sc.next();
		
		
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
			
		case "X":
                System.out.println("\nEnd of simulation");
                
                break;	
		}
		System.out.println("\n--------------------------------------------- RESULT ----------------------------------------------\n");
        System.out.println(controller.getCenterMap());
    }
}