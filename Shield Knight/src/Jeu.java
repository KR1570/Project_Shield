package src;

import org.newdawn.slick.tiled.TiledMap;

//import com.sun.prism.Graphics;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}

public class Jeu extends BasicGame{

	//Variables
	static TiledMap mapTest;
	private Music musique;
	private Hero carre;
	private Bouclier bouclier;
	private enemy enemy;
	private int mapTestX;
	private int mapTestY;
	
	//Constructeur du jeu
	public Jeu(String title) 
	{
		super(title);
		
	}
	
	//Allo
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	@Override
	public void init(GameContainer gc) throws SlickException {
			//initialisation de la mapTestTest
			mapTest = new TiledMap("./Maps/level1-2.tmx");
			//Initialisation du hero et son bouclier
			carre = new Hero();
			bouclier = new Bouclier();
			//Initialisation de notre ennemie
			enemy = new enemy();
			//musique
			musique = new Music("./Audio/musicDeFond.wav");
			musique.play();
			musique.setVolume(0.15f);
			
	}
	
	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//Dessin de la mapTest
		mapTest.render(0, 0);
		//Dessin du hero
		carre.render(gc, g);
		//Dessin ennemie
		enemy.render(gc,g);
		enemy.render(gc,g);
		bouclier.render(gc, g);
		
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		//update du hero
		carre.update(gc, delta);
		enemy.update(gc, delta);
		bouclier.update(gc, delta);
		
		
	}

}
