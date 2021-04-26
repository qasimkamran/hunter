package QasimProject.Hunter.Graveyard;

public class GraveyardController {

	private Graveyard graveyard;
	private GraveyardDisplay graveyardDisplay;
	
	public GraveyardController(Graveyard graveyard, GraveyardDisplay graveyardDisplay)
	{
		this.graveyard = graveyard;
		this.graveyardDisplay = graveyardDisplay;
	}
	
	public void initialiseGraveyard()
	{
		graveyard.setPosition();
		graveyard.setCounter();
		graveyardDisplay.displayGraveyard();
	}
}
