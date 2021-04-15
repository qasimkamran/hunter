package QasimProject.Hunter.Placeholders;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Light.Point;

public class PlaceholderFactory{
	
	private String owner;
	
	private Placeholder placeholder = null;

	public PlaceholderFactory(String owner) 
	{
		this.owner = owner;
	}

	public Placeholder initialisePlaceholder(String type, int zone)
	{
		if(type.equals("Animal"))
		{
			return placeholder = new AnimalPlaceholder(owner, zone);
		}
		else
			return null;
	}
	
	public String getOwner() {
		return owner;
	}
}
