package com.henzek.entities;

import java.awt.image.BufferedImage;

import com.henzek.main.KeyHandler;

public class Player extends Entity {
	public double speed= 1;
	KeyHandler keyH;

	public Player(int x, int y, int w, int h, BufferedImage sprite, KeyHandler keyH) {
		super(x, y, w, h, sprite);
		this.keyH = keyH;
	}
	
	public void update() {
		if(keyH.DK) {
			y+=speed;
		}else if(keyH.UK) {
			y-=speed;
		}
		if(keyH.RK) {
			x+=speed;
		}else if(keyH.LK) {
			x-=speed;
		}
	}

}
