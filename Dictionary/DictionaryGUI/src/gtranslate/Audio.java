/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtranslate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author thanh
 */
public class Audio {

    private static Audio audio;

    private Audio() {
    }

    public synchronized static Audio getInstance() {

        if (audio == null) {
            audio = new Audio();
        }
        return audio;
    }

    public InputStream getAudio(String text, String languageOutput)
            throws IOException {

        URL url = new URL("http://translate.google.com/translate_tts?ie=UTF-8&tl=" + languageOutput + "&client=tw-ob&q="
                + text.replace(" ", "%20"));
        URLConnection urlConn = url.openConnection();
        urlConn.addRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        InputStream audioSrc = urlConn.getInputStream();
        return new BufferedInputStream(audioSrc);
    }

    public void play(InputStream sound) throws JavaLayerException {
        new Player(sound).play();
    }
}
