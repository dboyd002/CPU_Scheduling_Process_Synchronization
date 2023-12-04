import java.lang.reflect.Array;
import java.util.ArrayList;

// Entry point for the producer consumer problem
public class ProducerConsumer {

    private static ArrayList<Thread> producerList = new ArrayList<>();
    private static ArrayList<Thread> consumerList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        // Check if three command-line arguments are provided
        if (args.length != 3) {
            System.out.println("Usage: java ProducerConsumer <sleepTime> <numProducers> <numConsumers>");
            System.exit(1); // Exit the program with an error code
        }

        // Create a new buffer for the simulation
        Buffer buffer = new Buffer();

        // Create the desired number of producer threads and add to producerList
        for (int i = 0; i < Integer.parseInt(args[1]); i++) {

            Thread producerThread = new Thread(new Producer(buffer.getBuffer()));
            producerList.add(producerThread);

        }

        // Create the desired number of consumer threads and add to consumerList
        for (int i = 0; i < Integer.parseInt(args[2]); i++) {

            Thread consumerThread = new Thread(new Consumer(buffer.getBuffer()));
            consumerList.add(consumerThread);

        }

        // Start all producer threads
        for (Thread thread : producerList) {
            thread.start();
        }

        // Start all consumer threads
        for (Thread thread : consumerList) {
            thread.start();
        }

        // Sleep main thread
        Thread.sleep(Integer.parseInt(args[0]));

        // Stop all producer threads
        for (Thread thread : producerList) {
            thread.interrupt();
        }

        // Stop all consumer threads
        for (Thread thread : consumerList) {
            thread.interrupt();
        }

    }

}