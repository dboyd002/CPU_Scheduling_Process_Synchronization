import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

// Stores Integer objects to be added by a configurable number of producer and consumer threads.
public class Buffer {
    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);

    public BlockingQueue<Integer> getBuffer() { return buffer; }

}
