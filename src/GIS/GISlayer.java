package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
* represent a layer includes the elements we created in GISelement
* @author Inna and Chen
*
*/
public class GISlayer implements GIS_layer {

	private ArrayList<GIS_element> layer;


	/**
	 * Constructor empty
	 */
	public GISlayer() {
		layer = new ArrayList<>();
	}

	/**
	 * Add a point to the layer
	 * @param arg0 GIS_element
	 * @return boolean
	 */
	@Override
	public boolean add(GIS_element arg0) {
		if(layer.contains(arg0))
			return false;
		else{ 
			layer.add(arg0);
			return true;
		}
	}

	/**
	 * Add a collection of points (representing by Geom elements)  to the layer
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		return false;
	}

	/**
	 * delete all the Geom elements in this layer
	 */
	@Override
	public void clear() {
		layer.clear();

	}

	@Override
	public boolean contains(Object arg0) {
		return layer.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return layer.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return this.layer.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		layer.remove(arg0);
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public int size() {
		return this.size();
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	@Override
	public Meta_data get_Meta_data() {
		return null;
	}

	public String toString(){
		if(!layer.isEmpty()){
			Iterator<GIS_element> i = layer.iterator();
			GIS_element g = i.next();
			String ans = g.toString()+"\n";
			while(i.hasNext()){
				g = i.next();
				ans = ans + "," + g.toString() +"\n";
			}
			return ans;
		}
		return null;
	}
	
	public GISlayer getLayer(){
		return this;
	}
}