package lab.game.model.piece;

import javafx.scene.image.Image;
/**
 *  Piece Square
 */
public class Square extends  Piece{


         public Square() {
            super("3", Type.SQUARE, new Image("file:src/main/resources/lab/game/images/4.png", 80, 80, true, true));
        }

}
