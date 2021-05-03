package QasimProject.Hunter.Graveyard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import QasimProject.Hunter.Constants;
import QasimProject.Hunter.GameObject;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Graveyard extends GameObject{

	private Image backgroundImage;
	private Text counter = new Text();
	private String owner;
	private int count = 0;
	
	public Graveyard(GraphicsContext gc, double x, double y, String owner) 
	{
		super(gc, x, y);
		this.owner = owner;
		FileInputStream imageInputStream = null;
		try {
			imageInputStream = new FileInputStream(Constants.ABSOLUTE_PATH+"/Placeholder/Graveyard.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backgroundImage = new Image(imageInputStream);
	}	
	
	public void setPosition()
	{
		if(owner.equals("P1"))
		{
			x = Constants.XPOS_START_P1 - Constants.SPACING - 10;
			y = Constants.HAND_YPOS_P1 - 170;
			System.out.println("x,y: " + x + "," + y);
		}
		else
		{
			x = Constants.XPOS_START_CPU + Constants.SPACING + 10;
			y = Constants.HAND_YPOS_CPU + 170;
			System.out.println("x,y: " + x + "," + y);
		}
	}
	
	public void setCounter()
	{
		counter.setFont(new Font(100));
		counter.setFill(Color.WHITE);
		counter.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3px");
		counter.setText((count == 0) ? "0" : ""+count);
		counter.setX(x + 45);
		if(owner.equals("CPU"))
		{
			counter.setY(y + counter.getBoundsInLocal().getHeight() + 10);
			counter.setRotate(180);
		}
		else
			counter.setY(y + counter.getBoundsInLocal().getHeight() - 10);
	}
	
	public void increment()
	{
		count++;
		counter.setText(""+count);
	}
	
	public void update()
	{
		img = backgroundImage;
		super.update();
	}

	public int getCount()
	{
		return count;
	}
	
	public Text getText() 
	{
		return counter;
	}
}
