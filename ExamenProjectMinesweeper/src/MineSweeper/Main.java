package MineSweeper;


import MineSweeper.model.ApplicatieModel;
import MineSweeper.model.MusicModel;
import MineSweeper.model.ProgrammaAfbeeldingen;
import MineSweeper.view.StartScreen.StartScreenPresenter;
import MineSweeper.view.StartScreen.StartScreenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {

        // we laden de muziek en afbeeldingen eenmaling in
        MusicModel.laadMuziek();
        ProgrammaAfbeeldingen.laadAfbeeldingen();

        //model en view
        ApplicatieModel model = new ApplicatieModel();
        StartScreenView view =  new StartScreenView();
        StartScreenPresenter presenter = new StartScreenPresenter(model, view);

        //basis settings voor de stage
        primaryStage.setMinHeight(740);
        primaryStage.setMinWidth(1280);
        primaryStage.getIcons().add(new Image("/images/MinesweeperIcon.png"));
        primaryStage.setTitle("Minesweeper - Gemaakt door Afidullah Hamid");
        primaryStage.setScene(new Scene(view));
        presenter.addWindowEventHandlers();
        primaryStage.show();
    }
    public static void main(String[] args) {
        //we gebruiken een extra klasse om de applicatie te lanceren "Launcher.java"
        Application.launch(args);
    }
}
