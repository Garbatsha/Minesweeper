package MineSweeper.spel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;

public class PlayState {
    //afmetingen en aantal mijnen voor verschillende moeilijkheidsgraden
    private final int EASY_BOARD_WIDTH = 10, EASY_BOARD_HEIGHT = 8;
    private final int MEDIUM_BOARD_WIDTH = 18, MEDIUM_BOARD_HEIGHT = 14;
    private final int HARD_BOARD_WIDTH = 24, HARD_BOARD_HEIGHT = 20;

    private final int EASY_MINE_AMOUNT = 10;
    private final int MEDIUM_MINE_AMOUNT = 40;
    private final int HARD_MINE_AMOUNT = 99;

    //tijdsintervallen voor de snelheids modus (in seconden)
    public final int SPEED_TIMER_EASY = 30;
    public final int SPEED_TIMER_MEDIUM = 120;
    public final int SPEED_TIMER_HARD = 300;

    private Board gameBoard;
    private Difficulty difficulty;
    boolean speedMode;

    //timer voor het spel
    private IntegerProperty timerSeconds = new SimpleIntegerProperty(0);
    private Timer gameTimer;

    private boolean playing = false;

    //constructor voor het speelstaat
    public PlayState(Difficulty difficulty, boolean speedMode) {
        this.difficulty = difficulty;
        this.speedMode = speedMode;

        gameTimer = new Timer("Timer thread");
        playing = false;

        //maak het spelbord op basis van de geselecteerde moeilijkheidsgraad
        switch (difficulty) {
            case BEGINNER:
                gameBoard = new Board(EASY_BOARD_WIDTH, EASY_BOARD_HEIGHT, EASY_MINE_AMOUNT);
                break;
            case AVERAGE:
                gameBoard = new Board(MEDIUM_BOARD_WIDTH, MEDIUM_BOARD_HEIGHT, MEDIUM_MINE_AMOUNT);
                break;
            case EXPERT:
                gameBoard = new Board(HARD_BOARD_WIDTH, HARD_BOARD_HEIGHT, HARD_MINE_AMOUNT);
                break;
        }
        // stel de timer op basis van de moeilijkheidsgraad en snelheidsmodus
        if (speedMode) {
            switch (difficulty) {
                case BEGINNER:
                    timerSeconds.setValue(SPEED_TIMER_EASY);
                    break;
                case AVERAGE:
                    timerSeconds.setValue(SPEED_TIMER_MEDIUM);
                    break;
                case EXPERT:
                    timerSeconds.setValue(SPEED_TIMER_HARD);
                    break;
            }
        } else {
            timerSeconds.setValue(0);
        }
    }

    //update een vakje op basis van de actie (onthullen of markeren)
    public void updateTile(int tileX, int tileY, ActionType action) {
        if (!gameBoard.isGameOver() && !gameBoard.isVictory()) {
            switch (action) {
                case REVEAL: //vakje onthullen
                    if (playing) {
                        gameBoard.revealTile(tileX, tileY, true);
                    } else {
                        gameBoard.startGame(tileX, tileY);
                        gameBoard.revealTile(tileX, tileY, true);
                        gameTimer.scheduleAtFixedRate(gameTimerTask, 1000, 1000);
                        playing = true;
                    }
                    break;
                case FLAG: //vakje markeren
                    if (playing) {
                        gameBoard.flagTile(tileX, tileY);
                    } else {
                        gameBoard.startGame(-1, -1);
                        gameBoard.flagTile(tileX, tileY);
                        gameTimer.scheduleAtFixedRate(gameTimerTask, 1000, 1000);
                        playing = true;
                    }
                    break;
            }
            //stop de timer als het spel voorbij is
            if (gameBoard.isGameOver() || gameBoard.isVictory()) {
                stopTimer();
            }
        }
    }

    //timer-taal voor het spel
    private final TimerTask gameTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (speedMode) {
                timerSeconds.setValue(timerSeconds.getValue() - 1);
                if (timerSeconds.get() < 0) {
                    //spel is voorbij
                    gameBoard.setGameOver(true);
                    gameTimer.cancel();
                    timerSeconds.setValue(0);
                }
            } else {
                timerSeconds.setValue(timerSeconds.get() + 1);
            }
        }
    };

    //getters

    //controleer of het spel gewonnen is
    public boolean isVictory() {
        return gameBoard.isVictory();
    }

    //controleer of het spel voorbij is
    public boolean isGameOver() {
        return gameBoard.isGameOver();
    }

    public Tile[][] getBoard() {
        return gameBoard.getBoard();
    }

    public void stopTimer() {     //deconstructor (opruimen van extra timer thread)
        gameTimer.cancel();
    }

    public IntegerProperty getSecondsClock() {
        return timerSeconds;
    }


    //haal het aantal overgebleven vlaggen op
    public int getFlagsLeft() {
        switch (difficulty) {
            case BEGINNER:
                return EASY_MINE_AMOUNT - gameBoard.getAmountFlagsPlaced();
            case AVERAGE:
                return MEDIUM_MINE_AMOUNT - gameBoard.getAmountFlagsPlaced();
            case EXPERT:
                return HARD_MINE_AMOUNT - gameBoard.getAmountFlagsPlaced();
        }
        return 99;
    }

    public boolean isAnimationPlaying() {
        return gameBoard.isAnimationPlaying();
    }

    public Board getGameBoard() {
        return gameBoard;
    }

}
