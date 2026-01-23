package doubleVectorElements;

public class Main {

    static class DoublerThread extends Thread {
        private int[] v;
        private int start;
        private int end;

        public DoublerThread(int[] v, int start, int end) {
            this.v = v;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                v[i] = v[i] * 2;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 100000013;
        int[] v = new int[N];
        int P = 4; // the program should work for any P <= N

        for (int i = 0; i < N; i++) {
            v[i] = i;
        }

        
        Thread[] threads = new Thread[P];
        int chunkSize = N / P;

        for (int i = 0; i < P; i++) {
            int start = i * chunkSize;
            int end = (i == P - 1) ? N : (i + 1) * chunkSize; // El último thread toma el resto
            
            threads[i] = new DoublerThread(v, start, end);
            threads[i].start();
        }

        // Esperar a que todos los threads terminen
        for (int i = 0; i < P; i++) {
            threads[i].join();
        }

        for (int i = 0; i < N; i++) {
            if (v[i] != i * 2) {
                System.out.println("Wrong answer");
                System.exit(1);
            }
        }
        System.out.println("Correct");
    }
}