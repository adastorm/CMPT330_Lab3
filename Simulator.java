import java.util.ArrayList;
import java.util.Random;

/**
 * Simulator
 * @author Jonah Watts
 */
public class Simulator {
    static Random r;
    static int process = 0;
    static int interactiveChance, batchChance, simulationTime, quantum, numberOfCPU;
    /**
     * main method to run the program that is specified by the user
     */
    public static void main(String[] args) {
        //Declare the start of the program
        System.out.println("\033[91m✨✨❤️Starting The Program❤️✨✨\033[39m\n");
        
        if (args.length != 5) {
            System.out.println("\033[91m 💀💀💀  Starting The Program Failed  💀💀💀\033[39m\n");
            System.out.println("Correct format  'java Simulator interactiveChance | Batch Chance | Simulation Time | Quantum | CPU Size '");
            System.exit(42);
        }
            
        interactiveChance = Integer.parseInt(args[0]);
        quantum = Integer.parseInt(args[3]);
        batchChance = Integer.parseInt(args[1]);
        simulationTime = Integer.parseInt(args[2]);

        //Create the turnaround time
        int turnAroundTime = 0;

        //Initialize the random variable
        r = new Random();

        //Create the queue for the processes

        Process cpu[] = new Process[Integer.parseInt(args[4])];
        ArrayList<Process> processQueue = new ArrayList<Process>();
        ArrayList<Process> tList;
        ArrayList<Process> completeList = new ArrayList<Process>();
        ArrayList<Process> IOSystem = new ArrayList<Process>();

        //Generate processes until its done
        int time = 0;

        //____ ___  _  _    ____ _   _ ____ _    ____ 
        //|    |__] |  |    |     \_/  |    |    |___ 
        //|___ |    |__|    |___   |   |___ |___ |___
        for (int j=0; j< Integer.parseInt(args[2]); j++) {
            
            //First create the new processes
            tList = createRandomProcesses(time);
            for (int i = 0; i< tList.size(); i++)
            {
                processQueue.add(tList.get(i));
            }

            //process the CPU
            for( int i = 0; i<cpu.length; i++){
                //Incriment and remove
                if(cpu[i] != null)
                {
                    //Incriment all counters
                    cpu[i].decrimentCPU();

                    //Remove completed processes
                    if (cpu[i].processComplete()) {
                        completeList.add(cpu[i]);
                        turnAroundTime += j - cpu[i].returnCreationTime();
                        cpu[i] = null;
                    }
                    //Move blocked processes to the IO queue
                    else if (cpu[i].processBlocked()) {
                        IOSystem.add(cpu[i]);
                        cpu[i] = null;
                    }
                    //Move the processes out of quantum to the ready queue
                    else if (cpu[i].processQuantumdone()) {
                        processQueue.add(cpu[i]);
                        cpu[i] = null;
                    }
                }
                
            }   

            //Process the ready queue
            for( int i = 0; i<cpu.length; i++){
                if(cpu[i] == null && processQueue.size()>0)
                {
                    cpu[i] = processQueue.get(0);
                    processQueue.remove(0);
                }
            }
            //Process the IO queue
            for(int i = 0; i < IOSystem.size(); i++){
                if(IOSystem.get(i).decrimentIO()){
                    IOSystem.get(i).setIO();
                    IOSystem.get(i).resetBurst();
                    processQueue.add(IOSystem.get(i));
                    IOSystem.remove(i);
                }
            }

            time++;
        }


        // ____ ____ ___  ____ ____ ___ 
        // |__/ |___ |__] |  | |__/  |  
        // |  \ |___ |    |__| |  \  |
                
        //Ready processes
            System.out.println("Ready Processes: "+processQueue.size());
        //processes in CPU
            int cpuProcesses = 0;
            for(int i = 0; i < cpu.length; i++)
            {
                if(cpu[i] != null)cpuProcesses++;
            }
            System.out.println("Processes in CPU: "+cpuProcesses);
        //Blocked processes
            System.out.println("Blocked processes: "+IOSystem.size());
        //Completed processes
            System.out.println("Completed processes: "+completeList.size());
        //Accounted for PRocesses
            int accounted = processQueue.size()+cpuProcesses+IOSystem.size()+completeList.size();
            System.out.println("Accounted for " + accounted+ " of " + process +" processes");
        //Number of CPU's
            System.out.println("Number of CPUs: " + cpu.length);
        //Exiting at simulation time
            System.out.println("Exiting at simulation time: "+ time);
        //Simulation result
            System.out.println("Simulation Result,"+quantum+","+completeList.size()+","+simulationTime+","+turnAroundTime);
            

    }


    /**
     * Method to generate processes.
     * @param creationTime the time of creation
     * @return the list of 0-2 processes
     */
    private static ArrayList<Process> createRandomProcesses(int creationTime)
    {
        ArrayList<Process> toReturn = new ArrayList<Process>();
        //Account for 0
        if(batchChance>0 && r.nextInt(batchChance) == 0)
        {
            toReturn.add(createBatchProcess(creationTime));
            process++;
        }
        //Create random Interactive process
        if (interactiveChance > 0 && r.nextInt(interactiveChance) == 0) {
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
        return new Process(type, processID, burst, ioBlock, cpuTime, creationTime,quantum);
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
        return new Process(type, processID, burst, ioBlock, cpuTime, creationTime,quantum);
    }
}


