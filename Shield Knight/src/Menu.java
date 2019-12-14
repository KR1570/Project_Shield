package src;

import org.newdawn.slick.*;

public class Menu{
	
	private Boutton buttonPlay;
	private Boutton buttonHelp;
	private Boutton buttonExit;
	private Image boutton;
	
	public Menu(){
		try {
			init();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init () throws SlickException 
	{
		//Boutton buttonPlay = new Boutton("./images/PressEnterToPlay.png", 10, 10, 20 ,20);
		//Boutton buttonHelp = new Boutton("./images/knightRight.png", 10, 20, 20 ,20);
		//Boutton buttonExit = new Boutton("./images/knightRight.png", 10, 30, 20 ,20);
		boutton = new Image("./images/PressEnterToPlay.png");
	}
	public void render(GameContainer gc,Graphics g)
	{
		boutton.draw(320,32);
		//buttonPlay.render();
		//buttonHelp.render();
		//buttonExit.render();
	}
	public void update(GameContainer gc, int delta) {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER) && Maps.compteurLevel == 0) {
			Maps.compteurLevel++;
		}
		if (input.isKeyDown(Input.KEY_ESCAPE) && Maps.compteurLevel == 0){
			System.exit(0);
		}
	}	
}