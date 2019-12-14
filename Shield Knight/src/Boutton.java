package src;

import java.awt.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.Input;

public class Boutton {

	private String image;
	private Image boutton;
	private int bouttonPosX;
	private int bouttonPosY;
	private int largeur;
	private int hauteur;
	
	public Boutton(String image, int bouttonPosX, int bouttonPosY, int largeur, int hauteur){
		this.image = image;
		this.bouttonPosX = bouttonPosX; 
		this.bouttonPosY = bouttonPosY;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	public void init (GameContainer gc) throws SlickException {
		boutton = new Image(image);
		largeur = 10;
		hauteur = 10;
		bouttonPosX = 10;
		bouttonPosY = 10;
		
	}
	public void render() {
		boutton.draw(bouttonPosX, bouttonPosY, largeur, hauteur);
	}
	public void update(GameContainer gc, int delta) {
		
	}	
}
