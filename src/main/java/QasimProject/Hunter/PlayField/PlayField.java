package QasimProject.Hunter.PlayField;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class PlayField {

	private Biome biome;
	private PlayFieldDisplay pfd;
	
	public PlayField(Biome biome, PlayFieldDisplay pfd)
	{
		this.biome = biome;
		this.pfd = pfd;
	}
	
	public void startDisplay() throws FileNotFoundException
	{
		biome.setBackgrounds();
	}
	
	public void initialiseBackground() throws FileNotFoundException
	{
		pfd.showBackground();
	}
	
	public void refreshBackground()
	{
		pfd.updateBackground();
	}
}
