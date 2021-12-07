package model;

public class Server {
    
    private double cache;
    private int numProcessor;
    private Processor markProcessor;
    private double ram;
    private int disc;
    private double capacityDisc;


	//-------------------------	Constructor--------------------------------
	
    public Server(double cache, int numProcessor, int markProcessor, double ram, int disc, double capacityDisc) {
        this.cache = cache;
        this.numProcessor = numProcessor;
        if (markProcessor == 1){
            this.markProcessor = Processor.INTEL;
        }else {
            this.markProcessor = Processor.AMD;
        }
        this.ram = ram;
        this.disc = disc;
        this.capacityDisc = capacityDisc;
    }

	//--------------------------------Getter and Setter---------------------------------
    public double getProccesCapacity(){
        return capacityDisc+ram;
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

    public double getcapacityDisc() {
        return this.capacityDisc;
    }

    public void setcapacityDisc(double capacityDisc) {
        this.capacityDisc = capacityDisc;
    }

    public Processor getmarkProcessor() {
        return this.markProcessor;
    }

    public void setmarkProcessor(Processor markProcessor) {
        this.markProcessor = markProcessor;
    }
}