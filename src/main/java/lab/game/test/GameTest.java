package lab.game.test;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import lab.game.App;
import lab.game.model.Cell;
import lab.game.model.Coordinate;
import lab.game.model.Replace;
import lab.game.model.Score;
import lab.game.model.piece.*;
import lab.game.util.FileUtil;
import lab.game.util.Tools;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertTrue;

public class GameTest {
    /**
     * testGetMapFile
     * load map folder
     */
    @Test
    public void testGetMapFile(){
        String path = "src/main/resources/lab/game/map/";
        File src =  new File(path);
        int index=-1;
        int size=0;
        for(int i=0;i<src.listFiles().length;i++) {
                System.out.println(src.listFiles()[i].getName());
            if(src.listFiles()[i].getName().equals("Starter.txt")) {
                index =i;
            }
            size+=1;
        }
        assertTrue(size==7);

    }

    /**
     * testInputName
     *
     * test input name fuction
     * Check name length
     */
    @Test
    public void testInputName(){
        String text="aa";
        boolean isTrue=false;
        if(text.length()>3||text.length()<=0){
            isTrue =true;
        }else{
            isTrue =false;
        }

        assertTrue(isTrue==false);

    }

    /**
     * test ReadRecord
     * load record.txt
     */
    @Test
    public void testReadRecord(){

        ArrayList<Score> list = FileUtil.inUtil();
        int i =0;
        for (Score score:
        list) {
            i++;
            if(i==3){
                break;
            }
        }
        assertTrue(i==3);

    }

