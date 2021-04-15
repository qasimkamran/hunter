package QasimProject.Hunter.Card;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class AnimalCard extends Card{

	private FileInputStream imageInputStream;
	private String animalType;
	private String animal;
	
	public AnimalCard(double x, double y, String owner, String animalType)
	{
		super(x, y, owner);
		this.animalType = animalType;
	}
	
	public String getCardType() 
	{
		return "Animal";
	}

	public boolean isPlayable() 
	{
		return true;
	}
	
	public void setBackgroundImage() throws FileNotFoundException
	{
		switch(animalType)
		{
			case "Carnivore":
				imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\Cards\\Carnivore.png");
				backgroundImage = new Image(imageInputStream, 100, 100, false, false);
				break;
			case "Herbivore":
				imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\Cards\\Herbivore.png");
				backgroundImage = new Image(imageInputStream, 100, 100, false, false);
				break;
			case "Omnivore":
				imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\Cards\\Omnivore.png");
				backgroundImage = new Image(imageInputStream, 100, 100, false, false);
				break;
		}
	}
	
	public void setAnimal() throws FileNotFoundException
	{
		
	}
}
