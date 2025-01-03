package MineSweeper.model;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicModel {
    // in dit model zetten we alle muziek klaar
    private static boolean geladen = false;
    private static boolean spelenMuziek = false;

    public static boolean speelGeluidsEffcten = true;

    private static MediaPlayer muziekSpeler;

    private static Media menuMuziek;
    private static Media winMuziek;
    private static Media lossMuziek;


    //we laden de music hier in met deze methode
    public static boolean laadMuziek() {
        try {
            menuMuziek = new Media(new File("resources/music/menuMuziek.mp3").toURI().toString());
            winMuziek = new Media(new File("resources/music/winMuziek.mp3").toURI().toString());
            lossMuziek = new Media(new File("resources/music/lossMuziek.mp3").toURI().toString());

            geladen = true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //muziek voor menu/win/loss
    public static void speelMuziek(MuziekType muziekSoort) {
        if (geladen) {
            if (!spelenMuziek) {
                switch (muziekSoort) {
                    case MENU:
                        muziekSpeler = new MediaPlayer(menuMuziek);
                        muziekSpeler.setVolume(0.15);
                        muziekSpeler.setCycleCount(MediaPlayer.INDEFINITE);
                        break;
                    case WIN:
                        muziekSpeler = new MediaPlayer(winMuziek);
                        muziekSpeler.setVolume(0.2);
                        break;
                    case LOSS:
                        muziekSpeler = new MediaPlayer(lossMuziek);
                        muziekSpeler.setVolume(0.2);
                        break;
                }

                //muziekSpeler.setVolume();
                muziekSpeler.play();
                spelenMuziek = true;
            }
        } else {
            System.out.println("Muziek moet eerst ingeladen worden.");
        }
    }

    //functie om muziek te stoppen
    public static void stopMuziek() {
        if (spelenMuziek) {
            muziekSpeler.stop();
            spelenMuziek = false;
        }
    }

    //functie voor de verschillende geluidseffecten zoals klikken/een vlag plaatsen/op een mijn klikken
    public static void speelGeluidEffect(GeluidsEffect geluidType) {
        if (speelGeluidsEffcten) {
            AudioClip audioClip = null;
            switch (geluidType) {
                case CLICK:
                    audioClip = new AudioClip(new File("resources/music/click.mp3").toURI().toString());
                    audioClip.setVolume(0.3);
                    audioClip.play();
                case VLAG:
                    audioClip = new AudioClip(new File("resources/music/vlag.mp3").toURI().toString());
                    audioClip.setVolume(0.3);
                    audioClip.play();
                    break;
                case MIJN:
                    audioClip = new AudioClip(new File("resources/music/mijn.mp3").toURI().toString());
                    audioClip.setVolume(0.3);
                    audioClip.play();
                    break;
                default:
                    break;
            }
        }
    }

    //2 enums voor muziekType en geluidseffect
    public enum MuziekType {
        MENU,
        WIN,
        LOSS
    }

    public enum GeluidsEffect {
        CLICK,
        VLAG,
        MIJN
    }


}
