package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public String[] dialogues = new String[20];
    public int dialogueIndex = 0;

    //CHARACTER STATUS
    public int maxHealth;
    public int health;

   public Entity(GamePanel gp){
       this.gp = gp;
   }

   public void setAction(){

   }

   public void speak(){
       if (dialogues[dialogueIndex] == null){
           dialogueIndex = 0;
       }
       gp.ui.currentDialogue = dialogues[dialogueIndex];
       dialogueIndex++;

       switch (gp.player.direction) {
           case "up" -> direction = "down";
           case "down" -> direction = "up";
           case "left" -> direction = "right";
           case "right" -> direction = "left";
       }
   }

   public void update(){
       setAction();
       collisionOn = false;
       gp.Checker.checkTile(this);
       gp.Checker.checkObject(this, false);
       gp.Checker.checkPlayer(this);

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

   public void draw(Graphics2D g2){
       int screenX = worldX - gp.player.worldX + gp.player.screenX;
       int screenY = worldY - gp.player.worldY + gp.player.screenY;

       BufferedImage image = null;
       if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)  {

           switch (direction) {
               case "up" -> {
                   if (spriteNum == 1) {
                       image = up1;
                   } else if (spriteNum == 2) {
                       image = up2;
                   }
               }
               case "down" -> {
                   if (spriteNum == 1) {
                       image = down1;
                   } else if (spriteNum == 2) {
                       image = down2;
                   }
               }
               case "left" -> {
                   if (spriteNum == 1) {
                       image = left1;
                   } else if (spriteNum == 2) {
                       image = left2;
                   }
               }
               case "right" -> {
                   if (spriteNum == 1) {
                       image = right1;
                   } else if (spriteNum == 2) {
                       image = right2;
                   }
               }
           }

               g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

       }
   }

    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
}

