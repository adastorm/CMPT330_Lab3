import java.util.ArrayList;
import java.util.Random;

/**
 * Simulator
 */
public class Simulator {
    Process processList[];
    static Random r;
    int process = 0;

    public static void main(String[] args) {
        //Declare the start of the program
        System.out.println("\033[91m✨✨❤️Starting The Program❤️✨✨\033[39m");
        
        //Initialize the random variable
        r = new Random();

        //Create the queue for the processes
        ArrayList<Process> processQueue = new ArrayList<Process>();

        //Generate processes until its done
        boolean makingProcesses = true;
        while (makingProcesses) {
            create
        }
    }


    /**
     * Method to generate processes.
     * @param creationTime the time of creation
     * @return the list of 0-2 processes
     */
    private ArrayList<Process> createRandomProcesses(int creationTime)
    {
        ArrayList<Process> toReturn = new ArrayList<Process>();
 
        int check = r.nextInt(10);
        //Create random Batch process 
        if (check == 1) {
            toReturn.add(createBatchProcess(creationTime));
            process++;
        }
        //Create random Interactive process
        check = r.nextInt(10);
        if (check == 1) {
            toReturn.add(createInteractiveProcess(creationTime));
            process++;
        }
        
        return toReturn;
    }

    /**
     * Method to create a batch process
     * @return A newly created batch process
     */
    private Process createBatchProcess(int time) {
        int type = 1;
        int processID = process;
        int burst = r.nextInt(100) + 200; //Between 200-300
        int ioBlock = r.nextInt(4) + 8; //Between 8-12
        int cpuTime = r.nextInt(200) + 400; // Between 400-600
        int creationTime = time;
        return new Process(type, processID, burst, ioBlock, cpuTime, creationTime);
    }    
    
    /**
     * Method to create an interactive process
     * @return A newly created interactive process
     */
    private Process createInteractiveProcess(int time) {
        int type = 0;
        int processID = process;
        int burst = r.nextInt(4) + 8; // Between 8-12
        int ioBlock = r.nextInt(2) + 4; // Between 4-6
        int cpuTime = r.nextInt(8) + 16; // Between 16-24
        int creationTime = time;
        return new Process(type, processID, burst, ioBlock, cpuTime, creationTime);
    }
}


