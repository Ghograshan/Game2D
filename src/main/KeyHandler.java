package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            } else if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            } else if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    if (gp.player.playerSkin == 0) {
                        gp.player.playerSkin = 1;
                    } else {
                        gp.player.playerSkin = 0;
                    }
                    gp.player.getPlayerImage();

                } else if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.playState;
                    gp.ui.messageOn = true;
                    gp.playMusic(0);
                } else if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        } else if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        } else if (gp.gameState == gp.endState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            } else if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            } else if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.nextStageConfigurations();
                } else if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        } else if (gp.gameState == gp.finalState) {
            if (code == KeyEvent.VK_ENTER) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        else if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
