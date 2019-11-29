package src;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class enemy {
	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	private int enemyHP;
	static float enemyPosX;
	float enemyPosY;
	static boolean reverse;
	private Image swat;
	Rectangle enemy;
	private Line limite1;
	private Line limite2;
	private Direction direction;
	public enemy() {
		init();
	}
	public enemy(int enemyHP,int enemyPosX,int enemyPosY) {
		init();
		this.enemyHP = enemyHP;
		this.enemyPosX= enemyPosX;
		this.enemyPosY = enemyPosY;
		// TODO Auto-generated constructor stub
	}
//-------------------------------------------------------INIT------------------------------------------------------------------
	public void init(){
		// TODO Auto-generated method stub
		direction = Direction.RIGHT;
		enemyPosX = 8;
		enemyPosY = 11f;
		enemyHP = 3;
		limite1 = new Line(16*31,14*32,16*31,12*32);
		limite2 = new Line(8*33,14*32,8*33,12*32);
		//limite1.setLocation(13*32, 12*32);
		enemy = new Rectangle(enemyPosX*32,enemyPosY*32,32,64);
		reverse = true;
		
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------

	public void render(GameContainer gc, Graphics g)  {
		
		// TODO Auto-generated method stub
		g.setColor(Color.transparent);
		g.draw(enemy);
		g.draw(limite1);
		g.draw(limite2);
		getSwat(direction).draw(enemyPosX*32,((enemyPosY)*32),2);
	}
	
//-------------------------------------------------------UPDATE------------------------------------------------------------------



	public void update(GameContainer gc, int delta){
		// TODO Auto-generated method stub
		int platformes = Jeu.mapTest.getLayerIndex("Platformes");
		if(!enemy.intersects(limite1)&& reverse && !enemy.intersects(Bouclier.bouclierHitBox) && reverse) {
			direction = Direction.RIGHT;
			enemyPosX +=0.03;
			enemy.setLocation(enemyPosX*32, enemyPosY*32);
		}
		else {
			reverse = false;
		}
		if(!enemy.intersects(limite2) && reverse == false && !enemy.intersects(Bouclier.bouclierHitBox) && reverse == false) {
			direction = Direction.LEFT;
			enemyPosX -=0.03;
			enemy.setLocation(enemyPosX*32, enemyPosY*32);
		}
		else {
			reverse = true;
		}
		System.out.println("X" + enemyPosX);
		System.out.println("Y" + enemyPosY);
	}
	public Image getSwat(Direction direction) {
		switch (direction) {
		case RIGHT:
			try {
				swat = new Image("./images/swatR.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case LEFT:
			
			try {
				swat = new Image("./images/swatL.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		}
		return swat;
		
	}
}	
