package nyu.edu.pqs.connectfour.model;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import nyu.edu.pqs.connectfour.board.BoardConstants;
import nyu.edu.pqs.connectfour.board.Move;
import nyu.edu.pqs.connectfour.listener.ConnectFourListener;
import nyu.edu.pqs.connectfour.player.ColorType;
import nyu.edu.pqs.connectfour.player.Player;

/**
 * This is the model of the Connect Four. The information of the board is stored here.
 *
 */
public class ConnectFourModel {
  private final int columns = BoardConstants.COLUMNS;
  private final int rows = BoardConstants.ROWS;
  private final int winSize = BoardConstants.WINSIZE;
  private GameType type;
  private Player[][] board;
  private List<ConnectFourListener> listeners = 
      new CopyOnWriteArrayList<ConnectFourListener>();  
  private static ConnectFourModel INSTANCE = new ConnectFourModel();
  
  private Player curPlayer;
  private Player player1 = new Player(ColorType.RED);
  private Player player2 = new Player(ColorType.BLUE);
  
  /**
   * Singleton Pattern. Only one instance of the model is allowed.
   */
  private ConnectFourModel() {}
  public static ConnectFourModel getInstance() {
    return INSTANCE;
  }
  
  /**
   * Add a disc to the board.
   * @param columnIndex  the index of column to drop the disc
   */
  public void addDisc(int columnIndex) {
    //Check if try to add the disc out of the board
    if(columnIndex < 0 || columnIndex >= columns) {
      fireOutOfBoardEvent(columnIndex);
      return;
    }
    
    //Check if the column is full
    if(board[rows-1][columnIndex] != null)  {
      fireColumnFullEvent(columnIndex);
      return;
    }
    else {
      //Add the disc to the board      
      int rowIndex = findRowIndex(columnIndex);
      
      board[rowIndex][columnIndex] = curPlayer;
      Move move = new Move(rowIndex, columnIndex, curPlayer);
      
      if(hasWinner(move)) {
        fireGameWinEvent(move);
      }      
      else if(isFull()) {
        fireGameTiedEvent(move);        
      }
      else {
        fireGameMoveOnEvent(move);
        switchPlayer();
      }
    }
  }
  
  /**
   * Find the row index of the valid position. If the column is full, returns -1.
   * @param columnIndex  where to drop the disc
   * @return  rowIndex of the disc
   */
  private int findRowIndex(int columnIndex) {
    int rowIndex = 0;
    for(; rowIndex<rows; ++rowIndex) {
      if(board[rowIndex][columnIndex] == null ) {
        return rowIndex;
      }
    }
    return -1;
  }
  
  /**
   * Switch the current player. If playing with computer, generate another movement.
   */
  private void switchPlayer() {
    if(curPlayer.equals(player1)) {
      curPlayer = player2;
      if(type==GameType.COMPUTER) {
        addDisc(generateColumnIndex());
        curPlayer = player1;
      }
    }
    else {
      curPlayer = player1;
    }
  }
  
  /**
   * Generate a random column index. The index is guaranteed to be valid, which means the column is not full.
   * If the return value is -1, the board is full.
   * @return  random column index
   */
  private int generateColumnIndex() {
    int columnIndex = hasWinMove();
    if( columnIndex != -1 ) {
      return columnIndex;
    }
    int count = 0;
    for(int i=0; i<columns; ++i) {
      if(board[rows-1][i]==null) {
        count++;
      }
    }
    
    Random random = new Random();
    int index = random.nextInt(count);
    count=0;
    for(int i=0; i<columns; ++i) {
      if(board[rows-1][i]==null) {
        if(count == index) {
          return i;
        }
        count++;
      }
    }
    return -1;
  }
  
  /**
   * Check if there is a possible a win for the computer.
   * @return  the possible win position of the computer
   */
  private int hasWinMove() {
    //Check by columns, from left to right.
    for(int i=0; i<columns; ++i){
      int rowIndex = rows-1;
      //Find the valid position to drop the disc.
      while(rowIndex>=0) {
        if(board[rowIndex][i]==null) {
          rowIndex--;
        }
        else {
          break;
        }
      }
      rowIndex++;
      //Pretend to make the move and check if there is a win
      if( rowIndex < rows) {
        board[rowIndex][i] = curPlayer;
        Move move = new Move(rowIndex, i, curPlayer);
        if(hasWinner(move)){
          board[rowIndex][i]=null;
          return i;
        }
        board[rowIndex][i] = null;
      }
    }
    return -1;
  }
  
