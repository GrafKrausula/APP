package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

class AppFrame extends JFrame {
    public AppFrame(String title) {
	     super(title);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


public class ROLF{
  public static void main(String[] args) {
    JFrame frame = new AppFrame("Schlonk");
		JPanel buttonPanel = new JPanel();
		JPanel exitPanel = new JPanel();

    Box buttonBox = new Box(BoxLayout.X_AXIS);
    Box exitBox = new Box(BoxLayout.X_AXIS);


    frame.pack();
    frame.setVisible(true);
  }
}
