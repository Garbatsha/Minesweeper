package MineSweeper.view.GameScreen;

import MineSweeper.spel.Difficulty;
import MineSweeper.spel.PlayState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameView extends BorderPane /* layout type */ {
    // private Node attributen (controls)

    private PlayState playState;

    private TilePane topPane;
    private GridPane gameField;
    private FlowPane statsPane;
    private TilePane controlsPane;

    private ImageView imgClock;
    private Label lblClock;

    private ImageView imgMinesLeft;
    private Label lblMinesLeft;

    private Label lblGameType;

    private CheckBox checkBoxGeluid;
    private Button btnOpniew;
    private Button btnClose;

    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        topPane = new TilePane();
        gameField = new GridPane();

        statsPane = new FlowPane();
        controlsPane = new TilePane();

        imgClock = new ImageView("/Game/clock.png");
        lblClock = new Label("");

        imgMinesLeft = new ImageView("/Game/flag.png");
        lblMinesLeft = new Label("");

        lblGameType = new Label("");

        checkBoxGeluid = new CheckBox("Geluid");
        btnOpniew = new Button("↺");
        btnClose = new Button("X");
    }

    public void initialiseGame(Difficulty difficulty, boolean speedMode) {
        //speltype instellen
        playState = new PlayState(difficulty, speedMode);

        String spelType = "";
        switch (difficulty) {
            case BEGINNER:
                spelType = "Beginner";
                break;
            case AVERAGE:
                spelType = "Average";
                break;
            case EXPERT:
                spelType = "Expert";
                break;
        }

        spelType += " - ";
        spelType += (speedMode) ? "speed" : "casual";

        lblGameType.setText(spelType);

        lblClock.setText(String.valueOf(playState.getSecondsClock().get()));
        lblMinesLeft.setText(String.valueOf(playState.getFlagsLeft()));

        this.btnOpniew.setCursor(Cursor.HAND);
        this.btnClose.setCursor(Cursor.HAND);
        this.checkBoxGeluid.setCursor(Cursor.HAND);
    }

    private void layoutNodes() {
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new Insets(20, 20, 20, 20));

        imgClock.setFitWidth(32);
        imgClock.setFitHeight(32);

        imgMinesLeft.setFitWidth(32);
        imgMinesLeft.setFitHeight(32);

        statsPane.getChildren().addAll(imgClock, lblClock, imgMinesLeft, lblMinesLeft);

        controlsPane.setAlignment(Pos.CENTER);
        checkBoxGeluid.setAlignment(Pos.CENTER_LEFT);

        btnClose.setPrefWidth(20);
        btnClose.setPrefHeight(20);
        btnClose.setAlignment(Pos.TOP_RIGHT);

        controlsPane.getChildren().addAll(checkBoxGeluid, btnOpniew, btnClose);

        topPane.getChildren().addAll(statsPane, lblGameType, controlsPane);

        gameField.setAlignment(Pos.CENTER);

        checkBoxGeluid.setStyle("-fx-text-fill: white");
        checkBoxGeluid.setFont(Font.font("Californian FB", FontWeight.BOLD, 15));
        lblClock.setStyle("-fx-text-fill: white");
        lblClock.setFont(Font.font("Californian FB", FontWeight.BOLD, 30));
        lblMinesLeft.setStyle("-fx-text-fill: white");
        lblMinesLeft.setFont(Font.font("Californian FB", FontWeight.BOLD, 30));
        lblGameType.setFont(Font.font("Californian FB", FontWeight.BOLD, 30));
        lblGameType.setStyle("-fx-text-fill: white");

        String buttonColor = "#FFFFFF";
        String textColor = "#134476";

        btnOpniew.setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: \"Serif\"; -fx-font-size: 16px;" +
                " -fx-text-fill: " + textColor + "; -fx-padding: 5 10 5 10;");
        btnClose.setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: \"Serif\"; -fx-font-size: 16px;" +
                " -fx-text-fill: " + textColor + "; -fx-padding: 5 10 5 10;");


        this.setTop(topPane);
        this.setCenter(gameField);
        this.setStyle("-fx-background-image: url('/images/achtergrondminesweeper.png'); -fx-background-repeat: no-repeat;" +
                " -fx-background-size: cover; -fx-background-position: center;");
    }

    // implementatie van de nodige
    // package-private Getters
    public PlayState getPlayState() {
        return playState;
    }

    public GridPane getGameField() {
        return gameField;
    }

    public Label getLblClock() {
        return lblClock;
    }

    public Label getLblMinesLeft() {
        return lblMinesLeft;
    }

    public CheckBox getCheckBoxGeluid() {
        return checkBoxGeluid;
    }

    public Button getBtnOpniew() {
        return btnOpniew;
    }

    public Button getBtnClose() {
        return btnClose;
    }
}