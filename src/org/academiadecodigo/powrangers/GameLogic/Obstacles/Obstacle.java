package org.academiadecodigo.powrangers.GameLogic.Obstacles;

import org.academiadecodigo.powrangers.GraphicPage.Game.Position;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public abstract class Obstacle {
    Position position;
    protected final int minColCellGen = 128;
    protected final int maxColCellGen = 130;
    protected final int minRowCellGen =0;
    protected final int maxRowCellGen =60;
    protected boolean isHit;
    private Rectangle rectangle;
    private boolean isOnBorder = false;
    protected final int bottomToFloor = 7;
    protected final int bottomToFloorPixel = 70;
    public boolean getIsOnBorderG1(){
        if(position.getMinCol() > 0){
            System.out.println("isonborder");
            return false;
        }
        System.out.println("isonborder");
        return true;
    }
    public Position getPosition() {
        System.out.println("here in getPos");
        return position;
    }
    public void delete(){
        rectangle.delete();
    }


    public  void move() {}

    public int getBottomToFloor() {
        return bottomToFloor;
    }

    public int getBottomToFloorPixel() {
        return bottomToFloorPixel;
    }
}
