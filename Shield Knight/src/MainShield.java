package src;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainShield {

	public static void main(String[] args) {
		
		int largeurAffichage = 1440;
		int hauteurAffichage = 768;
		boolean siPleinEcran = false;
		try {
			AppGameContainer app = new AppGameContainer(new Jeu("Shield Knight"));
			app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
			app.start();
			
		}
		catch(SlickException ex){
			Logger.getLogger(MainShield.class.getName()).log(Level.SEVERE, null, ex);
			
		}
	}

}
