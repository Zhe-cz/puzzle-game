package lab.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lab.game.App;
import lab.game.model.Cell;
import lab.game.model.Coordinate;
import lab.game.model.Replace;
import lab.game.model.piece.*;
import lab.game.util.Tools;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * game controller
 * Responsible for the interactive details of the game
 */
public class ViewController {

    /**
     * girdPane
     */
    @FXML
    private GridPane girdPane;
    @FXML
    private Pane pane;
    @FXML
    private Label score;


    /**
     * choose Pane
     */
    @FXML
    private ImageView btnImage1;
    @FXML
    private ImageView btnImage2;
    @FXML
    private ImageView btnImage3;
    @FXML
    private ImageView btnImage4;
    @FXML
    private ImageView btnImage5;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;

    /**
     * leftPane
     */
    @FXML
    private Label circleNumLable;
    @FXML
    private Label triangleNumLable;
    @FXML
    private Label squareNumLable;
    @FXML
    private Label pentagonNumLabel;
    @FXML
    private Label hexagonNumLabel;

    /**
     * rightPane
     */
    @FXML
    private Button hintButton;
    @FXML
    private Button undoReplaceBtn;
    @FXML
    private TextField fileNameField;

    /**
     * Field
     */
    public int rownum=4 ,colnum=4; //Number of map rownum and colnum
    public int size = 4; //Number of map rownum size and colnum  size
    public final Cell[][] board = new Cell[size][size];
    private final List<Replace> replaces = new ArrayList<>();
    private Cell clickCell;
    private int scores;
    private int ReplaceSize ;
    private int circleSize;
    private int triangleSize;
    private int squareSize;
    private int pentagonSize;
    private int hexagonSize;
    private String playerName;
    private String fileName;

    /**
     * to mene ui
     * @param event ActionEvent
     */
    @FXML
    void returnHome(ActionEvent event) {
        App.lauchMenu("view/menu.fxml");
    }


    /**
     * choosePane button1 Action
     *
     * @param event
     */
    @FXML
    void button1(ActionEvent event) {

        chooseButton("1");
    }


    /**
     * choosePane button2 Action
     *
     * @param event
     */
    @FXML
    void button2(ActionEvent event) {
        chooseButton("2");
    }


    /**
     * choosePane button3 Action
     *
     * @param event
     */
    @FXML
    void button3(ActionEvent event) {
        chooseButton("3");
    }

    /**
     * choosePane button4 Action
     *
     * @param event
     */
    @FXML
    void button4(ActionEvent event) {
        chooseButton("4");
    }

    /**
     * choosePane button5 Action
     *
     * @param event
     */
    @FXML
    void button5(ActionEvent event) {
        chooseButton("5");
    }




    /**
     * undo Replace Shape
     *
     * @param event ActionEvent
     */
    @FXML
    void undoReplace(ActionEvent event) {
        undoReplace();
    }

    /**
     * saveBoard to File
     * @param event
     */
    @FXML
    void saveBoard(ActionEvent event) {
        String saveFileSuccess = "Saved board";
        String saveFileExistsError = "Error: File already exists";
        String saveFileNotTxtError = "Error: File must end with .txt";
        String saveFileNameIsNullError = "Error: File must end with .txt and FileName cannot be null";
        if(fileNameField.getText().trim().equals("")) {
            Tools.informationDialog(Alert.AlertType.ERROR,"tip","tip",saveFileNotTxtError);
        }else {
            int index = fileNameField.getText().lastIndexOf(".");
            String  txt = fileNameField.getText().substring(index+1, fileNameField.getText().length());
            String  fileName =fileNameField.getText().substring(0, index);
            if(txt.equals("txt")) {
                File file =new File("src/main/resources/lab/game/map/"+fileNameField.getText());
                if(fileName.trim().equals("")) {
                    Tools.informationDialog(Alert.AlertType.ERROR,"tip","tip",saveFileNameIsNullError);
                }else {
                    if(file.exists()) {
                        Tools.informationDialog(Alert.AlertType.ERROR,"tip","tip",saveFileExistsError);
                    }else {
                        saveMap(new File("src/main/resources/lab/game/map/"+fileNameField.getText()));
                        Tools.informationDialog(Alert.AlertType.INFORMATION,"tip","tip",saveFileSuccess);
                    }
                }


            }else {
                Tools.informationDialog(Alert.AlertType.ERROR,"tip","tip",saveFileNotTxtError);
            }

        }


    }

