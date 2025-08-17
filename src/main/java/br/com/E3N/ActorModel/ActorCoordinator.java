package br.com.E3N.ActorModel;

import br.com.E3N.shared.SameImage;

import java.util.HashMap;
import java.util.Map;

public class ActorCoordinator extends Actor {

    private final Map<String, ActorImage> imageActors = new HashMap<>();

    protected ActorCoordinator(String name) {
        super(name);
    }

    @Override
    protected void onReceive(Message message) throws InterruptedException {
        SameImage sameImage = SameImage.getInstance();
        if ("stop".equalsIgnoreCase(message.content())){
            sameImage.hasFinished();
            this.stop();
        } else {
            sameImage.comparisonCount();
            sameImage.add(message.imageReference1().getName(), message.imageReference2().getName());
        }
    }
}
