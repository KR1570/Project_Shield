/*package projectile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import src.Enemy;
import src.Hero;

public class Pistol {
	Rectangle Pistol;
	private Projectile[] projectiles;
	private Projectile p;
	private int fireRate = 1000;
	private int current = 0;
	private int time = 0;
	private Hero hero;
	private Line laser;
	static Circle range;
	private float X;
	private float Y;
	private boolean counter;
	
	public Pistol(int PistolHp,int PistolPosX,int PistolPosY) {
		
		init();
	}
	public Pistol() {
		
	}
//-------------------------------------------------------INIT------------------------------------------------------------------

	public void init() {
		projectiles = new Projectile[8];
		for(int i = 0; i < projectiles.length; i++) {
			projectiles[i]= new Projectile();
		}
		
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------
	
	public  void render(GameContainer gc, Graphics g) {
		for(Projectile p : projectiles) {
			p.render(gc, g);
		}
	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta) {
		//calcul le vecteur vers le joueur
		time += delta;
		if(time > fireRate && gc.getInput().isKeyDown(Input.KEY_1)) {
			projectiles[current] = new Projectile(new Vector2f(0,0),new Vector2f(500,100));
			current++;
			if(current >= projectiles.length) {
				current = 0 ;
				time=0;
			}
		}
		Input input = gc.getInput();
		for(Projectile p : projectiles) {
			p.update(delta);
		}
	
	}
	}*/
package projectile;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import src.Enemy;
import src.Hero;

enum Direction {
	UP, DOWN, LEFT, RIGHT
}

public class Pistol {
	Rectangle sniper2;
	private Pistol[] pistol;
	private int fireRate = 1000;
	private int current = 0;
	private int time = 0;
	private Hero hero;
	private float pistolPosX;
	private float pistolPosY;
	private Rectangle radius;
	private Rectangle pistolero;
	private float pistoleroPosX;
	private float pistoleroPosY;
	private Circle[] bullet;
	private Direction direction = Direction.LEFT;
	private boolean exist = false;
	private boolean active = true;

	public Pistol(float pistolPosX, float pistolPosY) {
		this.pistolPosX = pistolPosX;
		this.pistolPosY = pistolPosY;

	}

	public Pistol() {

	}
//-------------------------------------------------------INIT------------------------------------------------------------------

	public void init() {
		pistol = new Pistol[3];
		
		pistolPosX = 1200;
		pistolPosY = 500;
		pistoleroPosX = 1200;
		pistoleroPosY = 500;
		pistolero = new Rectangle(pistoleroPosX, pistoleroPosY, 25, 50);
		bullet = new Circle[8];

	}
//-------------------------------------------------------RENDER------------------------------------------------------------------

	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.black);
		//g.draw(bullet);
		g.draw(pistolero);
		g.draw(getRadius(direction));
		if(exist) {
		g.draw(bullet[current]);
		}

	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta) {
		System.out.println(time);
		Input input = gc.getInput();
		if (getRadius(direction).intersects(hero.getHeroHitBox()) || input.isKeyPressed(input.KEY_1)) {

			if(active) {
				bullet[current] = new Circle(pistoleroPosX,pistoleroPosY,20,20);
				exist = true;
				time+=delta;
			}
				if(current >= bullet.length) {
					current = 0;
					time=0;
				}

			
			switch (direction) {
			case RIGHT:
				pistolPosX+=5;
				bullet[current].setX(pistolPosX);

				break;
			case LEFT:
				if(time >2000) {
					pistolPosX =1200;
					time = 0;

				}
				else {
				pistolPosX-=5;
				bullet[current].setX(pistolPosX);
				}
				break;
			}
		}
		else {
			exist = false;
			pistolPosX =1200;
		}
	}

	public Rectangle getRadius(Direction direction) {
		switch (direction) {
		case RIGHT:

			radius = new Rectangle(pistoleroPosX - 5, pistoleroPosY, 50, 50);

			break;
		case LEFT:

			radius = new Rectangle(pistoleroPosX - 600, pistoleroPosY, 500, 50);

			break;
		}
		return radius;
	}

}

