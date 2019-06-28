package Lesson_5;

public class ConcurrentOpsOnArray {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        new ConcurrentOpsOnArray().consecutiveOpsOnArr();
        new ConcurrentOpsOnArray().concurrentOpsOnArr();
    }

    void consecutiveOpsOnArr() {
        long a;
        float[] arr = new float[SIZE];
        for (float elem : arr) elem = 1;

        a = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        a = System.currentTimeMillis() - a;

        System.out.println(a);
    }

    void concurrentOpsOnArr() {
        long a;
        float[] arr = new float[SIZE];
        float[][] tempArr = new float[2][HALF];
        for (float elem : arr) elem = 1;

        a = System.currentTimeMillis();

        System.arraycopy(arr, 0, tempArr[0], 0, HALF);
        System.arraycopy(arr, HALF, tempArr[1], 0, HALF);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                tempArr[0][i] = (float) (tempArr[0][i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                tempArr[1][i] = (float) (tempArr[1][i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) * Math.cos(0.4f + (i + HALF) / 2));
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(tempArr[0], 0, arr, 0, HALF);
        System.arraycopy(tempArr[1], 0, arr, HALF, HALF);

        a = System.currentTimeMillis() - a;

        System.out.println(a);
    }
}
