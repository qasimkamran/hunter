package QasimProject.Hunter.MainPrompt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import QasimProject.Hunter.GameObject;
import QasimProject.Hunter.TurnCounter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainPrompt {
	
	private String displayMessage = null;
	private int overlay;
	
	private final double ypos = 360 - (0.05 * 720);
	private double xpos = 640 - (0.125 * 1280);
	private final double height = (0.1 * 720);
	private double width = (0.25 * 1280);
	
	private Rectangle promptRect, nextRect;
	private Text text;
	
	private TurnCounter turnCounter;
	
	public MainPrompt(TurnCounter turnCounter)
	{
		this.turnCounter = turnCounter;
	}
	
	public void outputDraw()
	{
		displayMessage = "Draw a card!";
	}
	
	public void outputSet()
	{
		displayMessage = "Set one card!";
	}
	
	public void outputEquip()
	{
		displayMessage = "Equip one card!";
	}
	
	public void outputEngage()
	{
		displayMessage = "Engage!";
	}
	
	public double setXPOS(double width)
	{
		double totalWidth = width + 200;
		return xpos = 640 - (totalWidth/2);
	}
	
	public Rectangle getPromptRectangle(double displayMessageWidth)
	{
		return promptRect = new Rectangle(setXPOS(displayMessageWidth), ypos, displayMessageWidth +200, height);
	}
	
	public double getDisplayMessageBoundW()
	{
		Text text = new Text();
		text.setFont(new Font(24));
		text.setText(displayMessage);
		return text.getLayoutBounds().getWidth();
	}
	
	public String getDisplayMessage()
	{
		return displayMessage;
	}
	
	public Image getNextArrow() throws FileNotFoundException
	{
		Image arrow;
		FileInputStream imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\ArrowButton\\Arrow.png");
		return arrow = new Image(imageInputStream, 100, 57, false, false);
	}
	
	public Rectangle getNextRectangle(double width)
	{
		return nextRect = new Rectangle(setXPOS(width) + width +65, ypos +6, 100, 57);
	}
	
	public void addClick(EventHandler<MouseEvent> clickEventHandler)
	{
		if(nextRect.getOnMouseClicked() == null)
			nextRect.addEventFilter(MouseEvent.MOUSE_CLICKED, clickEventHandler);
	}
	
	public void outputMessage()
	{
		switch(turnCounter.getPhase())
		{
			case "Draw":
				outputDraw();
				break;
			case "Set":
				outputSet();
				break;
			case "Stack":
				outputEquip();
				break;
			case "Engage":
				outputEngage();
				break;
			default:
				turnCounter.endTurn();
				break;
		}
	}
	
	public TurnCounter getTurnCounter()
	{
		return turnCounter;
	}
	
	public void update()
	{
		//System.out.println("x: " + x + " y: " + y + " imgRef: " + img + " phaseCount: " + turnCounter.getPhaseCount() + " promptImages.size(): " + promptImages.size());
		for(int i=0; i<promptImages.size(); i++)
			if(turnCounter.getPhaseCount() == i)
			{
				outputMessage();
				x = setXPOS(getDisplayMessageBoundW());
				y = ypos;
				img = promptImages.get(i);
				gc.drawImage(img, x, y);
				break;
			}
	}
}
