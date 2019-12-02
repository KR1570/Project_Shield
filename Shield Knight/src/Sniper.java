package src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Sniper extends enemy{
	Rectangle sniper;
	private Projectile[] projectiles;
	private Projectile p;
	private int fireRate = 1000;
	private int current = 0;
	private int time = 0;
	private Hero hero;
	
	
	public Sniper(int sniperHp,int sniperPosX,int sniperPosY) {
		super(sniperHp,sniperPosX,sniperPosY);
		init();
	}
	public Sniper() {
		
	}
//-------------------------------------------------------INIT------------------------------------------------------------------

	public void init() {
		hero = new Hero();
		p = new Projectile();
		p.init(1200, 260);
		sniper = new Rectangle(1200,260,50,25);
		projectiles = new Projectile[8];
		for(int i = 0; i < projectiles.length; i++) {
			
			projectiles[i] = new Projectile();
		}
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------
	
	public  void render(GameContainer gc, Graphics g) {
		g.draw(sniper);
		for(Projectile p : projectiles) {
			p.render(gc, g);
		}
	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------

	public void update(GameContainer gc, int delta,float heroX,float heroY) {
		time += delta;
		Input input = gc.getInput();
		if(time > fireRate || input.isKeyPressed(Input.KEY_UP))
		{
			projectiles[current] = new Projectile(new Vector2f(1200,260), new Vector2f(-heroY*32,heroX*32));
			current++;
			if(current >= projectiles.length) {
				current = 0;
				time=0;
			}
		}
		for(Projectile p : projectiles) {
			p.update(delta,5000f);
		}
	}
}
