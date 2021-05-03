package QasimProject.Hunter.Card;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import QasimProject.Hunter.Constants;
import QasimProject.Hunter.Placeholders.Placeholder;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*
 * AnimalCard extends Card and is involved with representing animal cards in the game
 */

public class AnimalCard extends Card{

	private String animal;
	
	private int baseStrength;
	private int otherPrey;
	
	private int finalStrength;
	
	//the following ArrayLists store predators, prey and equips that are applicable to an instance of an animal
	private ArrayList<String> predators = new ArrayList<>();
	private ArrayList<String> prey = new ArrayList<>();
	private ArrayList<String> equips = new ArrayList<>();
	
	public AnimalCard(GraphicsContext gc, double x, double y, String owner, String animal)
	{
		super(gc, x, y, owner, animal);
		this.animal = animal;
		try {
			String sourceDir = Constants.ABSOLUTE_PATH+"Animals/" + animal + "/";
			String imgSourceDir = sourceDir + animal + "C.png";
			if(owner.equals("CPU"))
				imgSourceDir = sourceDir + animal + "CC.png";
			System.out.println(imgSourceDir);
			String textSourceDir = sourceDir + animal + ".txt";
			System.out.println(imgSourceDir);
			scanner = new Scanner(new File(textSourceDir));
			imageInputStream = new FileInputStream(imgSourceDir);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCardType()
	{
		return "Animal";
	}

	public boolean isPlayable() 
	{
		return true;
	}
	
	//sets text on the card based on calculated final strength
	@Override
	public void setText()
	{
		text.setFont(new Font(36));
		text.setStyle("-fx-fill: blue; -fx-stroke: darkblue; -fx-stroke-width: 1px");
		text.setText(""+finalStrength);
		text.setX(rect.getX() +15);
		text.setY(rect.getY() + Constants.CARD_HEIGHT -30);
		if(owner.equals("CPU"))
		{
			text.setX(Constants.CARD_WIDTH/2 + rect.getX() -3);
			text.setY(rect.getY() + 60);
			text.setRotate(180);
		}
	}
	
	//reads card info from the relevant text file for animals
	public void readCardInfo()
	{
		scanner.useDelimiter("-");
		while(scanner.hasNextLine())
		{
			Scanner content = new Scanner(scanner.nextLine());
			content.useDelimiter("-");
			String heading = content.next();
			System.out.println(heading);
			content = new Scanner(content.next());
			content.useDelimiter(",");
			switch(heading)
			{
				case "BaseStrength":
					baseStrength = Integer.parseInt(content.next());
					finalStrength = baseStrength;
					break;
				case "Prey":
					while(content.hasNext())
						prey.add(content.next());
					break;
				case "OtherPrey":
					otherPrey = Integer.parseInt(content.next());
					break;
				case "Predator":
					while(content.hasNext())
						predators.add(content.next());
				case "Equip":
					while(content.hasNext())
						equips.add(content.next());
			}
		}
	}
	
	public void printInfo()
	{
		System.out.println("Base Strength: " + baseStrength);
		System.out.print("Prey: ");
		for(int i=0; i<prey.size(); i++)
			System.out.print((i+1 == prey.size()) ? prey.get(i) : prey.get(i)+", ");
		System.out.println("\nOther prey: " + otherPrey);
		System.out.print("Predator: ");
		for(int i=0; i<predators.size(); i++)
			System.out.print((i+1 == predators.size()) ? predators.get(i) : predators.get(i)+", ");
		System.out.print("\nEquips: ");
		for(int i=0; i<equips.size(); i++)
			System.out.print((i+1 == equips.size()) ? equips.get(i) : equips.get(i)+", ");
	}
	
	public ArrayList<String> getEquips()
	{
		return equips;
	}
	
	public ArrayList<String> getPredators()
	{
		return predators;
	}
	
	public ArrayList<String> getPrey()
	{
		return prey;
	}
	
	public double getFinalStrength()
	{
		return finalStrength;
	}
	
	public void addFatigue(int fatigueFactor)
	{
		finalStrength -= (100*fatigueFactor);
	}
	
	public void addEquip(int positiveBonus)
	{
		finalStrength += positiveBonus;
	}

	@Override
	public boolean isStackable() 
	{
		return true;
	}
}
