package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/knight_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/knight_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/knight_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/knight_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/knight_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/knight_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/knight_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/knight_right_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";



            }
            else if (keyH.downPressed) {
                direction = "down";




            }
            else if (keyH.leftPressed) {
                direction = "left";




            }
            else if (keyH.rightPressed) {
                direction = "right";




            }
           // CHECK tile collision
            collisionOn = false;
            gp.Checker.checkTile(this);
          // if its false, player can move
            if(collisionOn == false){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed;break;
                    case "right": worldX += speed;break;

                }

            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                else if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                else if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                else if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
