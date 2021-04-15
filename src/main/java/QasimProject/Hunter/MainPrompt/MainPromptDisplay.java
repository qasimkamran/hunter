package QasimProject.Hunter.MainPrompt;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainPromptDisplay {

	private Pane root;
	private Canvas canvas;
	
	private int mainIndex, overlayIndex, textIndex;
	private Text text;
	
	private double xpos;
	
	public MainPromptDisplay(Canvas canvas, Pane root)
	{
		this.canvas = canvas;
		this.root = root;
	}
	
	public void displayMainPrompt(Rectangle rect)
	{
		xpos = rect.getX();
		
		rect.setStyle("-fx-fill: olive; -fx-stroke: springgreen; -fx-stroke-width: 5;");
		
		root.getChildren().add(rect);
		mainIndex = root.getChildren().size()-1;
	}
	
	public void displayPromptText(String displayMessage)
	{
		text = new Text();
		text.setFont(new Font(24));
		text.setText(displayMessage);
		text.setLayoutX(xpos +30);
		text.setLayoutY(368);
		text.setFill(Color.SPRINGGREEN);
		root.getChildren().add(text);
		textIndex = root.getChildren().size()-1;
	}
	
	public void displayNextArrow(Rectangle rect, Image next)
	{
		rect.setFill(new ImagePattern(next));
		root.getChildren().add(rect);
	}
	
	public void emptyPreviousRectangle()
	{
		root.getChildren().remove(textIndex);
		root.getChildren().remove(mainIndex);
	}
}