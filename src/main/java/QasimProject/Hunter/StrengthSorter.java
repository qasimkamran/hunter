package QasimProject.Hunter;

import java.util.Comparator;

import QasimProject.Hunter.Card.AnimalCard;

public class StrengthSorter implements Comparator<AnimalCard>{

	@Override
	public int compare(AnimalCard o1, AnimalCard o2) 
	{
		Double o1FS = o1.getFinalStrength();
		Double o2FS = o2.getFinalStrength();
		return o1FS.compareTo(o2FS);
	}

	
	
}
