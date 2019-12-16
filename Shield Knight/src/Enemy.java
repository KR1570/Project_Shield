package src;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import src.Hero.Direction;

public class Enemy {
	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	public static Sound shot;
	private int enemyHP;
	static float enemyPosX;
	static float enemyPosY;
	static boolean reverse;
	private Image swatR;
	private Image swatL;
	static Rectangle enemy;
	static Line limite1;
	static Line limite2;
	private TiledMap buffer;
	private Direction direction;
	private Animation anim_RIGHT;
	private Animation anim_LEFT;
	public static boolean vie = true;
	//Animation droite
	private Animation getAnimationR(int rowX, int rowY, int frames) {
		Animation anim = new Animation(false);
		for (int x = 0; x < rowX; x++) {
			anim.addFrame(swatR.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}
	//Animation gauche
	private Animation getAnimationL(int rowX, int rowY, int frames) {
		Animation anim = new Animation(false);
		for (int x = 0; x < rowX; x++) {
			anim.addFrame(swatL.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}
	public Enemy() {
		try {
			init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public Enemy(int enemyHP,int enemyPosX,int enemyPosY) {
		try {
			init();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.enemyHP = enemyHP;
		this.enemyPosX= enemyPosX;
		this.enemyPosY = enemyPosY;
	}
	
//-------------------------------------------------------INIT------------------------------------------------------------------
	public void init() throws SlickException{
		direction = Direction.RIGHT;
		enemyPosX = 12;
		enemyPosY = 11f;
		enemyHP = 3;
		enemy = new Rectangle(enemyPosX*32,enemyPosY*32,32,64);
		reverse = true;
		swatR = new Image("./Sprites/Gladiator-Sprite Sheet.png");
		swatL = new Image("./Sprites/Gladiator-Sprite Sheet-Left.png");
		shot = new Sound("./Audio/shot.wav");
		anim_LEFT = getAnimationR(8, 1, 100);
		anim_RIGHT = getAnimationL(8, 1,100);
	}
	
//-------------------------------------------------------RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g)  {
		if (vie == true)
		{
			g.setColor(Color.transparent);
			g.draw(enemy);
			getSwat(direction).draw(enemyPosX*32-24,((enemyPosY)*32-24),96, 96);
		}
	}
	
//-------------------------------------------------------UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		//Animations
		switch (direction) {
			case RIGHT:
				anim_RIGHT.update(delta);
				break;
			case LEFT:
				anim_LEFT.update(delta);
				break;
		}
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
		if(Maps.compteurLevel == 21) {
			buffer = Maps.mapLevel21;
		}
		if(Maps.compteurLevel == 22) {
			buffer = Maps.mapLevel22;
		}
		if(Maps.compteurLevel == 23) {
			buffer = Maps.mapLevel23;
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
			direction = Direction.LEFT;
			enemyPosX +=0.03;
			enemy.setLocation(enemyPosX*32, enemyPosY*32);
		}
		if (!reverse && !enemy.intersects(Bouclier.bouclierHitBox)) {
			direction = Direction.RIGHT;
			enemyPosX -=0.03;
			enemy.setLocation(enemyPosX*32, enemyPosY*32);
		}
	}
	public Animation getSwat(Direction direction) {
		Animation anim = new Animation(false);
		switch (direction) {
			case LEFT:
				anim = anim_LEFT;
				break;
			case RIGHT:
				anim = anim_RIGHT;
				break;
		}
		return anim;
	}
}