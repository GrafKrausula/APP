package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

/**
* Frame-Type used. Automatically adds a default close operation
**/
class AppFrame extends JFrame {
    public AppFrame(String title) {
	super(title);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

/**
* Object for the actions of the draw-buttons
**/
class ButtonReaction extends MouseAdapter {
	/**
	* Static varable for the original image
	**/
	private static OriginalImage originalImg;
	/**
	* Static varable for the grayscale image
	**/
	private static GrayscaleImage grayscaleImg;
	/**
	* Static varable for the pattern image
	**/
	private static PatternImage patternImg;
	/**
	* Static varable for the frame
	**/
	private static JFrame frame;
	/**
	* Object variable for the color scheme that is to be drawn when the button is pressed.
	* 1 = original image
	* 2 = grayscale
	* 3 = pattern
	**/
	private int colorScheme;
	/**
	* Constructor for a new button reaction.
	* The images for a new object are only generated once to save computing power.
	* @param i the name of the image that is to be displayed
	* @param f the frame in which the image is to be displayed
	* @param s the desired color scheme
	**/
	public ButtonReaction(String i, JFrame f, int s){
		if (originalImg == null)
			originalImg = new OriginalImage(i);
		if (grayscaleImg == null)
			grayscaleImg = new GrayscaleImage(i);
		if (patternImg == null)
			patternImg = new PatternImage(i);
		if (frame == null)
			frame = f;
		colorScheme = s;
	}
	/**
	* The desired event when the button is pressed.
	* In this case, all pre-existing images in the frame are removed,
	* then the image is drawn in the given color scheme.
	**/
    public void mouseClicked(MouseEvent e) {
	if (e.getClickCount() > 0) {
		frame.remove(originalImg);
		frame.remove(grayscaleImg);
		frame.remove(patternImg);
	    if (colorScheme == 1)
	    	frame.add(originalImg);
	    if (colorScheme == 2)
	    	frame.add(grayscaleImg);
	    if (colorScheme == 3)
	    	frame.add(patternImg);
	    frame.repaint();
	    frame.pack();
	}
    }
}
/**
* Exit program when pressed
**/
class ExitButton extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
	if (e.getClickCount() > 0)
	    System.exit(0);
    }
}
/**
* The original image
**/
class OriginalImage extends JComponent {
	/**
	* The image that is being processed
	**/
	protected BufferedImage image;
	/**
	* Constructor for a new OriginalImage.
	* If no file with the name name can be found, a message appears and the program exits.
	* Otherwise, the file is saved as a buffered image and a prferred size is set.
	* @param name the name of the image that is to be processed
	**/
	public OriginalImage(String name){
		try{
			File imageToRead = new File(name);
			image = ImageIO.read(imageToRead);
			int imageWidth = image.getWidth(this);
        	int imageHeight = image.getHeight(this);
        	setPreferredSize(new Dimension(2*imageWidth,2*imageHeight));
		}
		catch (IOException i){
			System.out.println("File not found!");
			System.exit(0);
		}
	}
	/**
	* The buffered image is read line by line and every pixel is drawn as a 2x2 rectangle
	**/
	public void paintComponent(Graphics g) {
		if (image == null)
			return;
		int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        Color rGBColor;
        int line = 0, row;
        while (line < imageHeight){
        	row = 0;
        	while (row < imageWidth){
        		rGBColor = new Color (image.getRGB(row, line), true);
        		g.setColor(rGBColor);
				g.drawRect(2*row, 2*line, 1, 1);
				row++;
        	}
        	line++;
        }
	}
	public int getWidth(){
		return 2*image.getWidth(this);
	}
	public int getHeight(){
		return 2*image.getHeight(this);
	}
}
/**
* The image in grayscale.
* Extends {@link OriginalImage}, as the process of reading the file is the same.
* {@link OriginalImage #paintComponent()} in {@link OriginalImage}  is overriten.
**/
class GrayscaleImage extends OriginalImage {
	/**
	* Empty constructor
	**/
	public GrayscaleImage(String name){
		super(name);
	}
	/**
	* Overritten paintComponent-method.
	* The buffered image is procced line by line; first, the color of the current pixel in
	* image is read. The red, blue and green-components of that color are averaged out to create a new color (gray)
	* with red, blue and green-value of that average.
	* Afterwards, a 2x2 rectangle is drawn using the new color.
	**/
	public void paintComponent(Graphics g) {
		if (image == null)
			return;
		int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        Color rGBColor, grayColor;
        double red, green, blue;
        int gray;
        int line = 0, row;
        while (line < imageHeight){
        	row = 0;
        	while (row < imageWidth){
        		rGBColor = new Color (image.getRGB(row, line), true);
        		red = rGBColor.getRed();
        		blue = rGBColor.getBlue();
        		green = rGBColor.getGreen();
        		gray = (int)(red+blue+green)/3;
        		grayColor = new Color(gray, gray, gray);
        		g.setColor(grayColor);
				g.drawRect(2*row, 2*line, 1, 1);
				row++;
        	}
        	line++;
        }
	}
}
/**
* The image in pattern.
* Extends {@link OriginalImage}, as the process of reading the file is the same.
* {@link OriginalImage #paintComponent()} in {@link OriginalImage}  is overriten.
**/
class PatternImage extends OriginalImage {
	/**
	* Empty constructor
	**/
	public PatternImage(String name){
		super(name);
	}
	/**
	* Overritten paintComponent-method.
	* The buffered image is processed just like in {@link GrayscaleImage #paintComponent()}.
	* Instead of drawing a rectangle in color gray, gray is divided by 52 to create 5 possible values (0..5).
	* Depending on the value, a 2x2 rectangle in black and white is drawn.
	**/
	public void paintComponent(Graphics g) {
		if (image == null)
			return;
		int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        Color rGBColor, grayColor;
        double red, green, blue;
        int gray;
        int line = 0, row, tmp = 1;
        while (line < imageHeight){
        	row = 0;
        	while (row < imageWidth){
        		rGBColor = new Color (image.getRGB(row, line), true);
        		red = rGBColor.getRed();
        		blue = rGBColor.getBlue();
        		green = rGBColor.getGreen();
        		gray = (int)(red+blue+green)/3;
        		gray /= 52;
        		if (gray == 4){
	    			g.setColor(Color.WHITE);
	    			g.drawRect(row*2,line*2, 1, 1);
        		}
        		else if (gray == 3){
	    			g.setColor(Color.WHITE);
	    			g.drawRect(row*2,line*2, 1, 1);
	    			g.setColor(Color.BLACK);
	    			g.drawRect(row*2,line*2,0,0);
        		}
        		else if (gray == 2) {
	    			g.setColor(Color.WHITE);
	    			g.drawRect(row*2,line*2, 1, 1);
	    			g.setColor(Color.BLACK);
	    			g.drawRect(row*2,line*2,0,0);
	    			g.drawRect(row*2+1,line*2+1,0,0);
        		}
        		else if (gray == 1) {
	    			g.setColor(Color.BLACK);
	    			g.drawRect(row*2,line*2, 1, 1);
	    			g.setColor(Color.WHITE);
	    			g.drawRect(row*2,line*2,0,0);

        		}
        		else {
	    			g.setColor(Color.BLACK);
	    			g.drawRect(row*2,line*2, 1, 1);
        		}
				row++;
        	}
        	line++;
        }
	}
}
/**
* Test-Class
**/
public class AppDrawEvent { 
    public static void main( String[] args ) {
    	String name;
    	if (args.length == 0)
    		name = "kandinsky.jpg";
    	else
    		name = args[0];
		JFrame frame = new AppFrame("Output");
		JPanel buttonPanel = new JPanel();
		JPanel exitPanel = new JPanel();



		Box buttonBox = new Box(BoxLayout.X_AXIS);
		Box exitBox = new Box(BoxLayout.X_AXIS);

		exitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		exitPanel.add(exitBox, BorderLayout.SOUTH);
		buttonPanel.add(buttonBox, BorderLayout.NORTH);

		frame.add(exitPanel, BorderLayout.SOUTH);
		frame.add(buttonPanel, BorderLayout.NORTH);

		JButton originalButton = new JButton("Original");
		JButton grayscaleButton = new JButton("Grayscale");
		JButton patternButton = new JButton("Pattern");
		JButton exitButton = new JButton("Exit");
		exitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

		buttonBox.add(originalButton);
		buttonBox.add(grayscaleButton);
		buttonBox.add(patternButton);
		exitBox.add(exitButton);
		

		MouseAdapter m = new ButtonReaction(name, frame, 1);
		originalButton.addMouseListener(m);
		m = new ButtonReaction(name, frame, 2);
		grayscaleButton.addMouseListener(m);
		m = new ButtonReaction(name, frame, 3);
		patternButton.addMouseListener(m);
		m = new ExitButton();
		exitButton.addMouseListener(m);


		frame.pack();
		frame.setVisible(true);
	}
}
