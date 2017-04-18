package nyu.edu.pqs.connectfour.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nyu.edu.pqs.connectfour.board.BoardConstants;
import nyu.edu.pqs.connectfour.board.Move;
import nyu.edu.pqs.connectfour.model.ConnectFourModel;
import nyu.edu.pqs.connectfour.model.GameType;

public class ConnectFourView implements ConnectFourListener{
  private ConnectFourModel model;
  private JFrame frame = new JFrame("Connect Four");
  
  private int columns = BoardConstants.COLUMNS;
  private int rows = BoardConstants.ROWS;
  private JButton[][] board;
  private JPanel panel = new JPanel(new BorderLayout());
  
  /**
   * Construct a connect four window.
   * @param model  keep a instance of the model
   */
  public ConnectFourView(ConnectFourModel model) {
    this.model = model;
    model.addConnectFourListener(this);
    initializeBoard();
    showOptionDialog("Welcome to Connect Four");
     
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setSize(400, 400);
    frame.setVisible(true);    
  }
  
  /**
   * A subwindow lets player to choose play mode.
   */
  public void showOptionDialog(String message) {
    Object[] options = {"Two Players",
        "One Player",
        "Exit"};
    int choice = JOptionPane.showOptionDialog(frame,
        message,
        "Choose play mode",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        options,
        options[0]);
    switch(choice) {
    case 0:
      model.startGame(GameType.HUMAN);
      break;
    case 1:
      model.startGame(GameType.COMPUTER);
      break;
    default:
      System.exit(0);
    }
  }
    
  /**
   * Draw the board.
   */
  private void initializeBoard() {
    JPanel boardPanel = new JPanel(new GridLayout(rows, columns));
    board = new JButton[rows][columns];
    for(int i=0; i<rows; ++i) {
      for(int j=0; j<columns; ++j) {
        JButton button = new JButton();
        final int columnIndex = j;
        button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            addDisc(columnIndex);
          }
        });
        button.setBackground(Color.GRAY);
        board[i][j] = button;
        boardPanel.add(board[i][j]);
      }
    }
    panel.add(boardPanel, BorderLayout.CENTER);
  }
  
  /**
   * Clear the board.
   */
  private void clearBoard() {
    for(int i=0; i<rows; ++i) {
      for(int j=0; j<columns; ++j) {
        board[i][j].setBackground(Color.GRAY);
      }
    }
  }
    
  /**
   * Inverse the board upside down
   * @param move
   */
  private void inverseBoard(Move move) {
    int columnIndex = move.getColumnIndex();
    int rowIndex = rows-1 - move.getRowIndex();
    
    switch(move.getPlayer().getColor()) {
    case RED:
      board[rowIndex][columnIndex].setBackground(Color.RED);
      break;
    case BLUE:
      board[rowIndex][columnIndex].setBackground(Color.BLUE);
      break;
    }
  }
  
  private void addDisc(int columnIndex) {
    model.addDisc(columnIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameStarted() {
    clearBoard();    
  }
  
  /**
   * When showing the board, need to present it upside down so that is looks reasonable.
   * {@inheritDoc}
   */
  @Override
  public void makeMovement(Move move) {    
    inverseBoard(move);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameWin(Move move) {
    inverseBoard(move);
    showOptionDialog("The winner is "+move.getPlayer().getColor());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameTied(Move move) {
    inverseBoard(move);
    showOptionDialog("The game is tied");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void columnFull(int columnIndex) {
    JOptionPane.showMessageDialog(frame,
        "The column is full.",
        "Warning",
        JOptionPane.WARNING_MESSAGE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void outOfBoard(int columnIndex) {
    JOptionPane.showMessageDialog(frame,
        "Out of board.",
        "Warning",
        JOptionPane.WARNING_MESSAGE);
  }

}
