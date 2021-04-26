package QasimProject.Hunter.PlayField;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PlayFieldDisplay {
	
	private Biome biome;
	private GraphicsContext gc;
	
	public PlayFieldDisplay(Biome biome, GraphicsContext gc)
	{
		this.gc = gc;
		this.biome = biome;
	}

	public void showBackground()
	{
		Image background = biome.getRandomBackground();
		gc.drawImage(background, 0, 0);
	}
	
	public void updateBackground()
	{
		gc.drawImage(biome.getBackground(), 0, 0);
	}
}