    /**
     * testReadMap
     * load Cell piece
     * set Cell Field
     *
     */
    @Test
    public void testReadMap(){
                int[][] board = new int[4][4];
                File file =new File("src/main/resources/lab/game/map/Starter.txt") ;
                Scanner scanner = null;
                try {
                    scanner = new Scanner(file);
                } catch (
                        FileNotFoundException e) {
                    System.err.printf("File at %s not found.", file.getPath());
                    System.exit(1);
                }
                int row = 0, col = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] pieces = line.trim().split(" ");
                    for (String piece: pieces) {
                        int i =0;
                        switch (piece) {
                            case "1" -> i=1;
                            case "2" -> i=2;
                            case "3" -> i=3;
                            case "4" -> i=4;
                            case "5" -> i=5;
                        }
                        board[row][col] = i;
                        col += 1;
                    }
                    col = 0;
                    row += 1;
                }
                scanner.close();
                assertTrue(board[0][0] == 1);
            }
        @Test


        /**
         * test undoReplace
         *
         * remove list  element
         */
        public void undoReplace() { // TODO
            List<Integer> list =new ArrayList<>();
            list.add(1);
            list.add(2);
            list.remove(list.size()-1);
            assertTrue(list.size() == 1);

    }


    /**
     * saveBoard to File
     */
    @Test
    public void saveBoard() { // TODO

        //read board
        int[][] board = new int[4][4];
        File file =new File("src/main/resources/lab/game/map/Starter.txt") ;
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (
                FileNotFoundException e) {
            System.err.printf("File at %s not found.", file.getPath());
            System.exit(1);
        }
        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                int i =0;
                switch (piece) {
                    case "1" -> i=1;
                    case "2" -> i=2;
                    case "3" -> i=3;
                    case "4" -> i=4;
                    case "5" -> i=5;
                }
                board[row][col] = i;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();

        //save board
        Boolean isSaveSuccess =false;
        String  fileNameField ="test.txt";
        String saveFileNotTxtError = "Error: File must end with .txt";
        if (fileNameField.trim().equals("")) {
            //Tools.informationDialog(Alert.AlertType.ERROR, "tip", "tip", saveFileNotTxtError);
            isSaveSuccess=false;
        } else {
            int index = fileNameField.lastIndexOf(".");
            String txt = fileNameField.substring(index + 1, fileNameField.length());
            String fileName = fileNameField.substring(0, index);
            if (txt.equals("txt")) {
                File file3 = new File("src/main/resources/lab/game/map/" + fileNameField);
                if (fileName.trim().equals("")) {
                isSaveSuccess=false;
                } else {
                    if (file3.exists()) {
                     isSaveSuccess=false;
                    } else {
                       File file2 =new File("src/main/resources/lab/game/map/" + fileNameField);
                        try {
                            file.createNewFile();
                            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
                            StringBuilder line = new StringBuilder("");
                            for (int i =0;i<4;i++){
                                for (int j =0;j<4;j++){
                                    if (board[i][j]==1){
                                        line.append("1");

                                    }
                                    if (board[i][j]==2){
                                        line.append("2");
                                    }
                                    if (board[i][j]==3){
                                        line.append("3");
                                    }
                                    if (board[i][j]==4){
                                        line.append("4");
                                    }
                                    if (board[i][j]==5){
                                        line.append("5");

                                    }
                                    if(j<4-1){
                                        line.append(" ");
                                    }else{
                                        line.append("\n");
                                    }
                                }
                            }
                            writer.write(line.toString());
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        isSaveSuccess =true;
                    }
                }

            } else {
                isSaveSuccess =false;
            }

        }
            assertTrue(isSaveSuccess==true);
    }



    /**
     * Get all the possible cell SurroundingPosition that is possible to replace to from the fromCell.
     */
    @Test
    public void getSurroundingPosition() {
        //read board
        int[][] board = new int[4][4];
        File file = new File("src/main/resources/lab/game/map/Starter.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (
                FileNotFoundException e) {
            System.err.printf("File at %s not found.", file.getPath());
            System.exit(1);
        }
        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece : pieces) {
                int i = 0;
                switch (piece) {
                    case "1" -> i = 1;
                    case "2" -> i = 2;
                    case "3" -> i = 3;
                    case "4" -> i = 4;
                    case "5" -> i = 5;
                }
                board[row][col] = i;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();

        //check SurroungdingCells
        List<Integer> positionsNumber = new ArrayList<Integer>();

        //check board[0][0]
        int[][] possibleMoves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] move : possibleMoves) {
            int row2 = move[0] + 0;
            int col2 = move[1] + 0;
            if (0 <= col2 && col2 < 4 &&
                    0 <= row2 && row2 < 4) {
                if (board[row2][col2] != board[0][0]) {
                    positionsNumber.add(board[row2][col2]);
                }
            }
        }

        for (Integer i:
        positionsNumber) {
            System.out.println(i);

        }
        assertTrue(positionsNumber.size()==2);
    }


    /**
     * Check that the overwritten graph is correct
     *If not, the game ends
     */
    @Test
    public void  isErrorReplace(){

        boolean isErrorReplace =false;
        //read board
        int[][] board = new int[4][4];
        File file = new File("src/main/resources/lab/game/map/Starter.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (
                FileNotFoundException e) {
            System.err.printf("File at %s not found.", file.getPath());
            System.exit(1);
        }
        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece : pieces) {
                int i = 0;
                switch (piece) {
                    case "1" -> i = 1;
                    case "2" -> i = 2;
                    case "3" -> i = 3;
                    case "4" -> i = 4;
                    case "5" -> i = 5;
                }
                board[row][col] = i;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        // board[0][0]=1 surroundingNumber is 2,5
        board[0][0]=2;
        //check board[0][0] is Valid replace
        int[][] possibleMoves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] move : possibleMoves) {
            int row2 = move[0] + 0;
            int col2 = move[1] + 0;
            if (0 <= col2 && col2 < 4 &&
                    0 <= row2 && row2 < 4) {
                if (board[row2][col2] == board[0][0]) {
                    isErrorReplace =true;
                    break;

                }
            }
        }

        assertTrue(isErrorReplace==true);
    }


    /**
     * test sort List<Socre>>
     */
    @Test
    public void testSort(){
        List<Score> list =new ArrayList<>();
        Score score =new Score(1,"aa",77) ;
        Score score2 =new Score(2,"bb",88) ;
        list.add(score);
        list.add(score2);

        Collections.sort(list, new Comparator<Score>() {
            @Override
            public int compare(Score s1, Score s2) {
                return s2.getScores() - (s1.getScores());
            }
        });

        for (Score s:list){
            System.out.println(s.getPlayer()+","+s.getScores());

        }
        assertTrue( list.get(0).getScores()==88);

    }





}
