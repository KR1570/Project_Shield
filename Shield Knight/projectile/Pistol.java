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
	public static Direction direction = Direction.RIGHT;
	private Action action = Action.WAIT;
	public static int directionPistolero = 2;
	private Animation anim_SHOOT;
	private Animation anim_DIE;
	private Animation anim_WAIT;
	private Animation anim_SHOOTL;
	private Animation anim_DIEL;
	private Animation anim_WAITL;
	private boolean exist = false;
	public static boolean vie = true;
	private boolean active = true;
	private Bouclier bouclier;
	private Image pistoleroImageR;
	private Image pistoleroImageL;
	private Animation getAnimationR(int rowX, int rowY, int frames) {
		Animation anim = new Animation(false);
		for (int x = 0; x < rowX; x++) {
			anim.addFrame(pistoleroImageR.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}
	private Animation getAnimationL(int rowX, int rowY, int frames, int nombreImages) {
		Animation anim = new Animation(false);
		for (int x = nombreImages-1; x > nombreImages - rowX; x--) {
			anim.addFrame(pistoleroImageL.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}
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
		pistolPosX = 26*32;
		pistolPosY = 16*32;
		pistoleroPosX = pistolPosX;
		pistoleroPosY = pistolPosY;
		pistolero = new Rectangle(pistoleroPosX, pistoleroPosY, 25, 50);
		bullet = new Circle(pistolPosX*32,pistolPosY*32,10,10);
		pistoleroImageR = new Image("./Sprites/Space Cadet Sprite Sheet.png");
		pistoleroImageL = new Image("./Sprites/Space Cadet Sprite Sheet.png").getFlippedCopy(true, false);
		//animation droite
		anim_SHOOT = getAnimationR(5, 2,100);
		anim_DIE = getAnimationR(8, 4,75);
		anim_WAIT = getAnimationR(4, 0, 200);
		//animation gauche
		anim_SHOOTL = getAnimationL(5, 2,100, 8);
		anim_DIEL = getAnimationL(8, 4,75, 8);
		anim_WAITL = getAnimationL(4, 0, 200, 8);
	}
	
//-------------------------------------------------------RENDER------------------------------------------------------------------

	public void render(GameContainer gc, Graphics g) {
		if(exist) {
			if (vie == true)
			{
				g.setColor(Color.orange);
				g.fill(bullet);
				//g.setColor(Color.transparent);
				getPistoleroImage(action).draw(pistoleroPosX*32-32, pistoleroPosY*32-34,84,84);
			}
		}
	}
	
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta) {
		switch (action) {
		case SHOOT:
			if (directionPistolero == 1)
			{
				anim_SHOOT.update(delta);
			}
			else {
				anim_SHOOTL.update(delta);
			}
			
			break;
		case DIE :
			if (directionPistolero == 1)
			{
				anim_DIE.update(delta);
			}
			else {
				anim_DIEL.update(delta);
			}
			break;
		case WAIT :
			if (directionPistolero == 1)
			{
			anim_WAIT.update(delta);
			}
			else {
				anim_WAITL.update(delta);
			}
			break;

		}
			if(active) {
				exist = true;
				time+=delta;
			}


			
			switch (directionPistolero) {
			//Droite
			case 1:
				if(bouclier.isHitPistol()==true) {
					time=0;
					hitBouclier = true;
				}
				else if((bullet.intersects(pistolero)&&  hitBouclier == true)){
					hitBouclier = false;
					action = Action.DIE;
					vie = false;
				}
					 if(hitBouclier == true){
						pistolPosX-=5;
						getBullet().setLocation(pistolPosX,pistolPosY);
						action = Action.SHOOT;
					}
					else if(time >2000 &&  hitBouclier == true) {
							hitBouclier = false;
							pistolPosX = pistolero.getX();
							pistolPosY = pistolero.getY();
							time = 0;
						}
				
				else if(time >2000 &&  hitBouclier == false) {
					pistolPosX = pistolero.getX();
					pistolPosY = pistolero.getY();
					time = 0;
					action = Action.SHOOT;
					Enemy.shot.play();
				}
				if(hitBouclier == false) {
				pistolPosX+=5;
				getBullet().setLocation(pistolPosX,pistolPosY);
				}
				break;
			//Gauche
			case 2:
				if(bouclier.isHitPistol()==true) {
					time=0;
					hitBouclier = true;
				}
				else if((bullet.intersects(pistolero)&&  hitBouclier == true)){
					hitBouclier = false;
					action = Action.DIE;
					vie = false;
					
				}
					 if(hitBouclier == true){
						pistolPosX+=5;
						getBullet().setLocation(pistolPosX,pistolPosY);
						action = Action.SHOOT;
					}
					else if(time >2000 &&  hitBouclier == true) {
							hitBouclier = false;
							pistolPosX = pistolero.getX();
							pistolPosY = pistolero.getY();
							time = 0;
						}
				
				else if(time >2000 &&  hitBouclier == false) {
					pistolPosX = pistolero.getX();
					pistolPosY = pistolero.getY();
					time = 0;
					action = Action.SHOOT;
					Enemy.shot.play();
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
				if (directionPistolero == 1)
				{
				anim = anim_SHOOT;
				}
				else {
					anim = anim_SHOOTL;
				}
				break;
			case DIE:
				if (directionPistolero == 1)
				{
				anim = anim_DIE;
				} else {
					anim = anim_DIEL;
				}
				break;
			case WAIT : 
				if (directionPistolero == 1)
				{
				anim = anim_WAIT;
				}
				else {
					anim = anim_WAITL;
				}
				break;
		}
		return anim;
	}
	
	public Direction getDirection() {
		return direction;
	}


}

