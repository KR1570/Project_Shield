package src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import interfacejeu.*;

import interfacejeu.InterfacesEnJeu;
import projectile.Pistol;
import projectile.Sniper;
public class Maps {
	private TiledMap mapMenu;
	private TiledMap mapGameOver;
	public static TiledMap mapLevel1;
	public static TiledMap mapLevel2;
	public static TiledMap mapLevel3;
	public static TiledMap mapLevel21;
	public static TiledMap mapLevel22;
	public static TiledMap mapLevel23;
	public static int compteurLevel = 0;
	public static TiledMap mapWin;
	

	public Maps(){
		init();
	}
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	public void init() {
		try {
			mapLevel1 = new TiledMap("./Maps/level1-1.tmx");
			mapLevel2 = new TiledMap("./Maps/level1-2.tmx");
			mapLevel3 = new TiledMap("./Maps/level1-3.tmx");
			mapLevel21 = new TiledMap("./Maps/level2-1.tmx");
			mapLevel22 = new TiledMap("./Maps/level2-2.tmx");
			mapLevel23 = new TiledMap("./Maps/level2-3.tmx");
			mapMenu = new TiledMap("./Maps/menu.tmx");
			mapGameOver = new TiledMap("./Maps/gameOver.tmx");
			mapWin = new TiledMap("./Maps/win.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g) {
		//Render de la map
		switch(compteurLevel) {
			case 0 :
				mapMenu.render(0, 0);
				Hero.invincibilite=false;
				break;
			case 1 :
				mapLevel1.render(0,0);
				break;
			case 2 :
				mapLevel2.render(0,0);
				break;
			case 3 :
				mapLevel3.render(0,0);
				break;
			case 1000 :
				mapGameOver.render(0, 0);
				break;
				
			case 21 :
				mapLevel21.render(0, 0);
				break;
			case 22 :
				mapLevel22.render(0, 0);
				break;
			case 23 :
				mapLevel23.render(0, 0);
				break;
			case 1001 :
				mapWin.render(0, 0);
				break;
				
		}
		
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		//Level 1-1
		/*
		if(compteurLevel == 2 && Hero.heroHitBox.intersects(Hero.limiteGauche)){
			compteurLevel = 1;
			//Hero
			Hero.heroPosX = 40;
			//Enemie bloqueur
			Enemy.enemyPosX = 12;
			Enemy.enemyPosY = 11;
			//Pistolero
			Pistol.pistoleroPosX = 26;
			Pistol.pistoleroPosY = 16;
			Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
			Pistol.directionPistolero = 2;
			//Witch
			Sniper.posWitchX = 37;
			Sniper.posWitchY = 7;
			Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
			Sniper.vie=true;
		}
		*/
		if (Menu.gameOverBool == true)
		{
			if (Menu.chapitreLevel == 1) {
				compteurLevel = 1;
				//Enemie bloqueur
				Enemy.enemyPosX = 12;
				Enemy.enemyPosY = 11;
				//Hero
				Hero.heroPosX = 2;
				//Pistolero
				Pistol.pistoleroPosX = 26;
				Pistol.pistoleroPosY = 16;
				Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
				Pistol.directionPistolero = 2;
				Pistol.vie = true;
				//Witch
				Sniper.vie=true;
				Sniper.posWitchX = 37;
				Sniper.posWitchY = 7;
				Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
				//Mourir
				Menu.gameOverBool = false;
			}
			else if (Menu.chapitreLevel == 2) {
				compteurLevel = 21;
				//Hero
				Hero.heroPosX = 2;
				//Bloqueur
				Enemy.enemyPosX = 2;
				Enemy.enemyPosY= 19;
				Enemy.vie=false;
				//pistol
				Pistol.pistoleroPosX = 37;
				Pistol.pistoleroPosY = 15;
				Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
				Pistol.vie = true;
				Pistol.directionPistolero = 2;
				//witch
				Sniper.vie = true;
				Sniper.posWitchX = 35;
				Sniper.posWitchY =3;
				Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
				Menu.gameOverBool = false;
			}
		}
		//Level 1-2
		else if(compteurLevel == 1 && Hero.heroHitBox.intersects(Hero.limiteDroite)){
			compteurLevel = 2;
			//Hero
			Hero.heroPosX = 2;
			//Enemie bloqueur
			Enemy.enemyPosX = 24;
			Enemy.enemyPosY = 15;
			//Pistolero
			Pistol.pistoleroPosX = 16;
			Pistol.pistoleroPosY = 7;
			Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
			Pistol.vie = true;
			Pistol.directionPistolero = 1;
			//Witch
			Sniper.vie=true;
			Sniper.posWitchX = 37;
			Sniper.posWitchY = 19;
			Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
		}
		/*
		else if (compteurLevel == 3 && Hero.heroHitBox.intersects(Hero.limiteGauche)){
			compteurLevel = 2;
			//Hero
			Hero.heroPosX = 40;
			//Bloqueur
			Enemy.enemyPosX = 24;
			Enemy.enemyPosY = 15;
			//Pistolet
			Pistol.pistoleroPosX = 16;
			Pistol.pistoleroPosY = 7;
			Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
			//direction pistolero droite
			Pistol.directionPistolero = 1;
			Pistol.vie = true;
			//Witch
			Sniper.vie=true;
			Sniper.posWitchX = 37;
			Sniper.posWitchY = 19;
			Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
		}
		*/
		//Level 1-3
		else if(compteurLevel == 2 && Hero.heroHitBox.intersects(Hero.limiteDroite)){
			compteurLevel = 3;
			//Hero
			Hero.heroPosX = 2;
			//Bloqueur
			Enemy.enemyPosX = 12;
			Enemy.enemyPosY = 4;
			//Pistolet
			Pistol.pistoleroPosX = 2;
			Pistol.pistoleroPosY = 15;
			Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
			Pistol.vie = true;
			Pistol.directionPistolero = 1;
			//Witch
			Sniper.vie=true;
			Sniper.posWitchX = 42;
			Sniper.posWitchY = 9;
			Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
		}
		//Level 2-1
		else if(compteurLevel == 3 && Hero.heroHitBox.intersects(Hero.limiteHaut)){
			compteurLevel = 21;
			Menu.chapitreLevel = 2;
			//Hero
			Hero.heroPosX = 2;
			//Bloqueur
			Enemy.enemyPosX = 2;
			Enemy.enemyPosY= 19;
			Enemy.vie=false;
			//pistol
			Pistol.pistoleroPosX = 37;
			Pistol.pistoleroPosY = 15;
			Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
			Pistol.vie = true;
			Pistol.directionPistolero = 2;
			//witch
			Sniper.vie = true;
			Sniper.posWitchX = 35;
			Sniper.posWitchY =3;
			Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
		}
		//Level 2-2
		else if(compteurLevel == 21 && Hero.heroHitBox.intersects(Hero.limiteDroite)){
			compteurLevel = 22;
			//Hero
			Hero.heroPosX = 2;
			//Bloqueur
			Enemy.enemyPosX = 2;
			Enemy.enemyPosY= 19;
			Enemy.vie=false;
			//pistol
			Pistol.pistoleroPosX = 37;
			Pistol.pistoleroPosY = 15;
			Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
			Pistol.vie = false;
			Pistol.directionPistolero = 2;
			//witch
			Sniper.vie = true;
			Sniper.posWitchX = 21;
			Sniper.posWitchY =19;
			Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
		}
		//Level 2-3
		else if(compteurLevel == 22 && Hero.heroHitBox.intersects(Hero.limiteDroite)){
			compteurLevel = 23;
			//Hero
			Hero.heroPosX = 2;
			//Bloqueur
			Enemy.enemyPosX = 32;
			Enemy.enemyPosY = 13;
			Enemy.vie = true;
			//pistol
			Pistol.pistoleroPosX = 30;
			Pistol.pistoleroPosY = 14;
			Pistol.pistolero.setLocation(Pistol.pistoleroPosX*32, Pistol.pistoleroPosY*32);
			Pistol.vie = true;
			Pistol.directionPistolero = 2;
			//witch
			Sniper.vie = true;
			Sniper.posWitchX = 38;
			Sniper.posWitchY =3;
			Sniper.sniper.setLocation(Sniper.posWitchX*32, Sniper.posWitchX*32);
		}
		//Winning screen
		else if (compteurLevel == 23 && Hero.heroHitBox.intersects(Hero.limiteDroite)) {
			compteurLevel = 1001;
			Input input = gc.getInput();
			//Recommencer
			if (input.isKeyPressed(Input.KEY_ENTER)) {
				compteurLevel = 0;
				InterfacesEnJeu.nombreVieRestante = 3;
			}
		}
		
		//Game Over
		else if (compteurLevel == 1000) {
			Input input = gc.getInput();
			Hero.invincibilite = true;
			//Recommencer
			if (input.isKeyPressed(Input.KEY_ENTER)) {
				compteurLevel = 0;
				InterfacesEnJeu.nombreVieRestante = 3;
			}
		}
		else if (compteurLevel == 0){
			
		}
	}
}
