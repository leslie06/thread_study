package org.example.base0311;

/**
 * 卖票程序，保证不会卖重
 */
public class TicketDemo {
    private static int tickets = 10; // 10张票
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5]; // 5个窗口卖票
        for (int i = 0; i < 5; i++) {
            final int windowId = i + 1;
            threads[i] = new Thread(() -> {
                while (true){
                    synchronized (lock){
                        if(tickets <= 0){
                            System.out.println("窗口" + windowId + "票已售罄");
                            break;
                        }
                        System.out.println("窗口" + windowId + "卖出第" + tickets + "张票，剩余票数：" + (tickets - 1));
                        tickets--;
                    }
                    try {
                        Thread.sleep(10); // 模拟卖票的时间，增加发生线程切换的概率
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < 5; i++) {
            threads[i].join();
        }
        System.out.println("最终剩余票数：" + tickets); // 稳定输出 0

    }

}
