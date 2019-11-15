package src;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.sun.javafx.scene.traversal.Direction;
import com.sun.prism.Graphics;

public class Hero {

	//Variable du hero
	private int heroHP;
	private int heroPosX;
	private int heroPosY;
	private Image carreImage;
	
	public Hero() {
		
	}
	
	public Hero(int heroHP, int heroPosX, int heroPosY) {
		this.heroHP = heroHP;
		this.heroPosX = heroPosX;
		this.heroPosX = heroPosY;
	}
	
	//Init 
	public void init () throws SlickException {
		carreImage = new Image("./images/Square.png");
	}

	//Render
	public void render(GameContainer gc, Graphics g, Direction direction) {
		carreImage.draw(10,10);
	}
	
	//Update
	public void update(int i) {

	}
}
