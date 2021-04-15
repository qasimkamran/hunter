package QasimProject.Hunter.Placeholders;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class AnimalPlaceholder extends Placeholder{

	private final double ANIMAL_YPOS_CPU = (304 - CARD_HEIGHT);
	private final double ANIMAL_YPOS_P1 = (416);
	
	private String owner;
	private int zone;
	private boolean vacant = false;
	
	private Rectangle rect, collisionRect;
	
	public AnimalPlaceholder(String owner, int zone)
	{
		this.owner = owner;
		this.zone = zone;
	}
	
	public Rectangle getPlaceholderRectangle() 
	{
		double spacing = 0.14 * 1280;
		if(owner.equals("CPU"))
			return rect = new Rectangle((XPOS_START_CPU + ((-spacing * zone) + spacing)), ANIMAL_YPOS_CPU, CARD_WIDTH, CARD_HEIGHT);
		else
			return rect = new Rectangle((XPOS_START_P1  + ((spacing * zone) - spacing)), ANIMAL_YPOS_P1, CARD_WIDTH, CARD_HEIGHT);
	}
	
	public void setCollisionDetectionRectangle()
	{
		collisionRect = new Rectangle();
		collisionRect.setX(rect.getX() + 30);
		collisionRect.setY(rect.getY() + 30);
		collisionRect.setWidth(rect.getWidth() - 60);
		collisionRect.setHeight(rect.getHeight() - 60);
		collisionRect.setFill(Color.BLACK);
	}
	
	public Rectangle getCollisionDetectionRectangle()
	{
		return collisionRect;
	}
	
	public boolean isVacant()
	{
		return vacant;
	}

	public boolean setVacancy(boolean vacancy) 
	{
		return false;
	}
}
