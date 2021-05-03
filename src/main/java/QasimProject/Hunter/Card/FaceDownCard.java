package QasimProject.Hunter.Card;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import QasimProject.Hunter.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
 * FacedownCard extends Card and is involved with representing empty face downs in the game 
 */

public class FaceDownCard extends Card {

	public FaceDownCard(GraphicsContext gc, double x, double y, String owner) 
	{
		super(gc, x, y, owner, "FaceDown");
		try {
			imageInputStream = new FileInputStream(Constants.ABSOLUTE_PATH+"Cards/FaceDown1.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getCardType() 
	{
		return "FaceDown";
	}

	@Override
	public boolean isPlayable() 
	{
		return false;
	}
	
	@Override
	public void readCardInfo()
	{
		
	}
	
	@Override
	public void setText()
	{
		
	}

	@Override
	public boolean isStackable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}
}
