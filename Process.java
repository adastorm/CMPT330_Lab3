/**
 * Process
 */
public class Process {

    //type 0 interactive, 1 is batch
    private int type;
    private int processID;
    private int burst;
    private int ioBlock;
    private int cpuTime;
    private int creationTime;

    /**
     * Constructor for the process that creates a specified process
     * @param type 0 for interactive, 1 for batch
     * @param processID define the ID of the process
     * @param burst define the length of the burst
     * @param ioBlock determine what the IO block time is
     * @param cpuTime Deterimine the CPU time
     * @param creationTime Specify the creation time
     */
    public Process(int type, int processID, int burst, int ioBlock, int cpuTime, int creationTime) {
        this.type=type;
        this.processID=processID;
        this.burst=burst;
        this.ioBlock=ioBlock;
        this.cpuTime=cpuTime;
        this.creationTime = creationTime;
    }

    
    public String toString() {
        String s = "\033[7mProcess:\033[27m \nType: ";
        if (type == 0)
            s+="Interactive\n";
        else if (type == 1) 
            s+="Batch\n";
        s+="PID: " + processID + "\n";
        s+="Burst: " + burst + "\n";
        s+="IO Block Time 🕐️:" + ioBlock + "\n";
        s+="CPU Time ⏰: " + cpuTime + "\n";
        s+="Creation Time Stamp ⏳️: " + creationTime + "\n\n";
        return s;
    }
}