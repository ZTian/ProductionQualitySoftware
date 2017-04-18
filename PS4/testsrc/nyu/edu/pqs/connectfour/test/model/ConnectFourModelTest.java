package nyu.edu.pqs.connectfour.test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nyu.edu.pqs.connectfour.board.BoardConstants;
import nyu.edu.pqs.connectfour.board.Move;
import nyu.edu.pqs.connectfour.model.ConnectFourModel;
import nyu.edu.pqs.connectfour.model.GameType;
import nyu.edu.pqs.connectfour.player.ColorType;
import nyu.edu.pqs.connectfour.player.Player;

public class ConnectFourModelTest {
  private ConnectFourModel model;
  
  @Before
  public void testGetInstance() {
    model = ConnectFourModel.getInstance();
    
  }
  
  @Test
  public void testFindRowIndex() {
    model.startGame(GameType.HUMAN);
    int rowIndex = model.findRowIndex(0);
    assertEquals(rowIndex,0);
    for(int i=0; i<BoardConstants.ROWS; ++i ) {
      model.addDisc(0);
    }
    assertEquals(model.findRowIndex(0),-1);
  }
  
  @Test
  public void testNotFull() {
    model.startGame(GameType.HUMAN);
    assertFalse(model.isFull());
  }
  
  @Test
  public void testNoWinner() {
    model.startGame(GameType.HUMAN);
    assertFalse(model.hasWinner(new Move(0,0,new Player(ColorType.BLUE))));
  }
  
  @Test
  public void testAddDisc() {
    model.startGame(GameType.HUMAN);    
    model.addDisc(-1);
    model.addDisc(BoardConstants.COLUMNS+2);
    for(int i=0; i<=BoardConstants.ROWS; ++i ) {
      model.addDisc(0);
    }
  }
  
  @Test
  public void testCheckHorizontal() {
    model.startGame(GameType.HUMAN);
    model.addDisc(0);
    model.addDisc(0);
    model.addDisc(1);
    model.addDisc(1);
    model.addDisc(2);
    model.addDisc(2);
    model.addDisc(3);
    assertTrue(model.checkHorizontal(new Move(0,3,new Player(ColorType.RED))));
    assertFalse(model.checkHorizontal(new Move(1,2,new Player(ColorType.RED))));
  }
  
  @Test
  public void testCheckVertical() {
    model.startGame(GameType.HUMAN);
    model.addDisc(0);
    model.addDisc(1);
    model.addDisc(0);
    model.addDisc(1);
    model.addDisc(0);
    model.addDisc(1);
    model.addDisc(0);
    assertTrue(model.checkVertical(new Move(3,0,new Player(ColorType.RED))));
    assertFalse(model.checkVertical(new Move(2,1,new Player(ColorType.RED))));
  }
  
  @Test
  public void testCheckDiagonal() {    
    /*
     * Check from bottom left to up right. B stands for BLUE and R is RED.
     */
    model.startGame(GameType.HUMAN);
    //Row 0: B R B R
    model.addDisc(1);
    model.addDisc(0);
    model.addDisc(3);
    model.addDisc(2);
    //Row 1: B B R R
    model.addDisc(2);
    model.addDisc(1);
    model.addDisc(3);
    model.addDisc(0);
    //Row 2: B R
    model.addDisc(1);
    model.addDisc(0);
    //Row 3: R
    model.addDisc(0);
    assertTrue(model.checkDiagonal(new Move(2,1,new Player(ColorType.RED))));
    assertFalse(model.checkDiagonal(new Move(5,4,new Player(ColorType.BLUE))));
    
    /*
     * Check from up left to bottom right.
     */
    model.startGame(GameType.HUMAN);
    //Row 0: B R B R
    model.addDisc(1);
    model.addDisc(0);
    model.addDisc(3);
    model.addDisc(2);
    //Row 1: R B R B
    model.addDisc(2);
    model.addDisc(1);
    model.addDisc(0);
    model.addDisc(3);
    //Row 2:     B R
    model.addDisc(3);
    model.addDisc(2);
    //Row 3:     R B
    model.addDisc(2);
    model.addDisc(3);
    assertTrue(model.checkDiagonal(new Move(1,1,new Player(ColorType.BLUE))));
    assertFalse(model.checkDiagonal(new Move(0,1,new Player(ColorType.RED))));
  }
  
  @Test
  public void testGenerateColumnIndex() {
    model.startGame(GameType.HUMAN);
    
    //Check if the generated column is not full.
    int columnIndex = model.generateColumnIndex();
    assertFalse(model.isColumnFull(columnIndex));
    
    //Check if the generated column can take the win move.
    model.addDisc(0);
    model.addDisc(1);
    model.addDisc(0);
    model.addDisc(1);
    model.addDisc(0);
    model.addDisc(1);
    assertEquals(model.generateColumnIndex(),0);
    model.addDisc(2);
    assertEquals(model.generateColumnIndex(),1);    
  }
}
