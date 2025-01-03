package MineSweeper.model;

import MineSweeper.spel.Difficulty;

public class ApplicatieModel {
    private Difficulty difficulty;
    private boolean speedMode;
    private String spelerNaam;
    public ApplicatieModel() {
        // Constructor
    }

    //getters

    public String getSpelerNaam() {
        return spelerNaam;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isSpeedMode() {
        return speedMode;
    }

    //setters

    public void setSpelerNaam(String spelerNaam){
        this.spelerNaam = spelerNaam;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setSpeedMode(boolean speedMode) {
        this.speedMode = speedMode;
    }
}