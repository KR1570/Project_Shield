package projectile;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import src.Bouclier;
import src.Enemy;
import src.Hero;
import src.Jeu;
import src.Maps;

public class Sniper extends Enemy{
	enum Action {
		SHOOT, DIE, WAIT
	}
	private Action action = Action.SHOOT;
	public static Rectangle sniper;
	private SniperBullet[] SniperBullets;
	private SniperBullet bullet;
	private int fireRate = 1000;
	private int current = 0;
	private int time = 0;
	private Hero hero;
	private Line laser;
	static Circle range;
	private float X;
	private float Y;
	private Bouclier bouclier;
	private boolean hitBouclier;
	private TiledMap buffer;
	private Image witchImageR;
	private Image witchImageL;
	private Animation anim_SHOOT;
	private Animation anim_DIE;
	private Animation anim_WAIT;
	private Animation anim_SHOOTL;
	private Animation anim_DIEL;
	private Animation anim_WAITL;
	public static boolean vie;
	public static int directionWitch = 2;
	public static float posWitchX = 37;
	public static float posWitchY = 7;
	private Animation getAnimationR(int rowX, int rowY, int frames) {
		Animation anim = new Animation(false);
		for (int x = 0; x < rowX; x++) {
			anim.addFrame(witchImageR.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}
	private Animation getAnimationL(int rowX, int rowY, int frames, int nombreImages) {
		Animation anim = new Animation(false);
		for (int x = nombreImages-1; x > nombreImages - rowX; x--) {
			anim.addFrame(witchImageL.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}
	
	public Sniper(int sniperHp,int sniperPosX,int sniperPosY) throws SlickException {
		super(sniperHp,sniperPosX,sniperPosY);
		init();
	}
	public Sniper() {
		
	}
//-------------------------------------------------------INIT------------------------------------------------------------------

	public void init() throws SlickException {
		hitBouclier = false;
		bouclier = new Bouclier();		
		hero = new Hero();
		bullet = new SniperBullet();
		bullet.init(1200, 260);
		sniper = new Rectangle(posWitchX*32,posWitchY*32,50,25);
		SniperBullets = new SniperBullet[8];
		witchImageR = new Image("./Sprites/Witch Sprite Sheet.png");
		witchImageL = new Image("./Sprites/Witch Sprite Sheet.png").getFlippedCopy(true, false);
		//animation droite
		anim_SHOOT = getAnimationR(8, 2,100);
		anim_DIE = getAnimationR(8, 2,75);
		anim_WAIT = getAnimationR(4, 0, 200);
		//animation gauche
		anim_SHOOTL = getAnimationL(8, 2,100, 10);
		anim_DIEL = getAnimationL(8, 4,75, 10);
		anim_WAITL = getAnimationL(4, 0, 200, 10);
		for(int i = 0; i < SniperBullets.length; i++) {
			SniperBullets[i] = new SniperBullet();
		}
		laser = new Line(0,0,1200,260);
		range = new Circle(1200,260,700);
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------
	
	public  void render(GameContainer gc, Graphics g) {
		if (vie = true) {
			getWitchImage(action).draw(posWitchX*32, posWitchY*32-16,84,84);
			if(bullet.isActive()==true) {
				for(SniperBullet p : SniperBullets) {
					p.render(gc, g); 
				}
			}
		}
	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta,float heroX,float heroY) {
		switch (action) {
		case SHOOT:
			if (directionWitch == 1)
			{
				anim_SHOOT.update(delta);
			}
			else {
				anim_SHOOTL.update(delta);
			}
			
			break;
		case DIE :
			if (directionWitch == 1)
			{
				anim_DIE.update(delta);
			}
			else {
				anim_DIEL.update(delta);
			}
			break;
		case WAIT :
			if (directionWitch == 1)
			{
			anim_WAIT.update(delta);
			}
			else {
				anim_WAITL.update(delta);
			}
			break;

		}
		//calcul le vecteur vers le joueur
		//Maps Level 1
				if(Maps.compteurLevel == 1) {
					buffer = Maps.mapLevel1;
				}
				if(Maps.compteurLevel == 2) {
					buffer = Maps.mapLevel2;
				}
				if(Maps.compteurLevel == 3) {
					buffer = Maps.mapLevel3;
				}
		int sol = buffer.getLayerIndex("Sol");
		// || buffer.getTileId(Math.round(bullet.bullet.getCenterX()) , Math.round(bullet.bullet.getCenterY()), sol) != 0
		if(bouclier.isHitSniper() == true) {
			SniperBullets[current].setPosition(new Vector2f(2,2) ); 
			//laser.set(heroX*32+32,heroY*32-80,posWitchX*32,posWitchY*32);
			bullet.bullet.setLocation(0, 0);
		}
		else {
			X= (heroX*32)-posWitchX*32;
			Y= ((heroY*32)+35)-posWitchY*32;


			bullet.setActive(true);
		time += delta;
		//laser qui suit le joueur
		laser.set(heroX*32+32,heroY*32+32,1200,260);
		
		if(time > fireRate)
		{
			SniperBullets[current] = new SniperBullet(new Vector2f(posWitchX*32,posWitchY*32), new Vector2f(X,Y));

			current++;
			if(current >= SniperBullets.length) {
				current = 0;
				time=0;
			}
		}
		}
		
		for(SniperBullet p : SniperBullets) {
			p.update(delta,5000f);
		}
		if(bullet.bullet.intersects(sniper)&& hitBouclier == true) {
			hitBouclier = false;
		}

	
	}
	public Animation getWitchImage(Action action) {
		Animation anim = new Animation(false);
		switch (action) {
			case SHOOT : 
				if (directionWitch == 1)
				{
				anim = anim_SHOOT;
				}
				else {
					anim = anim_SHOOTL;
				}
				break;
			case DIE:
				if (directionWitch == 1)
				{
				anim = anim_DIE;
				} else {
					anim = anim_DIEL;
				}
				break;
			case WAIT : 
				if (directionWitch == 1)
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
	}

