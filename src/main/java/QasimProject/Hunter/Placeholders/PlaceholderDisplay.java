package QasimProject.Hunter.Placeholders;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlaceholderDisplay {
	
	private Pane root;
	
	public PlaceholderDisplay(Pane root)
	{
		this.root = root;
	}
	
	public void displayPlaceholder(Placeholder placeholder) throws FileNotFoundException
	{
		Rectangle rect;
		rect = placeholder.getPlaceholderRectangle();
		rect.setFill(Color.TRANSPARENT);
		if(!root.getChildren().contains(rect))
			root.getChildren().add(rect);
			
		placeholder.setCollisionDetectionRectangle();
		rect = placeholder.getCollisionDetectionRectangle();
		rect.setFill(Color.TRANSPARENT);
		if(!root.getChildren().contains(rect))
			root.getChildren().add(rect);
		
		placeholder.setBackgroundImage();
		placeholder.update();
	}
}
