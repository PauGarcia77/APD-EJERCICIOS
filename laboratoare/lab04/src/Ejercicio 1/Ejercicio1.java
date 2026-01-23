
public class Ejercicio1 {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors(); // numero de nucleos
        Thread[] threads = new Thread[cores];

        for (int i = 0; i < cores; i++) {
            final int id = i; // se usa final para que el hilo pueda capturarlo
            threads[i] = new Thread(() -> {
                System.out.println("Hello from thread #" + id);
            });
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < cores; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
