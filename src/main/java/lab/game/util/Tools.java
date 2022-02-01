package lab.game.util;

import java.util.Optional;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputControl;

/**
 * game Tools
 */
public class Tools {
    /**
     * set  informationDialog
     * @param alterType  Alert.AlertType
     * @param title title
     * @param header header
     * @param message message
     * @return if click ok return true,else return false
     */
    public static boolean informationDialog(Alert.AlertType alterType, String title, String header, String message) {
        // The button part can be used by default or can be new like this
        Alert alert = new Alert(alterType, message, new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE), new ButtonType("ok", ButtonBar.ButtonData.YES));
        // Set the title of the dialog box
        alert.setTitle(title);
        alert.setHeaderText(header);
        // showAndWait() will not execute the following code until the dialog disappears
        Optional<ButtonType> buttonType = alert.showAndWait();
        // Returns based on the result of the click, or true if "OK" is clicked
        return buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES);
    }
}
