import java.util.concurrent.BlockingQueue;
import java.util.Random;

public class Producer implements Runnable {

    private final BlockingQueue<Integer> buffer;

    public Producer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        try {

            while (true) {

                // Generate random integers for the object inserted into the buffer and the subsequent sleep time of the thread
                Random randomBuffer = new Random();
                int randBufferInt = randomBuffer.nextInt(100);
                Random randomSleep = new Random();
                int randSleepInt = randomSleep.nextInt(1000);

                // Place the randBufferInt Integer in the buffer, blocked by the interface if the buffer is full.
                buffer.put(randBufferInt);
                System.out.println(randBufferInt + " added to buffer");
                // Simulate some work by sleeping for randSleepInt
                Thread.sleep(randSleepInt);

            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
