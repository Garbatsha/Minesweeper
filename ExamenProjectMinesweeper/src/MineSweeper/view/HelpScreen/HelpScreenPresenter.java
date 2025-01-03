package MineSweeper.view.HelpScreen;


import MineSweeper.model.ApplicatieModel;
import MineSweeper.view.MainScreen.MainScreenPresenter;
import MineSweeper.view.MainScreen.MainScreenView;

public class HelpScreenPresenter {
    private ApplicatieModel model;
    private HelpScreenView view;
    public HelpScreenPresenter(ApplicatieModel model, HelpScreenView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    //ga terug naar mainmeny als we btnTerug drukken
    private void addEventHandlers() {
        view.getBtnTerug().setOnAction(e -> {
            MainScreenView mainMenuView = new MainScreenView();
            new MainScreenPresenter(model, mainMenuView);
            view.getScene().setRoot(mainMenuView);
        });
    }
    private void updateView() {
        // Vult de view met data uit model
    }

    public void addWindowEventHandlers () {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}
