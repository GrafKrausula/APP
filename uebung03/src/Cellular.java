package src;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

/**
* JPanal that is drawn.
* The buffered image currentImage is overriten via {@link #setCurrentImage(BufferedImage)} and the frame redrawn whenever a new picture is to be shown.
**/
class CellPanel extends JComponent{
	/**
	* Constant representing the size of the grid
	**/
	private final int size = 8-1;
	/**
	* The image read from the file with name name
	**/
	private BufferedImage image;
	/**
	* The image that is to be drawn
	**/
	private BufferedImage currentImage;
	/**
	* Constructor for a new cellular panel in which the images is being displayed.
	* @param name the name of the file that is to be read
	**/
	public CellPanel(String name){
		try{
			File imageToRead = new File(name);
			image = ImageIO.read(imageToRead);
			int imageWidth = image.getWidth(this);
        	int imageHeight = image.getHeight(this);
        	setPreferredSize(new Dimension(size*imageWidth*2,size*imageHeight*2));
		}
		catch (IOException i){
			System.exit(0);
		}
	}
	/**
	* How this panel is to be displayed.
	* Simply displays the image saved in currentImage.
	**/
	public void paintComponent(Graphics g) {
		g.drawImage(currentImage, 0, 0, null);
	}
	/**
	* Getter for the current image that is being displayed
	* @return the image that is being displayed
	**/
	public BufferedImage getCurrentImage(){
		return currentImage;
	}
	/**
	* Getter for tne image that was read from a file
	* @return the image image that was read from a file
	**/
	public BufferedImage getImage(){
		return image;
	}
	/**
	* Getter for the desired grid size
	* @return the desired grid size
	**/
	public int getGridSize(){
		return size;
	}
	/**
	* Setter for the desired image to be displayed once the frame is repainted
	* @param i the image to be displayed once the frame is repainted
	**/
	public void setCurrentImage(BufferedImage i){
		currentImage = i;
	}
}
/**
* What is to happen when the "start" button is pressed
**/
class StartButton extends MouseAdapter{
	/**
	* The JField in which the amount of times, the algorithm is to be repeated, is inputted
	**/
	private JTextField timeField;
	/**
	* The worker thread
	**/
	private Worker worker;
	/**
	* The drawer thread
	**/
	private Drawer drawer;
	/**
	* Basic constructor
	* @param t the textfield that is to be read once the button is pressed
	* @param cp the worker thread that is to be updated with a new time for the algorithm
	* @param p the drawer that is to be notified once the bunnon is pressed
	**/
	public StartButton(JTextField t, Worker cp, Drawer p){
		timeField = t;
		worker = cp;
		drawer = p;
	}
	/**
	* Once the linked button is pressed, the integer in the JField timeField is read and sent to the worker.
	* Then, the drawer is notified.
	**/
	synchronized public void mouseClicked(MouseEvent e) { 
		if (e.getClickCount() > 0) {
			worker.setTime(Integer.parseInt(timeField.getText()));
			drawer.sendNotify();			
		}
    }
}
/**
* The runnable drawer.
* Its job is to notify the worker and update the frame once the worker is done
**/
class Drawer extends Thread{
	/**
	* The worker that is to be notified
	**/
	private Worker worker;
	/**
	* The frame that is to be updated
	**/
	private JFrame frame;
	/**
	* true, if the cycle is to no longer be repeated
	**/
	private boolean stop = false;
	/**
	* Setter for the stop-boolean.
	* Immediately sets the stop variable to true
	**/
	public void setStop(){
		stop = true;
	}
	/**
	* Basic constructor
	**/
	Drawer(Worker p, JFrame c){
		worker = p;
		frame = c;
	}
	/**
	* Sends a notification to this thread
	**/
	synchronized public void sendNotify () {
		notify ();
	}
	synchronized public void run(){
		try{
			wait();
			frame.repaint();
			while (!stop){
				wait();
				worker.sendNotify();
				wait();
				frame.repaint();
			}
		}
		catch (InterruptedException e){}
	}
}
/**
* Runnable worker method.
* Its job is to run the celluar algorithm a given number of times on the image read from a file
**/
class Worker extends Thread{
	/**
	* The amount of times, the algorithm is to be repeated
	**/
	private int time = 0;
	/**
	* The size of the grid
	**/
	private int size;	
	/**
	* The drawer thread that is to be notified, once the algorithm is done
	**/
	private Drawer drawer;
	/**
	* The image that is to be used as a baseline for the algorithm
	**/
	private BufferedImage image;
	/**
	* The current image that is being processed
	**/
	private BufferedImage currentImage;
	/**
	* The panel in which the finished image is to be written into
	**/
	private CellPanel cellPanel;
	/**
	* true, if the cycle is to no longer be repeated
	**/
	boolean stop = false;
	/**
	* Setter for the time attribute
	**/
	public void setTime(int t){
		time = t;
	}
	/**
	* Setter for the partner that is to be notified
	**/
	public void setPartner(Drawer a){
		drawer = a;
	}
	/**
	* Setter for the stop-boolean.
	* Immediately sets the stop variable to true
	**/
	public void setStop(){
		stop = true;
	}
	/**
	* Send a notification to this thread
	**/
	synchronized public void sendNotify () {
		notify ();
	}
	/**
	* Basic constructor.
	* Uses CellPanel's getters to aquire necessary information
	**/
	public Worker(CellPanel c){
		cellPanel = c;
		size = c.getGridSize();
		image = c.getImage();
	}
	/**
	* The cellular algorithm using image as a baseline
	**/
	synchronized public void run() {
		if (image == null) 
			return;
		int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        int currentImageWidth = imageWidth*size*2, currentImageHeight = imageHeight*size*2;
        Color rGBColor, grayColor;
        BufferedImage newImage;
        double red, green, blue;
        int gray, line, row, countNeighbours;
        final int black = Color.BLACK.getRGB(), white = Color.WHITE.getRGB();
        try {
        	while(!stop){
        		currentImage = new BufferedImage(currentImageWidth, currentImageHeight, BufferedImage.TYPE_INT_ARGB);
		        for (int countToTime = 0; countToTime <= time; countToTime++){
		        	// Initialize currentImage with the image in black and white at its center
			        if (countToTime == 0){
						for(int i = 0; i < size; i++){
							for (int j = 0; j < size; j++){
								if (j == size/2 && i == size/2){
							        line = i*imageHeight;
							        while (line < imageHeight + i*imageHeight){
							        	row = j*imageWidth;
							        	while (row < imageWidth + j*imageWidth){
							        		rGBColor = new Color (image.getRGB(row - j*imageWidth, line - i*imageHeight), true);
							        		red = rGBColor.getRed();
							        		blue = rGBColor.getBlue();
							        		green = rGBColor.getGreen();
							        		gray = (int)(red+blue+green)/3;
							        		gray /= 52;
							        		if (gray == 4){
								    			currentImage.setRGB(row*2, line*2, white);
								    			currentImage.setRGB(row*2+1, line*2, white);
								    			currentImage.setRGB(row*2, line*2+1, white);
								    			currentImage.setRGB(row*2+1, line*2+1, white);						    			
							        		}
							        		else if (gray == 3){
								    			currentImage.setRGB(row*2, line*2, black);
								    			currentImage.setRGB(row*2+1, line*2, white);
								    			currentImage.setRGB(row*2, line*2+1, white);
								    			currentImage.setRGB(row*2+1, line*2+1, white);	    			
							        		}
							        		else if (gray == 2) {
								    			currentImage.setRGB(row*2, line*2, black);
								    			currentImage.setRGB(row*2+1, line*2, white);
								    			currentImage.setRGB(row*2, line*2+1, white);
								    			currentImage.setRGB(row*2+1, line*2+1, black);		    				        			
							        		}
							        		else if (gray == 1) {
								    			currentImage.setRGB(row*2, line*2, white);
								    			currentImage.setRGB(row*2+1, line*2, black);
								    			currentImage.setRGB(row*2, line*2+1, black);
								    			currentImage.setRGB(row*2+1, line*2+1, black);	
							        			
							        		}
							        		else {
								    			currentImage.setRGB(row*2, line*2, black);
								    			currentImage.setRGB(row*2+1, line*2, black);
								    			currentImage.setRGB(row*2, line*2+1, black);
								    			currentImage.setRGB(row*2+1, line*2+1, black);	      			
							        		}
											row++;
							        	}
							        	line++;
							        }
							    }
							}
						}
					}//End of initialization
					else{
						newImage = new BufferedImage(currentImageWidth, currentImageHeight, BufferedImage.TYPE_INT_ARGB);
						// i is the current line
						for (int i = 0; i < currentImageWidth; i++){
							// j is the current row
							for (int j = 0; j < currentImageHeight; j++){
								countNeighbours = 0;
								// count the number of black neighbours and adjust the current pixel accordingly
								for (int s = -1; s < 2; s++){
									for (int t = -1; t < 2; t++){
										if (s != 0 || t != 0){
											if (s + i > -1 && s + i < currentImageWidth && t + j > -1 && t + j < currentImageHeight)
												if (currentImage.getRGB(s + i, t + j) == black)
													countNeighbours++;
											else if (s + i == -1 && t + j > -1 && t + j < currentImageHeight)
												if (currentImage.getRGB(currentImageHeight-1, t + j) == black)
													countNeighbours++;
											else if (s + i > -1 && s + i < currentImageWidth && t + j == -1)
												if (currentImage.getRGB(s + i, currentImageWidth-1) == black)
													countNeighbours++;
											else if (s + i > -1 && s + i < currentImageWidth && t + j == currentImageHeight)
												if (currentImage.getRGB(s + i, 0) == black)
													countNeighbours++;
											else if (s + i == currentImageWidth && t + j > -1 && t + j < currentImageHeight)
												if (currentImage.getRGB(currentImageHeight-1, t + j) == black)
													countNeighbours++;
											else if (s + i == -1 && t + j == -1)
												if (currentImage.getRGB(currentImageWidth-1, currentImageHeight-1) == black)
													countNeighbours++;
											else if (s + i == -1 && t + j == currentImageWidth)
												if (currentImage.getRGB(currentImageWidth-1, 0) == black)
													countNeighbours++;
											else if (s + i == currentImageHeight && t + j == -1)
												if (currentImage.getRGB(0, currentImageHeight-1) == black)
													countNeighbours++;
											else
												if (currentImage.getRGB(0, 0) == black)
													countNeighbours++;	
										}
									}
								}
								if (countNeighbours % 2 == 0)
									newImage.setRGB(i, j, white);
								else
									newImage.setRGB(i, j, black);
							}
						}
						currentImage = newImage;
					}//End of else
				}//End of time-loop
				// override cellPanel's old image that was being displayed
				cellPanel.setCurrentImage(currentImage);
				drawer.sendNotify();
				wait();
			}
		}
		catch(InterruptedException e){
			System.out.println("Worker-Thread was interrupted!");
		}
	}//End of run()
	
}
/**
* Construction class
**/
public class Cellular
{ 
    public static void main( String[] args ) 
    { 
    	String name;
    	// If the commatd argumenh is empty, use smile32.jpg as a baseline image. 
    	// Else, use the filename given via the command argumenh
    	if (args.length == 0)
    		name = "smile32.jpg";
    	else
    		name = args[0];

    	// Create the frame and panels for buttons 
    	// and the picture that will be displayed
		JFrame frame = new AppFrame("Cell Output"); 
		JPanel buttonPanel = new JPanel();
		JPanel exitPanel = new JPanel();
		JTextField timeField = new JTextField(5);
		CellPanel cp = new CellPanel(name);

		frame.add(cp);

		// Start the worker and drawer threads
		Worker worker = new Worker(cp);
		Drawer drawer = new Drawer(worker, frame);
		worker.setPartner(drawer);
		worker.start();
		drawer.start();

		// Create the buttons
		JButton startButton = new JButton("Start");
		JButton exitButton = new JButton("Exit");
		exitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

		// Add behaviour to the buttons
		MouseAdapter m = new ExitButton();
		exitButton.addMouseListener(m);
		m = new StartButton(timeField, worker, drawer);
		startButton.addMouseListener(m);

		// Create boxes for the buttons
		Box buttonBox = new Box(BoxLayout.X_AXIS);
		Box exitBox = new Box(BoxLayout.X_AXIS);

		exitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		exitPanel.add(exitBox, BorderLayout.SOUTH);
		buttonPanel.add(buttonBox, BorderLayout.NORTH);

		frame.add(exitPanel, BorderLayout.SOUTH);
		frame.add(buttonPanel, BorderLayout.NORTH);

		buttonBox.add(timeField);
		buttonBox.add(startButton);
		exitBox.add(exitButton);		
		
		// Adjust size of the frame and make it visible
		frame.pack();
		frame.setVisible(true); 
	} 
}