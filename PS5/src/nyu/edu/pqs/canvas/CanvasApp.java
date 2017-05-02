package nyu.edu.pqs.canvas;

public class CanvasApp {
  
  /**
   * The method that runs the app
   */
  public void run() {
    CanvasModel model = CanvasModel.getInstance();
    @SuppressWarnings("unused")
    CanvasView view = new CanvasView(model);
  }

  public static void main(String[] args) {
    new CanvasApp().run();
  }

}
