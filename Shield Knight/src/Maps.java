package src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Maps {
	static TiledMap map1;

	//------------------------------------------------------------------M�THODE D'INTIALISATION------------------------------------------------------------------
	public void init(GameContainer gc) throws SlickException {
		//initialisation de la mapTest
		map1 = new TiledMap("./Maps/mapTest2.tmx");
	}
	
	//------------------------------------------------------------------M�THODE RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map1.render(0, 0);
	}

	//------------------------------------------------------------------M�THODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) throws SlickException {
		


	}
}
