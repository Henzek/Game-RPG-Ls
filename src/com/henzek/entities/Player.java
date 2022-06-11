package com.henzek.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.henzek.graphics.GamePanel;
import com.henzek.main.KeyHandler;

public class Player extends Entity {
	public double speed= 1;
	KeyHandler keyH;
	public int dir = 6;
	
	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 1;
	private boolean moved = false;
	
	private BufferedImage[] rp;
	private BufferedImage[] lp;

	public Player(int x, int y, int w, int h, BufferedImage sprite, KeyHandler keyH) {
		super(x, y, w, h, sprite);
		this.keyH = keyH;
		
		rp = new BufferedImage[2];
		lp = new BufferedImage[2];
		for(int i = 0; i < 2; i++) {
			rp[i] = GamePanel.sprites.getSprite(16+(i*16),0,16,16);
		}
		for(int i = 0; i < 2; i++) {
			lp[i] = GamePanel.sprites.getSprite(80+(i*16),0,16,16);
		}
	}
	
	public void update() {
		moved = false;
		if(keyH.DK) {
			moved = true;
			y+=speed;
		}else if(keyH.UK) {
			moved = true;
			y-=speed;
		}
		if(keyH.RK) {
			moved = true;
			dir = 6;
			x+=speed;
		}else if(keyH.LK) {
			moved = true;
			dir = 4;
			x-=speed;
		}
		
		animation();
	}
	
	public void animation() {
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				System.out.println(index);
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(dir == 4) {
			g.drawImage(lp[index], this.getX(), this.getY(), 16,16 , null);
		}else if(dir == 6) {
			g.drawImage(rp[index], this.getX(), this.getY(), 16,16 ,null);
		}
		
	}

}
