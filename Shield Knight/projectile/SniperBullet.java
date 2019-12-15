package projectile;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import src.Hero;

//test
public class SniperBullet {
	private static Vector2f position;
	private Vector2f speed;
	private int life = 0;
	public static Vector2f getPosition() {
		return position;
	}

	public static void setPosition(Vector2f position) {
		SniperBullet.position = position;
	}

	public Vector2f getSpeed() {
		return speed;
	}

	public void setSpeed(Vector2f speed) {
		this.speed = speed;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public static Circle getBullet() {
		return bullet;
	}

	public static void setBullet(Circle bullet) {
		SniperBullet.bullet = bullet;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	private int maxLife = 2000;
	private boolean active = true;
	static Circle bullet;

	public SniperBullet(Vector2f position, Vector2f speed) {
		this.position = position;
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}

	public SniperBullet() {
		active = false;
	}

	public void init(int posX, int posY) {
		// TODO Auto-generated method stub
		bullet = new Circle(200, 20, 12);
		position = new Vector2f(posX, posY);
		

	}

	public void render(GameContainer gc, Graphics g) {
		if (active) {
			g.setColor(Color.black);
			g.fillOval(position.getX() - 10, position.getY() - 10, 20, 20);
			g.setColor(Color.red);
			g.draw(bullet);
		}
	}

	public void update(int delta, float vitesse) {
		// TODO Auto-generated method stub

		if (active) {
			Vector2f realSpeed = speed.copy();
			realSpeed.scale((delta / vitesse));
			position.add(realSpeed);
			bullet.setLocation(position.getX() - 15, position.getY() - 10);
			life += delta;
			if (life > maxLife) {
				active = false;
			}
		}
	}

	public boolean isActive() {
		return active;
	}

	public static boolean isHit() {
		boolean hit = false;
		if (Hero.getHeroHitBox().intersects(bullet)) {
			hit = true;

		}
		return hit;
	}
}