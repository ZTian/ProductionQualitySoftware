package nyu.edu.pqs.canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CanvasView implements CanvasListener{
  private CanvasModel model;
  private JFrame frame = new JFrame("Canvas");
  private JPanel panel = new JPanel();
  private JButton button = new JButton("Reset");
  private Color penColor = Color.RED;
  private Color backgroundColor = Color.LIGHT_GRAY;
  private Point startPoint;
  private Point endPoint;
  
  /**
   * Constructor that set up the GUI and start the painting.
   * @param model
   */
  public CanvasView(CanvasModel model) {
    this.model = model;
    model.addListener(this);
    
    setGUI();
    model.startPaint();
  }
  
  /**
   * Set up the GUI component and event handlers.
   */
  public void setGUI() {
    JPanel bottomPanel = new JPanel(new BorderLayout());  {
      bottomPanel.add(button, BorderLayout.CENTER);
    }
    panel.add(bottomPanel, BorderLayout.SOUTH);
    panel.setPreferredSize(new Dimension(CanvasConstants.WIDTH, CanvasConstants.LENGTH));
    
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetButtonPressed();
      }
    });
    panel.setBackground(backgroundColor);
    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(),e.getY());
      }
    });
    
    panel.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        endPoint = new Point(e.getX(),e.getY());
        model.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
        startPoint = endPoint;
      }
    });
    
    frame.getContentPane().add(panel);
    frame.setSize(CanvasConstants.WIDTH+50, CanvasConstants.LENGTH+50);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.pack();
    frame.setVisible(true);
  }
  
  private void resetButtonPressed() {
    model.startPaint();
  }
  
  /**
   * Clean the panel when the paint has been restarted.
   */
  private void cleanPanel() {
    Graphics g = panel.getGraphics();
    g.setColor(backgroundColor);
    g.fillRect(0, 0, CanvasConstants.WIDTH, CanvasConstants.LENGTH);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void paintStarted() {
    cleanPanel();
    JOptionPane.showMessageDialog(frame,
        "Welcome to start painting.",
        "Notice",
        JOptionPane.INFORMATION_MESSAGE);    
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void lineAdded(Point startPoint, Point endPoint) {
    Graphics g = panel.getGraphics();
    g.setColor(penColor);
    g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void pointOutOfBound(Point point) {
    JOptionPane.showMessageDialog(frame,
        "Outside the canvas.",
        "Warning",
        JOptionPane.WARNING_MESSAGE);
  }
}
