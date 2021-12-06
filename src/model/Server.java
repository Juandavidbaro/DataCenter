package model;

public class Server {
    
    private double cache;
    private int numProcessor;
    private Processor MarkProcessor;
    private double ram;
    private int disc;
    private double CapacityDisc;


	//-------------------------	Constructor--------------------------------
	
    public Server(double cache, int numProcessor, int MarkProcessor, double ram, int disc, double CapacityDisc) {
        this.cache = cache;
        this.numProcessor = numProcessor;
        if (MarkProcessor == 1){
            this.MarkProcessor = Processor.INTEL;
        }else {
            this.MarkProcessor = Processor.AMD;
        }
        this.ram = ram;
        this.disc = disc;
        this.CapacityDisc = CapacityDisc;
    }

	//--------------------------------Getter and Setter---------------------------------
    public double getProccesCapacity(){
        return CapacityDisc+ram;
    }


    public double getCache() {
        return this.cache;
    }

    public void setCache(double cache) {
        this.cache = cache;
    }

    public int getNumProcessor() {
        return this.numProcessor;
    }

    public void setNumProcessor(int numProcessor) {
        this.numProcessor = numProcessor;
    }

    public double getRam() {
        return this.ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public int getDisc() {
        return this.disc;
    }

    public void setDisc(int disc) {
        this.disc = disc;
    }

    public double getCapacityDisc() {
        return this.CapacityDisc;
    }

    public void setCapacityDisc(double CapacityDisc) {
        this.CapacityDisc = CapacityDisc;
    }

    public Processor getMarkProcessor() {
        return this.MarkProcessor;
    }

    public void setMarkProcessor(Processor MarkProcessor) {
        this.MarkProcessor = MarkProcessor;
    }
}