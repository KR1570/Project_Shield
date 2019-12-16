package interfacejeu;

import org.newdawn.slick.*;
import src.Boutton;
import src.Maps;

public class Menu{
	public static boolean gameOverBool = false;
	public static int chapitreLevel = 1;
	
	public Menu(){
		try {
			init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void init () throws SlickException 
	{
		chapitreLevel = 1;
	}
	public void render(GameContainer gc,Graphics g)
	{
	}
	public void update(GameContainer gc, int delta) {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER) && Maps.compteurLevel <= 0) {
			if (chapitreLevel == 1)
			{
				Maps.compteurLevel = 1;
				gameOverBool = true;
				InterfacesEnJeu.nombreVieRestante = 3;
			}
			else if (chapitreLevel == 2) {
				Maps.compteurLevel = 21;
				gameOverBool = true;
				InterfacesEnJeu.nombreVieRestante = 3;
			}
		}
		if (input.isKeyDown(Input.KEY_ESCAPE) && Maps.compteurLevel == 0){
			System.exit(0);
		}
	}	
}