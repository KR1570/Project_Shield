package src;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import projectile.Pistol;
import projectile.SniperBullet;
import src.Enemy.Direction;

public class Bouclier {
	
	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	private float bouclierPosX;
	private float bouclierPosY;
	private boolean hit =false;
	private boolean hits=false;
	private Pistol pistol;
	private SniperBullet sniper;
	public Bouclier() {
		init();
	}
	//Bouclier
	static boolean bouclierUp;
	public static Rectangle bouclierHitBox;
	static Image bouclierImage;
	private Direction direction;

//-------------------------------------------------------INIT------------------------------------------------------------------
	public void init(){
		setDirection(Direction.RIGHT);
		pistol = new Pistol();
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g)  {
		g.setColor(Color.transparent);
		g.draw(getBouclierHitBox(getDirection()));
		try {
			
			getBouclierImage(direction).draw(getBouclierHitBox(direction).getCenterX()-32, getBouclierHitBox(direction).getCenterY()-32,0.7f);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta){
		//Activer les inputs
		Input input = gc.getInput();
		//Fleche de droite
		if (input.isKeyDown(Input.KEY_LEFT )) {
			bouclierUp = false;
			System.out.println(bouclierUp);
			setDirection(Direction.LEFT);
		}
		//Fleche de gauche
		else if (input.isKeyDown(Input.KEY_RIGHT )) {
			bouclierUp = false;
			System.out.println(bouclierUp);
			setDirection(Direction.RIGHT);
		}
		//Fleche du haut
		else if (input.isKeyDown(Input.KEY_UP )) {
			bouclierUp = true;
			System.out.println(bouclierUp);
			setDirection(Direction.UP);
		}
		else
		{
			setDirection(Direction.DOWN);
		}
	}
	//Fonction qui cr�� l'objet de bouclier et lui donne ses dimensions
	public Rectangle getBouclierHitBox(Direction direction){
		bouclierPosX= Hero.getHeroPosX();
		bouclierPosY= Hero.getHeroPosY();
		switch (direction) {

			case RIGHT:
				bouclierHitBox = new Rectangle(bouclierPosX*32+56,bouclierPosY*32,24,48);
				break;
			case LEFT:
				bouclierHitBox = new Rectangle(bouclierPosX*32-25,bouclierPosY*32,24,48);
				break;
			case UP:
				bouclierHitBox = new Rectangle(bouclierPosX*32+8,bouclierPosY*32-32,48,24);
				break;
			case DOWN :
				break;
		}
		return bouclierHitBox;
	}
	
	public Image getBouclierImage(Direction direction) throws SlickException{
		switch (direction) {
			case RIGHT:
				bouclierImage = new Image("./images/bouclierR.png");
				break;
			case LEFT:
				bouclierImage = new Image("./images/bouclierL.png");
				break;
			case UP:
				bouclierImage = new Image("./images/bouclierU.png");
				break;
			case DOWN:
				break;
		}
		return bouclierImage;
	}
	public boolean isHitPistol() {
		if(bouclierHitBox.intersects(pistol.getBullet())) {
			hit = true;
		}
		else {
			hit = false;
		}
		return hit;
	}
	public boolean isHitSniper() {
		if(bouclierHitBox.intersects(sniper.getBullet())) {
			hits = true;
		}
		else {
			hits = false;
		}
		return hits;
	}
	//Getter/Setter
	public float getBouclierPosX() {
		return bouclierPosX;
	}
	public void setBouclierPosX(float bouclierPosX) {
		this.bouclierPosX = bouclierPosX;
	}
	public float getBouclierPosY() {
		return bouclierPosY;
	}
	public void setBouclierPosY(float bouclierPosY) {
		this.bouclierPosY = bouclierPosY;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}