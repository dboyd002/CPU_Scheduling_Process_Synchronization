import java.util.concurrent.BlockingQueue;
import java.util.Random;

public class Consumer implements Runnable {

    private final BlockingQueue<Integer> buffer;

    public Consumer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        try {

            while (true) {

                // Consume an item from the buffer
                int intToRemove = buffer.take();
                System.out.println(intToRemove + " removed from buffer");

                Random randomSleep = new Random();
                int randSleepInt = randomSleep.nextInt(1000);

                // Simulate some work by sleeping for randSleepInt
                Thread.sleep(randSleepInt);

            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
