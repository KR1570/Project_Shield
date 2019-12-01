package src;

import org.newdawn.slick.tiled.TiledMap;

//import com.sun.prism.Graphics;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
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

	private Projectile p;

	int ypos;
	int xpos;
	//--------------------------------------
	private Hero carre;
	private Sniper sniper;
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

			sniper = new Sniper();
			//initialisation de la mapTest
			// = new TiledMap("./maps/mapTest.tmx"); 
			mapTest = new TiledMap("./maps/mapTest2.tmx");
			mapTest2 = new TiledMap("./maps/mapTest.tmx");
			//Initialisation du hero
			carre = new Hero();
			//Initialisation de notre ennemie
			enemy = new enemy();
			p = new Projectile();
			sniper.init();

			
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
			g.setColor(Color.red);
			sniper.render(gc, g);
		//Dessin du hero
		carre.render(gc, g);
		//Dessin ennemie
		enemy.render(gc,g);

		
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		Input input = gc.getInput();
		sniper.update(gc, delta,carre.heroPosX,carre.heroPosY);
		int xpos = input.getMouseX();
		int ypos = input.getMouseY();
		System.out.println("X :" + xpos);
		System.out.println("Y :" + ypos);
		//update du hero
		carre.update(gc, delta);
		enemy.update(gc, delta);
		
		
	}

}
