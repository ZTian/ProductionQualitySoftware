package nyu.edu.pqs.connectfour;

import nyu.edu.pqs.connectfour.listener.ConnectFourView;
import nyu.edu.pqs.connectfour.model.ConnectFourModel;

/**
 * This is the app of the Connect Four game.
 *
 */
public class ConnectFourApp {

  public static void main(String[] args) {
    new ConnectFourApp().begin();
  }
  
  private void begin(){
    ConnectFourModel model = ConnectFourModel.getInstance();
    @SuppressWarnings("unused")
    ConnectFourView view = new ConnectFourView(model);
  }
}
