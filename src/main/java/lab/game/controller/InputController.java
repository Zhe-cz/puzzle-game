package lab.game.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lab.game.App;
import lab.game.util.Tools;

import java.io.File;
/**
 *  this is input UI   Controller
 */
public class InputController {

//Field
    @FXML
    private TextField nameField;
    @FXML
    private Button changeBtn;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label fileNameLabel;
    private String fileName="Starter.txt";

    /**
     *Initializing the Controller
     * Set the listView value
     */
    public void init(){
        fileNameLabel.setText("Starter.txt");
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        int starterIndex = getFiles(listView);
        listView.getSelectionModel().select(starterIndex);
        changeBtn.setOnAction(e -> selectBoard( ));
    }


    /**
     * listView select way
     * set fileNameLabel Text
     * set  game load fileName value
     */
    private void selectBoard() { // TODO
        String noewFile= listView.getSelectionModel().getSelectedItem();
        fileNameLabel.setText( noewFile);
        fileName =noewFile;
    }

    /**
     * start game button way
     * check nameField length and remind
     * enter game ui
     * @param event ActionEvent
     */
    @FXML
    void startGame(ActionEvent event) {
        if(nameField.getText().length()>3||nameField.getText().length()<=0){
            Tools.informationDialog(Alert.AlertType.INFORMATION,"tip","tip","length should be > 0 and < 3");
        }else{
            App.lauchView("view/view.fxml",nameField.getText(),fileName);
        }


    }

    /**
     * load game map file name
     * and set listView value
     * @param listView game listView
     * @return map :Starter 's  index
     */
    private int getFiles (ListView<String> listView) { // TODO

        ObservableList<String > fileList= FXCollections.observableArrayList();
        String path = "src/main/resources/lab/game/map/";
        File src =  new File(path);
        int index=-1;
        for(int i=0;i<src.listFiles().length;i++) {
            fileList.add(src.listFiles()[i].getName());
            if(src.listFiles()[i].getName().equals("Starter.txt")) {
                index =i;
            }
        }
        this.listView .setItems(fileList);
        return index;
    }
}
