package br.com.E3N.test.thread;

public class CountWithoutSync {
    private int count = 0;

    public void increment(){
        count++;
    }

    public void execute() throws InterruptedException {
        Runnable task = () ->{
            for (int i = 0; i < 1_000_000; i++) {
                this.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Final value without sync " + count);
    }


}
