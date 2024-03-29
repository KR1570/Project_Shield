package src;
import projectile.Pistol;
import src.Enemy.Direction;
import projectile.Sniper;
import projectile.SniperBullet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.tiled.TiledMap;
import interfacejeu.*;
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
	private boolean hitSniper = false;
	private boolean hitPistol = false;
	private boolean droite;
	private boolean gauche;
	public static boolean mapBool = false;
	//Variable du hero
	public static boolean invincibilite = false;
	private int heroHP;
	static float heroPosX = 2;
	static float heroPosY = 18;
	private Image heroImage;
	public static float heroX;
	public static float heroY;
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
	private Sound sauter;
	//Variable grosseur tuile
	private float scale = 32;
	//Hitbox
	public static Rectangle heroHitBox;
	//Ligne 
	static Line limiteEau;
	static Line limiteGauche;
	static Line limiteDroite;
	static Line limiteHaut;
	//Ennemy
	private Enemy enemy1;
	private SniperBullet sniper;
	private Pistol pistol;
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
	
	//------------------------------------------------------------------M�THODE D'INTIALISATION------------------------------------------------------------------
	public void init (){
		try {
			//initialisation d'un cote pour commencer
			direction = Direction.NOTHING;
			//intialisation de la position du hero
			//heroImage = new Image("./images/knight.png");
			setHeroHitBox(new Rectangle(heroPosX*32,heroPosY*32,48,48));
			//Limites de la map
			limiteHaut = new Line(0,60,1440,60);
			limiteGauche = new Line(36,0,36,768);
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
			sauter = new Sound("./Audio/Jump.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	//------------------------------------------------------------------M�THODE RENDER------------------------------------------------------------------
	public void render(GameContainer gc, Graphics g) {
		//couleur des formes red ou transparent
		g.setColor(Color.transparent);
		//Dessin de toutes les formes
		getCarreImage(direction).draw(heroPosX * 32, heroPosY * 32 - 26,84,84);
		g.draw(getHeroHitBox());
		g.draw(limiteEau);
		g.draw(limiteGauche);
		g.draw(limiteDroite);
		g.draw(enemy1.enemy);
		g.draw(limiteHaut);
	}
	//------------------------------------------------------------------M�THODE UPDATE------------------------------------------------------------------
	public void update(GameContainer gc, int delta) {
		//Position
		System.out.println("X : " + heroPosX);
		System.out.println("Y : " + heroPosY);
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
		if(Maps.compteurLevel == 21) {
			buffer = Maps.mapLevel21;
		}
		if(Maps.compteurLevel == 22) {
			buffer = Maps.mapLevel22;
		}
		if(Maps.compteurLevel == 23) {
			buffer = Maps.mapLevel23;
		}
		int sol = buffer.getLayerIndex("Sol");
		enemy1.update(gc, delta);
		//HitBox mouvement
		getHeroHitBox().setLocation(heroPosX*32+10,heroPosY*32);
		//Gravit�
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
			if (jumping == true){
				sauter.play();
				jumping = false;
			}
			
			if(buffer.getTileId(Math.round(heroPosX) , Math.round(heroPosY) - 1, sol) == 0 && !getHeroHitBox().intersects(limiteHaut)) {
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
			if(buffer.getTileId(Math.round(heroPosX) - 1, Math.round(heroPosY), sol) == 0 && !getHeroHitBox().intersects(limiteGauche)) {
				heroPosX -= 0.12f;
				sautCompteur+= 0.05f;
				gauche = true;
				droite = false;
			}
		}
		//D pour aller a droite
		else if (input.isKeyDown(Input.KEY_D) ) {
			direction = Direction.RIGHT;
			if(buffer.getTileId(Math.round(heroPosX) + 1, Math.round(heroPosY), sol) == 0 && !getHeroHitBox().intersects(limiteDroite)) {
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
		if (buffer.getTileId(Math.round(heroPosX) , Math.round(heroPosY) + 1, sol) != 0 || (getHeroHitBox().intersects(Bouclier.bouclierHitBox) && Bouclier.bouclierUp == true)) {
			sautCompteur =0;
			jumping = true;
		}
		//Mourir
		if(getHeroHitBox().intersects(limiteEau) && invincibilite==false){
			heroPosX = 5;
			heroPosY = 15;
			InterfacesEnJeu.nombreVieRestante--;
			System.out.println(InterfacesEnJeu.nombreVieRestante);
			if (Menu.chapitreLevel == 1) {
				Maps.compteurLevel = 1;
			}
			else if (Menu.chapitreLevel == 2) {
				Maps.compteurLevel = 21;
			}
				
			Menu.gameOverBool = true;
		}
		if(isHitPistol() && Pistol.vie == true && invincibilite==false) {
			heroPosX = 5;
			heroPosY = 15;
			InterfacesEnJeu.nombreVieRestante--;
			System.out.println(InterfacesEnJeu.nombreVieRestante);
			Maps.compteurLevel = 1;
			Menu.gameOverBool = true;
		}
		if(isHitSniper() && Sniper.vie == true && invincibilite==false) {
			heroPosX = 5;
			heroPosY = 15;
			InterfacesEnJeu.nombreVieRestante--;
			System.out.println(InterfacesEnJeu.nombreVieRestante);
			Maps.compteurLevel = 1;
			Menu.gameOverBool = true;
		}
		//Ennemie bloqueur
		if(getHeroHitBox().intersects(enemy1.enemy) && Enemy.reverse == false && Enemy.vie == true && invincibilite==false) {
			heroPosX = Enemy.enemyPosX - 1.5f;
		}
		else if(getHeroHitBox().intersects(enemy1.enemy) && Enemy.reverse && Enemy.vie == true && invincibilite==false) {
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
	public boolean isHitPistol() {
		hitPistol = false;
		if(getHeroHitBox().intersects(pistol.bullet)) {
			hitPistol = true;
		}
		return hitPistol;
	}
	public boolean isHitSniper() {
		hitSniper = false;
		if(getHeroHitBox().intersects(sniper.getBullet())) {
			hitSniper = true;
		}
		return hitSniper;
	}
	public static float getHeroPosX() {
		return heroPosX;
	}
	public void setHeroPosX(float heroPosX) {
		this.heroPosX = heroPosX;
	}

	public static float getHeroPosY() {
		return heroPosY;
	}

	public void setHeroPosY(float heroPosY) {
		this.heroPosY = heroPosY;
	}
	public static Rectangle getHeroHitBox() {
		return heroHitBox;
	}
	public static void setHeroHitBox(Rectangle heroHitBox) {
		Hero.heroHitBox = heroHitBox;
	}

	public static Line getLimiteEau() {
		return limiteEau;
	}

	public static void setLimiteEau(Line limiteEau) {
		Hero.limiteEau = limiteEau;
	}

	public static Line getLimiteGauche() {
		return limiteGauche;
	}

	public static void setLimiteGauche(Line limiteGauche) {
		Hero.limiteGauche = limiteGauche;
	}

	public static Line getLimiteDroite() {
		return limiteDroite;
	}

	public static void setLimiteDroite(Line limiteDroite) {
		Hero.limiteDroite = limiteDroite;
	}
	
}