package br.com.E3N.ActorModel;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor {

    public final BlockingQueue<Message> mailbox = new LinkedBlockingQueue<>();
    private String name;
    private volatile boolean active = true;

    protected Actor(final String name) {
        this.name = name;
    }

    public void sendTo(Actor recipient, final File imageReference1, final File imageReference2, final String message) {
        var msg = new Message(this, imageReference1, imageReference2, message);
        if (recipient != null) recipient.enqueue(msg);
    }

    private void enqueue(Message message) {
        if (active) mailbox.offer(message);
    }

    public void stop() {
        this.active = false;
    }

    public boolean isActive() {
        return this.active;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        try {
            while (isActive()) {
                Message msg = mailbox.take();
                try {
                    onReceive(msg);
                } catch (Exception e) {
                    System.err.printf("[%s] error while processing content %s: %s%n", name, msg.content(), e.getMessage());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.printf("[%s stopped.]%n", name);
        }
    }

    protected abstract void onReceive(final Message message) throws IOException, InterruptedException;

}
