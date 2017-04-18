package nyu.edu.pqs.connectfour.listener;

import nyu.edu.pqs.connectfour.board.Move;

/**
 * This interface is the listener interface. The model sends information to the listeners by calling functions 
 * in this interface.
 *
 */
public interface ConnectFourListener {
  /**
   * To announce that game is started
   */
  void gameStarted();
  
  /**
   * The player makes a movement. This movement does not win the game or make the game tied.
   * @param move  the newly added disc
   */
  void makeMovement(Move move);
  
  /**
   * There exists a winner of the game
   * @param move  the disc that wins the game
   */
  void gameWin(Move move);
  
  /**
   * The board is full and no one wins the game
   * @param move  the last disc added
   */
  void gameTied( Move move );
  
  /**
   * The player tries to add a disc to a full column.
   * @param columnIndex  the index of the column where the disc is added
   */
  void columnFull( int columnIndex );
  
  /**
   * The player tries to add a disc out of the board
   * @param columnIndex
   */
  void outOfBoard( int columnIndex );
}
