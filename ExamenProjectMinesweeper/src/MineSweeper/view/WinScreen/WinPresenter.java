package MineSweeper.view.WinScreen;

import MineSweeper.model.ApplicatieModel;
import MineSweeper.model.MusicModel;
import MineSweeper.view.GameScreen.GamePresenter;

import javafx.stage.Stage;

public class WinPresenter {

    private GamePresenter gamePresenter;

    private ApplicatieModel model;
    private WinView view;

    public WinPresenter(ApplicatieModel model, WinView view, GamePresenter presenter) {
        this.model = model;
        this.view = view;
        this.gamePresenter = presenter;
        this.addEventHandlers();

        MusicModel.speelMuziek(MusicModel.MuziekType.WIN);
    }

    private void addEventHandlers() {
        view.getGaTerugMenuKnop().setOnAction(e -> {
            MusicModel.stopMuziek();
            gamePresenter.gaNaarHoofdMenu();
            this.closePopupWin();
        });

        view.getSpeelOpnieuwKnop().setOnAction(e -> {
            gamePresenter.startNieuwSpel();
            MusicModel.stopMuziek();
            this.closePopupWin();
        });
    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(e -> MusicModel.stopMuziek());

    }

    public void closePopupWin() {
        Stage stage = (Stage) view.getScene().getWindow();
        stage.close();
    }
}
