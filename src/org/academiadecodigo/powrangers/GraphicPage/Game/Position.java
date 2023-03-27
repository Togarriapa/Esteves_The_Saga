package org.academiadecodigo.powrangers.GraphicPage.Game;

public class Position {


    private int minCol;
    private int maxCol;
    private int minRow;
    private int maxRow;


    public Position(int minCol, int maxCol, int minRow, int maxRow){
        //System.out.println("position created");
        this.minCol = minCol;
        this.maxCol = maxCol;
        this.minRow = minRow;
        this.maxRow = maxRow;

    }

    public int getMinCol() {
        return minCol;
    }

    public void setMinCol(int minCol) {
        this.minCol = minCol;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }

    public int getMinRow() {
        return minRow;
    }

    public void setMinRow(int minRow) {
        this.minRow = minRow;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }


}
