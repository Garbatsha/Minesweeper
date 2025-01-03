package MineSweeper.view.WinScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class WinView extends VBox {


    private Button btnspeelOpnieuw;
    private Button btnGaTerugMenu;

    public WinView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {

        btnspeelOpnieuw = new Button("Speel opnieuw");
        btnGaTerugMenu = new Button("Ga terug naar menu");
    }

    private void layoutNodes() {

        btnspeelOpnieuw.setPrefWidth(120);

        ImageView winImage = new ImageView();
        winImage.setImage(new Image("/images/Win.png"));
        winImage.setFitHeight(200);
        winImage.setFitWidth(200);

        String buttonColor = "#FFFFFF";
        String textColor = "#134476";
        btnspeelOpnieuw.setPrefSize(150,30);
        btnGaTerugMenu.setPrefSize(150,30);

        btnspeelOpnieuw.setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold;" +
                " -fx-font-size: 18; -fx-text-fill: " + textColor + ";");
        btnGaTerugMenu.setStyle("-fx-background-color: " + buttonColor + "; -fx-font-family: Serif; -fx-font-weight: bold;" +
                " -fx-font-size: 18; -fx-text-fill: " + textColor + ";");



        this.getChildren().add(winImage);
        this.getChildren().add(btnspeelOpnieuw);
        this.getChildren().add(btnGaTerugMenu);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-image: url('/images/achtergrondminesweeper.png'); -fx-background-repeat: no-repeat;" +
                " -fx-background-size: cover; -fx-background-position: center;");
    }

    public Button getSpeelOpnieuwKnop() {
        return btnspeelOpnieuw;
    }

    public Button getGaTerugMenuKnop() {
        return btnGaTerugMenu;
    }
}
