package com.henzek.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.henzek.entities.Entity;
import com.henzek.entities.Player;
import com.henzek.main.KeyHandler;

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
    KeyHandler keyH = new KeyHandler();
    
    // Renderização de Layers
    private BufferedImage layer;
    
    //Carregamento das imagens
    public static SpriteLoad sprites;
    
    //Lista de Entity
    public List<Entity> entities;
    
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.GREEN);
		this.setFocusable(true);
		this.addKeyListener(keyH);
		initializations();
		loadEntities();
	}
	
	public void initializations() {
		layer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		sprites = new SpriteLoad("/spritesheet.png");
	}
	
	public void loadEntities() {
		entities = new ArrayList<Entity>();
		Player player = new Player(0,0, 16,16, sprites.getSprite(0, 0, originalTileSize, originalTileSize), keyH);
		entities.add(player);
	}
	
	
	public void update() {
		for(int i = 0; i< entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		//Definindo G como gerenciador de Layers
		Graphics g = layer.getGraphics();
		
		// Layers
		/////Background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, screenWidth, screenHeight);
		///////////////
		
		for(int i = 0; i< entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}

        
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
