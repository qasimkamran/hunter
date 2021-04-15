package QasimProject.Hunter.Card;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class EquipCard extends Card{

	private FileInputStream imageInputStream;
	
	public EquipCard(double x, double y, String owner) 
	{
		super(x, y, owner);
	}

	public String getType() 
	{
		return "Equip";
	}

	public boolean isPlayable() 
	{
		return false;
	}

	public void setBackgroundImage() throws FileNotFoundException 
	{
		imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\Cards\\Equip.png");
		backgroundImage = new Image(imageInputStream, 100, 100, false, false);
	}

	@Override
	public String getCardType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
