package nyu.edu.pqs.canvas;

/**
 * This class represents a point (x,y) in the canvas.
 * x represents the horizontal position and y represents the vertical position.
 *
 */
public class Point {
  private final int x;
  private final int y;
  
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  @Override
  public String toString() {
    return "Point: ("+x+","+y+")\n";
  }
}
