package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int playerSkin = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
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
        if (playerSkin == 0) {
            up1 = setup("/player/knight_up_1");
            up2 = setup("/player/knight_up_2");
            down1 = setup("/player/knight_down_1");
            down2 = setup("/player/knight_down_2");
            left1 = setup("/player/knight_left_1");
            left2 = setup("/player/knight_left_2");
            right1 = setup("/player/knight_right_1");
            right2 = setup("/player/knight_right_2");
        } else if (playerSkin == 1) {
            up1 = setup("/player/oldman_up_1");
            up2 = setup("/player/oldman_up_2");
            down1 = setup("/player/oldman_down_1");
            down2 = setup("/player/oldman_down_2");
            left1 = setup("/player/oldman_left_1");
            left2 = setup("/player/oldman_left_2");
            right1 = setup("/player/oldman_right_1");
            right2 = setup("/player/oldman_right_2");
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

            // Check object collision
            int objIndex = gp.Checker.checkObject(this, true);
            pickUpObject(objIndex);

            // if its false, player can move
            if(!collisionOn){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
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
    public void pickUpObject(int index){
        if (index != 999) {
            String objectName = gp.obj[gp.currentMap][index].name;
            switch (objectName) {
                case "Key" -> {
                    gp.playSE(1);
                    gp.ui.showMessage("You got a key!");
                    hasKey++;
                    gp.obj[gp.currentMap][index] = null;
                }
                case "Door" -> {
                    gp.playSE(3);
                    if (hasKey > 0) {
                        gp.obj[gp.currentMap][index] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key!");
                    }
                }
                case "Boots" -> {
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[gp.currentMap][index] = null;
                    gp.ui.showMessage("Speed up!");
                }
                case "Chest" -> {
                    if (gp.currentMap != 1) {
                        gp.gameState = gp.endState;
                    } else {
                        gp.gameState = gp.finalState;
                    }

                    gp.stopMusic();
                    gp.playSE(4);
                }
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
