package Scheduling;

import java.util.Iterator;
import java.util.LinkedList;

// Takes a queue of Processes, simulates First in First Out process scheduling and outputs: number of processes, total elapsed time,
// throughput, CPU utilization, average waiting time, average turnaround time, and average response time.
public class FIFOScheduler {

    // These variables store the results of the simulation
    private int numProcesses;
    private int totalElapsedTime = 0;
    private int processExecutionTime = 0;
    private double throughput = 0;
    private double cpuUtilization;
    private int avgWaitTime;
    private int avgTurnaroundTime;
    private int avgResponseTime;

    // Runs a FIFO scheduling simulation on the given process queue
    public FIFOScheduler(LinkedList<Process> processQueue) {

        for (Process nextProcess : processQueue) {

            numProcesses += 1;

            if (numProcesses > 500) {
                break;
            }

            // Add the time the process spent waiting to the total waiting time count if it waited at all
            if (totalElapsedTime > nextProcess.getArrivalTime()) {
                avgWaitTime += totalElapsedTime - nextProcess.getArrivalTime();
            }

            avgResponseTime += totalElapsedTime - nextProcess.getArrivalTime();

            // Each process has not completed until its burst time is exhausted
            while (nextProcess.getBurstTime() > 0) {

                // Increment elapsed time independent of whether process has arrived
                totalElapsedTime += 1;

                // If this is the last loop before the process terminates, increment the turnaround time of the process.
                // After iteration of processes has completed, this count will be divided by the total number of processes
                // to obtain the average turnaround time.
                if (nextProcess.getBurstTime() == 1) {
                    avgTurnaroundTime += (totalElapsedTime - nextProcess.getArrivalTime());
                }

                // If the process has arrived, decrement burst time for each increment of cpu time
                if (totalElapsedTime > nextProcess.getArrivalTime()) {

                    processExecutionTime += 1;
                    nextProcess.setBurstTime(nextProcess.getBurstTime() - 1);

                }

            }

        }

        // Set results members after all processes complete
        numProcesses--;
        avgWaitTime /= numProcesses;
        throughput = (double) numProcesses / totalElapsedTime;
        throughput = Math.round(throughput * 100.0) / 100.0;
        cpuUtilization = ((double) processExecutionTime / totalElapsedTime) * 100;
        cpuUtilization = Math.round(cpuUtilization * 100.0) / 100.0;
        avgTurnaroundTime /= numProcesses;
        avgResponseTime /= numProcesses;

    }

    public int getNumProcesses() {
        return numProcesses;
    }

    public int getTotalElapsedTime() {
        return totalElapsedTime;
    }

    public int getProcessExecutionTime() {
        return processExecutionTime;
    }

    public double getThroughput() {
        return throughput;
    }

    public double getCPUUtilization() {
        return cpuUtilization;
    }

    public int getAvgWaitTime() {
        return avgWaitTime;
    }

    public int getAvgTurnaroundTime() {
        return avgTurnaroundTime;
    }

    public int getAvgResponseTime() {
        return  avgResponseTime;
    }

}
