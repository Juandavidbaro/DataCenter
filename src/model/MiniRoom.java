package model;


import java.util.ArrayList;


public class MiniRoom {

    private int num;
    private double price;
    private boolean rent;
    private int colum;
    private int row;
    private boolean window;
    private ArrayList <Server> rack;
    private Company company;
	
    private boolean status;
    	//if status is true =on
        //if status is false=off;

    
	//--------------------------Constructor-----------------------------

    public MiniRoom(int num,int colum,int row,boolean window) {
        this.num = num;
        this.price = 0;
        this.rent = false;
        this.row=row;
        this.colum=colum;
        this.window=window;
        this.setRack(new ArrayList<>());
        this.setCompany(null);
        this.status=false;
    }

	/**
     * Descripcion: permite conocer la capacidad del procces
     * 
     * 
     * @return <total><double> debe ser un numero double
     */
    public double getProccesCapacity(){
        int total=0;
        for (int i = 0; i < rack.size(); i++) {
            total+=rack.get(i).getProccesCapacity();
        }
        return total;
    }
	
	/**
     * Descripcion: permite cancelar la renta de un mini room
     * 
     * 
     * @return <proccesCapacity><double> debe ser un numero double
     */
    public double cancelRent(){
        double proccesCapacity=getProccesCapacity();
        rack.clear();
        this.company=null;
        this.rent=false;
        this.status=false;

        return proccesCapacity ;
    }

	//---------------------------Getter and Setter---------------------
	
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList <Server> getRack() {
        return rack;
    }

    public void setRack(ArrayList <Server> rack) {
        this.rack = rack;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public boolean isRent() {
        return this.rent;
    }

    public boolean getRent() {
        return this.rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public int getColum() {
        return this.colum;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isWindow() {
        return this.window;
    }

    public boolean getWindow() {
        return this.window;
    }

    public void setWindow(boolean window) {
        this.window = window;
    }


	/**
     * Descripcion: permite dar el status de una mini room
     * 
     * 
     * @return <ON><String> en caso de que este encendido
	 *@return <OFF><String> en caso de que este apagado
     */
    public String getStatus() {
        if (this.status==true){
            return "ON";
        }else{
            return "OFF";
        }
        
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    


    @Override
    public String toString() {
        return 
            "the num: " + getNum() + "\n" +
            "the price: " + getPrice() + "\n" +
            "the rent: " + isRent() + "\n" +
            "the colum: " + getColum() + "\n" +
            "the row: " + getRow() + "\n" +
            "the window: " + isWindow() + "\n"+
            "Status:"+getStatus();
    }



    public String toStringStatus() {
        return 
            "the num: " + getNum() + "\n" +
            "Status:"+getStatus();
    }
  
}