package org.example.shareResource;

public class SynchronizedEvenGenerator extends EvenGenerator {
    private int currentEvenValue = 0;
    public synchronized int next(){
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
