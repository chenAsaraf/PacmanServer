package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * this class saves all the GIS layers into one project as if its a merged csv files
 * @author Inna and Chen
 *
 */
public class GISproject implements GIS_project{
	
	private ArrayList<GIS_layer> project;

	/**
	 * Constructor empty
	 */
	public GISproject() {
		project = new ArrayList<>();
	}
	
	/**
	 * Add a layer to the project
	 * @param e GIS_layer
	 * @return boolean
	 */
	@Override
	public boolean add(GIS_layer e) {
		if(project.contains(e))
			return false;
		else{ 
			project.add(e);
			return true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return false;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return this.project.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public Meta_data get_Meta_data() {
		return null;
	}

	public String toString(){
		if(!project.isEmpty()){
			Iterator<GIS_layer> i = project.iterator();
			GIS_layer g = i.next();
			String ans = g.toString()+"\n";
			while(i.hasNext()){
				g = i.next();
				ans = ans + g.toString() +"\n";
			}
			return ans;
		}
		return null;
	}
}