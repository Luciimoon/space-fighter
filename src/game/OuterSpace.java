package game;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;

import static java.lang.Character.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class OuterSpace extends Canvas implements KeyListener, Runnable {
	private Ship ship;
	private Ammo a;
	private boolean fired;
	private boolean lazer;
	private boolean alien;
	private boolean af1;
	private boolean af2;
	private boolean game;
	private AlienHorde horde;
	private Bullets shots;
	private int alienSpeed;
	private int round;

	private Random ran;
	private int r;
	private int g;
	private int b;

	private boolean[] keys;
	private BufferedImage back;




    public OuterSpace() {
		setBackground(Color.black);
		keys = new boolean[5];
		ship = new Ship(350, 400, 100, 100, 3);
		round = 1;

		alienSpeed = 1;
		alien = true;
		af1 = false;
		af2 = false;
		game = true;
		lazer = false;
		ran = new Random();
		a = new Ammo((ship.getX() + ship.getWidth() / 2) - 5, ship.getY() - 5, 5);
		horde = new AlienHorde(11);
		shots = new Bullets();
		this.addKeyListener(this);
		new Thread(this).start();
		setVisible(true);
	}

	public void update(Graphics window) {
		paint(window);
	}

	public void paint(Graphics window) {
		if (alien == true) {
			horde.generateHorde(115, 35, 40, 40, alienSpeed);
			alien = false;
		}
		Graphics2D twoDGraph = (Graphics2D) window;
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		Graphics graphToBack = back.createGraphics();
		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50);
		graphToBack.setColor(new Color(51, 204, 255));
		graphToBack.fillRect(0, 0, 800, 600);
		if (!horde.endGame(ship) || round == 0) {
			if (keys[0] == true) {
				if (ship.getX() > 0 - (ship.getWidth() / 2) + 2) {
					ship.move("LEFT");
				}
			}
			if (keys[1] == true) {
				if (ship.getX() < 800 - (ship.getWidth() / 2) - 18) {
					ship.move("RIGHT");
				}
			}
			if (keys[2] == true) {
				if (ship.getY() > 0 - 18) {
					ship.move("UP");
				}
			}
			if (keys[3] == true) {
				if (ship.getY() < 600 - ship.getHeight() / 2 - 50) {
					ship.move("DOWN");
				}
			}

			if (keys[4] == true && fired == true) {
				shots.add(new Ammo((ship.getX() + ship.getWidth() / 2) - 5,
						ship.getY() - 5, 5));

			}
			shots.draw_bullets(graphToBack);
			shots.move_bullets();
			horde.draw_aliens(graphToBack);
			ship.draw(graphToBack);
			horde.move_aliens();
			horde.aliens_shot(shots);
			shots.clean_bullets();
			if (horde.getSize() == 0) {
				if (af1 && af2) {
					r = ran.nextInt(1);
					g = ran.nextInt(256);
					// b = ran.nextInt(256-50)+50;
					alien = false;
					graphToBack.setColor(new Color(r, g, 255));
					graphToBack.setFont(new Font(Font.SANS_SERIF, 250, 250));
					graphToBack.drawString("WARP", 25, 300 - 40);
					graphToBack.setFont(new Font(Font.SANS_SERIF, 220, 220));
					graphToBack.drawString("SPEED", 25, 510 - 40);
				} else {
					alien = true;
					alienSpeed++;
				}
				round++;

			}
		} else {
			graphToBack.setColor(Color.magenta);
			graphToBack.setFont(new Font(Font.MONOSPACED, 150, 150));
			graphToBack.drawString("you", 250, 300 - 40);
			graphToBack.drawString("ded", 250, 510 - 40);
			shots.end();
			shots.draw_bullets(graphToBack);
			horde.draw_aliens(graphToBack);
			ship.draw(graphToBack);
		}
		graphToBack.setFont(new Font(Font.SANS_SERIF, 24, 24));
		graphToBack.setColor(Color.WHITE);
		if (round == 0) {
			graphToBack.drawString("Warm-Up", 335, 30);
			horde.setScore(0);
			if (horde.endGame(ship)) {
				graphToBack.setFont(new Font(Font.SANS_SERIF, 50, 50));
				graphToBack.drawString("DON'T GET HIT", 60, 300);
			}
		} else {
			graphToBack.setFont(new Font(Font.SANS_SERIF, 24, 24));
			graphToBack.drawString("SCORE: " + horde.getScore(), 335, 30);
			//graphToBack.drawString("LAZER AMMO: " + lazerAmmo, 550, 30);
		}
		graphToBack.setFont(new Font(Font.SANS_SERIF, 24, 24));
		graphToBack.drawString("ROUND " + round, 15, 30);
		twoDGraph.drawImage(back, null, 0, 0);
		if (lazer == false) {
			fired = false;
		}
		back = null;

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			fired = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_4) {
			af1 = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			af2 = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_V) {
			lazer = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_4) {
			af1 = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			af2 = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_V) {
			lazer = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {
		switch (toUpperCase(e.getKeyChar())) {
		case ' ':
			keys[4] = true;
			break;
		}
	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		} catch (Exception e) {
		}
	}
}