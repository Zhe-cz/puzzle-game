package lab.game.model;

/**
 * Represents the substitution of graphs
 *Replace fromCell with toCell
 *
 */
public class Replace {

    public final Cell fromCell;
    public final Cell toCell;

    /**
     * Construct a new Replace
     * A Replace represents Replace a Piece in fromCell to toCell
     * @param fromCell the Cell the Piece is in
     * @param toCell the Cell the Piece will replace to
     * 
     */
    public Replace(final Cell fromCell, final Cell toCell) {
        this.fromCell = fromCell;
        this.toCell = toCell;
    }


    

}
