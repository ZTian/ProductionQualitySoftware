package nyu.edu.pqs.connectfour.test.player;

import static org.junit.Assert.*;

import org.junit.Test;

import nyu.edu.pqs.connectfour.player.ColorType;
import nyu.edu.pqs.connectfour.player.Player;

public class PlayerTest {
  @Test
  public void testPlayer() {
    Player player1 = new Player(ColorType.BLUE);
    assertEquals(player1.getColor(),ColorType.BLUE);
    Player player2 = new Player(ColorType.RED);
    assertNotEquals(player1.getColor(), player2.getColor());
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testNullPlayer() {
    @SuppressWarnings("unused")
    Player player = new Player(null);    
  }
}
