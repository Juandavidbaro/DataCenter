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
        int num = 1;

        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                boolean window = false;
                if (i == 0 || i == 7 || j == 0 || j == 49) {
                    window = true;
                }

                miniRooms[i][j] = new MiniRoom(num, j, i, window);
                num++;
            }
        }

        miniRooms[7][49].setStatus(true);

    }
	
	public String changeStateMinirooms() {       
		String message = "";
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                               
                miniRooms[i][j].setStatus(true);             

            }
        }
		message="All the rooms were lit";
		
		return message;
       
    }
	
	public void turnOffL(){
		for (int i=0; i<miniRooms.length; i++){
			miniRooms[i][0].setStatus(false);
		}

		for (int i=1; i<miniRooms[0].length; i++){
			miniRooms[0][i].setStatus(false);
		}
	}
	public void turnOffZ(){
		for (int i=0; i<miniRooms[0].length; i++){
			miniRooms[0][i].setStatus(false);
			miniRooms[miniRooms.length-1][i].setStatus(false);
			
		}

		for (int i=miniRooms.length-1, j=miniRooms[0].length-1; i>-1 && j>-1; i--, j--){
			miniRooms[i][j].setStatus(false);
		}
	}
	public void turnOffH(){
		for (int i=0; i<miniRooms.length; i++){
			if ((i+1)%2 != 0){
				for (int j=0; j<miniRooms[0].length; j++){
					miniRooms[i][j].setStatus(false);
				}
			}
		}
	}
	public void turnOffO(){
		for (int i=0; i<miniRooms.length; i++){
			for (int j=0; j<miniRooms[0].length; j++){
				if (j==0 || j==(miniRooms[0].length-1) || i==0 || i==(miniRooms.length-1)){
					miniRooms[i][j].setStatus(false);
				}
			}
		}
	}
	public void turnOffM(int column){
		for (int i=0; i<miniRooms.length; i++){
			miniRooms[i][column-1].setStatus(false);
		}
	}
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
                if (aux.isRent()==false) {
                    message+=aux.toString()+"\n";
                }

            }
        }
        
        return message;
    }

	/**
     * Descripcion: permite conocer el status de las mini rooms
     * 
     * 
     * @return <message><String> con la informacion status de las mini rooms
     */
    public String infoStatusRooms() {
        String message = "";

        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                MiniRoom aux=miniRooms[i][j];
                if (aux.isRent()==false) {
                    message+="-----------------------------------\n"+aux.toStringStatus()+"\n";
                }

            }
        }
        
        return message;
    }
	
	/**
     * Descripcion: permite realizar la renta de un minicuarto
     * 
     * 
     * 
     */
    public String rentAMiniroom(int num, String name, String nit, ArrayList<Server> rack) {
        MiniRoom room=searchRoom(num);
        room.setCompany(new Company(name, nit));
        room.setRack(rack);
        room.setRent(true);

        return "Rent a mini-room success";

    }

	/**
     * Descripcion: permite buscar la mini room
     * 
     * @param <num><int> debe ser un entero
     * @return <room><MiniRoom> el objeto de la mini room
     */
    public MiniRoom searchRoom(int num){
        MiniRoom room=null;
        boolean find = false;
        for (int i = 0; i < miniRooms.length && !find; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                if (miniRooms[i][j].getNum()==num) {
                    find=true;
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

}