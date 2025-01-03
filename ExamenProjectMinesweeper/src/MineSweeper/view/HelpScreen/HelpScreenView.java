package MineSweeper.view.HelpScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class HelpScreenView extends VBox /* layout type */ {



    private Label lblTitel;
    private Button btnTerug;

    public HelpScreenView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        this.lblTitel = new Label("Tutorial");
        this.btnTerug = new Button("Terug naar Main menu");
    }
    private void layoutNodes() {
        lblTitel.setFont(Font.font("Lucida Fax", FontWeight.BOLD, 30));
        lblTitel.setPadding(new Insets(5, 5, 5, 5));
        lblTitel.setStyle("-fx-font-family: 'Agency FB'; -fx-text-fill: white;-fx-font-weight: bold; -fx-font-size: 50");

        String buttonColor = "#FFFFFF";
        String textColor = "#134476";

        btnTerug.setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold;" +
                " -fx-font-size: 18; -fx-text-fill: " + textColor + ";");

        ImageView handleiding = new ImageView();
        handleiding.setImage(new Image("/images/info.png"));


        this.getChildren().add(lblTitel);
        this.getChildren().add(handleiding);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        handleiding.setFitWidth(560);
        handleiding.setFitHeight(530);

        this.getChildren().add(btnTerug);

        btnTerug.setCursor(Cursor.HAND);

        this.setStyle("-fx-background-image: url('/images/achtergrondminesweeper.png'); -fx-background-repeat: no-repeat;" +
                " -fx-background-size: cover; -fx-background-position: center;");
    }

    // implementatie van de nodige
    // package-private Getters
    public Button getBtnTerug(){
        return btnTerug;
    }
}