package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        int mapNum = 0;
        gp.obj[mapNum][0] = new OBJ_Key();
        gp.obj[mapNum][0].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Key();
        gp.obj[mapNum][1].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 40 * gp.tileSize;

        gp.obj[mapNum][2] = new OBJ_Key();
        gp.obj[mapNum][2].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 8 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Door();
        gp.obj[mapNum][3].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 11 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Door();
        gp.obj[mapNum][4].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 28 * gp.tileSize;

        gp.obj[mapNum][5] = new OBJ_Door();
        gp.obj[mapNum][5].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 22 * gp.tileSize;

        gp.obj[mapNum][6] = new OBJ_Chest();
        gp.obj[mapNum][6].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][7] = new OBJ_Boots();
        gp.obj[mapNum][7].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 42 * gp.tileSize;

        mapNum = 1;
        gp.obj[mapNum][0] = new OBJ_Key();
        gp.obj[mapNum][0].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 22 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Key();
        gp.obj[mapNum][1].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 19 * gp.tileSize;

        gp.obj[mapNum][2] = new OBJ_Key();
        gp.obj[mapNum][2].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 34 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Door();
        gp.obj[mapNum][3].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 39 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Door();
        gp.obj[mapNum][4].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 29 * gp.tileSize;

        gp.obj[mapNum][5] = new OBJ_Door();
        gp.obj[mapNum][5].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 34 * gp.tileSize;

        gp.obj[mapNum][6] = new OBJ_Chest();
        gp.obj[mapNum][6].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 41 * gp.tileSize;

        gp.obj[mapNum][7] = new OBJ_Boots();
        gp.obj[mapNum][7].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 16 * gp.tileSize;
    }
}
