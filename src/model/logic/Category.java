package model.logic;


public class Category implements Comparable<Category>
{
	private String categoryID;
	private String categoryName;
	
	public Category (String pID, String pName)
	{
		categoryID = pID;
		categoryName = pName;
	}
	
	public String getCategoryID()
	{
		return categoryID;
	}
	
	public String getCategoryName()
	{
		return categoryName;
	}
	
	public int compareTo(Category otro) 
	{
		return this.getCategoryName().compareTo(otro.getCategoryName()); 
	}

}
