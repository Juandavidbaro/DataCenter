package model;

import java.util.ArrayList;

public class Controller {

    private MiniRoom[][] miniRooms;

    public Controller() {
        this.miniRooms = new MiniRoom[8][50];
        createMiniRooms();
    }
	/**
     * Descripcion: permite crear la mini room
     * 
     * 
     * 
     */
    public void createMiniRooms() {
        int number = 1;

        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                boolean window = false;
                if (i == 0 || i == 7 || j == 0 || j == 49) {
                    window = true;
                }

                miniRooms[i][j] = new MiniRoom(number, j, i, window);
                number++;
            }
        }

        miniRooms[7][49].setStatus(true);

    }
	
	/**
     * Descripcion: permite cambiar el estado de todos los cuartos a encendido
     * 
     * 
     *@return <message><String> todos los cuartos fueron encendidos
     */
	public String changeStateOnMinirooms() {       
		String message = "";
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                               
                miniRooms[i][j].setStatus(true);             

            }
        }
		message="All the rooms were lit";
		
		return message;
       
    }
	/**
     * Descripcion: permite apagar los mini cuartos en forma de L
     * 
     * 
     * 
     */
	public void turnOffL(){
		for (int i=0; i<miniRooms.length; i++){
			miniRooms[i][0].setStatus(false);
		}

		for (int i=1; i<miniRooms[0].length; i++){
			miniRooms[0][i].setStatus(false);
		}
	}
	
	/**
     * Descripcion: permite apagar los mini cuartos en forma de Z
     * 
     * 
     * 
     */
	public void turnOffZ(){
		for (int i=0; i<miniRooms[0].length; i++){
			miniRooms[0][i].setStatus(false);
			miniRooms[miniRooms.length-1][i].setStatus(false);
			
		}

		for (int i=miniRooms.length-1, j=miniRooms[0].length-1; i>-1 && j>-1; i--, j--){
			miniRooms[i][j].setStatus(false);
		}
	}
	
	/**
     * Descripcion: permite apagar los mini cuartos de los corredores impares
     * 
     * 
     * 
     */
	public void turnOffH(){
		for (int i=0; i<miniRooms.length; i++){
			if ((i+1)%2 != 0){
				for (int j=0; j<miniRooms[0].length; j++){
					miniRooms[i][j].setStatus(false);
				}
			}
		}
	}
	
	/**
     * Descripcion: permite apagar los mini cuartos en forma de O (ubicados en las ventanas)
     * 
     * 
     * 
     */
	public void turnOffO(){
		for (int i=0; i<miniRooms.length; i++){
			for (int j=0; j<miniRooms[0].length; j++){
				if (j==0 || j==(miniRooms[0].length-1) || i==0 || i==(miniRooms.length-1)){
					miniRooms[i][j].setStatus(false);
				}
			}
		}
	}
	
	/**
     * Descripcion: permite apagar los mini cuartos de la columna ingresada por el usuario
     * 
     * @param <column><int> debe ser un entero
     * 
     */
	public void turnOffM(int column){
		for (int i=0; i<miniRooms.length; i++){
			miniRooms[i][column-1].setStatus(false);
		}
	}
	
	/**
     * Descripcion: permite apagar los mini cuartos del corredor ingresado por el usuario
     * 
     * @param <corridor><int> debe ser un entero
     * 
     */
	public void turnOffP(int corridor){
		for (int j=0; j<miniRooms[0].length; j++){
			miniRooms[corridor-1][j].setStatus(false);
		}
	}

	/**
     * Descripcion: permite conocer la informacion de la disponibilidad de los mini rooms
     * 
     * 
     * @return <message><String> con la informacion de las mini rooms
     */
    public String infoAvailableRooms() {
        String message = "";

        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                MiniRoom aux=miniRooms[i][j];
                if (aux.rented()==false) {
                    message+=aux.toString()+"\n";
                }

            }
        }
        
        return message;
    }
	
	/*
	/**
     * Descripcion: permite conocer el status de las mini rooms y realiza una lista, con el número de la habitacion y el estado
     * 
     * 
     * @return <message><String> con la informacion status de las mini rooms
     *
    public String infoStatusRooms() {
        String message = "";

        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                MiniRoom aux=miniRooms[i][j];
                if (aux.rented()==false) {
                    message+="-----------------------------------\n"+
					aux.toStringStatus()+"\n";
                }

            }
        }
        
        return message;
    }*/
	
	/**
     * Descripcion: permite realizar la renta de un minicuarto
     * 
     * 
     * 
     */
    public String rentAMiniroom(int number, String name, String nit, ArrayList<Server> rack) {
        MiniRoom room=searchRoom(number);
        room.setCompany(new Company(name, nit));
        room.setRack(rack);
        room.setRent(true);
		room.setStatus(true);

        return "Rent a mini-room success";

    }

	/**
     * Descripcion: permite buscar la mini room
     * 
     * @param <number><int> debe ser un entero
     * @return <room><MiniRoom> el objeto de la mini room
     */
    public MiniRoom searchRoom(int number){
        MiniRoom room=null;
        boolean flag = false;
        for (int i = 0; i < miniRooms.length && !flag; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                if (miniRooms[i][j].getNumber()==number) {
                    flag=true;
                   room=miniRooms[i][j];
                }
        }
    }
    return room;
    }

	/**
     * Descripcion: permite cancelar la renta de una mini room con la id
     * 
     * @param <id><int> debe ser un entero
     * @return <message><String> con el resultado de la operacion
     */
    public String  cancelRentAMiniRoom(int id) {
        String message="THe mini room doesn't exist";
        if (searchRoom(id) !=null){
            MiniRoom mini=searchRoom(id);
            double proccesCapacity =mini.cancelRent();
            message="The mini room has been canceled rent successfully and your procces capacity is :"+proccesCapacity;
        }
        return message;
    }

	/**
     * Descripcion: permite cancelar la renta de una mini room con el nombre de la empresa
     * 
     * @param <name><String> debe ser un String
     * @return <message><String> con el resultado de la operacion
     */
    public String  cancelRentAMiniRoom(String name) {
        boolean cancel=false;
        double proccesCapacity=0;
        String message="The company don't have rent a mini-rooms";
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
               if (miniRooms[i][j].getCompany() != null){
                   if(miniRooms[i][j].getCompany().getName().equals(name)){
                    proccesCapacity+=miniRooms[i][j].cancelRent();
                    cancel=true;
                   }
               }
            }
        }
        if (cancel==true){
            message="The mini rooms has been canceled rent successfully and your procces capacity is :"+proccesCapacity;
        }

        return message;
    }
	
	/**
	*
	* Descripcion: Recorre toda la matriz de miniRooms y obtiene el estado de cada posición. 
	* Usa "O" para representar "encendido" y "X" para representar "apagado". 
	* @return actualStates char [] [] con los símbolos que representan los estados de las mini habitaciones.
	*/
	public char[][] mapMiniRoomStates(){
		char[][] actualStates = new char[8][50];
		char simbol = 'X';
		for (int i=0; i<miniRooms.length; i++){
			for (int j=0; j<miniRooms[0].length; j++){
				simbol = 'X';
				MiniRoom aux = miniRooms[i][j];
				if (aux.getStatus().equals("ON")){
					simbol = 'O';
				}
				actualStates[i][j] = simbol;
			}
		}

		return actualStates;
	}
	
	/**
     * Descripcion: permite dar el mapa de los estados de cada cuarto (encendido "O" y apagado "X")
     * 
     * 
     * @return <map><String> con el esquema del encendido y apagado de cada cuarto
     */
	public String getCenterMap(){
		char[][] states = mapMiniRoomStates();
		String map = getMap(states);
		return map;		
	}
	
	/**
	 * Descripcion: Devuelve una cadena con un "formato de mapa". 
	 * @param indica los elementos char [] [] utilizados en el mapa.
	 * @return map Cadena con los datos de los estados pero organizados, simulando ver el edificio
	 * forma legible.
	 */
	public String getMap(char[][] states){
		String map = "";
		for (int i=0; i<miniRooms.length; i++){
			for (int j=0; j<miniRooms[0].length; j++){
				map += states[i][j] + " ";
			}
			map += "\n";
		}
		return map;
	}

}