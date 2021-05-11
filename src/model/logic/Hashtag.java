package model.logic;

public class Hashtag  implements Comparable<Hashtag>{
	
	private String id;
	private String nom;
	
	public Hashtag(String i, String n)
	{
		id=i;
		nom=n;
	}
	
	public String getid()
	{
		return id;
	}
	
	public String getnom()
	{
		return nom;
	}

	@Override
	public int compareTo(Hashtag o) {
		// TODO Auto-generated method stub
		return nom.compareToIgnoreCase(o.getnom());
	}
}
