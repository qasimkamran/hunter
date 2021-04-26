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
	private Canvas canvas;
	
	public PlayFieldDisplay(Biome biome, Canvas canvas)
	{
		this.canvas = canvas;
		this.biome = biome;
	}

	public void showBackground()
	{
		GraphicsContext backgroundGC = canvas.getGraphicsContext2D();
		Image background = biome.getRandomBackground();
		backgroundGC.drawImage(background, 0, 0);
	}
}
