package org.example.base0311;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程往 ArrayList 里加数据
 * 修复方式：
 * 方案1：Collections.synchronizedList
 * List<Integer> list = Collections.synchronizedList(new ArrayList<>());
 *
 * 方案2：CopyOnWriteArrayList（读多写少场景）
 * List<Integer> list = new CopyOnWriteArrayList<>();
 */
public class UnsafeListDemo {
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            final int num = i;
            threads[i] = new Thread(() -> {
                list.add(num);
            });
            threads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }

        System.out.println("size: " + list.size());
    }
}
