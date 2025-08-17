package br.com.E3N.ActorModel;

import java.util.Map;
import java.util.concurrent.*;

public class ActorSystem {

    private final ExecutorService executorService;
    private final ConcurrentMap<String, Actor> actors = new ConcurrentHashMap<>();

    public ActorSystem(int qtdThreads) {
        this.executorService = Executors.newFixedThreadPool(Math.max(2, qtdThreads));
    }

    public Actor actorOf(Actor actor){
        if (actors.putIfAbsent(actor.getName(), actor) != null){
            throw new IllegalArgumentException("Actor already exists with this name: " + actor.getName());
        }
        executorService.submit(actor::run);
        return actor;
    }

    public void shutdown(){
        for (Map.Entry<String, Actor> a: actors.entrySet()){
            a.getValue().stop();
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS))
                executorService.shutdownNow();
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
