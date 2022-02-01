package lab.game.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lab.game.App;

/**
 *  Home ui Controller
 *  Handles home page click events
 */
public class MenuController {
	

    private Stage stage;

    /**
     * enter help ui
     * @param event ActionEvent
     */
	@FXML
    void action_help(ActionEvent event) {

        App.lauchHelp("view/help.fxml");

    }
    /**
     * enter game ui
     * @param event ActionEvent
     */
    @FXML
    void action_start(ActionEvent event) {

        App.lauchInput("view/input.fxml");
    }
    /**
     * enter rank ui
     * @param event ActionEvent
     */
    @FXML
    void action_rank(ActionEvent event) {
        App.lauchRank("view/rank.fxml");


    }
    /**
     * set Stage
     */
	public void setStage(Stage stage) {
		this.stage =stage;
		
	}

}
