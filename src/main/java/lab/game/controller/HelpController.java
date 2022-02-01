package lab.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lab.game.App;
import lab.game.util.FileUtil;

/**
 *  this is help UI   Controller
 */
public class HelpController {



    /**
     * return menu ui
     * @param event
     */
    @FXML
    void action_button(ActionEvent event) {
        App.lauchMenu("view/menu.fxml");
    }



}
