module game {
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;
    opens lab.game.controller to javafx.fxml,javafx.graphics;
    opens lab.game to javafx.fxml,javafx.graphics;
    exports lab.game.test to  junit;

}