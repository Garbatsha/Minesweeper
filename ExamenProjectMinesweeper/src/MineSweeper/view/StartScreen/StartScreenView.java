package MineSweeper.view.StartScreen;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class StartScreenView extends VBox /* layout type */ {
    private GridPane inloggenGrid;
    private Label lblHeader;
    private Label lblGebruikersNaam;
    private TextField txtGebruikersNaam;
    private Button btnInloggen;


    public StartScreenView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.inloggenGrid = new GridPane();

        this.lblHeader = new Label("Welkom op Minesweeper");
        this.lblGebruikersNaam = new Label("Gebruikersnaam: ");

        this.txtGebruikersNaam = new TextField();

        this.btnInloggen = new Button("Inloggen");
        this.btnInloggen.setCursor(Cursor.HAND);
    }

    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);

        inloggenGrid.setAlignment(Pos.CENTER);
        inloggenGrid.setHgap(10);
        inloggenGrid.setVgap(10);
        inloggenGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        inloggenGrid.setStyle("-fx-padding: 15; -fx-background-color: #5C9FC1");
        inloggenGrid.setMaxWidth(1280.0 / 3);


        String buttonColor = "#FFFFFF";
        String textColor = "#134476";

        lblHeader.setStyle("-fx-font-size: 35; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family:'Agency FB';");
        lblGebruikersNaam.setStyle("-fx-font-family: Serif; -fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #FFFFFF;");



        btnInloggen.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 5,4,3,5; -fx-background-insets: 0,1,2,0;" +
                " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 2, 0.0, 0, 1); -fx-font-family: \"Serif\";" +
                " -fx-text-fill: " + textColor + "; -fx-font-size: 16px; ");

        inloggenGrid.add(lblGebruikersNaam, 0,0);
        inloggenGrid.add(txtGebruikersNaam, 1,0, 2,1);
        inloggenGrid.add(btnInloggen, 1,2);

        inloggenGrid.setHalignment(btnInloggen, HPos.CENTER);

        this.getChildren().addAll(lblHeader, inloggenGrid);
        this.setStyle("-fx-background-image: url('/images/achtergrondminesweeper.png');" +
                " -fx-background-repeat: no-repeat; -fx-background-size: cover; -fx-background-position: center;");

    }


    public TextField getTxtGebruikersNaam() {
        return txtGebruikersNaam;
    }

    public Button getBtnInloggen() {
        return btnInloggen;
    }
}