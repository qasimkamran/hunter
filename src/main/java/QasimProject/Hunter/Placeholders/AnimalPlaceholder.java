package QasimProject.Hunter.Placeholders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import QasimProject.Hunter.Constants;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class AnimalPlaceholder extends Placeholder{

	private final double ANIMAL_YPOS_CPU = (304 - Constants.CARD_HEIGHT);
	private final double ANIMAL_YPOS_P1 = (416);
	
	private Point2D placeholderPosition;
	
	public AnimalPlaceholder(GraphicsContext gc, String owner, int zone)
	{
		super(gc, 0, 0);
		this.owner = owner;
		this.zone = zone;
	}
	
	public Point2D setPosition()
	{
		if(owner.equals("CPU"))
			return placeholderPosition = new Point2D((Constants.XPOS_START_CPU + (-Constants.SPACING * zone) + Constants.SPACING), ANIMAL_YPOS_CPU);
		else
			return placeholderPosition = new Point2D((Constants.XPOS_START_P1  + ((Constants.SPACING * zone) - Constants.SPACING)), ANIMAL_YPOS_P1);
	}
	
	public Rectangle getPlaceholderRectangle() 
	{
		if(rect == null)
			return rect = new Rectangle(setPosition().getX(), setPosition().getY(), Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		return rect;
	}
	
	public void setCollisionDetectionRectangle()
	{
		if(collisionRect == null) {
			collisionRect = new Rectangle();
		}		
		collisionRect.setX(rect.getX() + 30);
		collisionRect.setY(rect.getY() + 30);
		collisionRect.setWidth(rect.getWidth() - 60);
		collisionRect.setHeight(rect.getHeight() - 60);
	}
	
	public void setBackgroundImage() throws FileNotFoundException
	{
		FileInputStream imageInputStream = new FileInputStream(Constants.ABSOLUTE_PATH+"Placeholder/Placeholder.png");
		backgroundImage = new Image(imageInputStream);
	}
	
	public void update()
	{
		img = backgroundImage;
		x = setPosition().getX();
		y = setPosition().getY();
		super.update();
	}

	public boolean setVacancy(boolean vacancy) 
	{
		return false;
	}
}
