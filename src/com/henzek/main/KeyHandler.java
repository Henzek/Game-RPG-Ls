package com.henzek.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean UK, DK, LK, RK = false;
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
            UK = true;
        }
        if (code == KeyEvent.VK_S) {
            DK = true;
        }
        if (code == KeyEvent.VK_A) {
            LK = true;
        }
        if (code == KeyEvent.VK_D) {
            RK = true;
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
            UK = false;
        }
        if (code == KeyEvent.VK_S) {
            DK = false;
        }
        if (code == KeyEvent.VK_A) {
            LK = false;
        }
        if (code == KeyEvent.VK_D) {
            RK = false;
        }
		
	}

}
