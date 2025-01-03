package MineSweeper.view.LossScreen;


import MineSweeper.model.ApplicatieModel;
import MineSweeper.model.MusicModel;
import MineSweeper.view.GameScreen.GamePresenter;
import javafx.stage.Stage;

public class LossPresenter extends ApplicatieModel {

    private GamePresenter gamePresenter;
    private ApplicatieModel model;
    private LossView view;

    public LossPresenter(ApplicatieModel model, LossView view, GamePresenter presenter) {
        this.model = model;
        this.view = view;
        this.gamePresenter = presenter;
        this.addEventHandlers();

        MusicModel.speelMuziek(MusicModel.MuziekType.LOSS);
    }

    //eventhandlers voor knoojes om opnieuw te spelen of naar main menu te gaan
    private void addEventHandlers() {
        view.getGaTerugMenuKnop().setOnAction(e -> {
            MusicModel.stopMuziek();
            gamePresenter.gaNaarHoofdMenu();
            this.closePopupLoss();
        });

        view.getSpeelOpnieuwKnop().setOnAction(e -> {
            MusicModel.stopMuziek();
            gamePresenter.startNieuwSpel();
            this.closePopupLoss();
        });
    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(e -> MusicModel.stopMuziek());
    }

    public void closePopupLoss() {
        Stage stage = (Stage) view.getScene().getWindow();
        stage.close();
    }
}
