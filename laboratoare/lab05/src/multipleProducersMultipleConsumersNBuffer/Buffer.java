package multipleProducersMultipleConsumersNBuffer;

import java.util.Queue;

public class Buffer {
    
    private Queue<Integer> queue;
    private final int maxSize;
    
    public Buffer(int size) {
        queue = new LimitedQueue<>(size);
        this.maxSize = size;
    }
    
    public synchronized void put(int value) {
        while (queue.size() >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        queue.add(value);
        
        notifyAll();
    }
    
    public synchronized int get() {

        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        
        int value = queue.poll();
        
        notifyAll();
        
        return value;
    }
}