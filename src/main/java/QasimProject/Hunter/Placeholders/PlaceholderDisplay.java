package QasimProject.Hunter.Placeholders;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlaceholderDisplay {
	
	private Pane root;
	private Canvas canvas;
	
	public PlaceholderDisplay(Canvas canvas, Pane root)
	{
		this.canvas = canvas;
		this.root = root;
	}
	
	public void displayPlaceholder(Rectangle rect)
	{
		rect.setFill(Color.SPRINGGREEN);
		root.getChildren().add(rect);
		
		double xpos = rect.getX();
		double ypos = rect.getY();
		
		Rectangle rectOverlay = new Rectangle(xpos +5, ypos +5, Placeholder.CARD_WIDTH -10, Placeholder.CARD_HEIGHT -10);
		rectOverlay.setFill(Color.OLIVE);
		root.getChildren().add(rectOverlay);
	}
	
	public void setCollisionRectangle(Rectangle rect)
	{
		root.getChildren().add(rect);
	}
}
