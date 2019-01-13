package GameEntities;

import java.util.Comparator;

public class Packman_comperator implements Comparator<Packman>{

	/**Compare between 2 Packman by their speed
	 * @return negative number if  the first one is bigger
	 * @return positive number if the second one is bigger
	 * @return 0 if equals
	 */
	@Override
	public int compare(Packman pac1, Packman pac2) {
		double speedPac1 = pac1.getSpeed();
		double speedPac2 = pac2.getSpeed();
		return ((int)-(speedPac1-speedPac2));		
	}

	

}
