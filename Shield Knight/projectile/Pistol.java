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

import src.Bouclier;
import src.Enemy;
import src.Hero;

enum Direction {
	UP, DOWN, LEFT, RIGHT
}

public class Pistol {

	private int time = 0;
	private Hero hero;
	private float pistolPosX;
	private float pistolPosY;
	private Rectangle radius;
	private Rectangle pistolero;
	private float pistoleroPosX;
	private float pistoleroPosY;
	public static Circle bullet;
	public static Circle counter;
	private boolean hitBouclier = false;
	private Direction direction = Direction.LEFT;
	private boolean exist = false;
	private boolean active = true;
	private Bouclier bouclier;
	public Circle getBullet() {
		return bullet;
	}



	public Pistol(float pistolPosX, float pistolPosY) {
		this.pistolPosX = pistolPosX;
		this.pistolPosY = pistolPosY;

	}

	public Pistol() {

	}
//-------------------------------------------------------INIT------------------------------------------------------------------

	public void init() {
		bouclier = new Bouclier();	
		hero = new Hero();
		pistolPosX = 1200;
		pistolPosY = 520;
		pistoleroPosX = 1200;
		pistoleroPosY = 500;
		pistolero = new Rectangle(pistoleroPosX, pistoleroPosY, 25, 50);
		bullet = new Circle(pistolPosX,pistolPosY,10,10);

	}
//-------------------------------------------------------RENDER------------------------------------------------------------------

	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.black);
		//g.draw(bullet);
		g.draw(pistolero);
		g.draw(getRadius(direction));
		if(exist) {
		g.fill(bullet);
		}

	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta) {
		Input input = gc.getInput();
		if (getRadius(direction).intersects(hero.getHeroHitBox())) {

			if(active) {
				exist = true;
				time+=delta;
			}


			
			switch (direction) {
			case RIGHT:
				if(false) {
					System.out.println("intersect");
					pistolPosX-=5;
					getBullet().setX(pistolPosX);
				}
				else {
				pistolPosX+=5;
				getBullet().setX(pistolPosX);
				}
				break;
			case LEFT:
				if(bouclier.isHit()==true) {
					time=0;
					hitBouclier = true;
				}
				else if((bullet.intersects(pistolero)&&  hitBouclier == true)){
					hitBouclier = false;
				}
					 if(hitBouclier == true){
						pistolPosX+=5;
						getBullet().setLocation(pistolPosX,pistolPosY);
						
					}
					else if(time >2000 &&  hitBouclier == true) {
							hitBouclier = false;
							pistolPosX =1200;
							time = 0;
						}
				
				else if(time >2000 &&  hitBouclier == false) {
					pistolPosX =1200;
					time = 0;
				}
				if(hitBouclier == false) {
				pistolPosX-=5;
				getBullet().setLocation(pistolPosX,pistolPosY);
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

