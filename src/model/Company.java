package model;

public class Company {
    private String name;
    private String nit;

	//------------------Constructor-------------------

    public Company(String name, String nit) {
        this.name = name;
        this.nit = nit;
    }
	
	//------------------Getter and setter ------------
	
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNit() {
        return this.nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
  
}