package MineSweeper.spel;

import MineSweeper.model.ProgrammaAfbeeldingen;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public class Tile {
    // Variabelen voor mijnhoeveelheid en mijnstatus
    private int mineAmount;
    private boolean isMine;

    // Variabelen voor vlag- en verborgen status
    private boolean isFlagged;
    private boolean isHidden;

    // Variabelen voor animatie
    private long animatieStart;
    private IntegerProperty animatieFrame;
    private boolean animatieSpelend;

    // Constructor voor een vakje
    public Tile(int mineAmount, boolean isMine) {
        this.mineAmount = mineAmount;
        this.isMine = isMine;
        isFlagged = false;
        isHidden = true;
        animatieFrame = new SimpleIntegerProperty(0);
        animatieSpelend = false;
    }

    // Haal de juiste afbeelding op basis van de status van het vakje
    public Image getDisplayImage() {
        if (isFlagged) {
            return ProgrammaAfbeeldingen.vlagAfbeelding;    // Teruggeven van vlag afbeelding
        }

        if (isHidden) {
            return ProgrammaAfbeeldingen.mineNummers[0];    // Verborgen -> lege afbeelding
        }

        if (isMine) {
            return ProgrammaAfbeeldingen.mineAnimatie[animatieFrame.getValue()];
        }

        if (mineAmount == 0) {   // Als het aantal mijnen 0 is, geef null terug
            return null;
        }

        return ProgrammaAfbeeldingen.mineNummers[mineAmount];    // Teruggeven van afbeelding met het juiste aantal
    }

    // Start de animatie voor een ontploffende mijn
    public void startAnimatie() {
        animatieStart = System.currentTimeMillis();
        animatieSpelend = true;
    }

    //getters en setters
    public int getMineAmount() {
        return mineAmount;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    // Update de huidige frame van de ontploffende mijn-animatie
    public void updateAnimationFrame() {
        animatieFrame.setValue((int) (System.currentTimeMillis() - animatieStart) / 50);
        if (animatieFrame.getValue() >= ProgrammaAfbeeldingen.mineAnimatie.length - 1) {
            animatieFrame.setValue(ProgrammaAfbeeldingen.mineAnimatie.length - 1);
            animatieSpelend = false;
        }
    }

    public IntegerProperty getAnimatieFrame() {
        return animatieFrame;
    }

    public boolean isPlayingAnimation() {
        return animatieSpelend;
    }
}
