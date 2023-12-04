
// Entry point for the producer consumer problem
public class PCMain {

    public static void main(String[] args) throws InterruptedException {

        // Create a new buffer for the simulation
        Buffer buffer = new Buffer();

        // Create a producer thread
        Thread producerThread = new Thread(new Producer(buffer.getBuffer()));

        // Create a consumer thread
        Thread consumerThread = new Thread(new Consumer(buffer.getBuffer()));

        // Start the producer and consumer threads
        producerThread.start();
        consumerThread.start();

        // Sleep main thread
        Thread.sleep(10000);

        producerThread.interrupt();
        consumerThread.interrupt();

    }

}