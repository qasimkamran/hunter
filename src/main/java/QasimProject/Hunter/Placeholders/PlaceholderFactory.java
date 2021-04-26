package QasimProject.Hunter.Placeholders;

import QasimProject.Hunter.GameObject;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;

public class PlaceholderFactory implements PlaceholderFactoryIF{
	
	private String owner;
	
	private Placeholder placeholder = null;
	
	private GraphicsContext gc;

	public PlaceholderFactory(GraphicsContext gc, String owner) 
	{
		this.gc = gc;
		this.owner = owner;
	}

	@Override
	public Placeholder initialisePlaceholder(String type, int zone)
	{
		if(type.equals("Animal"))
			return placeholder = new AnimalPlaceholder(gc, owner, zone);
		else
			return null;
	}
	
	public String getOwner() {
		return owner;
	}
}
