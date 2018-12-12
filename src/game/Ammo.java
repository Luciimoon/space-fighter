package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing {
	private int speed;
	private boolean alive = true;

	public Ammo() {
		this(5, 5, 5);
	}

	public Ammo(int x, int y) {
		this(x, y, 5);
	}

	public Ammo(int x, int y, int s) {
		super(x, y);
		speed = s;
	}

	public void setSpeed(int s) {
		speed = s/2;
	}

	public int getSpeed() {
		return speed;
	}

	public void draw(Graphics window) {
		window.setColor(Color.blue);
		window.fillOval(getX(), getY(), 10, 10);
	}

	public void move(String direction) {
		if (direction.equals("RIGHT")) {
			setX(getX() + speed);

		}
		if (direction.equals("LEFT")) {
			setX(getX() - speed);

		}
		if (direction.equals("UP")) {
			// () - speed);
			setY(getY() - speed);
		}
		if (direction.equals("DOWN")) {
			setY(getY() + speed);
		}
	}

	public String toString() {
		return super.toString() + " " + getSpeed();
	}

	public boolean didCollide(Alien al) {
		if (getX() + 10 >= al.getX() && getX() <= al.getX() + al.getWidth()
				&& getY() - 10 >= al.getY()
				&& getY() <= al.getY() + al.getHeight()) {
			kill();
			return true;
		} else {
			return false;
		}
	}

	public boolean isAlive() {
		if (getY() < 0) {
			kill();
		}
		return alive;
	}

	public void kill() {
		alive = false;
	}
}