package QasimProject.Hunter.Card;

import java.awt.image.ImageProducer;
import java.awt.image.renderable.RenderableImage;
import java.awt.image.renderable.RenderableImageProducer;
import java.awt.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.imageio.ImageWriter;

import QasimProject.Hunter.Constants;
import QasimProject.Hunter.GameObject;
import QasimProject.Hunter.Placeholders.Placeholder;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/*
 * this class models a card and extends GameObject.
 * all basic features and attributes of a card are implemented in this class.
 */

public abstract class Card extends GameObject{
	
	protected String cardName;
	protected Image cardImage = null;
	protected Rectangle rect;
	protected String owner;
	protected boolean inHand = true;
	protected Scanner scanner;
	protected boolean isSet = false;
	protected FileInputStream imageInputStream;
	protected Text text = new Text();
	private boolean mousePressedHandlerAdded, mouseDragHandlerAdded, mouseReleaseHandlerAdded;
	
	public Card(GraphicsContext gc, double x, double y, String owner, String cardName)
	{
		super(gc, x, y);
		this.rect = new Rectangle(x, y, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		this.owner = owner;
		this.cardName = cardName;
	}
	
	//the following methods require implementation from their relevant subclasses
	public abstract String getCardType();
	public abstract boolean isPlayable();
	public abstract void readCardInfo();
	public abstract boolean isStackable();
	
	public void setCard()
	{
		isSet = true;
	}
	
	public boolean getIsSet()
	{
		return isSet;
	}
	
	public Image getCardImage() 
	{
		return cardImage;
	}
	
	//effectively undos all translation from an initial position for a card
	public void snapToOrigin()
	{
		rect.setTranslateX(-1 * rect.getLayoutX());
		rect.setTranslateY(-1 * rect.getLayoutY());
		text.setTranslateX(-1 * text.getLayoutX());
		text.setTranslateY(-1 * text.getLayoutY());
	}
	
	public Rectangle getCardRectangle() 
	{
		return rect; 
	}
	
	//returns a translated version of the cards rectangle
	public Rectangle getCardRectangle(double dX, double dY) 
	{
		rect.setTranslateX(dX);
		rect.setTranslateY(dY);
		return rect; 
	}
	
	//returns a translated version of the text assigned to this card
	public Text getText(double dX, double dY)
	{
		text.setTranslateX(dX);
		text.setTranslateY(dY);
		return text;
	}
	
	public abstract void setText();
	
	//adds all event handlers associated with a the drag feature to the card's rectangle
	public void addDrag(Rectangle rect, EventHandler<MouseEvent> pressEventHandler, EventHandler<MouseEvent> dragEventHandler, EventHandler<MouseEvent> releaseEventHandler)
	{
		if(!mousePressedHandlerAdded) {
			rect.addEventFilter(MouseEvent.MOUSE_PRESSED, pressEventHandler);
			mousePressedHandlerAdded = true;
		}
		if(!mouseDragHandlerAdded) {
			rect.addEventFilter(MouseEvent.MOUSE_DRAGGED, dragEventHandler);
			mouseDragHandlerAdded = true;
		}
		if(!mouseReleaseHandlerAdded) {
			rect.addEventFilter(MouseEvent.MOUSE_RELEASED, releaseEventHandler);
			mouseReleaseHandlerAdded = true;
		}
	}
	
	public void clearCard()
	{
		rect = null;
	}
	
	public void setCardImage()
	{
		if(imageInputStream!=null)
			cardImage = new Image(imageInputStream);
	}
	
	public boolean getInHand()
	{
		return inHand;
	}
	
	public void setInPlay()
	{
		inHand = false;
	}
	
	public void setInHand()
	{
		inHand = true;
	}
	
	public String getCardName()
	{
		return cardName;
	}
	
	public void update()
	{
		img = cardImage;
		super.update();
	}
	
	public void removeImage()
	{
		img = null;
	}
	
	public void setImageTranslation(double dX, double dY)
	{
		x = dX;
		y = dY;
	}
	
	public String getOwner()
	{
		return owner;
	}

	public Text getText()
	{
		return text;
	}
	
	public abstract void printInfo();

	//removes all assigned event handlers related with the drag feature, from the cards rectangle object
	public void removeDrag(Rectangle cardRectangle, EventHandler<MouseEvent> pressEventHandler,	EventHandler<MouseEvent> dragEventHandler, EventHandler<MouseEvent> releaseEventHandler) 
	{
		rect.removeEventFilter(MouseEvent.MOUSE_PRESSED, pressEventHandler);
		rect.removeEventFilter(MouseEvent.MOUSE_DRAGGED, dragEventHandler);
		rect.removeEventFilter(MouseEvent.MOUSE_RELEASED, releaseEventHandler);
	}
}
