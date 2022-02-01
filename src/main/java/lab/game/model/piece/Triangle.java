package lab.game.model.piece;

import javafx.scene.image.Image;
/**
 *  Piece Triangle
 */
public class Triangle extends  Piece {

    public Triangle() {
        super("2", Type.TRIANGLE, new Image("file:src/main/resources/lab/game/images/3.png", 80, 80, true, true));
    }
}
