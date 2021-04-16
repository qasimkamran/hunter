package QasimProject.Hunter.PlayField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import QasimProject.Hunter.GameObject;
import javafx.scene.image.Image;

public class Biome{

	private ArrayList<Image> backgrounds = new ArrayList<Image>();
	
	private Image currentBackground;
	
	public void setBackgrounds() throws FileNotFoundException
	{
		FileInputStream imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\Backgrounds\\Mountains.png");
		Image mountain = new Image(imageInputStream, 1280, 720, false, false);
		imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\Backgrounds\\Swamp.png");
		Image swamp = new Image(imageInputStream, 1280, 720, false, false);
		imageInputStream = new FileInputStream("D:\\Uni Docs\\Design Patterns\\Assignment\\Images\\Backgrounds\\Desert.png");
		Image desert = new Image(imageInputStream, 1280, 720, false, false);
		
		backgrounds.add(desert);
		backgrounds.add(swamp);
		backgrounds.add(mountain);
	}
	
	public Image getRandomBackground()
	{
		int random = (int) (Math.random() * backgrounds.size());
		return currentBackground = backgrounds.get(random);
	}
	
	public Image getBackground()
	{
		return currentBackground;
	}
}
