package main;
import controller.Controller;

public class Main {

	public static void main(String[] args) 
	{
		Controller controler = new Controller();
		try 
		{
			String st1 = "mexico-Entertainment";
			String st2 = "mexico-Entertainment";
			System.out.println(st1.compareTo(st2));
			controler.run();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
