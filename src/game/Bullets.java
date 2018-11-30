package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets {
	private ArrayList<Ammo> ammo;

	public Bullets() {
		ammo = new ArrayList<Ammo>(0);
	}

	public void add(Ammo a) {
		ammo.add(a);
	}

	public void drawEmAll(Graphics window) {
		if (ammo.size() > 0) {
			for (Ammo a : ammo) {
				a.draw(window);
			}
		}
	}

	public void moveEmAll() {
		if (ammo.size() > 0) {
			for (Ammo a : ammo) {
				a.move("UP");
			}
		}
	}

	public void cleanEmUp() {
		if (ammo.size() > 0) {
			for (int i = 0; i < ammo.size(); i++) {
				if (!ammo.get(i).isAlive()) {
					ammo.remove(i);
					i = 0;
				}
			}
		}
	}

	public Ammo getAmmo(int pos) {
		return ammo.get(pos);
	}

	public List<Ammo> getList() {
		return ammo;
	}

	public int getSize() {
		return ammo.size();
	}
	
	public void end()
	{
		if (ammo.size() > 0) {
			for (int i = 0; i < ammo.size(); i++) {
				ammo.get(i).setSpeed(0);
			}
		}
	}

	public String toString() {
		return "";
	}
}