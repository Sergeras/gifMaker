package application;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Sprite {
	
	private BufferedImage spriteSheet;								// sprite sheet image
	private Path myDir =  FileSystems.getDefault().getPath("C:\\");	// Default path set for sprite sheet location

	//allows to set custom path for location of sprite sheet
	public void setPath(String path) {
		try {
			myDir = Paths.get(path);
		} catch (InvalidPathException e) {
			e.printStackTrace();
		}
	}
	
	// loads the sprite sheet by name in preset location and also return the spritesheet as value
	public BufferedImage loadSprite (String file) {
		
		BufferedImage sprite = null;
		// get file from root location
		try {
			sprite = ImageIO.read(myDir.resolve(file + ".png").toFile());
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		spriteSheet = sprite;
		return sprite;
	}
	
	//To be used for taking the sprite you want, for example 1st is on 0 position. If you want 3rd sprite, just type in 2 same as indexes
	public BufferedImage getSprite (int xPos, int yPos, int tileSize) {
		
		if (spriteSheet == null) {
			
			spriteSheet = loadSprite("AnimationSpriteSheeet");
			
		} 
			return spriteSheet.getSubimage(xPos *tileSize , yPos * tileSize , tileSize, tileSize);
			
	}

	public void setSprite (BufferedImage sprite) {
		this.spriteSheet = sprite;
	}
	
	public BufferedImage getSpriteSheet () {
		return spriteSheet;
	}
	
}//Sprite end
