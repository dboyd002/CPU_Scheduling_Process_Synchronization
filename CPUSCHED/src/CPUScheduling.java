import Scheduling.FIFOScheduler;
import Scheduling.PriorityPreemptiveScheduler;
import Scheduling.ShortestJobFirstScheduler;
import Scheduling.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CPUScheduling {

    public static void main(String[] args) throws FileNotFoundException {

        String datafilePath = "CPUSCHED/Resources/Datafile1-txt.txt";
        Parser parser1 = new Parser(datafilePath);
        Parser parser2 = new Parser(datafilePath);
        Parser parser3 = new Parser(datafilePath);

        String outputPath = "CPUSCHED/Output/output.txt";

        File outputFile = new File(outputPath);

        PrintStream filePrintStream = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(filePrintStream);

        FIFOScheduler fifoScheduler = new FIFOScheduler(parser1.getProcessQueue());

        System.out.println("**FIFO**");

        System.out.println("Number of Processes: " + String.valueOf(fifoScheduler.getNumProcesses()));
        System.out.println("Total Time Elapsed: " + String.valueOf(fifoScheduler.getTotalElapsedTime()));
        System.out.println("Throughput: " + String.valueOf(fifoScheduler.getThroughput()) + " processes completed per unit of burst time");
        System.out.println("CPU Utilization: " + String.valueOf(fifoScheduler.getCPUUtilization()) + '%');
        System.out.println("Average Wait Time: " + String.valueOf(fifoScheduler.getAvgWaitTime()));
        System.out.println("Average Turnaround Time: " + String.valueOf(fifoScheduler.getAvgTurnaroundTime()));
        System.out.println("Average Response Time: " + String.valueOf(fifoScheduler.getAvgResponseTime()));

        ShortestJobFirstScheduler sjf = new ShortestJobFirstScheduler(parser2.getProcessQueue());

        System.out.println("**SJF**");

        System.out.println("Number of Processes: " + String.valueOf(sjf.getNumProcesses()));
        System.out.println("Total Time Elapsed: " + String.valueOf(sjf.getTotalElapsedTime()));
        System.out.println("Throughput: " + String.valueOf(sjf.getThroughput()) + " processes completed per unit of burst time");
        System.out.println("CPU Utilization: " + String.valueOf(sjf.getCPUUtilization()) + '%');
        System.out.println("Average Wait Time: " + String.valueOf(sjf.getAvgWaitTime()));
        System.out.println("Average Turnaround Time: " + String.valueOf(sjf.getAvgTurnaroundTime()));
        System.out.println("Average Response Time: " + String.valueOf(sjf.getAvgResponseTime()));

        PriorityPreemptiveScheduler priority = new PriorityPreemptiveScheduler(parser3.getProcessQueue());

        System.out.println("**Priority**");

        System.out.println("Number of Processes: " + String.valueOf(priority.getNumProcesses()));
        System.out.println("Total Time Elapsed: " + String.valueOf(priority.getTotalElapsedTime()));
        System.out.println("Throughput: " + String.valueOf(priority.getThroughput()) + " processes completed per unit of burst time");
        System.out.println("CPU Utilization: " + String.valueOf(priority.getCPUUtilization()) + '%');
        System.out.println("Average Wait Time: " + String.valueOf(priority.getAvgWaitTime()));
        System.out.println("Average Turnaround Time: " + String.valueOf(priority.getAvgTurnaroundTime()));
        System.out.println("Average Response Time: " + String.valueOf(priority.getAvgResponseTime()));

    }

}