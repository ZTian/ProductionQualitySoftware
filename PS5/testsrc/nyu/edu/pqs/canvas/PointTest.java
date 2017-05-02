package nyu.edu.pqs.canvas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointTest {
  @Test
  public void testPoint() {
    Point point = new Point(1,2);
    assertEquals(point.getX(),1);
    assertEquals(point.getY(),2);
  }
}
