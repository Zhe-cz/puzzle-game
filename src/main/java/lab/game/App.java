package lab.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lab.game.controller.*;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage =stage;
        stage.setTitle("game");
        lauchMenu("view/menu.fxml");
        stage.centerOnScreen();
        stage.getIcons().add(new Image("file:src/main/resources/lab/game/images/1.png"));
        stage.show();
    }

    /**
     * lauch game View ui
     * @param Path Path
     * @param name name
     * @param fileName fileName
     */
    public static void lauchView(String Path,String name,String fileName){
        try {

            FXMLLoader fxmlLoader= loadFXML(Path);
            AnchorPane anchorPane = fxmlLoader.load();
            ViewController viewController = fxmlLoader.getController();
            viewController.setPlayerName(name);
            viewController.setFileName(fileName);
            viewController.initUI();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *lauchInput ui
     * @param Path Path
     */
    public static void lauchInput(String Path){
        try {

            FXMLLoader fxmlLoader= loadFXML(Path);
            AnchorPane anchorPane = fxmlLoader.load();
            InputController inputController = fxmlLoader.getController();
            inputController.init();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *lauchMenu ui
     * @param Path Path
     */
    public static void lauchMenu(String Path){
        try {

            FXMLLoader fxmlLoader= loadFXML(Path);
            AnchorPane anchorPane = fxmlLoader.load();
            MenuController mainViewController =fxmlLoader.getController();
            mainViewController.setStage(stage);
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    /**
     *lauchHelp ui
     * @param path Path
     */
    public static void lauchHelp(String path){
        try {

            FXMLLoader fxmlLoader= loadFXML(path);
            AnchorPane anchorPane = fxmlLoader.load();
            HelpController helpController =fxmlLoader.getController();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     **setRank ui
     * @param path path
     * @param viewController viewController
     */
    public static void setRank(String path, ViewController viewController){
        try {
            Stage stage =new Stage();
            FXMLLoader fxmlLoader= loadFXML(path);
            AnchorPane anchorPane = fxmlLoader.load();
            RankController RankController =fxmlLoader.getController();
            RankController.setViewController(viewController);
            RankController.sort();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Rank");
            stage.centerOnScreen();
            stage.getIcons().add(new Image("file:src/main/resources/lab/game/images/1.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     **lauchRank ui
     * @param path path
     */
    public static void lauchRank(String path){
        try {

            Stage stage =new Stage();
            FXMLLoader fxmlLoader= loadFXML(path);

            AnchorPane anchorPane = fxmlLoader.load();

            RankController RankController =fxmlLoader.getController();

            RankController.getMyRank();

            Scene scene = new Scene(anchorPane);

            stage.setScene(scene);
            stage.setTitle("Rank");

            stage.centerOnScreen();
            stage.getIcons().add(new Image("file:src/main/resources/lab/game/images/1.png"));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *loadFXML
     * @param path
     * @return FXMLLoader
     */
    public static FXMLLoader  loadFXML(String path) {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path));

        return fxmlLoader;

    }

    public static void main(String[] args) {
        launch();
    }
}
