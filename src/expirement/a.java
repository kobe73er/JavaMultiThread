package expirement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class RaceCar extends JPanel implements Runnable {
    private int xBase = 0;
    private Thread thread = new Thread(this);
    private int sleepTime = 10;

    public RaceCar() {
      setBorder(BorderFactory.createLineBorder(Color.BLACK));
      // Create a timer for the listener raceCar
      thread.start();
    }

    public void run() {
      try {
        while (true) {
          repaint();
          Thread.sleep(sleepTime);
        }
      }
      catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }

    public void setSpeed(int speed) {
      sleepTime = speed;
    }

    /** Paint message */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      int yBase = getHeight();
      if (xBase > getWidth()) {
        xBase = -20;
      }
      else {
        xBase += 1;
      }

      // Draw two wheels
      g.setColor(Color.BLACK);
      g.fillOval(xBase + 10, yBase - 10, 10, 10);
      g.fillOval(xBase + 30, yBase - 10, 10, 10);

      // Draw the car body
      g.setColor(Color.GREEN);
      g.fillRect(xBase, yBase - 20, 50, 10);

      // Draw the top
      g.setColor(Color.RED);
      Polygon polygon = new Polygon();
      polygon.addPoint(xBase + 10, yBase - 20);
      polygon.addPoint(xBase + 20, yBase - 30);
      polygon.addPoint(xBase + 30, yBase - 30);
      polygon.addPoint(xBase + 40, yBase - 20);
      g.fillPolygon(polygon);
    }
  }
