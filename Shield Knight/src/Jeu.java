package src;
import interfacejeu.InterfacesEnJeu;
import org.newdawn.slick.tiled.TiledMap;

import interfacejeu.Menu;

//import com.sun.prism.Graphics;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

	enum Direction{
		UP, 
		DOWN, 
		LEFT, 
		RIGHT
	}

public class Jeu extends BasicGame{

	//Variables
	private Music musique;
	private Hero hero;
	private Bouclier bouclier;
	private Enemy enemy;
	private Maps maps;
	private Projectile p;
	private Sniper sniper;
	private Projectile projectile;
	private Menu menu;
	private InterfacesEnJeu interfaces;
	//Constructeur du jeu
	public Jeu(String title) 
	{
		super(title);
		
	}
	
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	@Override
	public void init(GameContainer gc) throws SlickException {
			try {
				//Maps
				maps = new Maps();
				//Enemies
				enemy = new Enemy();
				//sniper = new Sniper();
				//Hero
				hero = new Hero();
				//Bouclier
				bouclier = new Bouclier();
				//Projectile
				p = new Projectile();
				//musique
				musique = new Music("./Audio/musicDeFond.wav");
				musique.play();
				musique.setVolume(0.15f);
				//Menu
				menu = new Menu();
				interfaces = new InterfacesEnJeu();
				
				
			}
			catch (Exception e) 
			{
				System.out.println("La map ne load pas");
			}
	}
	
	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//Renders des classes
		
		if (Maps.compteurLevel == 0)
		{
			maps.render(gc, g);
			menu.render(gc, g);
		}
		//Game Over
		else if(Maps.compteurLevel == 1000) {
			interfaces.render(gc, g);
			maps.render(gc, g);
		}
		else {
			maps.render(gc, g);
			hero.render(gc, g);
			enemy.render(gc,g);
			bouclier.render(gc, g);
			interfaces.render(gc, g);
			g.setColor(Color.red);
			//sniper.render(gc, g);
		}
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		//Updates des classes
		if (Maps.compteurLevel == 0)
		{
			maps.update(gc, delta);
			menu.update(gc, delta);
		}
		//Game Over
		else if(Maps.compteurLevel == 1000) {
			interfaces.update(gc, delta);
			maps.update(gc, delta);
		}
		else {
			maps.update(gc, delta);
			hero.update(gc, delta);
			enemy.update(gc, delta);
			bouclier.update(gc, delta);
			interfaces.update(gc, delta);
		}
	}
}
