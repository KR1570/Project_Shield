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

import org.newdawn.slick.Animation;
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
//import src.Hero.Direction;
//import src.Hero.Direction;
enum Direction {
	RIGHT,LEFT
}
enum Action {
	SHOOT, DIE, WAIT
}

public class Pistol {
	private int time = 0;
	private Hero hero;
	private float pistolPosX;
	private float pistolPosY;
	private Rectangle radius;
	public static Rectangle pistolero;
	public static  float pistoleroPosX;
	public static float pistoleroPosY;
	public static Circle bullet;
	public static Circle counter;
	private boolean hitBouclier = false;
	private Direction direction = Direction.LEFT;
	private Action action = Action.WAIT;
	private Animation anim_SHOOT;
	private Animation anim_DIE;
	private Animation anim_WAIT;
	private boolean exist = false;
	private boolean active = true;
	private Bouclier bouclier;
	private Image pistoleroImage;
	private Animation getAnimation(int rowX, int rowY, int frames) {
		Animation anim = new Animation(false);
		for (int x = 0; x < rowX; x++) {
			anim.addFrame(pistoleroImage.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}
	/*private Animation getAnimation(int rowX, int rowY, int frames) {
		Animation anim = new Animation(false);
		for (int x = 0; x < rowX; x++) {
			anim.addFrame(pistoleroImage.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}*/
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

	public void init() throws SlickException {
		bouclier = new Bouclier();	
		hero = new Hero();
		pistolPosX = 26;
		pistolPosY = 16;
		pistoleroPosX = pistolPosX*32;
		pistoleroPosY = pistolPosY*32;
		pistolero = new Rectangle(pistoleroPosX*32, pistoleroPosY*32, 25, 50);
		bullet = new Circle(pistolPosX*32,pistolPosY*32,10,10);
		pistoleroImage = new Image("./Sprites/Space Cadet Sprite Sheet.png")/*.getFlippedCopy(true, false)*/;
		anim_SHOOT = getAnimation(5, 2,100);
		anim_DIE = getAnimation(8, 4,75);
		anim_WAIT = getAnimation(4, 0, 200);
	}
	
//-------------------------------------------------------RENDER------------------------------------------------------------------

	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.black);
		//g.draw(bullet);
		g.draw(pistolero);
		//g.draw(getRadius(direction));
		if(exist) {
		g.fill(bullet);
		getPistoleroImage(action).draw(pistoleroPosX*32-32, pistoleroPosY*32-34,84,84);
		}
	}
	
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta) {
		switch (action) {
		case SHOOT:
			anim_SHOOT.update(delta);
			break;
		case DIE :
			anim_DIE.update(delta);
			break;
		case WAIT :
			anim_WAIT.update(delta);
			break;

		}
		Input input = gc.getInput();
			if(active) {
				exist = true;
				time+=delta;
			}


			
			switch (direction) {
			//Droite
			case RIGHT:
				if(bouclier.isHitPistol()==true) {
					time=0;
					hitBouclier = true;
				}
				else if((bullet.intersects(pistolero)&&  hitBouclier == true)){
					hitBouclier = false;
				}
					 if(hitBouclier == true){
						pistolPosX-=5;
						getBullet().setLocation(pistolPosX,pistolPosY);
						action = Action.SHOOT;
						
					}
					else if(time >2000 &&  hitBouclier == true) {
							hitBouclier = false;
							pistolPosX =pistoleroPosX;
							time = 0;
						}
				
				else if(time >2000 &&  hitBouclier == false) {
					pistolPosX = pistoleroPosX;
					time = 0;
					action = Action.SHOOT;
				}
				if(hitBouclier == false) {
				pistolPosX+=5;
				getBullet().setLocation(pistolPosX,pistolPosY);
				}
				break;
			//Gauche
			case LEFT:
				if(bouclier.isHitPistol()==true) {
					time=0;
					hitBouclier = true;
				}
				else if((bullet.intersects(pistolero)&&  hitBouclier == true)){
					hitBouclier = false;
					action = Action.DIE;
				}
					 if(hitBouclier == true){
						pistolPosX+=5;
						getBullet().setLocation(pistolPosX,pistolPosY);
						
					}
					else if(time >2000 &&  hitBouclier == true) {
							hitBouclier = false;
							pistolPosX = pistoleroPosX;
							time = 0;
						}
				
				else if(time >2000 &&  hitBouclier == false) {
					pistolPosX = pistoleroPosX;
					time = 0;
					action = Action.SHOOT;
				}
				if(hitBouclier == false) {
				pistolPosX-=5;
				getBullet().setLocation(pistolPosX,pistolPosY);
				}
				break;
			}
		

	}
	public Animation getPistoleroImage(Action action) {
		Animation anim = new Animation(false);
		switch (action) {
			case SHOOT : 
				anim = anim_SHOOT;
				break;
			case DIE:
				anim = anim_DIE;
				break;
			case WAIT : 
				anim = anim_WAIT;
				break;
		}
		return anim;
	}



}

