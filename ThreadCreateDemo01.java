class Mythread extends Thread{
    @Override
    public void run() {
        super.run();
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {

    }
}

public class ThreadCreateDemo01 {
    public static void main(String[] args) {
        //第一种方式，直接实现一个覆写了run的Thread类
        Thread t1 = new Mythread();
        //第二种，将实现了Runnable接口的类传入Thread的构造方法
        Thread t2 = new Thread(new MyRunnable());

        //第一种方法的匿名类
        Thread t3 = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
        //第二种方法的构造方法
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
