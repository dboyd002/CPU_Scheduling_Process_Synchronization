import Scheduling.FIFOScheduler;
import Scheduling.Parser;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String datafilePath = "CPUSCHED/Resources/Datafile1-txt.txt";
        Parser parser = new Parser(datafilePath);

        FIFOScheduler fifoScheduler = new FIFOScheduler(parser.getProcessQueue());

        System.out.println("Number of Processes: " + String.valueOf(fifoScheduler.getNumProcesses()));
        System.out.println("Total Time Elapsed: " + String.valueOf(fifoScheduler.getTotalElapsedTime()));
        System.out.println("Throughput: " + String.valueOf(fifoScheduler.getThroughput()) + " processes completed per unit of burst time");
        System.out.println("CPU Utilization: " + String.valueOf(fifoScheduler.getCPUUtilization()) + '%');
        System.out.println("Average Wait Time: " + String.valueOf(fifoScheduler.getAvgWaitTime()));
        System.out.println("Average Turnaround Time: " + String.valueOf(fifoScheduler.getAvgTurnaroundTime()));
        System.out.println("Average Response Time: " + String.valueOf(fifoScheduler.getAvgResponseTime()));

    }

}