package src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Sniper extends Enemy{
	Rectangle sniper;
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
	
	public Sniper(int sniperHp,int sniperPosX,int sniperPosY) {
		super(sniperHp,sniperPosX,sniperPosY);
		init();
	}
	public Sniper() {
		
	}
//-------------------------------------------------------INIT------------------------------------------------------------------

	public void init() {
		counter = false;
		hero = new Hero();
		p = new Projectile();
		p.init(1200, 260);
		sniper = new Rectangle(1200,260,50,25);
		projectiles = new Projectile[8];
		
		for(int i = 0; i < projectiles.length; i++) {
			
			projectiles[i] = new Projectile();
		}

		laser = new Line(0,0,1200,260);
		range = new Circle(1200,260,700);
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------
	
	public  void render(GameContainer gc, Graphics g) {
		g.draw(sniper);
		if(range.intersects(hero.heroHitBox)) {
		g.draw(laser);
		g.draw(range);
		for(Projectile p : projectiles) {
			p.render(gc, g);
		}
		}
	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta,float heroX,float heroY) {
		//calcul le vecteur vers le joueur
		if(!p.bullet.intersects(hero.heroHitBox)) {
			X= (heroX*32)-1200;
			Y= ((heroY*32)+35)-260;

		}
		else {
			X=1200-(heroX*32);
			Y=260-(heroY*32);
		}
		//si le hero n'est plus dans le range le projectile retourne a son point de depart
		if(!range.intersects(hero.heroHitBox)) {
		projectiles[current] = new Projectile(new Vector2f(1200,260), new Vector2f(1200,260));
		}
		else {
		time += delta;
		//laser qui suit le joueur
		laser.set(heroX*32+32,heroY*32+32,1200,260);
		
		if(time > fireRate)
		{
			projectiles[current] = new Projectile(new Vector2f(1200,260), new Vector2f(X,Y));
			current++;
			if(current >= projectiles.length) {
				current = 0;
				time=0;
			}
		}

			
		//counter 
		

		
		for(Projectile p : projectiles) {
			p.update(delta,5000f);
		}
	
	}
	}
}
