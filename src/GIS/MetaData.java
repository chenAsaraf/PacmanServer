package GIS;


import Geom.Point3D;

/**
 * this class keeps the meta data of the csv files 
 * @author Inna and Chen
 *
 */
public class MetaData implements Meta_data {

	private long utc = 0;

	//constructor
	public MetaData(long utc){
		this.utc = utc;
	}

	@Override
	public long getUTC() {
		return this.utc;
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}

	public String toString(){
		return "the UTC of this metaData is: " + this.getUTC();
	}

}