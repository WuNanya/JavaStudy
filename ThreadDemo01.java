public class ThreadDemo01 {
    private static class MyThread  extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("Thread2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void Method() throws InterruptedException {
        while(true){
            System.out.println("MainThread");
            Thread.sleep(1000);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();
        mt.start();
        Method();
    }
}
