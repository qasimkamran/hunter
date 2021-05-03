package QasimProject.Hunter.Card;

import QasimProject.Hunter.GameObject;
import javafx.scene.canvas.GraphicsContext;

public interface CardFactoryIF {
	
	public GameObject initialiseCard(String cardType, String cardName, int zone);

}
