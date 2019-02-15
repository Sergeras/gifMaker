package application;
	
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;



public class Display extends Application {
	int xSize = 400;							//Set size for the window
	int ySize = 400;							//Set size for the window
	Sprite sprite = new Sprite();		
	String imgName = "Phoenix_SpriteSheet";		//Name for the file
	
	@Override
	public void start(Stage primaryStage) {
		try {
			sprite.setPath("D:\\stazene soubory\\New folder");							// Path needs to be set to place where is sprite sheet
			AnimatedSprite animation = new AnimatedSprite(sprite.loadSprite(imgName));	// Sprite sheet is loaded into animation class
			animation.loadAnimation(1,2,32,6);											// Animation is loaded into the class from the sprite sheet
			animation.setCycleCount(Animation.INDEFINITE);								// Set to go for indefinite cycles, can be adjusted
			animation.play();															// Starts the animation
			ImageView imageView = animation.getImageView();									// This is where interpolate from animatedSprite puts the images from
			imageView.setPreserveRatio(true);				
			
			
			BorderPane root = new BorderPane(imageView); 								// Add the information in the middle of screen
			Scene scene = new Scene(root,xSize,ySize);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Gif Display");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

