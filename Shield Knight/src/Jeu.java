package src;

import org.newdawn.slick.tiled.TiledMap;

//import com.sun.prism.Graphics;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}

public class Jeu extends BasicGame{

	//Variables
	static TiledMap mapTest;
	static TiledMap mapTest2;
	//projectile
	private Projectile[] projectiles;
	private Projectile p;
	private int fireRate = 400;
	private int current = 0;
	private int time = 0;
	//--------------------------------------
	private Hero carre;
	
	private enemy enemy;

	
	//Constructeur du jeu
	public Jeu(String title) 
	{
		super(title);
		
	}
	
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	@Override
	public void init(GameContainer gc) throws SlickException {
		try {
			//initialisation de la mapTest
			// = new TiledMap("./maps/mapTest.tmx"); 
			mapTest = new TiledMap("./maps/mapTest2.tmx");
			mapTest2 = new TiledMap("./maps/mapTest.tmx");
			//Initialisation du hero
			carre = new Hero();
			//Initialisation de notre ennemie
			enemy = new enemy();
			p = new Projectile();
			p.init();
			projectiles = new Projectile[8];
			for(int i = 0; i < projectiles.length; i++) {
				
				projectiles[i] = new Projectile();
			}

			
		}
		catch (Exception e) 
		{
			System.out.println("Ma map load pas :(");
		}
	}
	
	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

			//Dessin de la map
			mapTest.render(0, 0);
		
		//Dessin du hero
		carre.render(gc, g);
		//Dessin ennemie
		enemy.render(gc,g);
		for(Projectile p : projectiles) {
			p.render(gc, g);
		}

		
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = input.getMouseX();
		int ypos = input.getMouseY();
		System.out.println("X :" + xpos);
		System.out.println("Y :" + ypos);
		//update du hero
		carre.update(gc, delta);
		enemy.update(gc, delta);
		
		time += delta;
		if(time > fireRate && input.isKeyPressed(Input.KEY_UP))
		{
			projectiles[current] = new Projectile(new Vector2f(0,0), new Vector2f(carre.heroPosX*32,carre.heroPosY*32));
			current++;
			if(current >= projectiles.length) {
				current = 0;
				time=0;
			}
		}
		for(Projectile p : projectiles) {
			p.update(delta);
		}
	}

}
