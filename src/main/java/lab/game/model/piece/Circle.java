package lab.game.model.piece;

import javafx.scene.image.Image;

/**
 *  Piece Circle
 */
public class Circle extends  Piece{


         public Circle() {
            super("1", Type.CIRCLE, new Image("file:src/main/resources/lab/game/images/1.png", 80, 80, true, true));
        }

    @Override
    public void setImage(Image image) {
        super.setImage(image);
    }

}
