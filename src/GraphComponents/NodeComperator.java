package GraphComponents;

import java.util.Comparator;

public class NodeComperator implements Comparator<Node>{

	@Override
	public int compare(Node node1, Node node2) {
		/**Compare between 2 nodes by there distance from source node
		 * @return positive number if  the first one is bigger
		 * @return negative number if the second one is bigger
		 * @return 0 if equals
		 */
		double distance1 = node1.getDistance();
		double distance2 = node2.getDistance();
		return ((int)(distance1-distance2));
	}

}
