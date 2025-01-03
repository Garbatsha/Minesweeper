package MineSweeper.model;

import javafx.scene.image.Image;

public class ProgrammaAfbeeldingen {

    public static Image vlagAfbeelding;
    public static Image[] mineAnimatie;
    public static Image[] mineNummers;

    private static boolean geladen = false;     //afbeeldignen maar een keer inladen

    public static boolean laadAfbeeldingen() {
        if (!geladen) {
            try {
                vlagAfbeelding = new Image("/Game/flag.png");

                mineAnimatie = new Image[18];
                for (int i = 0; i < mineAnimatie.length; ++i) {
                    mineAnimatie[i] = new Image("Game/animatie/mijn" + i + ".png");
                }

                mineNummers = new Image[9];
                for (int i = 0; i < mineNummers.length; i++) {
                    mineNummers[i] = new Image("Game/nummers/nummer" + i + ".png");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            geladen = true;
            return true;
        }
        return false;
    }
}
