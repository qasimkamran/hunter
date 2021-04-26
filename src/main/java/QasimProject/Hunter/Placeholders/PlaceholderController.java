package QasimProject.Hunter.Placeholders;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class PlaceholderController {

	private PlaceholderDisplay view;
	
	private PlaceholderFactory phACF, phAPF;
	
	public static ArrayList<Placeholder> placeholders = new ArrayList<>();
	
	public PlaceholderController(PlaceholderFactory phACF, PlaceholderFactory phAPF, PlaceholderDisplay view)
	{
		this.phACF = phACF;
		this.phAPF = phAPF;
		this.view = view;
	}
	
	public void initialisePlaceholderList()
	{
		if(placeholders.isEmpty()) {
			for(int i=1; i<4; i++)
			{
				placeholders.add(phACF.initialisePlaceholder("Animal", i));
				placeholders.add(phAPF.initialisePlaceholder("Animal", i));
			}
		}
	}
	
	public void initialiseEmptyPlaceholders()
	{
		for(Placeholder p : placeholders)
		{
			try {
				view.displayPlaceholder(p);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
