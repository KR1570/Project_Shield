package src;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.tiled.TiledMap;

import src.Enemy.Direction;
import interfacejeu.InterfacesEnJeu;
public class Hero {
	//Direction du hero
	enum Direction{
		UP, DOWN, LEFT, RIGHT, NOTHING
	}
	private Direction direction;
	private Animation anim_UP;
	private Animation anim_DOWN;
	private Animation anim_RIGHT;
	private Animation anim_LEFT;
	private Animation anim_NOTHINGLEFT;
	private Animation anim_NOTHING;
	private boolean droite;
	private boolean gauche;
	//Variable du hero
	private int heroHP;
	static float heroPosX = 2;
	static float heroPosY = 18;
	private Image heroImage;
	float heroX;
	float heroY;
	private Animation getAnimation(int rowX, int rowY, int frames) {
		Animation anim = new Animation(false);
		for (int x = 0; x < rowX; x++) {
			anim.addFrame(heroImage.getSubImage(x * 32, rowY * 32, 32, 32),frames);
		}
		return anim;
	}
	//Variable sauter
	private boolean jumping = false;
	float gravite;
	private float sautCompteur = 0;
	private Vector2f velocite;
	private Point position;
	//Variable grosseur tuile
	private float scale = 32;
	//Hitbox
	static Rectangle heroHitBox;

	//Ligne 
	static Line limiteEau;
	static Line limiteGauche;
	static Line limiteDroite;
	private Line limiteHaut;
	//Ennemy
	private Enemy enemy1;
	static TiledMap buffer;
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
		try {
			//initialisation d'un cote pour commencer
			direction = Direction.NOTHING;
			//intialisation de la position du hero
			//heroImage = new Image("./images/knight.png");
			heroHitBox = new Rectangle(heroPosX*32,heroPosY*32,48,48);
			//Limites de la map
			limiteHaut = new Line(0,24,1440,24);
			limiteGauche = new Line(20,0,20,768);
			limiteDroite = new Line(1420,0,1420,768);
			limiteEau = new Line(0,748,1440,748);
			//Enemie
			enemy1 = new Enemy();
			//Vecteur de saut
			velocite = new Vector2f(heroPosX+1,heroPosY+4);
			position = new Point(heroPosX,heroPosY);
			//Hero
			heroImage = new Image("./Sprites/Adventurer Sprite Sheet v1.1.png");
			anim_UP = getAnimation(5, 5,120);
			anim_LEFT = getAnimation(8, 9,50);
			anim_NOTHING = getAnimation(12, 8,100);
			anim_RIGHT = getAnimation(8, 1,50);
			anim_NOTHING = getAnimation(12, 0,100);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	//------------------------------------------------------------------MÉTHODE RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g) {
		//couleur des formes red ou transparent
		g.setColor(Color.transparent);
		//Dessin de toutes les formes
		getCarreImage(direction).draw(heroPosX * 32, heroPosY * 32 - 26,84,84);
		g.draw(heroHitBox);
		g.draw(limiteEau);
		g.draw(limiteGauche);
		g.draw(limiteDroite);
		g.draw(enemy1.enemy);
		g.draw(limiteHaut);
	}
	//------------------------------------------------------------------MÉTHODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		//Position
		//System.out.println("X : " + heroPosX);
		//System.out.println("Y : " + heroPosY);
		//animation 
		switch (direction) {
		case UP:
			anim_UP.update(delta);
			break;
		case NOTHING :
			anim_NOTHING.update(delta);
			break;
		case RIGHT:
			anim_RIGHT.update(delta);
			break;
		case LEFT:
			anim_LEFT.update(delta);
			break;
		}
		//Maps Level 1
		if(Maps.compteurLevel == 1) {
			buffer = Maps.mapLevel1;
		}
		if(Maps.compteurLevel == 2) {
			buffer = Maps.mapLevel2;
		}
		if(Maps.compteurLevel == 3) {
			buffer = Maps.mapLevel3;
		}
		//Maps level 2
		int sol = buffer.getLayerIndex("Sol");
		enemy1.update(gc, delta);
		//HitBox mouvement
		heroHitBox.setLocation(heroPosX*32,heroPosY*32);
		//Gravité
		if(buffer.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) != 0 || (heroHitBox.intersects(Bouclier.bouclierHitBox) && Bouclier.bouclierUp)) {
		}
		else {
			heroPosY += 0.13f;
		}
		//Activer les inputs
		Input input = gc.getInput();
		//SPACE pour sauter
		if (input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_W)) {
			direction = Direction.UP;
			if(buffer.getTileId(Math.round(heroPosX) , Math.round(heroPosY) - 1, sol) == 0 && !heroHitBox.intersects(limiteHaut)) {
				if (sautCompteur <= 8.0f) {
					heroPosY -= 0.3f;
					sautCompteur+= 0.2f;
				}
			}
		}
		else {
			direction = Direction.NOTHING;
		}
		//A pour aller a gauche
		if (input.isKeyDown(Input.KEY_A) ) {
			direction = Direction.LEFT;
			if(buffer.getTileId(Math.round(heroPosX) - 1, Math.round(heroPosY), sol) == 0 && !heroHitBox.intersects(limiteGauche)) {
				heroPosX -= 0.12f;
				sautCompteur+= 0.05f;
				gauche = true;
				droite = false;
			}
		}
		//D pour aller a droite
		else if (input.isKeyDown(Input.KEY_D) ) {
			direction = Direction.RIGHT;
			if(buffer.getTileId(Math.round(heroPosX) + 1, Math.round(heroPosY), sol) == 0 && !heroHitBox.intersects(limiteDroite)) {
				heroPosX += 0.12f;
				sautCompteur+= 0.05f;
				droite = true;
				gauche = false;
			}
		}
		else {
			direction = Direction.NOTHING;
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE) ) {
			Maps.compteurLevel = 0;
		}
		
		
		//Reset le saut
		if (buffer.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) != 0 || (heroHitBox.intersects(Bouclier.bouclierHitBox) && Bouclier.bouclierUp == true)) {
			sautCompteur =0;
		}
		//Limites du bas
		if(heroHitBox.intersects(limiteEau)) {
			heroPosX = 5;
			heroPosY = 15;
			InterfacesEnJeu.nombreVieRestante--;
			System.out.println(InterfacesEnJeu.nombreVieRestante);
		}
		//Ennemie swat
		if(heroHitBox.intersects(enemy1.enemy) && Enemy.reverse == false) {
			heroPosX = Enemy.enemyPosX - 1.5f;
		}
		else if(heroHitBox.intersects(enemy1.enemy) && Enemy.reverse) {
			heroPosX = Enemy.enemyPosX + 1.5f;
		}
}
	//Direction du hero avec images
	public Animation getCarreImage(Direction direction) {
		Animation anim = new Animation(false);
		switch (direction) {
			case UP:
				anim = anim_UP;
				break;
			case DOWN:
				anim = anim_DOWN;
				break;
			case LEFT:
				anim = anim_LEFT;
				break;
			case RIGHT:
				anim = anim_RIGHT;
				break;
			case NOTHING:
				anim = anim_NOTHING;
				break;
		}
		return anim;
	}
	
}