package lab.game.model.piece;

import javafx.scene.image.Image;
/**
 *  Piece Hexagon
 */
public class Hexagon extends  Piece{
    public Hexagon() {
        super("5", Type.HEXAGON, new Image("file:src/main/resources/lab/game/images/6.png", 80, 80, true, true));
    }
}
