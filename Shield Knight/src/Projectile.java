package src;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
//test
public class Projectile{
	private static Vector2f position;
	private Vector2f speed;
	private int life=0;
	private int maxLife = 2000;
	private boolean active = true;
	static Circle bullet;
	public Projectile(Vector2f position, Vector2f speed) {
		this.position = position;
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}
	public Projectile() {
		active = false;
	}
	
	public void init(int posX,int posY) {
		// TODO Auto-generated method stub
		bullet = new Circle(20,20, 12);
		position = new Vector2f(posX,posY);
		
	}
	public void render(GameContainer gc, Graphics g){
		if(active) {
		g.setColor(Color.black);
		g.fillOval(position.getX()-10, position.getY()-10, 20, 20);
		g.setColor(Color.red);
		g.draw(bullet);
		}
		
	}



	public void update(int delta,float vitesse) {
		// TODO Auto-generated method stub

		if(active) {
			Vector2f realSpeed = speed.copy();
			realSpeed.scale((delta/vitesse));
			position.add(realSpeed);
			bullet.setLocation(position.getX()-15, position.getY()-10);
			life += delta;
			if(life > maxLife) {
				active = false;
			}
		}
	}
	public boolean isActive() {
		return active;
	}
	
	public static boolean isHit() {
		boolean hit = false;
		if(Hero.heroHitBox.intersects(bullet)) {
			hit = true;
			
		}
		return hit;
	}
}
