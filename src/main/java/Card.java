import java.util.*;

public class Card{
	String color;
	boolean isdouble;
	boolean special;
	String specText;
	int specNum;
	
	public Card(String c, boolean d) //throws InvalidColorException
	{
		c = c.toLowerCase();
		if(!(c.equals("red") || c.equals("blue") || c.equals("orange") || c.equals("yellow") || c.equals("green")))
		{
			//throw new InvalidColorException("Color must be red, blue, green, yellow, or orange!");
			System.out.println("Color must be red, blue, green, yellow, or orange!");
			return;
		}
		color = c;
		isdouble = d;
		special = false;
		specText = null;
	}
	
	//1 = skip, 2 = middle
	//There's no reason you couldn't just use a string here,
	//but I used an int to further distinguish it from the other constructor
	public Card(int Num)
	{
		color = "none";
		isdouble = false;
		special = true;
		specNum = Num;
		switch(Num)
		{
			case 1: specText = "skip";
					break;
			case 2: specText = "middle";
					break;
			default:
					break;
		}
	}
}