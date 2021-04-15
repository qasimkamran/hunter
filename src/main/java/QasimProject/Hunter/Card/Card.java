package QasimProject.Hunter.Card;

import java.io.FileNotFoundException;

import QasimProject.Hunter.GameObject;
import QasimProject.Hunter.Placeholders.Placeholder;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public abstract class Card{
	
	protected Image coverImage = null;
	protected Image backgroundImage = null;
	protected Rectangle rect;
	protected String owner;
	
	public Card(double x, double y, String owner)
	{
		this.rect = new Rectangle(x, y, Placeholder.CARD_WIDTH, Placeholder.CARD_HEIGHT);
		this.owner = owner;
	}
	
	public abstract String getCardType();
	public abstract boolean isPlayable();
	
	public boolean isStackable() 
	{
		return false;
	}
	
	public Image getCoverImage() 
	{
		return coverImage;
	}
	
	public void snapToOrigin()
	{
		rect.setTranslateX(-1 * rect.getLayoutX());
		rect.setTranslateY(-1 * rect.getLayoutY());
	}
	
	public Rectangle getCardRectangle() 
	{
		return rect; 
	}
	
	public Rectangle getCardRectangle(double x, double y) 
	{
		rect.setTranslateX(x);
		rect.setTranslateY(y);
		//System.out.println("getTX: " + rect.getTranslateX() + " getLX: " + rect.getLayoutX() + " getTY: " + rect.getTranslateY() + " getLY: " + rect.getLayoutY());
		return rect; 
	}
	
	public void addDrag(Rectangle rect, EventHandler<MouseEvent> pressEventHandler, EventHandler<MouseEvent> dragEventHandler, EventHandler<MouseEvent> releaseEventHandler)
	{
		rect.addEventFilter(MouseEvent.MOUSE_PRESSED, pressEventHandler);
		rect.addEventFilter(MouseEvent.MOUSE_DRAGGED, dragEventHandler);
		rect.addEventFilter(MouseEvent.MOUSE_RELEASED, releaseEventHandler);
	}
	
	public void clearCard()
	{
		rect = null;
	}
	
	public abstract void setBackgroundImage() throws FileNotFoundException;

	public Image getBackgroundImage()
	{
		return backgroundImage;
	}
	
	public void setCoverImage(Image coverImage) 
	{
		this.coverImage = coverImage;
	}
	
	public String getOwner()
	{
		return owner;
	}
}
