package interfacejeu;

import src.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class InterfacesEnJeu {

	public Image vie;
	public int vieX;
	public int vieY;
	public static int nombreVieRestante = 3;
	public Image pause;
	
	public InterfacesEnJeu(){
		try {
			init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	public void init () throws SlickException 
	{
		vie = new Image("./images/CoeurDeVie.png");
		pause = new Image("./images/ExitIcon.png");
		vieX = 16;
		vieY = 16;
	}
	public void render(GameContainer gc,Graphics g)
	{
		for(int x = 1; x<= nombreVieRestante; x++)
		{
			vie.draw(vieX+(x*32),vieY, 64, 64);
		}
		pause.draw(1360,16, 64, 64);
	}
	public void update(GameContainer gc, int delta) {
		if(nombreVieRestante == 0) {
			Maps.compteurLevel = 1000;
		}
			
	}	
}
