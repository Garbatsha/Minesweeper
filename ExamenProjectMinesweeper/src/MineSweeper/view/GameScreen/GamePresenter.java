package MineSweeper.view.GameScreen;


import MineSweeper.model.ApplicatieModel;
import MineSweeper.model.MusicModel;
import MineSweeper.spel.ActionType;
import MineSweeper.spel.Tile;
import MineSweeper.view.MainScreen.MainScreenPresenter;
import MineSweeper.view.MainScreen.MainScreenView;
import MineSweeper.view.LossScreen.LossPresenter;
import MineSweeper.view.LossScreen.LossView;
import MineSweeper.view.WinScreen.WinPresenter;
import MineSweeper.view.WinScreen.WinView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Timer;
import java.util.TimerTask;

public class GamePresenter {
    //attributen
    private ApplicatieModel model;
    private GameView view;

    private Timer animatieTimer;
    private boolean popupGetoond;
    private boolean animatieGestart;
    public GamePresenter(ApplicatieModel model, GameView view) {
        this.model = model;
        this.view = view;

        this.startNieuwSpel(); //start een nieuw spel
        this.addEventHandlers(); // voeg event handlers toe
        this.initialiseView(); //initialiseer de weergave van het spel
    }

    private void initialiseView() {
        view.getCheckBoxGeluid().setSelected(MusicModel.speelGeluidsEffcten);
        //reset de teksten op de knoppen in het speelveld
        for (int row = 0; row < view.getPlayState().getBoard().length; ++row) {
            for (int col = 0; col < view.getPlayState().getBoard()[row].length; ++col) {
                Button tmpButton = (Button) view.getGameField().getChildren().get(row * view.getPlayState().getBoard()[row].length + col);
                tmpButton.setText("");
            }
        }
    }

    private void updateView() {
        for (int row = 0; row < view.getPlayState().getBoard().length; ++row) {
            for (int col = 0; col < view.getPlayState().getBoard()[row].length; ++col) {

                Button tmpButton = (Button) view.getGameField().getChildren().get(row * view.getPlayState().getBoard()[row].length + col);

                Tile currentTile = view.getPlayState().getBoard()[row][col];

                Image displayImage = currentTile.getDisplayImage();

                if (displayImage == null) {
                    tmpButton.setDisable(true);
                } else {
                    ImageView btnImage = new ImageView(currentTile.getDisplayImage());
                    btnImage.setFitWidth(14);
                    btnImage.setFitHeight(14);
                    tmpButton.setGraphic(btnImage);
                }
            }
        }
        view.getLblMinesLeft().setText(Integer.toString(view.getPlayState().getFlagsLeft()));
    }

    //event handlers voor de verschillende knoopjes zoals sluiten/opnieuw/muziek
    private void addEventHandlers() {
        view.getBtnClose().setOnAction(e -> gaNaarHoofdMenu());
        view.getBtnOpniew().setOnAction(e -> startNieuwSpel());

        view.getCheckBoxGeluid().setOnAction(e -> MusicModel.speelGeluidsEffcten = view.getCheckBoxGeluid().isSelected());
    }


    private void addGameFieldEventHandlers() {
        view.getGameField().getChildren().clear();
        for (int i = 0; i < view.getPlayState().getBoard().length; ++i) {
            for (int j = 0; j < view.getPlayState().getBoard()[i].length; ++j) {
                Button btnTile = new Button();
                btnTile.setPrefWidth(32);
                btnTile.setPrefHeight(32);

                //key events
                int finalI = i;
                int finalJ = j;
                btnTile.setOnMouseClicked(event -> buttonPressed(finalI, finalJ, event.getButton()));

                // voeg de knop toe aan het speelveld
                view.getGameField().add(btnTile, i, j);
            }

            //initialisatie voor het bijwerken van de klok op het scherm
            view.getPlayState().getSecondsClock().addListener(clockChangeListener);
        }
    }

