package src;

import org.newdawn.slick.tiled.TiledMap;

//import com.sun.prism.Graphics;

import org.newdawn.slick.*;

	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}

public class Jeu extends BasicGame{

	//Variables
	private TiledMap mapTest;
	
	//Constructeur du jeu
	public Jeu(String title) 
	{
		super(title);
		
	}
	
	//------------------------------------------------------------------M�THODE D'INTIALISATION------------------------------------------------------------------
	@Override
	public void init(GameContainer gc) throws SlickException {
		try {
			//initialisation de la mapTest
			mapTest = new TiledMap("./maps/mapTest.tmx");
			//Initialisation du hero
			Hero carre = new Hero();
		}
		catch (Exception e) 
		{
			System.out.println("Probl�me dans l'intialisation du jeu");
		}
		
	}
	
	//------------------------------------------------------------------M�THODE RENDER------------------------------------------------------------------
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//Dessin de la map
		mapTest.render(0, 0);
	}

	//------------------------------------------------------------------M�THODE UPDATE------------------------------------------------------------------
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}

}