    /**
     * Initialize the number of game graphics
     * load game map
     */
    public void initUI(){
          circleSize =2;
          triangleSize =3;
          squareSize =3;
          pentagonSize =4;
          hexagonSize =4;

        String boardName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".txt";
        fileNameField.setText(boardName);
        fileNameField.setStyle("-fx-background-color: #181a1b; -fx-text-fill: white;");

         circleNumLable.setText(circleSize+"");
        triangleNumLable.setText(triangleSize+"");
        squareNumLable.setText(squareSize+"");
        pentagonNumLabel.setText(pentagonSize+"");
         hexagonNumLabel.setText(hexagonSize+"");


        File file =new File("src/main/resources/lab/game/map/"+fileName);
        girdPane.setVgap(5);
        girdPane.setHgap(5);
        readMap(file);
        CheckisErrorReplace();
        

    }

    /**
     * Reload mapList
     */
    void readBoard(){
        girdPane.getChildren().clear();
        for (int i =0;i<rownum;i++){
            for (int j =0;j<colnum;j++){
                girdPane.add(board[i][j],i,j);


            }
        }
    }


    /**
     * read map from file
     * @param file
     */
    protected void readMap(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", file.getPath());
            System.exit(1);
        }

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                switch (piece) {
                    case "1" -> cell.setPiece(new Circle());
                    case "2" -> cell.setPiece(new Square());
                    case "3" -> cell.setPiece(new Triangle());
                    case "4" -> cell.setPiece(new Pentagon());
                    case "5" -> cell.setPiece(new Hexagon());
                    default -> cell.setPiece(null);
                }
                this.board[row][col] = cell;

