package org.academiadecodigo.powrangers.GraphicPage.Game;

import org.academiadecodigo.powrangers.Program;

public class Grid {

    public static final int gridMaxCol = 128; //Grid Cells
    public static final int gridMaxRow = 72; //Grid Cells
    public static final int gridSize = 10; // Grid
    public static final int BUFFER = 10;
    public static final String filepath = Program.prefix + "Background.png";
    public static final int floorY = 46;



    public static int getPixelGridSizeCol(){ //Returns max pixel

        return gridMaxCol * gridSize;

    }
    public static int getPixelGridSizeRow(){ //Returns max pixel

        return gridMaxRow * gridSize;

    }

}

