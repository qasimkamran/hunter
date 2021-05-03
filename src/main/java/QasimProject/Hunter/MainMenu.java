package QasimProject.Hunter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * models the main menu screen that leads to either scene based on user selection
 */

public class MainMenu {

	private Pane root;

	private Button play = new Button();
	private Button howToPlay = new Button();
	private Button exit = new Button();
	
	private FileInputStream imageInputStream;
	public static Image menuSelection, menuBackground;
	
	private Scene playScene, howToPlayScene;
	
	private Stage primaryStage;

	public MainMenu(Pane root, Stage primaryStage, Scene playScene, Scene howToPlayScene)
	{
		try {
			imageInputStream = new FileInputStream(Constants.ABSOLUTE_PATH+"Backgrounds/MenuSelections.png");
			menuSelection = new Image(imageInputStream);
			imageInputStream = new FileInputStream(Constants.ABSOLUTE_PATH+"Backgrounds/MainScreen.png");
			menuBackground = new Image(imageInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.primaryStage = primaryStage;
		this.playScene = playScene;
		this.howToPlayScene = howToPlayScene;
		this.root = root;
	}
	
	//click event that leads to changing scene to a game situation 
	private EventHandler<MouseEvent> playClickEvent = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent e) 
		{
			primaryStage.close();
			primaryStage.setScene(playScene);
			primaryStage.show();
		}
	};
	
	//click event that leads to changing scene to a tutorial section
	private EventHandler<MouseEvent> howToPlayClickEvent = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent e) 
		{
			primaryStage.close();
			primaryStage.setScene(howToPlayScene);
			primaryStage.show();
		}
	};
	
	//click event that closes the application
	private EventHandler<MouseEvent> exitClickEvent = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent e) 
		{
			primaryStage.close();
			Platform.exit();
		}
	};
	
	//initialises buttons for selection
	public void setButtons()
	{
		play.setText("Play");
		howToPlay.setText("How To Play");
		exit.setText("Exit");
		
		Button[] buttons = {play, howToPlay, exit};
		
		BackgroundImage image = new BackgroundImage(menuSelection, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(image);
		
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].setLayoutX(100);
			if(i==0)
				buttons[i].setLayoutY(230);
			else
				buttons[i].setLayoutY((buttons[i-1].getLayoutY())+130);
			buttons[i].setMinHeight(70);
			buttons[i].setBackground(background);
			buttons[i].setFont(new Font(24));
			buttons[i].setTextFill(Color.WHITE);
			buttons[i].setPadding(new Insets(30));
			if(!root.getChildren().contains(buttons[i]))
				root.getChildren().add(buttons[i]);
		}
	}
	
	//adds the background image to the pane
	public void setBackground()
	{
		Rectangle rect = new Rectangle(0, 0, 1280, 720);
		rect.setFill(new ImagePattern(menuBackground));
		if(!root.getChildren().contains(rect))
			root.getChildren().add(rect);
	}
	
	//sets the click events relevant to each selection
	public void setClickEvents()
	{
		play.setOnMouseClicked(playClickEvent);
		howToPlay.setOnMouseClicked(howToPlayClickEvent);
		exit.setOnMouseClicked(exitClickEvent);
	}
}
