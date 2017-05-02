package nyu.edu.pqs.canvas;

public interface CanvasListener {
  
  /**
   * To announce that the canvas is clear and can start painting.
   */
  void paintStarted();
  
  /**
   * To announce that a line has been drawn.
   */
  void lineAdded(Point startPoint, Point endPoint);
  
  /**
   * To announce that the point(x,y) is out of the canvas.
   * @param x  the x component of the point
   * @param y  the y component of the point
   */
  void pointOutOfBound(Point point);
  
}
