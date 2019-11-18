package src;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Hero {

	//Variable du hero
	private int heroHP;
	private int heroPosX;
	private int heroPosY;
	private float gravity;
	private Image carreImage;
	private boolean aTerre;
	//Variable grosseur tuile
	private int tilesSize;
	
	public Hero() {
		init();
	}
	
	public Hero(int heroHP, int heroPosX, int heroPosY){
		this.heroHP = heroHP;
		this.heroPosX = heroPosX;
		this.heroPosX = heroPosY;
		init();
	}
	
	//Init 
	public void init (){
		try {
			//intialisation de la position du her
			tilesSize = 32;
			heroPosY = 15;
			heroPosX = 0;
			gravity = 15;
			aTerre = true;
			carreImage = new Image("./images/Square.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	//Render
	public void render(GameContainer gc, Graphics g) {
		
		carreImage.draw(heroPosX * tilesSize, gravity * tilesSize, 0.16f);
	}
	
	//Update
	public void update(GameContainer gc, int delta) {
		int sol = Jeu.mapTest.getLayerIndex("Sol");
		int fond = Jeu.mapTest.getLayerIndex("Fond");
		int platformes = Jeu.mapTest.getLayerIndex("Platformes");
		heroPosY = Math.round(gravity);
		Input input = gc.getInput();
		//SPACE pour sauter
		if (input.isKeyDown(Input.KEY_SPACE) && !aTerre) {
			gravity -= 0.3 * delta;
			aTerre = true;
			if(aTerre) {
				
				
					gravity -=0.8;
					
				
			}
			heroPosY = Math.round(gravity);
		}
		//A pour aller a gauche
		if (input.isKeyDown(Input.KEY_A) ) {
			heroPosX -= 1;
		}
		//S pour dessendre
		if (input.isKeyDown(Input.KEY_S) ) {
			if(Jeu.mapTest.getTileId(heroPosX , heroPosY + 1, sol) == 0 && Jeu.mapTest.getTileId(heroPosX , heroPosY + 1, platformes) == 0) {
				heroPosY += 1;
			}
		}
		//D pour aller a droite
		if (input.isKeyDown(Input.KEY_D) ) {
			heroPosX += 1;
		}
		if (Jeu.mapTest.getTileId(heroPosX , heroPosY - 1, sol) != 0 || Jeu.mapTest.getTileId(heroPosX , heroPosY - 1, platformes) != 0) {
			aTerre = true;
		}
		heroPosY += 0.5f;
	}
	
	public void mouvement(int heroPosX, int heroPosY) {
		
		
		
	}
}
