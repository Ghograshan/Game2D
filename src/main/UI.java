package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public boolean stageMessageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;

    double playTime;
    ArrayList<String> stageTimes = new ArrayList<String>();
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e){
            e.printStackTrace();
        }
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
        stageTimes.add("");
        stageTimes.add("");
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void showStageText(String text){
        message = text;
        stageMessageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //Title State
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //Play State
        if (gp.gameState == gp.playState){
            drawPlayScreen();
        }
        if (gp.gameState == gp.endState) {
            drawEndState();
        }
        if (gp.gameState == gp.finalState) {
            drawFinalState();
            gp.gameThread = null;
        }
    }

    public void drawTitleScreen(){
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Knight's Adventure";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);

        //MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //KNIGHT IMAGE
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        if (commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y + gp.tileSize * 2 - 20);
        }

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPlayScreen() {
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 50F));
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString("Key: " + gp.player.hasKey, 74, 65);

        //TIME
        playTime += (double) 1 / 60;
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

        //MESSAGE
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

            messageCounter++;
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }

        //STAGE MESSAGE


        if (stageMessageOn) {
            String text = "Stage " + (gp.currentMap + 1);
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.setFont(maruMonica.deriveFont(Font.BOLD, 50F));
            g2.setColor(Color.WHITE);

            g2.drawString(text, x - gp.tileSize / 2, y + 5);

            messageCounter++;
            if (messageCounter > 300) {
                messageCounter = 0;
                stageMessageOn = false;

            }
        }
    }

    public void drawEndState(){
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        String text;
        int textLenght, x, y;
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 50F));
        g2.setColor(Color.white);

        text = "You found the treasure!";
        textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLenght / 2;
        y = gp.screenHeight / 2 - (gp.tileSize * 3);
        g2.drawString(text, x, y);

        text = "Total time: " + dFormat.format(playTime) + "!";
        textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        stageTimes.set(gp.currentMap, dFormat.format(playTime));
        x = gp.screenWidth / 2 - textLenght / 2;
        y += gp.tileSize * 1.5;
        g2.drawString(text, x, y);

        //MENU
        text = "NEXT STAGE";
        g2.setColor(Color.yellow);
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawFinalState() {
        commandNum = 0;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        String text;
        int textLenght, x, y;
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 50F));
        g2.setColor(Color.white);
        stageTimes.set(gp.currentMap, dFormat.format(playTime));

        text = "You have finished the all stages!";
        textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLenght / 2;
        y = gp.screenHeight / 2 - (gp.tileSize * 3);
        g2.drawString(text, x, y);

        text = "Total time: " + (Float.parseFloat(stageTimes.get(0)) +  Float.parseFloat(stageTimes.get(1))) + "!";
        textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLenght / 2;
        y += gp.tileSize * 1.5;
        g2.drawString(text, x, y);

        for (int i = 0; i < stageTimes.size(); i++) {
            text = String.format("Stage %d: %s", (i + 1), stageTimes.get(i));
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLenght / 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
        }

        text = "QUIT";
        g2.setColor(Color.red);
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
}
