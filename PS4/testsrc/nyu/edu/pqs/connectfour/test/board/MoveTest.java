package nyu.edu.pqs.connectfour.test.board;

import static org.junit.Assert.*;

import org.junit.Test;

import nyu.edu.pqs.connectfour.board.Move;
import nyu.edu.pqs.connectfour.player.ColorType;
import nyu.edu.pqs.connectfour.player.Player;

public class MoveTest {
  @Test
  public void testMove() {
    Player player = new Player(ColorType.RED);
    Move move = new Move(1,0,player);
    assertEquals(move.getColumnIndex(),0);
    assertEquals(move.getRowIndex(),1);
    assertTrue(move.getPlayer().equals(player));
  }
}
