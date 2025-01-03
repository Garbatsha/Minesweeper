package MineSweeper.view.MainScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MainScreenView extends VBox /* layout type */ {
    // private Node attributen (controls)

    private TilePane aflsuitPane;

    private Label lblTitle;
    private CheckBox checkBoxSpeedMode;

    private Button btnPlay;

    private Button btnHelp;
    private CheckBox checkBoxMuteGeluid;

    ToggleGroup difficulty;
    ToggleButton btnBeginner;
    ToggleButton btnAverage;
    ToggleButton btnExpert;



    public MainScreenView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {

        this.aflsuitPane = new TilePane();
        this.lblTitle = new Label("Minesweeper");

        this.btnBeginner = new ToggleButton("Beginner");
        this.btnAverage = new ToggleButton("Average");
        this.btnExpert = new ToggleButton("Expert");

        this.difficulty = new ToggleGroup();
        this.btnBeginner.setToggleGroup(difficulty);
        this.btnAverage.setToggleGroup(difficulty);
        this.btnExpert.setToggleGroup(difficulty);

        this.checkBoxSpeedMode = new CheckBox("Speed");

        this.btnPlay = new Button("Play");
        this.btnHelp = new Button("Help/Info");

        this.checkBoxMuteGeluid = new CheckBox("Sound");

        //Wanneer we hoveren over de items dat onze cursor veranderd naar een hand
        this.checkBoxSpeedMode.setCursor(Cursor.HAND);

        this.btnPlay.setCursor(Cursor.HAND);
        this.btnHelp.setCursor(Cursor.HAND);
        this.btnBeginner.setCursor(Cursor.HAND);
        this.btnAverage.setCursor(Cursor.HAND);
        this.btnExpert.setCursor(Cursor.HAND);
        this.btnHelp.setCursor(Cursor.HAND);
        this.checkBoxMuteGeluid.setCursor(Cursor.HAND);

    }
    private void layoutNodes() {
        //de mute en sluiten button/checkbox locatie
        aflsuitPane.setAlignment(Pos.TOP_RIGHT);
        aflsuitPane.setPadding(new Insets(5));
        aflsuitPane.getChildren().add(checkBoxMuteGeluid);

        //opmaak van de buttons
        btnBeginner.setPrefSize(150,30);
        btnAverage.setPrefSize(150,30);
        btnExpert.setPrefSize(150,30);
        btnHelp.setPrefSize(150,30);
        btnPlay.setPrefSize(150,30);
        btnHelp.setPrefSize(150,30);


        String buttonColor = "#FFFFFF";
        String textColor = "#134476";

        checkBoxMuteGeluid.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 5,4,3,5;" +
                " -fx-background-insets: 0,1,2,0; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 2, 0.0, 0, 1);" +
                " -fx-font-family: \"Serif\"; -fx-text-fill: " + textColor + "; -fx-font-size: 12px; -fx-padding: 5 10 5 10;");


        lblTitle.setStyle("-fx-font-family: 'Agency FB'; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 50");

        btnBeginner.setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold;" +
                " -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
        btnAverage.setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold;" +
                " -fx-font-size: 18; -fx-text-fill: " + textColor + ";");

        btnExpert.setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold;" +
                " -fx-font-size: 18; -fx-text-fill: " + textColor + ";");

        btnHelp.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 5,4,3,5; -fx-background-insets:" +
                " 0,1,2,0; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 2, 0.0, 0, 1); -fx-font-family: \"Serif\";" +
                " -fx-text-fill: " + textColor + "; -fx-font-size: 16px; -fx-padding: 10 20 10 20;");

        checkBoxSpeedMode.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 5,4,3,5;" +
                " -fx-background-insets: 0,1,2,0; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 2, 0.0, 0, 1);" +
                " -fx-font-family: \"Serif\"; -fx-text-fill: " + textColor + "; -fx-font-size: 16px; -fx-padding: 10 20 10 20;");

        btnPlay.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 5,4,3,5; -fx-background-insets: 0,1,2,0;" +
                " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 2, 0.0, 0, 1); -fx-font-family: \"Serif\";" +
                " -fx-text-fill: " + textColor + "; -fx-font-size: 16px; -fx-padding: 10 20 10 20;");


        // Voeg de nodes toe aan de Vbox
        this.getChildren().addAll(aflsuitPane,lblTitle,btnBeginner,btnAverage,btnExpert,checkBoxSpeedMode,btnPlay,btnHelp);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        this.setStyle("-fx-background-image: url('/images/achtergrondminesweeper.png');" +
                " -fx-background-repeat: no-repeat; -fx-background-size: cover; -fx-background-position: center;");

    }

    // implementatie van de nodige
    // package-private Getters



    public Label getLblTitle() {
        return lblTitle;
    }

    public CheckBox getCheckBoxSpeedMode() {
        return checkBoxSpeedMode;
    }

    public Button getBtnPlay() {
        return btnPlay;
    }


    public Button getBtnHelp() {
        return btnHelp;
    }

    public CheckBox getCheckBoxMuteGeluid() {
        return checkBoxMuteGeluid;
    }

    public ToggleGroup getDifficulty() {
        return difficulty;
    }

    public ToggleButton getBtnBeginner() {
        return btnBeginner;
    }

    public ToggleButton getBtnAverage() {
        return btnAverage;
    }

    public ToggleButton getBtnExpert() {
        return btnExpert;
    }
}