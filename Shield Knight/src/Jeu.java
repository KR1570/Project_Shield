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
	static TiledMap mapTest2;
	private Music musique;
	private Hero carre;
	
	private enemy enemy;

	
	//Constructeur du jeu
	public Jeu(String title) 
	{
		super(title);
		
	}
	
	//Allo
	//------------------------------------------------------------------M�THODE D'INTIALISATION------------------------------------------------------------------
	@Override
	public void init(GameContainer gc) throws SlickException {
			//initialisation de la mapTest
			mapTest = new TiledMap("./Maps/mapTest2.tmx");
			//Initialisation du hero
			carre = new Hero();
			//Initialisation de notre ennemie
			enemy = new enemy();
			//musique
			musique = new Music("./Audio/musicDeFond.wav");
			musique.play();
			musique.setVolume(0.2f);
	}
	
	//------------------------------------------------------------------M�THODE RENDER------------------------------------------------------------------
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//Dessin de la map
		mapTest.render(0, 0);
		//Dessin du hero
		carre.render(gc, g);
		//Dessin ennemie
		enemy.render(gc,g);

		
	}

	//------------------------------------------------------------------M�THODE UPDATE------------------------------------------------------------------
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
	}

}
