package org.academiadecodigo.powrangers.GameLogic.Obstacles;

import org.academiadecodigo.powrangers.GameLogic.StaticResources.NumberGenerator;
import org.academiadecodigo.powrangers.GraphicPage.Game.Grid;
import org.academiadecodigo.powrangers.GraphicPage.Game.Position;
import org.academiadecodigo.powrangers.Program;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Plushie extends Obstacle {
    private Picture picture;
    private String filepath = Program.prefix + "rat.png";
    private Position position;
    private int speed = 1 ; //in grid size

    /// SIZE IN PIXEL
    private int height = 60;
    private int width = 120;

    Rectangle rectangle;
    public Plushie() {
        int randomNumber = NumberGenerator.NumberGenerator(50);

        position = new Position(Grid.gridMaxCol-(width/Grid.gridSize),
                Grid.gridMaxCol,
                Grid.gridMaxRow-(height/Grid.gridSize) - getBottomToFloor()-21,
                (Grid.gridMaxRow - getBottomToFloor())-21);

        rectangle = new Rectangle((Grid.gridMaxCol*Grid.gridSize)-width,
                ((Grid.gridMaxRow*Grid.gridSize)-height )-getBottomToFloorPixel()-210,
                width,height);

        picture = new Picture(position.getMinCol()*10, (position.getMinRow()*10)-10,filepath);

        //rectangle.draw();
        picture.draw();





    }
    @Override
    public void move() {

        if(position.getMinCol() > -width/Grid.gridSize && !isHit()) {

            for (int i = 0; i < speed; i++) {

                movementLogicPuff();

            }

        }

    }
    private void movementLogicPuff (){

        position.setMinCol(position.getMinCol()-1);
        position.setMaxCol(position.getMaxCol()-1);
        //System.out.println(positionG1.getMinCol());
        //System.out.println(positionG1.getMaxCol());
        rectangle.translate(-10,0);
        picture.translate(-10,0);
    }

    public void delete(){
        rectangle.delete();
        picture.delete();
    }


    public boolean isHit() {

        return isHit;
    }

    public Position getPosition() {
        return position;
    }
}