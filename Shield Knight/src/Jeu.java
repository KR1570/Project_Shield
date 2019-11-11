package src;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.*;

public class Jeu extends BasicGame{

	//Variables
	private TiledMap mapTest;
	
	//Constructeur du jeu
	public Jeu(String title) 
	{
		super(title);
		
	}
	
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	@Override
	public void init(GameContainer gc) throws SlickException {
		try {
		//initialisation de la mapTest
		mapTest = new TiledMap("./mapTest.tmx");
		}
		catch (Exception e) 
		{
			System.out.println("Problème dans l'intialisation du jeu");
		}
		
	}
	
	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//Dessin de la map
		mapTest.render(0, 0);
		
	}

	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		//Initialisation des claques de notre map test
		/*int indexClaquePlatformes = mapTest.getLayerIndex("Platformes");
		int indexCalqueFond = mapTest.getLayerIndex("Fond");
		int indexCalqueSol = mapTest.getLayerIndex("Sol");*/
		
	}

}
