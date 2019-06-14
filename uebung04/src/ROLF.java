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

    exitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));


    //frame.pack(); was macht das?
    frame.setVisible(true);
  }
}
