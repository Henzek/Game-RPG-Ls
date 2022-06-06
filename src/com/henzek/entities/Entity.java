package com.henzek.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
protected int w,h;
protected double x,y;
private BufferedImage sprite;

public Entity(int x, int y, int w, int h, BufferedImage sprite) {
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
	this.sprite = sprite;
}

public int getX() {
	return (int)this.x;
	
}public int getY() {
	return (int)this.y;
}
public int getWidth() {
	return this.w;
}
public int getHeight() {
	return this.h;
}

public void update() {
	
}

public void render(Graphics g) {
	g.drawImage(sprite, this.getX(), this.getY(), null);
}
}
