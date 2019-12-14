package src;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import src.Hero.Direction;

public class Enemy {
	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	private int enemyHP;
	static float enemyPosX;
	static float enemyPosY;
	static boolean reverse;
	private Image swat;
	static Rectangle enemy;
	static Line limite1;
	static Line limite2;
	private TiledMap buffer;
	private Direction direction;
	public Enemy() {
		init();
	}
	public Enemy(int enemyHP,int enemyPosX,int enemyPosY) {
		init();
		this.enemyHP = enemyHP;
		this.enemyPosX= enemyPosX;
		this.enemyPosY = enemyPosY;
		// TODO Auto-generated constructor stub
	}
//-------------------------------------------------------INIT------------------------------------------------------------------
	public void init(){
		direction = Direction.RIGHT;
		enemyPosX = 12;
		enemyPosY = 11f;
		enemyHP = 3;
		enemy = new Rectangle(enemyPosX*32,enemyPosY*32,32,64);
		reverse = true;
		
	}
	
//-------------------------------------------------------RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g)  {
		g.setColor(Color.transparent);
		g.draw(enemy);
		getSwat(direction).draw(enemyPosX*32,((enemyPosY)*32),2);
	}
	
//-------------------------------------------------------UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		//Maps Level 1
		if(Maps.compteurLevel == 1) {
			buffer = Maps.mapLevel1;
		}
		if(Maps.compteurLevel == 2) {
			buffer = Maps.mapLevel2;
		}
		if(Maps.compteurLevel == 3) {
			buffer = Maps.mapLevel3;
		}
		//buffer des maps
		int sol = buffer.getLayerIndex("Sol");
		//Droite virage à gauche
		if(buffer.getTileId(Math.round(enemyPosX)+1, Math.round(enemyPosY) +2, sol) == 0 && reverse || enemy.intersects(Hero.limiteDroite) ) {
			reverse = false;
		}
		//Gauche virage à droite
		if(buffer.getTileId(Math.round(enemyPosX)-1, Math.round(enemyPosY) +2 , sol) == 0 && !reverse || enemy.intersects(Hero.limiteGauche)) {
			reverse = true;
		}
		//Mouvements
		if (reverse && !enemy.intersects(Bouclier.bouclierHitBox)) {
			direction = Direction.RIGHT;
			enemyPosX +=0.03;
			enemy.setLocation(enemyPosX*32, enemyPosY*32);
		}
		if (!reverse && !enemy.intersects(Bouclier.bouclierHitBox)) {
			direction = Direction.LEFT;
			enemyPosX -=0.03;
			enemy.setLocation(enemyPosX*32, enemyPosY*32);
		}
	}
	public Image getSwat(Direction direction) {
		switch (direction) {
		case RIGHT:
			try {
				swat = new Image("./images/swatR.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			break;
		case LEFT:
			try {
				swat = new Image("./images/swatL.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		break;
		}
		return swat;
		
	}
}