package br.com.E3N.ActorModel;

import java.io.*;

public class ActorImage extends Actor {

    protected ActorImage(String name) {
        super(name);
    }

    @Override
    protected void onReceive(Message message) throws IOException, InterruptedException {
        if("stop".equalsIgnoreCase(message.content())){
            this.stop();
        }
//        else {
//            byte[] imageBytes = Files.readAllBytes(Path.of(content.imageReference1().getAbsolutePath()));
//            byte[] imageBytes2 = Files.readAllBytes(Path.of(content.imageReference2().getAbsolutePath()));
//            if (Arrays.equals(imageBytes2, imageBytes)) {
//                this.sendTo(content.sender(), content.imageReference1(), content.imageReference2(), "result");
//            }
//        }

        if (message.imageReference1().length() != message.imageReference2().length()) {
            return;
        }

        try (InputStream is1 = new BufferedInputStream(new FileInputStream(new File(message.imageReference1().toURI())));
             InputStream is2 = new BufferedInputStream(new FileInputStream(new File(message.imageReference2().toURI())))) {

            int b1, b2;
            while ((b1 = is1.read()) != -1 && (b2 = is2.read()) != -1) {
                if (b1 != b2) return;
            }
            this.sendTo(message.sender(), message.imageReference1(), message.imageReference2(), "result");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
