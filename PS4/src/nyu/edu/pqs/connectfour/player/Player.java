package nyu.edu.pqs.connectfour.player;

/**
 * This class represents a player in the game¡£ To simplify the program, only the color of the player
 * is needed.
 *
 */
public class Player {
  private ColorType color;
  
  public Player( ColorType color ) {
    if( color == null ) {
      throw new IllegalArgumentException("A player cannot have null color"); 
    }
    else {
      this.color = color;
    }
  }
  
  public ColorType getColor() {
    return color;
  }
}