  /**
   * Check if the board is full.
   * @return  whether the board is full.
   */
  private boolean isFull() {    
    for(int i=0; i<columns; ++i) {
      if(board[rows-1][i]==null) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Check if there are four discs forming the winning size.
   * @param move  the newly disc
   * @return  whether there is a winner
   */
  private boolean hasWinner(Move move) {
    if(checkHorizontal(move) || checkVertical(move) || checkDiagonal(move)) {
      return true;
    }
    return false;
  }
  
  /**
   * Check if there are discs of winning size on the diagonal line
   * @param move  the newly added disc
   * @return  whether there is a winning size of discs
   */
  private boolean checkDiagonal(Move move) {
    int rowIndex = move.getRowIndex();
    int columnIndex = move.getColumnIndex();
    int indexSum = rowIndex + columnIndex;
    ColorType color = move.getPlayer().getColor();
    int line = 0;
    
    /*
     * Count the maximum length of the same discs. 
     * From the position where the disc is to the up left.
     */
    int leftColumn = columnIndex;
    int leftRow = rowIndex;
    while(leftColumn >= 0 && leftRow >= 0) {
      if(board[leftRow][leftColumn]!=null && board[leftRow][leftColumn].getColor() == color) {
        line++;
        leftColumn--;
        leftRow--;
      }
      else {
        break;
      }
    }
    
    /*
     * Count towards the bottom right.
     */
    int rightColumn = columnIndex+1;
    int rightRow = rowIndex+1;
    while(rightColumn < columns && rightRow < rows) {
      if(board[rightRow][rightColumn]!=null && board[rightRow][rightColumn].getColor() == color) {
        line++;
        rightRow++;
        rightColumn++;
      }
      else {
        break;
      }
    }
    if(line >= winSize) {
      return true;
    }
    
    /*
     * Count the maximum length of the same discs from bottom left to up right.
     */
    line = 0;
    for(int i=0; i<columns; ++i) {
      int row = indexSum - i;
      if(row >= 0 && row < rows ) {
        if( board[row][i]!=null && board[row][i].getColor() == color ) {
          line++;
          if( line == winSize ) {
            return true;
          }
        }
        else {
          line = 0;
        }
      }
    }
    return false;
  }
  
  /**
   * Check if there are discs of winning size on the horizontal line
   * @param move  the newly added disc
   * @return  whether there is a winning size of discs
   */
  private boolean checkHorizontal(Move move) {
    int rowIndex = move.getRowIndex();
    int line = 0;
    ColorType color = move.getPlayer().getColor();
    
    for(int i=0; i<columns; ++i) {
      if(board[rowIndex][i] != null && board[rowIndex][i].getColor() == color) {
        line++;
        if(line == winSize) {
          return true;
        }
      }
      else {
        line = 0;
      }
    }    
    return false;
  }
  
  /**
   * Check if there are discs of winning size on the vertical line
   * @param move  the newly added disc
   * @return  whether there is a winning size of discs
   */
  private boolean checkVertical(Move move) {
    int columnIndex = move.getColumnIndex();
    int line = 0;
    ColorType color = move.getPlayer().getColor();
    
    for(int i=0; i<rows; ++i) {
      if(board[i][columnIndex] != null && board[i][columnIndex].getColor() == color) {
        line++;
        if(line == winSize) {
          return true;
        }
      }
      else {
        line = 0;
      }
    }
    return false;
  }
  
  /**
   * Start a new game.
   * @param type  the type of the new game
   */
  public void startGame(GameType type) {
    initializeModel(type);
    fireGameStartedEvent();
  } 
  
  private void initializeModel(GameType type) {
    this.type = type;
    board = new Player[rows][columns];
    curPlayer = player1;
  }
  
  private void fireGameStartedEvent() {
    for( ConnectFourListener listener : listeners ) {
      listener.gameStarted();
    }
  }

  private void fireGameTiedEvent(Move move) {
    for( ConnectFourListener listener : listeners ) {
      listener.gameTied(move);
    }
  }
  private void fireOutOfBoardEvent(int columnIndex) {
    for( ConnectFourListener listener : listeners ) {
      listener.outOfBoard(columnIndex);
    }
  }
  
  private void fireColumnFullEvent(int columnIndex) {
    for( ConnectFourListener listener : listeners ) {
      listener.columnFull(columnIndex);
    }
  }

  private void fireGameWinEvent(Move move) {
    for( ConnectFourListener listener : listeners ) {
      listener.gameWin(move);
    }
  }
  
  private void fireGameMoveOnEvent(Move move) {
    for( ConnectFourListener listener : listeners ) {
      listener.makeMovement(move);
    }
  }
  
  public void addConnectFourListener(ConnectFourListener listener) {
    listeners.add(listener);
  }
  
  public void removeConnectFourListener(ConnectFourListener listener) {
    listeners.remove(listener);
  }
}
