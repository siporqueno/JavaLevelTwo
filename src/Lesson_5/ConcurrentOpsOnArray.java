package Lesson_5;

public class ConcurrentOpsOnArray {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        new ConcurrentOpsOnArray().consecutiveOpsOnArr();
        new ConcurrentOpsOnArray().concurrentOpsOnArrForTwoThreads();

        for (int i = 0; i <= 7; i++) {
            System.out.println("Число потоков: " + (int) Math.pow(2, i) + " (2 в степени " + i + ")");
            new ConcurrentOpsOnArray().concurrentOpsOnArrForSomeNoOfThreads((int) Math.pow(2, i));
        }

        for (int i = 0; i <= 7; i++) {
            System.out.println("Число потоков: " + (int) Math.pow(10, i) + " (10 в степени " + i + ")");
            new ConcurrentOpsOnArray().concurrentOpsOnArrForSomeNoOfThreads((int) Math.pow(10, i));
        }
    }

    // One thread
    void consecutiveOpsOnArr() {
        long a;
        float[] arr = new float[SIZE];
        for (float elem : arr) elem = 1;

        a = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        a = System.currentTimeMillis() - a;

        System.out.println("Один поток \n" + a);
    }

    // Two threads
    void concurrentOpsOnArrForTwoThreads() {
        long a;
        float[] arr = new float[SIZE];
        float[][] tempArr = new float[2][HALF];
        for (float elem : arr) elem = 1;

        a = System.currentTimeMillis();

        System.arraycopy(arr, 0, tempArr[0], 0, HALF);
        System.arraycopy(arr, HALF, tempArr[1], 0, HALF);

        Thread t1 = new Thread(() -> {
            calculate(tempArr[0], 0);
        });

        Thread t2 = new Thread(() -> {
            calculate(tempArr[1], HALF);
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

        System.out.println("Два потока \n" + a);
    }

    // Number of threads = threadsNo
    void concurrentOpsOnArrForSomeNoOfThreads(int threadsNo) {
        long a;
        float[] arr = new float[SIZE];
        int subSize = SIZE / threadsNo;
        float[][] tempArr = new float[threadsNo][subSize];
        Thread[] t = new Thread[threadsNo];

        for (float elem : arr) elem = 1;

        a = System.currentTimeMillis();

        for (int i = 0; i < threadsNo; i++) {
            System.arraycopy(arr, subSize * i, tempArr[i], 0, subSize);
        }

        for (int k = 0; k < threadsNo; k++) {

            final int temp = k;
            t[k] = new Thread(() -> {
                calculate(tempArr[temp], subSize * temp);
            });

        }

        for (Thread thread : t) thread.start();

        try {
            for (Thread thread : t) thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < threadsNo; i++) {
            System.arraycopy(tempArr[i], 0, arr, subSize * i, subSize);
        }

        a = System.currentTimeMillis() - a;

        System.out.println(a);
    }

    void calculate(float[] inputArr, int shift) {
        for (int i = 0; i < inputArr.length; i++) {
            inputArr[i] = (float) (inputArr[i] * Math.sin(0.2f + (i + shift) / 5) * Math.cos(0.2f + (i + shift) / 5) * Math.cos(0.4f + (i + shift) / 2));
        }
    }
}
