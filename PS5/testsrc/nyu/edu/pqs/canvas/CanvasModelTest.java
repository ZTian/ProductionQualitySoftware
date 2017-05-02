package nyu.edu.pqs.canvas;

import org.junit.Test;

public class CanvasModelTest {
  @Test
  public void testCanvasModel() {
    CanvasModel model = CanvasModel.getInstance();
    model.drawLine(-1, 0, 1, 2);
    model.drawLine(0, -1, 1, 2);
    model.drawLine(1, 2, -1, 0);
    model.drawLine(1, 2, 0, -1);
    model.drawLine(1, 2, 1, 2);
  }
}
