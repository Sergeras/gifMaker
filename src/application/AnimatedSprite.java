package application;

import java.awt.image.BufferedImage;
import javafx.util.Duration;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedSprite extends Transition {
	
	private Duration duration = Duration.millis(1000); 	// set as standart length
	private int totalFrame = 0;							//Total amount of frames in animation
	private BufferedImage spriteSheet;					//To hold spriteSheet
	private ImageView imageView = new ImageView(); 				// to be refreshed when animation is called
	
	// Place to store all frames/images
	private java.util.List<Frame> frames = new ArrayList<Frame>();
	
	{super.setCycleDuration(duration);}
	
	public AnimatedSprite () {
	}
	
	public AnimatedSprite (BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	//Load specified amount the pictures from specified position on X axis, starts as normal from 1st position, if 0 is put
	// in, it wont work
	public void loadAnimation(int xPos, int yPos, int tileSize, int numberOfImages) {
		//Add the frames to array in a order so it can be played later
		for (int i = 0; i < numberOfImages; i++) {
			frames.add(new Frame (spriteSheet.getSubimage(xPos * i * tileSize, yPos * tileSize, tileSize, tileSize)));
		}
		this.totalFrame = frames.size();
	}

	
	// allows to add frame at specified position
	public void addFrame (Frame frame, int framePosition) {
		frames.add(framePosition, frame);
	}
	
	// allows to change the frames in the animation as needed
	public void setFrame (Frame frame, int framePosition) {
		frames.set(framePosition, frame);
	}
	// return specific frame/image/sprite
	public BufferedImage getFrame (int i) {
		return frames.get(i).getFrame();
	}
	// return the amount of pictures in animation
	public int getTotalFrame () {
		return totalFrame;
	}
	// set duration in miliseconds
	public void setDuration (int d) {
		this.duration = Duration.millis(d);
	}
	// get duration
	public Duration getDuration() {
		return this.duration;
	}
	
	//get imageview
	public ImageView getImageView () {
		return this.imageView;
	}
	
	/*Function use fraction of value thats rounded up to next full number thats then used for getting specific frame. 
	* Frame is then converted to Image thats displayed later.*/
	@Override
	protected void interpolate(double frac) {
		try {
			int index = (int) (frac*(frames.size()-0.01));
			imageView.setImage(SwingFXUtils.toFXImage(frames.get(index).getFrame(),null));
			}
		catch (Exception e)
		{
		};
	}
	
}// End of AnimatedSprite
