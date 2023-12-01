package Scheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

// Creates and fills a queue with the processes from the simulated processes datafile
public class Parser {

    // Create a Linked List that will store all the newly created processes from the datafile
    LinkedList<Process> processQueue = new LinkedList<Process>();

    public Parser(String datafilePath) throws FileNotFoundException {

        // Init a datafile object for our txt file
        File datafile = new File(datafilePath);
        // Init a scanner object for parsing datafile
        Scanner scanner = new Scanner(datafile);

        // Skip first header line before beginning to create/store processes
        scanner.nextLine();

        // Parses line by line
        while(scanner.hasNextLine()) {

            // Create an array of 3 Strings for each line delimited by one or more spaces
            String[] processMembers = scanner.nextLine().split("\\s+");

            // Create integers from the array Strings
            int arrivalTime = Integer.parseInt(processMembers[0]);
            int burstTime = Integer.parseInt(processMembers[1]);
            int priority = Integer.parseInt(processMembers[2]);

            // Init a new Process object constructed with the parsed values
            Process newProcess = new Process(arrivalTime, burstTime, priority);

            // Add the newly created Process to the queue
            processQueue.offer(newProcess);

        }

    }

    public LinkedList<Process> getProcessQueue() {
        return processQueue;
    }

}
