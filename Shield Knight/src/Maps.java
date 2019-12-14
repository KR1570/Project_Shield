package src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import javafx.scene.shape.Line;

public class Maps {
	private TiledMap mapMenu;
	static TiledMap mapLevel1;
	static TiledMap mapLevel2;
	static TiledMap mapLevel3;
	
	static int compteurLevel = 0;
	

	public Maps(){
		init();
	}
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	public void init() {
		try {
			mapLevel1 = new TiledMap("./Maps/level1-1.tmx");
			mapLevel2 = new TiledMap("./Maps/level1-2.tmx");
			mapLevel3 = new TiledMap("./Maps/level1-3.tmx");
			mapMenu = new TiledMap("./Maps/menu.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g){
		
		//Render de la map
		switch(compteurLevel) {
			case 0 :
				mapMenu.render(0, 0);
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
		}
		
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		//Level 1-1
		if(compteurLevel == 2 && Hero.heroHitBox.intersects(Hero.limiteGauche)){
			compteurLevel = 1;
			Enemy.enemyPosX = 12;
			Enemy.enemyPosY = 11;
			Hero.heroPosX = 40;
		}
		//Level 1-2
		else if(compteurLevel == 1 && Hero.heroHitBox.intersects(Hero.limiteDroite)){
			compteurLevel = 2;
			Hero.heroPosX = 2;
			Enemy.enemyPosX = 24;
			Enemy.enemyPosY = 15;
			Enemy.enemy.setLocation(Enemy.enemyPosX*32, Enemy.enemyPosY*32);
		}
		else if (compteurLevel == 3 && Hero.heroHitBox.intersects(Hero.limiteGauche)){
			compteurLevel = 2;
			Enemy.enemyPosX = 24;
			Enemy.enemyPosY = 15;
			Hero.heroPosX = 40;
		}
		//Level 1-3
		else if(compteurLevel == 2 && Hero.heroHitBox.intersects(Hero.limiteDroite)){
			compteurLevel = 3;
			Enemy.enemyPosX = 12;
			Enemy.enemyPosY = 4;
			Hero.heroPosX = 2;
		}
	}
}
