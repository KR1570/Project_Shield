/*package src;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Hero {

	//Variable du hero
	private int heroHP;
	private int heroPosX = 1;
	private int heroPosY = 1;
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
		
		carreImage.draw(heroPosX * scale, heroPosY * scale, 0.16f);
		g.draw(heroPoly);
	}
	
	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		int sol = Jeu.mapTest.getLayerIndex("Sol");
		//int platformes = Jeu.mapTest.getLayerIndex("Platformes");
		//int fond = Jeu.mapTest.getLayerIndex("Fond");

		//Gravité
		if(Jeu.mapTest.getTileId(heroPosX , heroPosY + 1, sol) == 0) {
			heroPosY += 1;
		}
		//Activer les inputs
		Input input = gc.getInput();
		//SPACE pour sauter
		if (input.isKeyDown(Input.KEY_SPACE) ) {
			if(Jeu.mapTest.getTileId(heroPosX , heroPosY - 1, sol) == 0) {
				heroPosY -= 2;
			}
		}
		//A pour aller a gauche
		if (input.isKeyDown(Input.KEY_A) ) {
			heroPosX -= 1;
		}
		//S pour dessendre
		if (input.isKeyDown(Input.KEY_S) ) {
			if(Jeu.mapTest.getTileId(heroPosX , heroPosY + 1, sol) == 0) {
				heroPosY += 1;
			}
		}
		//D pour aller a droite
		if (input.isKeyDown(Input.KEY_D) ) {
			heroPosX += 1;
		}
		
	}
}
*/
package src;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Hero {

	//Variable du hero
	private int heroHP;
	private float heroPosX = 1;
	private float heroPosY = 1;
	private Image carreImage;
	private Polygon heroPoly;
	float heroX;
	float heroY;
	//Variable sauter
	private boolean jumping = false;
	float gravite;
	private float sautCompteur = 0;
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
		
		carreImage.draw(heroPosX * scale, heroPosY * scale, 0.16f);
		g.draw(heroPoly);
	}
	
	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		int sol = Jeu.mapTest.getLayerIndex("Sol");
		//Gravité
		if(Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) == 0) {
			heroPosY += 0.18f;
		}
		//Activer les inputs
		Input input = gc.getInput();
		
		//SPACE pour sauter
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) - 1, sol) == 0) {
				if (sautCompteur <= 12.0f) {
					heroPosY -= 0.3f;
					sautCompteur+= 0.3f;
				}
			}
		}
		//A pour aller a gauche
		if (input.isKeyDown(Input.KEY_A) ) {
			heroPosX -= 0.15f;
		}
		//D pour aller a droite
		if (input.isKeyDown(Input.KEY_D) ) {
			heroPosX += 0.15f;
		}
		if (Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) != 0) {
			sautCompteur =0;
		}
		
	}
}