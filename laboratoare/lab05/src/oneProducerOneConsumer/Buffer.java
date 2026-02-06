package oneProducerOneConsumer;

public class Buffer {
    private int a;
    private boolean available = false; 
    
    synchronized void put(int value) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        a = value;
        available = true; 
        
        notify();
    }
    
    synchronized int get() {
        
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int value = a;
        available = false; 
        
        notify();
        
        return value;
    }
}