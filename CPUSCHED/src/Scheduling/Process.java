package Scheduling;

// Process object represents a single process. Composed 3 integer members: arrival time, burst time, and priority.
public class Process {

    private int arrivalTime;
    private int burstTime;
    private int priority;

    public Process(int initArrivalTime, int initBurstTime, int initPriority) {

        arrivalTime = initArrivalTime;
        burstTime = initBurstTime;
        priority = initPriority;

    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setBurstTime(int newBurstTime) {
        burstTime = newBurstTime;
    }

}
