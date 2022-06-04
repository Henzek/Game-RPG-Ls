package com.henzek.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GamePanel extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	// Configurações de tela
    final int originalTileSize = 16;
    final int scale = 3;

    public int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;

    // Criação de Thread
    Thread gameThread;
    
    // Renderização de Layers
    private BufferedImage layer;
    private SpriteLoad sprites;
    
    private BufferedImage eu,ela;
    
	
	public GamePanel() {
		sprites = new SpriteLoad("/spritesheet.png");
		eu = sprites.getSprite(originalTileSize*0, originalTileSize*0, originalTileSize, originalTileSize);
		ela = sprites.getSprite(originalTileSize * 1, originalTileSize*0, originalTileSize, originalTileSize);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		layer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	public void update() {}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		//Definindo G como gerenciador de Layers
		Graphics g = layer.getGraphics();
		
		// Layers
        g.setColor(Color.CYAN);
        g.fillOval(20, 20, 16, 16);

        g.setColor(Color.RED);
        g.fillOval(36, 20, 16, 16);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("Primeiro teste", 60, 17);
        
        /*Mostrar eu e ela*/
        
        g.setColor(Color.GREEN);
        g.fillRect(30, 40, 64, 34);
        
        g.drawImage(ela, 40, 40, 32, 32, null);
        g.drawImage(eu, 50, 40, 32, 32, null);
        
        
        
        /////////////////////////////////
        
        //Limpar as layers para criar novas
        g.dispose();
        
        // Cria um contexto gráfico para o buffer de desenho
        g = bs.getDrawGraphics();

        // Desenha dentro do retângulo especificado e dimensiona se necessário.
        // Os pixels transparentes não afetam os pixels já existentes.
        g.drawImage(layer, 0, 0, screenWidth * scale, screenHeight * scale, null);

        // Mostrar na tela
        bs.show();
	}

	public synchronized void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

	@Override
	public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
            	update();
                render();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
		
	}
	

}
