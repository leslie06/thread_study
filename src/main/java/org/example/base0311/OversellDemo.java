package org.example.base0311;

/**
 * 多线程扣库存，观察超卖
 * 解决方式：同步代码块
 */
public class OversellDemo {
    private static int stock = 10; // 库存10件

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20]; // 20个线程抢10件

        for (int i = 0; i < 20; i++) {
            final int userId = i;
            threads[i] = new Thread(() -> {
                if (stock > 0) {
                    // 模拟网络延迟
                    try { Thread.sleep(10); } catch (InterruptedException e) {}
                    stock--;
                    System.out.println("用户" + userId + "抢购成功，剩余库存：" + stock);
                } else {
                    System.out.println("用户" + userId + "抢购失败，库存不足");
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < 20; i++) {
            threads[i].join();
        }

        System.out.println("最终库存：" + stock); // 可能是负数，超卖了
    }
}
