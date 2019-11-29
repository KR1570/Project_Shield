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

import org.lwjgl.util.vector.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import src.enemy.Direction;

public class Hero {

	//Variable du hero
	private int heroHP;
	float heroPosX = 2;
	float heroPosY = 2;
	private Image carreImage;
	float heroX;
	float heroY;
	//Variable sauter
	private boolean jumping = false;
	float gravite;
	private float sautCompteur = 0;
	//Variable grosseur tuile
	private float scale = 32;
	//Hitbox
	static Rectangle heroHitBox;
	//Ligne 
	private Line limiteEau;
	private Line limiteGauche;
	private Line limiteDroite;
	//Ennemy
	private enemy enemy1;
	//projectile
	
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
			carreImage = new Image("./images/knight.png");
			heroHitBox = new Rectangle(heroPosX*32,heroPosY*32,48,48);
			//Limite de gauche
			limiteGauche = new Line(28,0,28,768);
			limiteDroite = new Line(1420,0,1420,768);
			limiteEau = new Line(0,748,1440,748);
			enemy1 = new enemy();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g) {
		
		carreImage.draw(heroPosX * scale, heroPosY * scale, 0.16f);
		g.setColor(Color.red);
		g.draw(heroHitBox);

		g.draw(limiteEau);
		g.draw(limiteGauche);
		g.draw(limiteDroite);
		g.draw(enemy1.enemy);

		
		
	}
	
	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		int sol = Jeu.mapTest.getLayerIndex("Sol");
		int fond = Jeu.mapTest.getLayerIndex("Fond");
		enemy1.update(gc, delta);
		//HitBox mouvement
		heroHitBox.setLocation(heroPosX*32,heroPosY*32);
		//Gravité
		if(Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) == 0) {
			heroPosY += 0.18f;
		}
		//Activer les inputs
		Input input = gc.getInput();
		//SPACE pour sauter
		if (input.isKeyDown(Input.KEY_SPACE)||input.isKeyDown(Input.KEY_W)) {
			if(Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) - 1, sol) == 0) {
			if (sautCompteur <= 8.0f) {
				heroPosY -= 0.4f;
				sautCompteur+= 0.3f;
				}


			}
		}
		//A pour aller a gauche
		if (input.isKeyDown(Input.KEY_A) ) {
			if(Jeu.mapTest.getTileId(Math.round(heroPosX) - 1, Math.round(heroPosY), sol) == 0 && !heroHitBox.intersects(limiteGauche)) {
				heroPosX -= 0.15f;
			}
		}
		//D pour aller a droite
		if (input.isKeyDown(Input.KEY_D) ) {
			if(Jeu.mapTest.getTileId(Math.round(heroPosX) + 1, Math.round(heroPosY), sol) == 0 && !heroHitBox.intersects(limiteDroite)) {
				heroPosX += 0.15f;
			}
		}
		if (Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) != 0) {
			sautCompteur =0;
		}
		//Limites du bas
		if(heroHitBox.intersects(limiteEau) || heroHitBox.intersects(enemy1.enemy)|| Projectile.isHit()==true) {
			heroPosX = 5;
			heroPosY = 15;
		}
		
	}
	public boolean newMap() {
		boolean nextMap = false;
		if(heroPosX == 40) {
			nextMap = true;
		}
		return nextMap;
		
	}
	
}