    // wat gebeurt er als je op linker en rechter muisknop drukt
    private void buttonPressed(int x, int y, MouseButton mouseButton) {
        if (mouseButton == MouseButton.PRIMARY) {
            view.getPlayState().updateTile(x, y, ActionType.REVEAL); //tegel onthullen
        }

        if (mouseButton == MouseButton.SECONDARY) {
            view.getPlayState().updateTile(x, y, ActionType.FLAG); //tegel onthullen met een vlag
        }

        updateView();

        //check voor win/loss
        if (view.getPlayState().isGameOver() && !animatieGestart) {
            //start animatie bij verlies
            animatieTimer = new Timer();
            animatieTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    view.getPlayState().isAnimationPlaying();
                }
            }, 0, 10);

            //begin animatie
            view.getPlayState().getGameBoard().getClickedBomb().getAnimatieFrame().addListener(animatieChangeListener);
            animatieGestart = true;
        }

        if (view.getPlayState().isVictory() && !popupGetoond) {
            this.openPopupWin(); //open popup voor overwinning



        }
    }

    // als de tijd verstreken is dan verlies je
    private final ChangeListener<? super Number> clockChangeListener = (observableValue, oldValue, newValue) -> Platform.runLater(() -> {
        view.getLblClock().setText(String.valueOf(newValue));
        if (newValue.equals(0) && !popupGetoond && model.isSpeedMode()) {
            closeGameView();
            openPopupLoss();
        }
    });

    //sluit de lopende animatie af
    private final ChangeListener<? super Number> animatieChangeListener = (observableValue, oldValue, newValue) -> Platform.runLater(() -> {
        updateView();
        if (!view.getPlayState().isAnimationPlaying()) {
            stopSpelendeAnimatie();
        }
    });

    private void stopSpelendeAnimatie() {
        animatieTimer.cancel();
        view.getPlayState().getGameBoard().getClickedBomb().getAnimatieFrame().removeListener(animatieChangeListener);
        if (!popupGetoond) {
            openPopupLoss();
        }
        animatieGestart = false;
    }

    public void startNieuwSpel() {
        popupGetoond = false;
        animatieGestart = false;

        if (view.getPlayState() != null) {
            view.getPlayState().stopTimer();
        }

        view.initialiseGame(model.getDifficulty(), model.isSpeedMode());
        this.addGameFieldEventHandlers();


    }

    public void gaNaarHoofdMenu() {
        this.closeGameView();
        MainScreenView mainMenuView = new MainScreenView();
        new MainScreenPresenter(model, mainMenuView);
        view.getScene().setRoot(mainMenuView);
    }

    public void openPopupLoss() {
        popupGetoond = true;
        closeGameView();
        LossView lossView = new LossView();
        LossPresenter lossPresenter = new LossPresenter(model, lossView, this);
        Stage lossStage = new Stage(StageStyle.UNDECORATED);
        lossStage.initOwner(view.getScene().getWindow());
        lossStage.initModality(Modality.APPLICATION_MODAL);
        Scene sceneLoss = new Scene(lossView);
        lossStage.setTitle("Helaas, je hebt verloren :(");
        lossStage.setScene(sceneLoss);
        lossStage.setHeight(600);
        lossStage.setWidth(600);
        lossStage.setResizable(false);
        lossStage.centerOnScreen();
        lossStage.getIcons().add(new Image("/images/MinesweeperIcon.png"));
        lossStage.show();
        lossPresenter.addWindowEventHandlers();
    }

    public void openPopupWin() {
        popupGetoond = true;
        closeGameView();
        WinView winView = new WinView();
        WinPresenter winPresenter = new WinPresenter(model, winView, this);
        Stage winStage = new Stage(StageStyle.UNDECORATED);
        winStage.initOwner(view.getScene().getWindow());
        winStage.initModality(Modality.APPLICATION_MODAL);
        Scene sceneWin = new Scene(winView);
        winStage.setTitle("Goed gedaan, nooit gedacht dat jij dit zou halen");
        winStage.setScene(sceneWin);
        winStage.setHeight(600);
        winStage.setWidth(600);
        winStage.setResizable(false);
        winStage.centerOnScreen();
        winStage.getIcons().add(new Image("/images/MinesweeperIcon.png"));
        winStage.show();
        winPresenter.addWindowEventHandlers();
    }
    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(e -> closeGameView());
    }

    //sluit de huidige spelvenster
    private void closeGameView() {
        if (view.getPlayState().getGameBoard().getClickedBomb() != null) {
            view.getPlayState().getGameBoard().getClickedBomb().getAnimatieFrame().removeListener(animatieChangeListener);
        }

        view.getPlayState().getSecondsClock().removeListener(clockChangeListener);

        //timer uitzetten (indien ze aanstaan)
        view.getPlayState().stopTimer();

        if (animatieTimer != null) {
            animatieTimer.cancel();
        }
    }
}
