package org.example.juc.raceConditions;

public class TestSynchronizedIncrementDemo {

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 20;
        int incrementNum = 10000;

        TestSynchronizedIncrement testIncrement = new TestSynchronizedIncrement();
        Thread[] threads = new Thread[threadNum];
        for(int i = 0;i < threadNum;i++){
            threads[i] = new Thread(() ->{
                for (int j = 0; j < incrementNum; j++) {
                    testIncrement.increment();
                }
            });
        }
        //开启线程
        for(Thread t : threads){
            t.start();
        }
        //等待所有线程执行完毕
        for(Thread t : threads){
            t.join();
        }
        System.out.println("最终结果: " + testIncrement.getCount());

        System.out.println("期望结果: " + (threadNum * incrementNum));
    }

}
