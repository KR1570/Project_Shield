package src;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
//test
public class Projectile{
	private Vector2f position;
	private Vector2f speed;
	private int life=0;
	private int maxLife = 2000;
	private boolean active = true;
	public Projectile(Vector2f position, Vector2f speed) {
		this.position = position;
		this.speed = speed;
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}
	public void render(GameContainer gc, Graphics g){
		g.setColor(Color.black);
		g.fillOval(position.getX()-10, position.getY()-10, 20, 20);
		
	}



	public void update(int delta) {
		// TODO Auto-generated method stub
		if(active) {
			Vector2f realSpeed = speed.copy();
			realSpeed.scale((delta/1000.0f));
			position.add(realSpeed);
			
			life += delta;
			if(life > maxLife) {
				active = false;
			}
		}
	}
	public boolean isActive() {
		return active;
	}
	

}
