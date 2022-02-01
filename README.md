# A board-based game which has a 4 x 4 grid and five types of piece which can be placed in grid cells on the board. 

The player has 16 (4 x 4 = 16) pieces that they can place onto the board, and the objective of the game is to place all pieces. If the player manages to place all pieces, they win, if not they lose (i.e. if there are no valid moves they can make). 


**The rules which determine if a move is valid are:**
1. No piece may be placed in a grid cell adjacent to a piece of the same type (for randomly generated boards, this rule must also be adhered to in the initial board state).
2. Once a piece is placed the identity of the grid cell changes to that piece.
3. Once placed, a piece cannot be picked up (removed from the board).
4. The number of pieces of each type is constrained to the distribution: 2, 3, 3, 4, 4, i.e. some pieces are less / more frequent than others. You are free to decide what the distribution of the initial board should be.
