package src;


import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class enemy {
	private int enemyHP;
	private float enemyPosX;
	private float enemyPosY;
	private boolean reverse;
	private Rectangle enemy;
	private Line limite1;
	private Line limite2;
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
		enemyPosX = 1;
		enemyPosY = 12;
		enemyHP = 3;
		limite1 = new Line(14*32,14*32,14*32,12*32);
		limite2 = new Line(0,14*32,0*32,12*32);
		//limite1.setLocation(13*32, 12*32);
		enemy = new Rectangle(enemyPosX*32,enemyPosY*32,32,32);
		reverse = true;
		
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------

	public void render(GameContainer gc, Graphics g)  {
		
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.draw(enemy);
		g.setColor(Color.transparent);
		g.draw(limite1);
		g.draw(limite2);
	}
	
//-------------------------------------------------------UPDATE------------------------------------------------------------------



	public void update(GameContainer gc, int delta){
		// TODO Auto-generated method stub
		int platformes = Jeu.mapTest.getLayerIndex("Platformes");
		if(!enemy.intersects(limite1)&& reverse) {
			enemyPosX +=0.1;
		enemy.setLocation(enemyPosX*32, enemyPosY*32);

 }
		else {
			reverse = false;
		}
		if(!enemy.intersects(limite2) && reverse == false) {
			enemyPosX -=0.1;
		enemy.setLocation(enemyPosX*32, enemyPosY*32);
		}
		else {
			reverse = true;
		}
		
	}
}	
