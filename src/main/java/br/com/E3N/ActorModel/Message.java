package br.com.E3N.ActorModel;

import java.io.File;

public record Message(Actor sender, File imageReference2, File imageReference1, String content) {
}
