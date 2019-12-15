package src;

import org.newdawn.slick.tiled.TiledMap;

import projectile.Pistol;
import projectile.Sniper;

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
	private static TiledMap mapTest;
	static TiledMap mapTest2;
	//projectile

	//private Projectile p;
	private Pistol pistol;

	//--------------------------------------
	private Hero carre;
	private Sniper sniper;
	private Enemy enemy;
	private Bouclier bouclier;
	private Music musique;
	private int mapTestX;
	private int mapTestY;

	public static TiledMap getMapTest() {
		return mapTest;
	}

	public static void setMapTest(TiledMap mapTest) {
		Jeu.mapTest = mapTest;
	}

	//Constructeur du jeu
	public Jeu(String title) 
	{
		super(title);
		
	}
	
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	@Override
	public void init(GameContainer gc) throws SlickException {
		try {
			
			pistol= new Pistol();
			sniper = new Sniper();
			//initialisation de la mapTest
			// = new TiledMap("./maps/mapTest.tmx"); 
			setMapTest(new TiledMap("./maps/mapTest2.tmx"));
			mapTest2 = new TiledMap("./maps/mapTest.tmx");
			//Initialisation du hero
			carre = new Hero();
			bouclier = new Bouclier();
			//Initialisation de notre ennemie
			enemy = new Enemy();
			//p = new Projectile();
			sniper.init();
			pistol.init();
			//musique
			musique = new Music("./Audio/musicDeFond.wav");
			musique.play();
			musique.setVolume(0.15f);

			
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
		getMapTest().render(0, 0);
		g.setColor(Color.red);
		pistol.render(gc, g);
		sniper.render(gc, g);
		//Dessin du hero
		carre.render(gc, g);
		//Dessin ennemie
		enemy.render(gc,g);
		bouclier.render(gc, g);

		
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		pistol.update(gc, delta);
		Input input = gc.getInput();
		sniper.update(gc, delta,carre.heroPosX,carre.heroPosY);
		int xpos = input.getMouseX();
		int ypos = input.getMouseY();
		System.out.println("X :" + xpos);
		System.out.println("Y :" + ypos);
		//update du hero
		carre.update(gc, delta);
		enemy.update(gc, delta);
		bouclier.update(gc, delta);
		
	}

}
