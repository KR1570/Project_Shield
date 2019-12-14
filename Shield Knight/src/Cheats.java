package src;

import java.awt.Font;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

public class Cheats{
private TextField console;
private int consolePosX;
private int consolePosY;

	public Cheats() {
	//init();
		// TODO Auto-generated constructor stub
	}

	public void init(GameContainer gc)  {
		// TODO Auto-generated method stub
		consolePosX=10*32;
		consolePosY=10*32;
		console = new TextField(gc,gc.getDefaultFont(), Font.BOLD, consolePosX, consolePosY, 30);
		
	}
	
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		console.render(gc, g);
	}



	public void update(GameContainer gc, int delta)  {
		// TODO Auto-generated method stub
		
	}

	public void noClip(float posX,float posY,GameContainer gc) {
		Input input = gc.getInput();
		//SPACE pour sauter
		if (input.isKeyDown(Input.KEY_W) ) {
	//		if(Jeu.mapTest.getTileId(heroPosX , heroPosY - 32, sol) == 0 && Jeu.mapTest.getTileId(heroPosX , heroPosY - 32, platformes) == 0 && sautCompteur != 3) {

				posY -= 64;
	//		}
		}
		//A pour aller a gauche
		if (input.isKeyDown(Input.KEY_A) ) {
			posX -= 32;
		}
		//S pour dessendre
		if (input.isKeyDown(Input.KEY_S) ) {
	//		if(Jeu.mapTest.getTileId(heroPosX , heroPosY + 32, sol) == 0 && Jeu.mapTest.getTileId(heroPosX , heroPosY + 32, platformes) == 0) {
				posY += 32;
	//		}
		}
		//D pour aller a droite
		if (input.isKeyDown(Input.KEY_D) ) {
			posX += 32;
		}
		
	}

}
