package QasimProject.Hunter.PlayField;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class PlayField {

	private Biome biome = new Biome();
	
	private PlayFieldDisplay pfd;
	
	public void startDisplay(Canvas canvas) throws FileNotFoundException
	{
		biome.setBackgrounds();
		pfd = new PlayFieldDisplay(biome, canvas);
	}
	
	public void initialiseBackground() throws FileNotFoundException
	{
		pfd.showBackground();
	}	
}
