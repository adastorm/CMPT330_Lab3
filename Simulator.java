import java.util.ArrayList;
import java.util.Random;

/**
 * Simulator
 */
public class Simulator {
    static Random r;
    static int process = 0;

    static int interactiveChance, batchChanse, simulationTime, quantum, numberOfCPU;

    /**
     * main method to run the program that is specified by the user
     */
    public static void main(String[] args) {
        //Declare the start of the program
        System.out.println("\033[91m✨✨❤️Starting The Program❤️✨✨\033[39m\n");
        
        if (args.length > 5 || args.length <= 0) {
            System.out.println("\033[91m 💀💀💀  Starting The Program Failed  💀💀💀\033[39m\n");
            System.exit(42);
        }
            



        //Initialize the random variable
        r = new Random();

        //Create the queue for the processes

        Process cpu[] = new Process[Integer.parseInt(args[4])];
        ArrayList<Process> processQueue = new ArrayList<Process>();
        ArrayList<Process> tList;
        ArrayList<Process> completeList = new ArrayList<Process>();
        ArrayList<Process> IOSystem = new ArrayList<Process>():

        //Generate processes until its done
        boolean makingProcesses = true;
        int time = 0;

        //Running the CPU
        for (int i=0; i< Integer.parseInt(args[2]); i++) {
            tList = createRandomProcesses(time);
            for (int i = 0; i< tList.size(); i++)
            {
                processQueue.add(tList.get(i));
                if(i>=1) makingProcesses = false;
            }
            time++;
        }

        //Print it out
        for(int i = 0; i<processQueue.size(); i++)
        {
            System.out.println(processQueue.get(i));
        }
    }


    /**
     * Method to generate processes.
     * @param creationTime the time of creation
     * @return the list of 0-2 processes
     */
    private static ArrayList<Process> createRandomProcesses(int creationTime)
    {
        ArrayList<Process> toReturn = new ArrayList<Process>();
 
        int check = r.nextInt(batchChanse);
        //Create random Batch process 
        if (check == 1) {
            toReturn.add(createBatchProcess(creationTime));
            process++;
        }
        //Create random Interactive process
        check = r.nextInt(interactiveChance);
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
    private static Process createBatchProcess(int time) {
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
    private static Process createInteractiveProcess(int time) {
        int type = 0;
        int processID = process;
        int burst = r.nextInt(4) + 8; // Between 8-12
        int ioBlock = r.nextInt(2) + 4; // Between 4-6
        int cpuTime = r.nextInt(8) + 16; // Between 16-24
        int creationTime = time;
        return new Process(type, processID, burst, ioBlock, cpuTime, creationTime);
    }
}


