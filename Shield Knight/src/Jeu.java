package src;

import org.newdawn.slick.tiled.TiledMap;

//import com.sun.prism.Graphics;

import org.newdawn.slick.*;

	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}

public class Jeu extends BasicGame{

	//Variables
	static TiledMap mapTest;
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
			mapTest = new TiledMap("./maps/mapTest.tmx");
			//Initialisation du hero
			carre = new Hero();
			enemy = new enemy();
		}
		catch (Exception e) 
		{
			System.out.println("Problème dans l'intialisation du jeu");
		}
		
	}
	
	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//Dessin de la map
		mapTest.render(0, 0);
		//Dessin du hero
		carre.render(gc, g);
		enemy.render(gc,g);
		
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		//update du hero
		carre.update(gc, delta);
		enemy.update(gc, delta);
	}

}
