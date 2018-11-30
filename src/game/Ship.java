package game;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;

	public Ship()
	{
		this(200,200,100,100,5);
	}

	public Ship(int x, int y)
	{
	   this(x,y,100,100,5);
	}

	public Ship(int x, int y, int s)
	{
		this(x,y,100,100,s);
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s; 
		try
		{
			URL url = getClass().getResource("images/ship.png");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println("file error");
		}
	}


	public void setSpeed(int s)
	{
		speed=s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void move(String direction)
	{	
		if(direction.equals("RIGHT"))
		{
			setX(getX() + speed);
			//setY(getY() + speed);
		}
		if(direction.equals("LEFT"))
		{
			setX(getX() - speed);
			//setY(getY() + speed);
		}
		if(direction.equals("UP"))
		{
			//setX(getX() - speed);
			setY(getY() - speed);
		}
		if(direction.equals("DOWN"))
		{
			//setX(getX() - speed);
			setY(getY() + speed);
		}
	}

	public void draw( Graphics window )
	{
		//System.out.println("drawing");
		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}
	
	public boolean didCollide(Alien al) {
		if (getX() + getWidth()-30 >= al.getX() && getX() <= al.getX() + al.getWidth()-30
				&& getY() + getHeight()-30 >= al.getY()
				&& getY() <= al.getY() + al.getHeight()-30) {
			return true;
		} else {
			return false;
		}
	}

	public String toString()
	{
		return super.toString() +" "+ getSpeed();
	}
}