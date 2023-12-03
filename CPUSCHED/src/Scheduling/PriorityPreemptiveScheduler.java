package Scheduling;

import java.util.LinkedList;

public class PriorityPreemptiveScheduler {

    // These variables store the results of the simulation
    private int numProcesses;
    private int totalElapsedTime;
    private int processExecutionTime;
    private double throughput;
    private double cpuUtilization;
    private int avgWaitTime;
    private final double avgTurnaroundTime;
    private int avgResponseTime;

    public PriorityPreemptiveScheduler(LinkedList<Process> processQueue) {

        // Stores processes from processQueue when they have reached their arrivalTime
        LinkedList<Process> arrivedQueue = new LinkedList<>();

        while (numProcesses < 500 || !arrivedQueue.isEmpty()) {

            numProcesses += 1;

            // Check if there are processes arriving at the current time
            while (!processQueue.isEmpty() && processQueue.peek().getArrivalTime() == totalElapsedTime) {
                Process arrivingProcess = processQueue.poll();
                arrivedQueue.add(arrivingProcess);
            }

            // If there are processes in arrivedQueue, find the one with the shortest burst time
            if (!arrivedQueue.isEmpty()) {
                Process currentProcess = arrivedQueue.getFirst();

                // Compare the currentProcess to processes in the arrivedQueue. If a process in the arrivedQueue has a
                // shorter burst time than the currentProcess, set it as the currentProcess.
                // If the two burst times are equal, sort based on priority.
                for (Process process : arrivedQueue) {
                    if (process.getBurstTime() < currentProcess.getBurstTime()) {
                        currentProcess = process;
                    } else if (process.getBurstTime() == currentProcess.getBurstTime()) {
                        if (process.getPriority() < currentProcess.getPriority()) {
                            currentProcess = process;
                        }
                    }
                }

                // When a new process is assigned as the current process, increment wait time.
                if (totalElapsedTime > currentProcess.getArrivalTime()) {
                    avgWaitTime += totalElapsedTime - currentProcess.getArrivalTime();
                }

                avgResponseTime += totalElapsedTime - currentProcess.getArrivalTime();

                processExecutionTime += 1;
                currentProcess.setBurstTime(currentProcess.getBurstTime() - 1);

                // If the burst time is completed, remove the process from arrivedQueue
                if (currentProcess.getBurstTime() == 0) {
                    arrivedQueue.remove(currentProcess);
                }
            }

            // Increment the total elapsed time
            totalElapsedTime++;

        }

        // Set results members after all processes complete

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
