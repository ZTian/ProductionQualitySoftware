package nyu.edu.pqs.canvas;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CanvasModel {
  
  private List<CanvasListener> listeners = new CopyOnWriteArrayList<CanvasListener>();
  
  private static CanvasModel INSTANCE = new CanvasModel();
  
  /*
   * Singleton pattern. Only one instance of the model is allowed.
   */
  private CanvasModel() {}
  public static CanvasModel getInstance() {
    return INSTANCE;
  }
  
  /**
   * Start the paint. Notify all listeners.
   */
  public void startPaint() {
    firePaintStartedEvent();
  }
  
  /**
   * 
   * @param x  the x component of the point
   * @param y  the y component of the point
   */
  public void drawLine(int x1, int y1, int x2, int y2) {
    Point startPoint = new Point(x1,y1);
    if(!insideCanvas(startPoint)) {
      firePointOutOfBoundEvent(startPoint);
      return;
    }
    Point endPoint = new Point(x2,y2);
    if(!insideCanvas(endPoint)) {
      firePointOutOfBoundEvent(endPoint);
      return;
    }
    
    fireLineAddedEvent(startPoint, endPoint);
  }
  
  /**
   * Notify all listeners that a line from startPoint to endPoint has been added
   * @param startPoint  the point where the line starts
   * @param endPoint  the point where the line ends
   */
  private void fireLineAddedEvent(Point startPoint, Point endPoint) {
    for(CanvasListener listener : listeners) {
      listener.lineAdded(startPoint, endPoint);
    }
  }
  
  /**
   * Notify all listeners that the point is outside the canvas
   * @param point  the point object
   */
  private void firePointOutOfBoundEvent(Point point) {
    for(CanvasListener listener : listeners) {
      listener.pointOutOfBound(point);
    }
  }
  
  /**
   * Check if the point is inside the canvas
   * @param point  (x,y)
   * @return  whether the point is inside the canvas
   */
  private boolean insideCanvas(Point point) {
    if(point.getX()<0 || point.getY()<0 || point.getX() >= CanvasConstants.WIDTH || 
        point.getY() >= CanvasConstants.LENGTH) {
      return false;
    }
    return true;
  }

  /**
   * Add listener to the model so that they can receive the update of the canvas
   * @param listener  the listener object
   */
  public void addListener(CanvasListener listener) {
    listeners.add(listener);
  }
  
  /**
   * Remove listener from the model.
   * @param listener  the listener object
   */
  public void removeListener(CanvasListener listener) {
    listeners.remove(listener);
  }

  /**
   * To notify that painting has started.
   */
  private void firePaintStartedEvent() {
    for(CanvasListener listener : listeners) {
      listener.paintStarted();
    }
  }
}
