package org.example.base0311;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

    static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> stockFuture = CompletableFuture.supplyAsync(()->{
            sleep(300);
            return "库存充足";
        },pool);

        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(()->{
            sleep(200);
            return "用户正常";
        },pool);

        CompletableFuture<String> couponFuture = CompletableFuture.supplyAsync(()->{
            sleep(300);
            return "优惠券有效";
        },pool);

        //等待所有查询完成
        CompletableFuture.allOf(stockFuture,userFuture,couponFuture).join();

        System.out.println(stockFuture.get());
        System.out.println(userFuture.get());
        System.out.println(couponFuture.get());

        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) + "ms");

        pool.shutdown();

    }

    static void sleep(int seconds){
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
