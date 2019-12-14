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

import src.Enemy.Direction;

public class Bouclier {
	
	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	private float bouclierPosX;
	private float bouclierPosY;
	public Bouclier() {
		init();
	}
	//Bouclier
	static boolean bouclierUp;
	static Rectangle bouclierHitBox;
	static Image bouclierImage;
	
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
	Direction direction;

//-------------------------------------------------------INIT------------------------------------------------------------------
	public void init(){
		direction = Direction.RIGHT;
	}
//-------------------------------------------------------RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g)  {
		g.setColor(Color.black);
		g.draw(getBouclierHitBox(direction));
		//g.draw(getBouclierImage(direction));
		//getBouclierImage(direction).draw(Hero.heroPosX*32+56,Hero.heroPosY*32,24,48);
	}
//-------------------------------------------------------UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta){
		//Activer les inputs
		Input input = gc.getInput();
		//Fleche de droite
		if (input.isKeyDown(Input.KEY_LEFT )) {
			bouclierUp = false;
			System.out.println(bouclierUp);
			direction = Direction.LEFT;
		}
		//Fleche de gauche
		else if (input.isKeyDown(Input.KEY_RIGHT )) {
			bouclierUp = false;
			System.out.println(bouclierUp);
			direction = Direction.RIGHT;
		}
		//Fleche du haut
		else if (input.isKeyDown(Input.KEY_UP )) {
			bouclierUp = true;
			System.out.println(bouclierUp);
			direction = Direction.UP;
		}
		else
		{
			direction = Direction.DOWN;
		}
	}
	//Fonction qui créé l'objet de bouclier et lui donne ses dimensions
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
				bouclierImage = new Image("./images/bouclierRight.png");
				break;
			case LEFT:
				bouclierImage = new Image("./images/bouclierLeft.png");
				break;
			case UP:
				bouclierImage = new Image("./images/bouclierUp.png");
				break;
			case DOWN:
				break;
		}
		return bouclierImage;
	}
}
