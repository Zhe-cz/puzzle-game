package lab.game.model.piece;

import javafx.scene.image.Image;
import lab.game.model.Cell;

import java.util.Objects;

/**
 * Shape Piece
 * Has the shape of the symbol and type and image
 */
public abstract class Piece {

    private final String symbol;
    private final Type type;
    private  Image image;


    /**
     * Construct a new Piece
     *
     * @param symbol to represent the Piece
     * @param type   a Type of Piece
     */
    public Piece(String symbol, Type type, Image image) {
        this.symbol = symbol;
        this.type = type;
        this.image = image;
    }

    /**
     * All possible Piece types
     */
    public enum Type {
        CIRCLE ("CIRCLE"),
        SQUARE("SQUARE"),
        TRIANGLE("TRIANGLE"),
        PENTAGON("PENTAGON"),
        HEXAGON("HEXAGON");

        private final String type;

        Type(final String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * @return symbol representation of Piece
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return Image representation of Piece
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the Type of piece
     */
    public Type getType() {
        return type;
    }




    public void setImage(Image image) {
        this.image = image;
    }
}
