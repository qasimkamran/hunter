package QasimProject.Hunter.Card;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CardDisplay {
	
	private Canvas canvas;
	private Pane root;
	
	private int cardIndex;
	
	public CardDisplay(Canvas canvas, Pane root)
	{
		this.canvas = canvas;
		this.root = root;
	}
	
	public void displayCard(Rectangle rect, Image image)
	{
		rect.setFill(new ImagePattern(image));
		if(!root.getChildren().contains(rect))
			root.getChildren().add(rect);
		cardIndex = root.getChildren().size()-1;
	}
	
	public void displayCard(Rectangle rect)
	{	
		if(!root.getChildren().contains(rect))
			root.getChildren().add(rect);
		cardIndex = root.getChildren().size()-1;
	}
	
	public void removeCard()
	{
		root.getChildren().remove(cardIndex);
	}
}
