package src;

import org.newdawn.slick.tiled.TiledMap;

import java.awt.Font;

//import com.sun.prism.Graphics;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;

	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}

public class Jeu extends BasicGame{

	//Variables
	static TiledMap mapTest;
	private Hero carre;
	private enemy enemy;
	private Cheats cheat;
	private TextField console;
	private Direction direction;
	//Constructeur du jeu
	public Jeu(String title) 
	{
		super(title);
		
	}
	
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	@Override
	public void init(GameContainer gc) throws SlickException {
		direction = Direction.RIGHT;
		try {
			//initialisation de la mapTest
			mapTest = new TiledMap("./maps/mapTest.tmx");
			//Initialisation du hero
			carre = new Hero();
			enemy = new enemy();
			cheat = new Cheats();
			//console = new Cheats();
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
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_L) ) {
			cheat.noClip(carre.heroX, carre.heroY, gc);
		}
		else {
			carre.update(gc, delta);
		}
		enemy.update(gc, delta);
	}

}
