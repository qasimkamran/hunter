package QasimProject.Hunter.Placeholders;

import java.io.FileNotFoundException;

import QasimProject.Hunter.GameObject;
import QasimProject.Hunter.Card.AnimalCard;
import QasimProject.Hunter.Card.Card;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Placeholder extends GameObject{
	protected String owner;
	protected int zone;
	protected boolean vacant = false;
	protected AnimalCard assignedCard;
	protected Rectangle rect, collisionRect;
	protected Image backgroundImage;
	public Placeholder(GraphicsContext gc, double x, double y) 
	{
		super(gc, x, y);
	}
	public abstract Rectangle getPlaceholderRectangle();
	public abstract boolean isVacant();
	public abstract boolean setVacancy(boolean vacancy);
	public abstract void setBackgroundImage() throws FileNotFoundException;
	public abstract void setCollisionDetectionRectangle();
	public abstract Rectangle getCollisionDetectionRectangle();
	public void setCard(AnimalCard card)
	{
		this.assignedCard = card;
	}
	public AnimalCard getCard()
	{
		return assignedCard;
	}
	public void update()
	{
		super.update();
	}
	public int getZone() 
	{
		return zone;
	}
	public String getOwner()
	{
		return owner;
	}
}
