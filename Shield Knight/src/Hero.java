package src;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Hero {

	//Variable du hero
	private int heroHP;
	private int heroPosX = 32;
	private int heroPosY = 32;
	private Image carreImage;
	private Polygon heroPoly;
	float heroX;
	float heroY;
	//Variable sauter
	private boolean jumping = false;
	float gravite;
	private int sautCompteur = 0;
	//Variable grosseur tuile
	private float scale = 32;
	
	public Hero() {
		init();
	}
	
	public Hero(int heroHP, int heroPosX, int heroPosY){
		this.heroHP = heroHP;
		this.heroPosX = heroPosX;
		this.heroPosX = heroPosY;
		init();
	}
	
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	public void init (){
		try {
			//intialisation de la position du her
			//Hero
			carreImage = new Image("./images/Square.png");
			heroPoly = new Polygon(new float[]{
	                heroX, heroY,
	                heroX + 17 ,heroY,
	                heroX + 17 ,heroY + 48,
	                heroX ,heroY + 48
	        });
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g) {
		
		carreImage.draw(heroPosX, heroPosY, 0.16f);
		g.draw(heroPoly);
	}
	
	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		int sol = Jeu.mapTest.getLayerIndex("Sol");
		int fond = Jeu.mapTest.getLayerIndex("Fond");
		int platformes = Jeu.mapTest.getLayerIndex("Platformes");
		//Gravité
		//if(Jeu.mapTest.getTileId(heroPosX , heroPosY + 32, sol) == 0 && Jeu.mapTest.getTileId(heroPosX , heroPosY + 32, platformes) == 0) {
			heroPosY += 16;
		//}
		//Activer les inputs
		Input input = gc.getInput();
		//SPACE pour sauter
		if (input.isKeyDown(Input.KEY_SPACE) ) {
	//		if(Jeu.mapTest.getTileId(heroPosX , heroPosY - 32, sol) == 0 && Jeu.mapTest.getTileId(heroPosX , heroPosY - 32, platformes) == 0 && sautCompteur != 3) {
				
				heroPosY -= 64;
	//		}
		}
		//A pour aller a gauche
		if (input.isKeyDown(Input.KEY_A) ) {
			heroPosX -= 32;
		}
		//S pour dessendre
		if (input.isKeyDown(Input.KEY_S) ) {
	//		if(Jeu.mapTest.getTileId(heroPosX , heroPosY + 32, sol) == 0 && Jeu.mapTest.getTileId(heroPosX , heroPosY + 32, platformes) == 0) {
				heroPosY += 32;
	//		}
		}
		//D pour aller a droite
		if (input.isKeyDown(Input.KEY_D) ) {
			heroPosX += 32;
		}
		
	}
}

