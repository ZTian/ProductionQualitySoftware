package nyu.edu.pqs.connectfour.board;

import nyu.edu.pqs.connectfour.player.Player;

/**
 * A move represents a disc on the board. It has the detailed information about where the disc is located and 
 * the player who placed it
 *
 */
public class Move {
  private final int columnIndex;
  private final int rowIndex;
  private final Player player;
  
  /**
   * The movement made by the player
   * @param columnIndex  the index of the column where the disc is
   * @param rowIndex  the index of the row where the disc is
   * @param player  the player who place the disc
   */
  public Move( int rowIndex, int columnIndex, Player player ) {
    this.columnIndex = columnIndex;
    this.rowIndex = rowIndex;
    this.player = player;
  }

  public int getColumnIndex() {
    return columnIndex;
  }

  public int getRowIndex() {
    return rowIndex;
  }

  public Player getPlayer() {
    return player;
  }  
  
}
