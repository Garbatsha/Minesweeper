package MineSweeper.view.StartScreen;


import MineSweeper.model.ApplicatieModel;
import MineSweeper.model.MusicModel;
import MineSweeper.view.MainScreen.MainScreenPresenter;
import MineSweeper.view.MainScreen.MainScreenView;

public class StartScreenPresenter {

    private StartScreenView view;
    private ApplicatieModel model;


    public StartScreenPresenter(ApplicatieModel model, StartScreenView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
        MusicModel.speelMuziek(MusicModel.MuziekType.MENU);
    }
    private void addEventHandlers() {

        view.getBtnInloggen().setOnAction(event -> inloggen());


    }

    //nadat we drukken op inloggen gaan we wisselen naar de hoofdscherm/mainmenu
    void inloggen(){
        model.setSpelerNaam(view.getTxtGebruikersNaam().getText());

        MainScreenView mainScreenView = new MainScreenView();
        new MainScreenPresenter(model, mainScreenView);
        view.getScene().setRoot(mainScreenView);

    }
    private void updateView() {
        // Vult de view met data uit model
        view.getTxtGebruikersNaam().setText("");
    }

    public void addWindowEventHandlers () {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}
