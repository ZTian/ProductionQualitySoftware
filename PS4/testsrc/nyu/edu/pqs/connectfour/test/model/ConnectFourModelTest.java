package nyu.edu.pqs.connectfour.test.model;

import org.junit.Before;
import org.junit.Test;

import nyu.edu.pqs.connectfour.board.BoardConstants;
import nyu.edu.pqs.connectfour.model.ConnectFourModel;
import nyu.edu.pqs.connectfour.model.GameType;

public class ConnectFourModelTest {
  private ConnectFourModel model;
  
  @Before
  public void testGetInstance() {
    model = ConnectFourModel.getInstance();    
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
}
