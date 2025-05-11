package br.com.E3N.test.thread;

public class CountWithSync {

    private int count = 0;

    public synchronized void increment(){
        count++;
    }

    public Runnable execute(final int id) throws InterruptedException {
        return  () -> {
            for (int i = 0; i < 1_000; i++) {
                increment();
            }
            System.out.println("Counted by  " + id + " how many "+ this.count);
        };

    }

}
