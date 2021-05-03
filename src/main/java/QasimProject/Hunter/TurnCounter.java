package QasimProject.Hunter;

/*
 * this class models a turn counter, keeping track of the phase and turns in the game
 */

public class TurnCounter {
	
	private boolean p1Turn;
	
	private String phase = "Draw";

	private int phaseCount = 0;
	
	public void incrementPhaseCount()
	{
		phaseCount++;
	}
	
	public void switchTurn()
	{
		if(p1Turn == true)
			p1Turn = false;
		else
			p1Turn = true;
	}
	
	public int getPhaseCount()
	{
		return phaseCount;
	}
	
	public void setPhase()
	{
		switch(phaseCount)
		{
			case 0:
				phase = "Draw";
				break;
			case 1:
				phase = "Set";
				break;
			case 2:
				phase = "Stack";
				break;
			case 3:
				phase = "Engage";
				break;
			case 4:
				phase = "End";
				endTurn();
				break;
		}
	}
	
	public void endTurn()
	{
		phaseCount = -1;
		switchTurn();
	}
	
	public String getPhase()
	{
		return phase;
	}
}
