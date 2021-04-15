package QasimProject.Hunter.Placeholders;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class PlaceholderController {

	private PlaceholderFactory factoryCPU;
	private PlaceholderFactory factoryP1;
	
	private PlaceholderDisplay view;
	
	private Placeholder phAC1, phAC2, phAC3;
	
	public static ArrayList<Placeholder> phACRectList = new ArrayList<>();
	public static ArrayList<Placeholder> phAPRectList = new ArrayList<>();

	private Placeholder phAP1, phAP2, phAP3;
	
	public PlaceholderController(PlaceholderFactory factoryCPU, PlaceholderFactory factoryP1, PlaceholderDisplay view)
	{
		this.factoryCPU = factoryCPU;
		this.factoryP1 = factoryP1;
		this.view = view;
	}
	
	public void initialiseEmptyPlaceholders()
	{
		phAC1 = factoryCPU.initialisePlaceholder("Animal", 1);
		view.displayPlaceholder(phAC1.getPlaceholderRectangle());
		phAC1.setCollisionDetectionRectangle();
		view.setCollisionRectangle(phAC1.getCollisionDetectionRectangle());
		phACRectList.add(phAC1);
		
		phAC2 = factoryCPU.initialisePlaceholder("Animal", 2);
		view.displayPlaceholder(phAC2.getPlaceholderRectangle());
		phAC2.setCollisionDetectionRectangle();
		view.setCollisionRectangle(phAC2.getCollisionDetectionRectangle());
		phACRectList.add(phAC2);
		
		phAC3 = factoryCPU.initialisePlaceholder("Animal", 3);
		view.displayPlaceholder(phAC3.getPlaceholderRectangle());
		phAC3.setCollisionDetectionRectangle();
		view.setCollisionRectangle(phAC3.getCollisionDetectionRectangle());
		phACRectList.add(phAC3);
		
		phAP1 = factoryP1.initialisePlaceholder("Animal", 1);
		view.displayPlaceholder(phAP1.getPlaceholderRectangle());
		phAP1.setCollisionDetectionRectangle();
		view.setCollisionRectangle(phAP1.getCollisionDetectionRectangle());
		phAPRectList.add(phAP1);
		
		phAP2 = factoryP1.initialisePlaceholder("Animal", 2);
		view.displayPlaceholder(phAP2.getPlaceholderRectangle());
		phAP2.setCollisionDetectionRectangle();
		view.setCollisionRectangle(phAP2.getCollisionDetectionRectangle());
		phAPRectList.add(phAP2);
		
		phAP3 = factoryP1.initialisePlaceholder("Animal", 3);
		view.displayPlaceholder(phAP3.getPlaceholderRectangle());
		phAP3.setCollisionDetectionRectangle();
		view.setCollisionRectangle(phAP3.getCollisionDetectionRectangle());
		phAPRectList.add(phAP3);
	}
	
}
