package lab.game.model.piece;

import javafx.scene.image.Image;
/**
 *  Piece Pentagon
 */
public class Pentagon  extends  Piece{

    public Pentagon() {
        super("4", Type.PENTAGON, new Image("file:src/main/resources/lab/game/images/5.png", 80, 80, true, true));
    }
}
