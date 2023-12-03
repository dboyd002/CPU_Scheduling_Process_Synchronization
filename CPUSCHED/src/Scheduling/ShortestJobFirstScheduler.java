package Scheduling;

import java.util.LinkedList;

public class ShortestJobFirstScheduler {

    // These variables store the results of the simulation
    private int numProcesses;
    private int totalElapsedTime;
    private int processExecutionTime;
    private double throughput;
    private double cpuUtilization;
    private int avgWaitTime;
    private double avgTurnaroundTime;
    private int avgResponseTime;

    public ShortestJobFirstScheduler(LinkedList<Process> processQueue) {

        totalElapsedTime = 0;
        Process currentProcess;

        while (numProcesses <= 500) {

            numProcesses += 1;

            // Set the currentProcess to the first  in the queue
            currentProcess = processQueue.getFirst();

            // Check each process in the queue to check if it has arrived and compare for which has the smallest burst time
            for (Process process : processQueue) {

                if (process.getArrivalTime() <= totalElapsedTime) {

                    if (process.getBurstTime() < currentProcess.getBurstTime()) {
                        currentProcess = process;
                    } else if (process.getBurstTime() == currentProcess.getBurstTime()) {
                        if (process.getPriority() < currentProcess.getPriority()) {
                            currentProcess = process;
                        }
                    }

                }

            }

            // Add the time the process spent waiting to the total waiting time count if it waited at all
            if (totalElapsedTime > currentProcess.getArrivalTime()) {
                avgWaitTime += totalElapsedTime - currentProcess.getArrivalTime();
            }

            avgResponseTime += totalElapsedTime - currentProcess.getArrivalTime();

            // No preemption, once the smallest burst time has been determined, complete the process.
            while (currentProcess.getBurstTime() > 0) {

                totalElapsedTime += 1;

                // If this is the last loop before the process terminates, increment the turnaround time of the process.
                // After iteration of processes has completed, this count will be divided by the total number of processes
                // to obtain the average turnaround time.
                if (currentProcess.getBurstTime() == 1) {
                    avgTurnaroundTime += (totalElapsedTime - currentProcess.getArrivalTime());
                }

                // If the process has arrived, decrement burst time for each increment of cpu time
                if (totalElapsedTime > currentProcess.getArrivalTime()) {

                    processExecutionTime += 1;
                    currentProcess.setBurstTime(currentProcess.getBurstTime() - 1);

                }

            }

            processQueue.remove(currentProcess);

        }

        // Update numProcesses to reflect the number of processes that actually got processed
        numProcesses--;
        // Calculate avgTurnaroundTime before waitTime gets divided to find its average
        avgTurnaroundTime = (double) (avgWaitTime + processExecutionTime) / numProcesses;
        avgWaitTime /= numProcesses;
        throughput = (double) numProcesses / totalElapsedTime;
        throughput = Math.round(throughput * 100.0) / 100.0;
        cpuUtilization = ((double) processExecutionTime / totalElapsedTime) * 100;
        cpuUtilization = Math.round(cpuUtilization * 100.0) / 100.0;
        avgResponseTime /= numProcesses;

    }

    public int getNumProcesses() {
        return numProcesses;
    }

    public int getTotalElapsedTime() {
        return totalElapsedTime;
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

    public double getAvgTurnaroundTime() {
        return avgTurnaroundTime;
    }

    public int getAvgResponseTime() {
        return  avgResponseTime;
    }

}
