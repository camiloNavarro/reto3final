package model.logic;

public class HashValue implements Comparable<HashValue>{
	
	private String nombre;
	
	private float value;
	
	public HashValue(String n, float v)
	{
		nombre = n;
		value = v;
	}
	
	public String getNom(){
		return nombre;
	}
	
	public float getVal(){
		return value;
	}

	@Override
	public int compareTo(HashValue o) {
		// TODO Auto-generated method stub
		return nombre.compareToIgnoreCase(o.getNom());
	}
	
}
