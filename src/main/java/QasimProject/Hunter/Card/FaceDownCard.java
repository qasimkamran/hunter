package QasimProject.Hunter.Card;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class FaceDownCard extends Card {

	private FileInputStream imageInputStream;

	public FaceDownCard(double x, double y, String owner) 
	{
		super(x, y, owner);
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

	public void setBackgroundImage() throws FileNotFoundException 
	{
		imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\Cards\\FaceDown.png");
		backgroundImage = new Image(imageInputStream, 100, 100, false, false);
	}

}
