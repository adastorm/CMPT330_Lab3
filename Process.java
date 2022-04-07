/**
 * Process class
 * @author Jonah Watts
 */
public class Process {

    //type 0 interactive, 1 is batch
    //Tons of private data for the process to store
    private int type;
    private int processID;
    private int burst;
    private int ioBlock;
    private int cpuTime;
    private int creationTime;
    private int ioClock;
    private int quantum;
    private int quantumRemain;
    private int burstRemain;
    private int cpuOriginalTime;

    /**
     * Constructor for the process that creates a specified process
     * @param type 0 for interactive, 1 for batch
     * @param processID define the ID of the process
     * @param burst define the length of the burst
     * @param ioBlock determine what the IO block time is
     * @param cpuTime Deterimine the CPU time
     * @param creationTime Specify the creation time
     * @param quantum Specify the quantum of the process
     */
    public Process(int type, int processID, int burst, int ioBlock, int cpuTime, int creationTime, int quantum) {
        this.type = type;
        this.processID = processID;
        this.burst = burst;
        this.ioBlock = ioBlock;
        this.cpuTime = cpuTime;
        cpuOriginalTime = cpuTime;
        this.creationTime = creationTime;
        this.quantum = quantum;
        quantumRemain = quantum;
        burstRemain = burst;
    }
    
    /**
     * Function to get what the original set runtime of the program was
     * @return the original cpu runtime
     */
    public int originalCPUTime() {
        return cpuOriginalTime;
    }

    /**
     * Function to simulate a clock tick on a process
     */
    public void decrimentCPU() {
        cpuTime--;
        burstRemain--;
        quantumRemain--;
    }

    /**
     * Process that shows if the procces is done running
     * @return True if finished, false if not
     */
    public boolean processComplete(){
        if (cpuTime <= 0)
            return true;
        else
            return false;
    }

    /**
     * Process that shows if the process is blocked
     * @return True if blocked, false otherwise
     */
    public boolean processBlocked(){
        if ( burstRemain <= 0)
            return true;
        else
            return false;
    }

    /**
     * Process that shows if the process is out of quantum
     * @return true if out of quantum, false otherwise
     */
    public boolean processQuantumdone() {
        if (quantumRemain <= 0)
            return true;
        else
            return false;
    }
    
    /**
     * Resets the quantim to the established quantum
     */
    public void resetQuantum() {
        quantumRemain = quantum;
    }

    /**
     * Resets the burst time
     */
    public void resetBurst(){
        burstRemain = burst;
    }
    

    /**
     * decriment the io clock
     * @return flase if done running, true otherwise
     */
    public boolean decrimentIO()
    {
        ioClock--;
        if (ioClock <= 0)
            return true;
        else
            return false;
    }

    /**
     * Method to reset the IO block clock
     */
    public void setIO()
    {
        ioClock = ioBlock;
    }


    /**
     * prints out a formatted version of the process
     */
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