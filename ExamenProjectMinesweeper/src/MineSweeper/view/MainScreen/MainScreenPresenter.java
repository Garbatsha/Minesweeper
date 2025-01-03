package MineSweeper.view.MainScreen;

import MineSweeper.spel.Difficulty;
import MineSweeper.model.ApplicatieModel;
import MineSweeper.model.MusicModel;
import MineSweeper.view.GameScreen.GamePresenter;
import MineSweeper.view.GameScreen.GameView;
import MineSweeper.view.HelpScreen.HelpScreenPresenter;
import MineSweeper.view.HelpScreen.HelpScreenView;



public class MainScreenPresenter {
    private ApplicatieModel model;
    private MainScreenView view;

    public MainScreenPresenter(ApplicatieModel model, MainScreenView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.resetModel();
        this.updateView();

    }

    private void addEventHandlers() {
        // kleuren voor het spel
        String buttonColor = "#FFFFFF";
        String textColor = "#134476";

        // geluid muten en unmuten -> checkboxmutegeluid
        view.getCheckBoxMuteGeluid().setOnAction(e -> {
            if (!view.getCheckBoxMuteGeluid().isSelected()) {
                MusicModel.stopMuziek();
            } else {
                MusicModel.speelMuziek(MusicModel.MuziekType.MENU);
            }
        });

        view.getDifficulty().selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });

        //Verander de kleur van de selected Difficulty buttons, behoud de kleuren van de andere buttons.
        view.getBtnBeginner().setOnAction(e -> {
            if (view.getBtnBeginner().isSelected()) {
                view.getBtnBeginner().setStyle("-fx-background-color: #32a439; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: white");
                view.getBtnAverage().setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
                view.getBtnExpert().setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
                model.setDifficulty(Difficulty.BEGINNER);
            }
        });

        view.getBtnAverage().setOnAction(e -> {
            if (view.getBtnAverage().isSelected()) {
                view.getBtnBeginner().setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
                view.getBtnAverage().setStyle("-fx-background-color: #FFFF00; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: black;");
                view.getBtnExpert().setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
                model.setDifficulty(Difficulty.AVERAGE);
            }
        });

        view.getBtnExpert().setOnAction(e -> {
            if (view.getBtnExpert().isSelected()) {
                view.getBtnBeginner().setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
                view.getBtnAverage().setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
                view.getBtnExpert().setStyle("-fx-background-color: #FF0000; -fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
                model.setDifficulty(Difficulty.EXPERT);
            }
        });

        //speedmode aan of uit zetten
        view.getCheckBoxSpeedMode().setOnAction(e -> model.setSpeedMode(!model.isSpeedMode()));



        // nadat we drukken op de hulpknoop gaan we naar de help/info screen screen
        view.getBtnHelp().setOnAction(e -> {
            HelpScreenView helpScreenView = new HelpScreenView();
            HelpScreenPresenter helpScreenPresenter = new HelpScreenPresenter(model, helpScreenView);
            view.getScene().setRoot(helpScreenView);
        });

        // nadat we drukken op de play knop gaan we naar de game screen
        view.getBtnPlay().setOnAction(e -> {
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(model, gameView);
            view.getScene().setRoot(gameView);
            gamePresenter.addWindowEventHandlers();
            MusicModel.stopMuziek();
        });




    }

    private void resetModel(){
        model.setDifficulty(Difficulty.BEGINNER);
        model.setSpeedMode(false);
    }

    private void updateView() {
        view.getBtnBeginner().setSelected(true);
        view.getCheckBoxSpeedMode().setSelected(false);
        view.getCheckBoxMuteGeluid().setSelected(true);
    }


}
