package src;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import src.enemy.Direction;

public class Hero {
	//Direction du hero
	enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	private Direction direction;
	private boolean droite;
	private boolean gauche;
	//Variable du hero
	private int heroHP;
	static float heroPosX = 22;
	static float heroPosY = 10;
	private Image carreImage;
	float heroX;
	float heroY;
	//Variable sauter
	private boolean jumping = false;
	float gravite;
	private float sautCompteur = 0;
	private Vector2f velocite;
	private Point position;
	//Variable grosseur tuile
	private float scale = 32;
	//Hitbox
	Rectangle heroHitBox;

	//Ligne 
	private Line limiteEau;
	private Line limiteGauche;
	private Line limiteDroite;
	//Ennemy
	private enemy enemy1;
	public Hero() {
		init();
	}
	
	public Hero(int heroHP, int heroPosX, int heroPosY){
		this.heroHP = heroHP;
		this.heroPosX = heroPosX; 
		this.heroPosX = heroPosY;
		init();
	}
	
	//------------------------------------------------------------------MÉTHODE D'INTIALISATION------------------------------------------------------------------
	public void init (){
			//initialisation d'un cote pour commencer
			direction = Direction.RIGHT;
			//intialisation de la position du hero
			//carreImage = new Image("./images/knight.png");
			heroHitBox = new Rectangle(heroPosX*32,heroPosY*32,48,48);
			//Limites de la map
			limiteGauche = new Line(20,0,20,768);
			limiteDroite = new Line(1420,0,1420,768);
			limiteEau = new Line(0,748,1440,748);
			//Enemie
			enemy1 = new enemy();
			//Vecteur de saut
			velocite = new Vector2f(heroPosX+1,heroPosY+4);
			position = new Point(heroPosX,heroPosY);
	}

	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g) {
		//red ou transparent
		g.setColor(Color.transparent);
		//Dessin de toutes les formes
		getCarreImage(direction).draw(heroPosX * scale, heroPosY * scale, 0.16f);
		g.draw(heroHitBox);
		g.draw(limiteEau);
		g.draw(limiteGauche);
		g.draw(limiteDroite);
		g.draw(enemy1.enemy);
	}
	
	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		int sol = Jeu.mapTest.getLayerIndex("Sol");
		int fond = Jeu.mapTest.getLayerIndex("Fond");
		enemy1.update(gc, delta);
		//HitBox mouvement
		heroHitBox.setLocation(heroPosX*32,heroPosY*32);
		//Gravité
		if(Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) == 0 && !heroHitBox.intersects(Bouclier.bouclierHitBox)) {
			heroPosY += 0.13f;
		}
		//Activer les inputs
		Input input = gc.getInput();
		//SPACE pour sauter
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) - 1, sol) == 0 ) {
				if (sautCompteur <= 8.0f) {
					heroPosY -= 0.3f;
					sautCompteur+= 0.2f;
				}
			}
		}
		//A pour aller a gauche
		if (input.isKeyDown(Input.KEY_A) ) {
			direction = Direction.LEFT;
			if(Jeu.mapTest.getTileId(Math.round(heroPosX) - 1, Math.round(heroPosY), sol) == 0 && !heroHitBox.intersects(limiteGauche)) {
				heroPosX -= 0.12f;
				sautCompteur+= 0.05f;
				gauche = true;
				droite = false;
			}
		}
		//D pour aller a droite
		if (input.isKeyDown(Input.KEY_D) ) {
			direction = Direction.RIGHT;
			if(Jeu.mapTest.getTileId(Math.round(heroPosX) + 1, Math.round(heroPosY), sol) == 0 && !heroHitBox.intersects(limiteDroite)) {
				heroPosX += 0.12f;
				sautCompteur+= 0.05f;
				droite = true;
				gauche = false;
			}
		}
		//Reset le saut
		if (Jeu.mapTest.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) != 0 || heroHitBox.intersects(Bouclier.bouclierHitBox)) {
			sautCompteur =0;
		}
		//Limites du bas
		if(heroHitBox.intersects(limiteEau)) {
			heroPosX = 5;
			heroPosY = 15;
		}
		if(heroHitBox.intersects(enemy1.enemy) && enemy.reverse == false) {
			heroPosX = enemy.enemyPosX - 1.0f;
		}
		if(heroHitBox.intersects(enemy1.enemy) && enemy.reverse == true) {
			heroPosX = enemy.enemyPosX + 1.0f;
		}
	}
	//Direction du hero avec images
	public Image getCarreImage(Direction direction) {
		switch (direction) {
		case RIGHT:
			try {
				carreImage = new Image("./images/knightRight.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			break;
		case LEFT:
			
			try {
				carreImage = new Image("./images/knightLeft.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		break;
		}
		return carreImage;
	}
	
}