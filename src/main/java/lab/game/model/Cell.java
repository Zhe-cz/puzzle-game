package lab.game.model;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import lab.game.model.piece.Piece;

/**
 *  The pieces of the game ，extends  Button
 */
public class Cell extends Button {

    private  Coordinate coordinate;
    private Piece piece;
    private static final int size = 100;

    /**
     * Creates a cell with the given coordinate.
     * Piece is null if there is no piece on the cell.
     *
     * @param coordinate Coordinate of the cell on the board.
     */

    public Cell(Coordinate coordinate) {
        super("");
        this.coordinate = coordinate;
        this.setSize();
        this.setDefaultColor();
        this.setShape(new Rectangle(5,5));

    }


    public Cell() {
		this.coordinate = null;
    
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public  Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.setGraphic(hasPiece() ? new ImageView(piece.getImage()) : null);
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    private void setSize() {
        this.setMinSize(size, size);
        this.setPrefSize(size, size);
    }

    /**
     *  Cell setDefaultColor
     */
    protected void setDefaultColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #00307b;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }

}
