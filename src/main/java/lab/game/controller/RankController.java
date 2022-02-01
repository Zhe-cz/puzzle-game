package lab.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lab.game.App;
import lab.game.model.Score;
import lab.game.util.FileUtil;

import java.util.ArrayList;

public class RankController {

   private ViewController viewController;
    @FXML
    private VBox names;

    @FXML
    private VBox scores;

    @FXML
    private VBox ids;
    private Stage stage;


    /**
     * set rank
     */
    public void sort() {

        ArrayList<Score> list = FileUtil.inUtil();
        int id =list.size()+1;
        String name = viewController.getPlayerName();
        int score =viewController.getScores();
        Score scores =new Score(id,name,score);
        list.add(scores);
        FileUtil.sort(list);
        FileUtil.outUtil(list);


        Font font =new Font(15);


        Label idLabelTop =new Label();
        idLabelTop.setPrefWidth(names.getPrefWidth());
        idLabelTop.setAlignment(Pos.CENTER);
        idLabelTop.setFont(font);
        idLabelTop.setText("Id");
        ids.getChildren().add(idLabelTop);


        Label nameLabelTop =new Label();
        nameLabelTop.setPrefWidth(names.getPrefWidth());
        nameLabelTop.setAlignment(Pos.CENTER);
        nameLabelTop.setFont(font);
        nameLabelTop.setText("Name");
        names.getChildren().add(nameLabelTop);

        Label  socreLabelTop =new Label();
        socreLabelTop.setText("Score");
        socreLabelTop.setFont(font);
        socreLabelTop.setPrefWidth(names.getPrefWidth());
        socreLabelTop.setAlignment(Pos.CENTER);
        this.scores.getChildren().add(socreLabelTop);
        int i =1;
        for ( Score s :
                list ) {

            Label idLabel =new Label();
            idLabel.setPrefWidth(ids.getPrefWidth());
            idLabel.setPrefHeight(30);
            idLabel.setAlignment(Pos.CENTER);
            idLabel.setFont(font);
            idLabel.setText(i+"");
            ids.getChildren().add(idLabel);



            Label nameLabel =new Label();
            nameLabel.setPrefWidth(names.getPrefWidth());
            nameLabel.setPrefHeight(30);
            nameLabel.setAlignment(Pos.CENTER);
            nameLabel.setFont(font);
            nameLabel.setText(s.getPlayer());

            Label  socreLabel =new Label();
            socreLabel.setText(s.getScores()+"");
            socreLabel.setFont(font);
            socreLabel.setPrefWidth(names.getPrefWidth());
            socreLabel.setPrefHeight(30);
            socreLabel.setAlignment(Pos.CENTER);

            names.getChildren().add(nameLabel);
            this. scores.getChildren().add(socreLabel);
            if(i==10){
                break;
            }
            i++;

        }





    }


    /**
     * get rank record
     */
    public void getMyRank() {

        ArrayList<Score> list =FileUtil.inUtil();

        Font font =new Font(15);


        Label idLabelTop =new Label();
        idLabelTop.setPrefWidth(names.getPrefWidth());
        idLabelTop.setAlignment(Pos.CENTER);
        idLabelTop.setFont(font);
        idLabelTop.setText("Id");
        ids.getChildren().add(idLabelTop);


        Label nameLabelTop =new Label();
        nameLabelTop.setPrefWidth(names.getPrefWidth());
        nameLabelTop.setAlignment(Pos.CENTER);
        nameLabelTop.setFont(font);
        nameLabelTop.setText("Name");
        names.getChildren().add(nameLabelTop);

        Label  socreLabelTop =new Label();
        socreLabelTop.setText("Score");
        socreLabelTop.setFont(font);
        socreLabelTop.setPrefWidth(names.getPrefWidth());
        socreLabelTop.setAlignment(Pos.CENTER);
        scores.getChildren().add(socreLabelTop);
        int i =1;
        for ( Score s :
                list ) {

            Label idLabel =new Label();
            idLabel.setPrefWidth(ids.getPrefWidth());
            idLabel.setPrefHeight(30);
            idLabel.setAlignment(Pos.CENTER);
            idLabel.setFont(font);
            idLabel.setText(i+"");
            ids.getChildren().add(idLabel);



            Label nameLabel =new Label();
            nameLabel.setPrefWidth(names.getPrefWidth());
            nameLabel.setPrefHeight(30);
            nameLabel.setAlignment(Pos.CENTER);
            nameLabel.setFont(font);
            nameLabel.setText(s.getPlayer());

            Label  socreLabel =new Label();
            socreLabel.setText(s.getScores()+"");
            socreLabel.setFont(font);
            socreLabel.setPrefWidth(names.getPrefWidth());
            socreLabel.setPrefHeight(30);
            socreLabel.setAlignment(Pos.CENTER);

            names.getChildren().add(nameLabel);
            scores.getChildren().add(socreLabel);
            if(i==10){
                break;
            }
            i++;

        }
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }
}
