package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTING
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //WORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //SYSTEM
    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker Checker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];

    //FPS
    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();

        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;
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
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Tile
        tileManager.draw(g2);
        //Object
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }
        //Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
