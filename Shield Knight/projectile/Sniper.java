package projectile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
	Rectangle sniper;
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
	
	public Sniper(int sniperHp,int sniperPosX,int sniperPosY) {
		super(sniperHp,sniperPosX,sniperPosY);
		init();
	}
	public Sniper() {
		
	}
//-------------------------------------------------------INIT------------------------------------------------------------------

	public void init() {
		hitBouclier = false;
		bouclier = new Bouclier();		hero = new Hero();
		bullet = new SniperBullet();
		bullet.init(1200, 260);
		sniper = new Rectangle(1200,260,50,25);
		SniperBullets = new SniperBullet[8];
		
		for(int i = 0; i < SniperBullets.length; i++) {
			
			SniperBullets[i] = new SniperBullet();
		}

		laser = new Line(0,0,1200,260);
		range = new Circle(1200,260,700);
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------
	
	public  void render(GameContainer gc, Graphics g) {
		g.draw(sniper);

		g.draw(laser);
	//	g.draw(range);
		if(bullet.isActive()==true) {
		for(SniperBullet p : SniperBullets) {
			p.render(gc, g);
			
		
		}
		}
	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta,float heroX,float heroY) {
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
			SniperBullets[current].setPosition(new Vector2f(2,2)); 
			bullet.bullet.setLocation(0, 0);
		}
		else {
			X= (heroX*32)-1200;
			Y= ((heroY*32)+35)-260;


			bullet.setActive(true);
		time += delta;
		//laser qui suit le joueur
		laser.set(heroX*32+32,heroY*32+32,1200,260);
		
		if(time > fireRate)
		{
			SniperBullets[current] = new SniperBullet(new Vector2f(1200,260), new Vector2f(X,Y));
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
	}

