package org.example.base0311;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture执行并发任务有三种模式
 * 1.exceptionally  返回默认值
 * 2.handle  不管成功还是失败都会进来，可以统一处理结果。
 * 3.whenComplete  不改变结果，记录日志或者执行补偿逻辑
 */
public class TaskFailDemo {
    public static void main(String[] args) {
        //1.异常处理，返回默认值
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
            int x = 1/0;
            return "success";
        })
        .exceptionally(ex->{
            System.out.println("发生异常：" + ex.getMessage());
            return "默认结果";
        });
        System.out.println(f1.join());

        //2.统一处理结果
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
            if(true){
                throw new RuntimeException("调用失败");
            }
            return "正常结果";
        })
        .handle((result,ex)->{
            if(ex != null){
                System.out.println("发生异常：" + ex.getMessage());
                return "处理后的默认结果";
            }
            return result;
        });
        System.out.println(f2.join());

        //3.记录日志，不改变结果
        CompletableFuture<Object> f3 = CompletableFuture.supplyAsync(()->{
                throw new RuntimeException("错误");
            })
            .whenComplete((result,ex)->{
                if(ex != null){
                    System.out.println("记录异常日志：" + ex.getMessage());
                }
            });
        try{
            System.out.println(f3.join());
        }catch (Exception e){
            System.out.println("最终还是会抛异常");
        }
    }
}
