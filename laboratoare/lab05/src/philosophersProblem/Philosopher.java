package philosophersProblem;

public class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;
    private final int id;
    
    public Philosopher(int id, Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;
    }
    
    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        //La solucion es que el último filósofo toma los tenedores en orden inverso
        // Esto rompe el ciclo circular y previene el deadlock
        if (id == Main.N - 1) {
            // Último filósofo: primero el derecho, luego el izquierdo
            synchronized (rightFork) {
                sleep();
                synchronized (leftFork) {
                    System.out.println("Philosopher " + id + " is eating");
                }
            }
        } else {
            // Resto de filósofos: primero el izquierdo, luego el derecho
            synchronized (leftFork) {
                sleep();
                synchronized (rightFork) {
                    System.out.println("Philosopher " + id + " is eating");
                }
            }
        }
    }
}