                girdPane.add(cell,row,col);
                cell.setOnAction(e->{
                    if(clickCell!=null){
                        Tools.informationDialog(Alert.AlertType.WARNING,"tip","tip",
                                "You have selected a Cell, please select the shape you want to replace ");
                    }else
                    {
                        clickCell =cell;
                        chaneImageToYellow(cell);
                        this.board[cell.getCoordinate().row][cell.getCoordinate().col] =cell;
                        this.pane.setDisable(false);
                        hintButton.setDisable(false);
                    }


                });
                col += 1;
            }
            col = 0;
            row += 1;
        }

        scanner.close();
        System.out.printf("Loaded board from %s.\n", file.getPath());
    }

    /**
     * If you click on the button of choosePane,
     * the selected piece will replace the image
     */
    void chooseButton(String button){
        Cell formCell =getFromCell();
        Piece piece =null;
        //The corresponding button
        if(button.equals("1")){
            piece =new Circle();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/2/1.png", 80, 80, true, true));
        }
        if (button.equals("2")){
            piece =new Triangle();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/2/3.png", 80, 80, true, true));
        }
        if (button.equals("3")){
            piece =new Square();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/2/4.png", 80, 80, true, true));

        }
        if (button.equals("4")){
            piece =new Pentagon();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/2/5.png", 80, 80, true, true));

        }
        if (button.equals("5")){
            piece =new Hexagon();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/2/6.png", 80, 80, true, true));

        }
        this.pane.setDisable(true);
        clickCell.setPiece(piece);
        Replace replace =new Replace(formCell,clickCell);
        replaces.add(replace);
        board[clickCell.getCoordinate().row][clickCell.getCoordinate().col] =clickCell;
        readBoard();
        scores+=1;
        score.setText(scores+"");
        ReplaceSize+=1;
        isErrorReplace(clickCell);
        isWin();
        clickCell=null;



        //The corresponding button
        if(button.equals("1")){
            circleSize-=1;
            circleNumLable.setText(circleSize+"");
            if(circleSize==0){
                btn1.setDisable(true);
            }
        }
        if(button.equals("2")){
            triangleSize-=1;
            triangleNumLable.setText(triangleSize+"");
            if(triangleSize==0){
                btn2.setDisable(true);
            }
        }
        if(button.equals("3")){
            squareSize-=1;
            squareNumLable.setText(squareSize+"");
            if(squareSize==0){
                btn3.setDisable(true);
            }
        }
        if(button.equals("4")){
            pentagonSize-=1;
            pentagonNumLabel.setText(pentagonSize+"");
            if(pentagonSize==0){
                btn4.setDisable(true);
            }
        }

        if(button.equals("5")){
            hexagonSize-=1;
            hexagonNumLabel.setText(hexagonSize+"");
            if(hexagonSize==0){
                btn5.setDisable(true);
            }
        }
        getCell(new Coordinate(formCell.getCoordinate().row,formCell.getCoordinate().col)).setDisable(true);
        restChoosePanel();

    }


    /**
     * save map to file
     * @param file txt
     */
    private void saveMap(File file) {

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuilder line = new StringBuilder("");
            for (int i =0;i<rownum;i++){
                for (int j =0;j<colnum;j++){
                    if (board[i][j].getPiece().getType()==Piece.Type.CIRCLE){
                        line.append("1");

                    }
                    if (board[i][j].getPiece().getType()==Piece.Type.TRIANGLE){
                        line.append("2");
                    }
                    if (board[i][j].getPiece().getType()==Piece.Type.SQUARE){
                        line.append("3");
                    }
                    if (board[i][j].getPiece().getType()==Piece.Type.PENTAGON){
                        line.append("4");
                    }
                    if (board[i][j].getPiece().getType()==Piece.Type.HEXAGON){
                        line.append("5");

                    }
                    if(j<colnum-1){
                        line.append(" ");
                    }else{
                        line.append("\n");
                    }



                }
            }
            writer.write(line.toString());
            writer.close();
            System.out.printf("Saved board to %s.\n", file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", file.getPath());
        }
    }


    /**
     * Returns the cell ready to overwrite the image
     * @return cell
     */
    public Cell getFromCell(){
        Cell cell =new Cell();
        cell.setCoordinate(clickCell.getCoordinate());
        cell.setPiece(clickCell.getPiece());
        return cell;
    }

    /**
     *  undoReplace  Detail
     *  if replaces.size()>0 ,you can undo
     *
     */
    public void undoReplace() { // TODO
        if(replaces.size()>0) {
            Replace replace = replaces.get(replaces.size()-1);
            resetSize(replace.toCell.getPiece().getType());
            Cell fromCell = replace.fromCell;
            Cell toCell = replace.toCell;
            board[toCell.getCoordinate().row][toCell.getCoordinate().col].setPiece(fromCell.getPiece());
            board[toCell.getCoordinate().row][toCell.getCoordinate().col].setDisable(false);
            chaneImageToBlank( board[toCell.getCoordinate().row][toCell.getCoordinate().col]);
            replaces.remove(replace);
            readBoard();
            scores-=1;
            score.setText(scores+"");
            Tools.informationDialog(Alert.AlertType.INFORMATION,"tip","tip","undoMove success");
        }else{
            Tools.informationDialog(Alert.AlertType.WARNING,"tip","tip","you have no undoMove");
        }
    }


    /**if you undoReplace
     *reset Shape Size
     *
     */
    public void resetSize(Piece.Type type){
        if(type.equals(Piece.Type.CIRCLE)){
           circleSize+=1;
            circleNumLable.setText(circleSize+"");

        }
        if(type.equals(Piece.Type.TRIANGLE)){
            triangleSize+=1;
            triangleNumLable.setText(triangleSize+"");
        }
        if(type.equals(Piece.Type.SQUARE)){
                squareSize+=1;
                squareNumLable.setText(squareSize+"");
        }
        if(type.equals(Piece.Type.PENTAGON)){
            pentagonSize+=1;
            pentagonNumLabel.setText(pentagonSize+"");
        }
        if(type.equals(Piece.Type.HEXAGON)){
            hexagonSize+=1;

            hexagonNumLabel.setText(hexagonSize+"");
        }

    }

    /**
     * Get all the possible cell SurroundingPosition that is possible to replace to from the fromCell.
     * @param fromCell The cell that has the piece that is going to be replace
     * @return List of cells that are SurroundingPosition
     */
    public List<Cell> getSurroundingPosition(Cell fromCell) {
        List<Cell> positionsCell = new ArrayList<>();
        int[][] possibleMoves = {{-1,0}, {0,1}, {1,0}, {0,-1}};

        for (int[] move: possibleMoves) {
            Coordinate oldCoordinate = fromCell.getCoordinate();
            int row = move[0] + oldCoordinate.row;
            int col = move[1] + oldCoordinate.col;
            Coordinate newCoordinate = new Coordinate(row, col);
            // Determine if the neighbor element is out of bounds
            if (onBoard(newCoordinate)){
                Cell toCell = getCell(newCoordinate);
                // Check whether the neighbor type is the same
                if (haveReplace(new Replace(fromCell, toCell))){
                    positionsCell.add(toCell);
                }
            }

        }

        return positionsCell;
    }

    /**
     * Checks if the given replace is valid.
     * @param replace a replace
     * @return True, if the replace is valid, false otherwise
     */
    public Boolean haveReplace(Replace replace) {
        Cell fromCell = replace.fromCell;
        Cell toCell = replace.toCell;
        Coordinate fromCoordinate = fromCell.getCoordinate();
        Coordinate toCoordinate = replace.toCell.getCoordinate();
        if (!onBoard(toCoordinate)) return false;
//        Returns true if the surrounding piece type is different from the piece from which it came;
        if(fromCell.getPiece().getType()!=toCell.getPiece().getType()){
            return  true;
        }else{
            return false;
        }
    }

    /**
     * Check that the overwritten graph is correct
     *If not, the game ends
     * @param fromCell overwritten Cell
     * @return
     */
    public Boolean  isErrorReplace(Cell fromCell){

        boolean isErrorReplace =false;
        int[][] possibleMoves = {{-1,0}, {0,1}, {1,0}, {0,-1}};
        for (int[] move_now: possibleMoves) {
            Coordinate oldCoordinate = fromCell.getCoordinate();
            int row = move_now[0] + oldCoordinate.row;
            int col = move_now[1] + oldCoordinate.col;
            Coordinate newCoordinate = new Coordinate(row, col);
            if (onBoard(newCoordinate)) {
                Cell toCell = getCell(newCoordinate);
                if (!haveReplace(new Replace(fromCell, toCell))) {
                    isErrorReplace =true;
                    Tools.informationDialog(Alert.AlertType.ERROR,"GameOver","GameOver","GameOver Type is  the same!");
                    girdPane.setDisable(true);
                    undoReplaceBtn.setDisable(true);
                    hintButton.setDisable(true);
                    App.setRank("view/rank.fxml",this);
                }
            }
        }

        return false;
    }


    /**
     * Check that the All cell graph is correct
     *If not, the game ends
     */
    public void  CheckisErrorReplace(){
        for (int i=0;i<size;i++){
            for(int j = 0;j<size;j++){
                Cell fromCell =board[i][j];
                int[][] possibleMoves = {{-1,0}, {0,1}, {1,0}, {0,-1}};
                for (int[] move_now: possibleMoves) {
                    Coordinate oldCoordinate = fromCell.getCoordinate();
                    int row = move_now[0] + oldCoordinate.row;
                    int col = move_now[1] + oldCoordinate.col;
                    Coordinate newCoordinate = new Coordinate(row, col);
                    if (onBoard(newCoordinate)) {
                        Cell toCell = getCell(newCoordinate);
                        if (!haveReplace(new Replace(fromCell, toCell))) {
                            girdPane.setDisable(true);
                            undoReplaceBtn.setDisable(true);
                            hintButton.setDisable(true);
                            break;
                        }
                    }
                }break;
            }
        }

    }

    /**
     * hint
     *Click the Hint button when the selected piece is clicked
     * Hint effect: Replace choosePane's Button piece image
     * @param event ActionEvent
     */
    @FXML
    void hint(ActionEvent event) {
        if(clickCell==null){
            Tools.informationDialog(Alert.AlertType.WARNING,"tip","tip","Please select the graphic first");
        }else{

            List <Cell>SurroundingPosition =  getSurroundingPosition(clickCell);
            ArrayList<Piece.Type> types =new ArrayList<>();
            types.add(Piece.Type.CIRCLE);
            types.add(Piece.Type.TRIANGLE);
            types.add(Piece.Type.SQUARE);
            types.add(Piece.Type.PENTAGON);
            types.add(Piece.Type.HEXAGON);
            for (Cell cell2:SurroundingPosition){
                types.remove(cell2.getPiece().getType());
                readBoard();
            }
            HintChoosePanel(types);


            hintButton.setDisable(true);
        }
    }

    /**
     ** Hint effect: Replace choosePane's Button piece image
     * @param types Piece.Type
     */
    void HintChoosePanel(ArrayList<Piece.Type> types){

        for (Piece.Type type:
        types) {
            if(type.equals(Piece.Type.CIRCLE)){
                btnImage1.setImage(new Image("file:src/main/resources/lab/game/images/3/1.png", 80, 80, true, true));

            }
            if(type.equals(Piece.Type.TRIANGLE)){
                btnImage2.setImage(new Image("file:src/main/resources/lab/game/images/3/3.png", 80, 80, true, true));

            }
            if(type.equals(Piece.Type.SQUARE)){
                btnImage3.setImage(new Image("file:src/main/resources/lab/game/images/3/4.png", 80, 80, true, true));

            }
            if(type.equals(Piece.Type.PENTAGON)){
                btnImage4.setImage(new Image("file:src/main/resources/lab/game/images/3/5.png", 80, 80, true, true));

            }
            if(type.equals(Piece.Type.HEXAGON)){
                btnImage5.setImage(new Image("file:src/main/resources/lab/game/images/3/6.png", 80, 80, true, true));

            }



        }


    }

    /**
     *
     * rest choosePane button Image
     */
    void restChoosePanel(){
        btnImage1.setImage(new Image("file:src/main/resources/lab/game/images/1.png", 80, 80, true, true));
        btnImage2.setImage(new Image("file:src/main/resources/lab/game/images/3.png", 80, 80, true, true));
        btnImage3.setImage(new Image("file:src/main/resources/lab/game/images/4.png", 80, 80, true, true));
        btnImage4.setImage(new Image("file:src/main/resources/lab/game/images/5.png", 80, 80, true, true));
        btnImage5.setImage(new Image("file:src/main/resources/lab/game/images/6.png", 80, 80, true, true));
    }

    /**
     * boolean is Win
     * @return if win,return true,else retrun false
     */
    boolean isWin(){

        if(ReplaceSize==16){
            Tools.informationDialog(Alert.AlertType.INFORMATION,"success","success","you win!");
            girdPane.setDisable(true);
            undoReplaceBtn.setDisable(true);
            hintButton.setDisable(true);
            App.setRank("view/rank.fxml",this);
            return  true;


        }else{
            return  false;
        }

    }

    /**
     * Replace the image with the cell
     * @param cell
     */
    void chaneImageToYellow(Cell cell ){
       Piece.Type type =cell.getPiece().getType();
       if(type == Piece.Type.CIRCLE){
           Piece piece =new Circle();
           piece.setImage(new Image("file:src/main/resources/lab/game/images/3/1.png", 80, 80, true, true));
           board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
       }
        if(type == Piece.Type.TRIANGLE){
            Piece piece =new Triangle();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/3/3.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }
        if(type == Piece.Type.SQUARE){
            Piece piece =new Square();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/3/4.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }
        if(type == Piece.Type.PENTAGON){
            Piece piece =new Pentagon();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/3/5.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }
        if(type == Piece.Type.HEXAGON){
            Piece piece =new Hexagon();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/3/6.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }



    }

    /**
     * Replace the image with the cell
     * @param cell
     */
    void chaneImageToBlank(Cell cell ){
        Piece.Type type =cell.getPiece().getType();
        if(type == Piece.Type.CIRCLE){
            Piece piece =new Circle();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/1.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }
        if(type == Piece.Type.TRIANGLE){
            Piece piece =new Triangle();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/3.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }
        if(type == Piece.Type.SQUARE){
            Piece piece =new Square();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/4.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }
        if(type == Piece.Type.PENTAGON){
            Piece piece =new Pentagon();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/5.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }
        if(type == Piece.Type.HEXAGON){
            Piece piece =new Hexagon();
            piece.setImage(new Image("file:src/main/resources/lab/game/images/6.png", 80, 80, true, true));
            board[cell.getCoordinate().row][cell.getCoordinate().col].setPiece(piece);
        }



    }



    public Cell getCell(Coordinate coordinate) {
        return this.board[coordinate.row][coordinate.col];
    }

    /**
     * get All cells
     * @return
     */
    protected List<Cell> getAllCells() {
        return Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList());
    }


    /**
     * boolean Cell is onBoard
     * @param coordinate cell coordinate
     * @return if onBoard ,return true else return false;
     */
    private Boolean onBoard(Coordinate coordinate) {

        return 0 <= coordinate.col && coordinate.col < this.size &&
                0 <= coordinate.row && coordinate.row < this.size;
    }




    public void setPlayerName(String name) {
        this.playerName =name;
    }



    public String getPlayerName() {
        return playerName;
    }



    public int getScores() {
        return scores;
    }



    public void setFileName(String fileName) {
        this.fileName =fileName;
    }
}
