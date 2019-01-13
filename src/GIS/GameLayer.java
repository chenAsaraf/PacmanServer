package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GameLayer extends GISlayer  {

	private ArrayList<GIS_element> Layer;
	
	/**
	 * Constructor empty
	 */
	public GameLayer() {
		Layer = new ArrayList<>();
	}
	
	@Override
	public boolean add(GIS_element arg0) {
		if(Layer.contains(arg0))
			return false;
		else{ 
			Layer.add(arg0);
			return true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		return false;
	}

	/**
	 * Delete all the Geom elements in this layer
	 */
	@Override
	public void clear() {
		Layer.clear();

	}

	@Override
	public boolean contains(Object arg0) {
		return Layer.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return Layer.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return this.Layer.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		Layer.remove(arg0);
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
		return this.Layer.size();
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
		if(!Layer.isEmpty()){
			Iterator<GIS_element> i = Layer.iterator();
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

	
	public GIS_element indexAt(int i){
		return this.Layer.get(i);
	}

	public ArrayList<GIS_element> getPackmans(){
		return this.Layer;
	}
}
