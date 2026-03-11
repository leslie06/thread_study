package org.example.base0311;

/**
 * volatile练习
 */
public class TaskRunner {
    private static volatile boolean running = true;

    public void stop(){
        running = false;
    }
    public void run(){
        while(running){
            // 模拟任务执行
        }
        System.out.println("任务停止");
    }

    public static void main(String[] args) throws InterruptedException {
        TaskRunner taskRunner = new TaskRunner();
        //子线程跑任务
        Thread t = new Thread(()-> taskRunner.run());
        t.start();

        Thread.sleep(3000); // 主线程睡3秒后停止任务
        taskRunner.stop();
    }
}
