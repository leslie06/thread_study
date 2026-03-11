package org.example.base0311;

/**
 * 银行账户转账
 */
public class BankAccount {
    private String name;
    private int balance;

    public BankAccount(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public static void transfer(BankAccount from,BankAccount to,int amount){
        // 锁两个账户，避免死锁：统一按对象hashCode排序加锁
        Object first = System.identityHashCode(from) < System.identityHashCode(to) ? from : to;
        Object second = first == from ? to : from;
        synchronized (first){
            synchronized (second){
                if(from.balance < amount){
                    System.out.println("余额不足，转账失败");
                    return;
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println(from.name + "->" + to.name + " 转账：" + amount + "，余额：" + from.balance
                + " 对方余额：" + to.balance);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount alice = new BankAccount("Alice", 1000);
        BankAccount bob = new BankAccount("Bob", 1000);

        //模拟并发转账
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                transfer(alice, bob, 100);
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                transfer(bob, alice, 100);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("最终余额：Alice=" + alice.balance + " Bob=" + bob.balance);
    }
}
