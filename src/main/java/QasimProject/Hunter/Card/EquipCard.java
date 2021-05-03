package QasimProject.Hunter.Card;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import QasimProject.Hunter.Constants;
import QasimProject.Hunter.Placeholders.Placeholder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

/*
 * EquipCard extends Card and is involved with representing equip cards in the game
 */

public class EquipCard extends Card{

	private int positiveBonus;

	public EquipCard(GraphicsContext gc, double x, double y, String owner, String equip) 
	{
		super(gc, x, y, owner, equip);
		try {
			String sourceDir = Constants.ABSOLUTE_PATH+"Equip/" + equip + "/";
			String imgSourceDir = sourceDir + equip + "C.png";
			System.out.println(imgSourceDir);
			String textSourceDir = sourceDir + equip + ".txt";
			System.out.println(imgSourceDir);
			scanner = new Scanner(new File(textSourceDir));
			imageInputStream = new FileInputStream(imgSourceDir);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getCardType() 
	{
		return "Equip";
	}

	public boolean isPlayable() 
	{
		return false;
	}
	
	@Override
	public void readCardInfo()
	{
		scanner.useDelimiter("-");
		scanner.next();
		positiveBonus = scanner.nextInt();
	}
	
	//sets text on the card based on the positive bonus assigned to this instance
	@Override
	public void setText()
	{
		text.setFont(new Font(36));
		text.setStyle("-fx-fill: green; -fx-stroke: darkgreen; -fx-stroke-width: 1px");
		text.setText(""+positiveBonus);
		text.setX(rect.getX() +15);
		text.setY(rect.getY() + Constants.CARD_HEIGHT -30);
	}

	public int getPositiveBonus()
	{
		return positiveBonus;
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
