package org.academiadecodigo.powrangers.GameLogic.PlayerRelated;

import org.academiadecodigo.powrangers.GameLogic.Obstacles.Obstacle;
import org.academiadecodigo.powrangers.GraphicPage.Game.Position;
import org.academiadecodigo.powrangers.Program;
import org.academiadecodigo.powrangers.Sounds;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.powrangers.GraphicPage.Game.Grid.*;


public class Player {

    private static String filepath = Program.prefix + "character0.png";
    private int highScore;
    private int lives = 3;
    private int delay = 30;
    //JUMP ----------------------------------------------------------------------------------
    private final int maxJump = 190;
    private final int jumpingIndex = 10;
    private int jumpCounter = -1;
    private boolean goingUp = true;

    //CROUCHED-------------------------------------------------------------------------------
    private Picture crouched;
    private String crouchedFilePath = "filepathC";
    //PLAYER--------------------------------------------------------------------------------
    private Position position;
    private Rectangle rectangle;
    private final int hitSound = 1;
    private final int gameOverSound = 0;
    //-------------------------------------------------------------------------------------
    private int gapToWallInCols = 7;
    private int widthInPixels = 105;
    private int heigthInPixels = 180;
    private int minColPixels = gapToWallInCols* gridSize;
    private int minRowPixels = (gridMaxRow* gridSize)-70-heigthInPixels;
    private int minCol = gapToWallInCols;
    private int widthInCols = widthInPixels/ gridSize;
    private int minRow = minRowPixels / gridSize;
    private int heightInRows =heigthInPixels/ gridSize;
    protected final int bottomToFloor = 7;
    protected final int bottomToFloorPixel = 70;
    private int x = minColPixels;;
    private int y = minRowPixels - BUFFER;
    private Picture picture;
    //Jesus take the wheel-----------------------------------------------------------------------------------------
    private int counterRun = 0;
    private int counterJump = 0;
    private int counterCrouch = 0;
    private int counterDie = 0;
    private Picture liveFull = new Picture(1000,50, Program.prefix + "Lives3.png");
    private Picture live2 = new Picture(1000,50,Program.prefix + "Lives2.png");
    private Picture live1 = new Picture(1000,50,Program.prefix + "Lives1.png");
    private Picture live0 = new Picture(1000,50,Program.prefix + "Lives0.png");
    Picture[] run = {new Picture(x, y, Program.prefix + "run0001.png"), new Picture(x, y, Program.prefix + "run0002.png"),
            new Picture(x, y, Program.prefix + "run0003.png"), new Picture(x, y, "resources/run0004.png"),
            new Picture(x, y, Program.prefix + "run0005.png"), new Picture(x, y, Program.prefix + "run0006.png"),
            new Picture(x, y, Program.prefix + "run0007.png"), new Picture(x, y, Program.prefix + "run0008.png"),
            new Picture(x, y, Program.prefix + "run0009.png"), new Picture(x, y, Program.prefix + "run0010.png"),
            new Picture(x, y, Program.prefix + "run0011.png"), new Picture(x, y, Program.prefix + "run0012.png"),
            new Picture(x, y, Program.prefix + "run0013.png"), new Picture(x, y, Program.prefix + "run0014.png"),
            new Picture(x, y, Program.prefix + "run0015.png"), new Picture(x, y, Program.prefix + "run0016.png"),
            new Picture(x, y, Program.prefix + "run0017.png"), new Picture(x, y, Program.prefix + "run0018.png"),
            new Picture(x, y, Program.prefix + "run0019.png"), new Picture(x, y, Program.prefix + "run0020.png"),
            new Picture(x, y, Program.prefix + "run0021.png"),};



    Picture[] crouch = {new Picture(x, y, Program.prefix + "crouch0001.png"), new Picture(x, y, Program.prefix + "crouch0002.png"),
            new Picture(x, y, Program.prefix + "crouch0003.png"), new Picture(x, y, Program.prefix + "crouch0004.png"),
            new Picture(x, y, Program.prefix + "crouch0005.png"), new Picture(x, y, Program.prefix + "crouch0006.png"),
            new Picture(x, y, Program.prefix + "crouch0007.png"), new Picture(x, y, Program.prefix + "crouch0008.png"),
            new Picture(x, y, Program.prefix + "crouch0009.png"), new Picture(x, y, Program.prefix + "crouch0010.png"),
            new Picture(x, y, Program.prefix + "crouch0011.png"), new Picture(x, y, Program.prefix + "crouch0012.png"),
            new Picture(x, y, Program.prefix + "crouch0013.png"), new Picture(x, y, Program.prefix + "crouch0014.png"),
            new Picture(x, y, Program.prefix + "crouch0015.png"), new Picture(x, y, Program.prefix + "crouch0016.png"),
            new Picture(x, y, Program.prefix + "crouch0017.png"), new Picture(x, y, Program.prefix + "crouch0018.png"),
            new Picture(x, y, Program.prefix + "crouch0019.png"), new Picture(x, y, Program.prefix + "crouch0020.png"),
            new Picture(x, y, Program.prefix + "crouch0021.png")};


    Picture[] fall = {new Picture(x, y, Program.prefix + "fall0001.png"), new Picture(x, y, Program.prefix + "fall0002.png"),
            new Picture(x, y, Program.prefix + "fall0003.png"), new Picture(x, y, Program.prefix + "fall0004.png"),
            new Picture(x, y, Program.prefix + "fall0005.png"), new Picture(x, y, Program.prefix + "fall0006.png"),
            new Picture(x,y,Program.prefix + "fall0007.png"),new Picture(x,y,Program.prefix + "fall0008.png"),
            new Picture(x, y, Program.prefix + "fall0009.png"), new Picture(x, y, Program.prefix + "fall0010.png"),
            new Picture(x, y, Program.prefix + "fall0013.png"), new Picture(x, y, Program.prefix + "fall0014.png"),
            new Picture(x,y,Program.prefix + "fall0015.png"),new Picture(x,y,Program.prefix + "fall0016.png"),
            new Picture(x, y, Program.prefix + "fall0017.png"), new Picture(x, y, Program.prefix + "fall0018.png"),
            new Picture(x, y, Program.prefix + "fall0019.png"), new Picture(x, y, Program.prefix + "fall0020.png"),
            new Picture(x,y,Program.prefix + "fall0021.png"),new Picture(x,y,Program.prefix + "fall0022.png"),
            new Picture(x, y, Program.prefix + "fall0023.png"), new Picture(x, y, Program.prefix + "fall0024.png"),
            new Picture(x, y, Program.prefix + "fall0025.png"), new Picture(x, y, Program.prefix + "fall0026.png"),
            new Picture(x,y,Program.prefix + "fall0027.png"),new Picture(x,y,Program.prefix + "fall0028.png"),
            new Picture(x, y, Program.prefix + "fall0029.png"), new Picture(x, y, Program.prefix + "fall0030.png"),
            new Picture(x, y, Program.prefix + "fall0031.png"), new Picture(x, y, Program.prefix + "fall0032.png"),
            new Picture(x,y,Program.prefix + "fall0033.png"),new Picture(x,y,Program.prefix + "fall0034.png"),
            new Picture(x, y, Program.prefix + "fall0035.png"), new Picture(x, y, Program.prefix + "fall0036.png"),
            new Picture(x, y, Program.prefix + "fall0037.png"), new Picture(x, y, Program.prefix + "fall0038.png"),
            new Picture(x,y,Program.prefix + "fall0039.png"),new Picture(x,y,Program.prefix + "fall0040.png"),
            new Picture(x, y, Program.prefix + "fall0041.png"), new Picture(x, y, Program.prefix + "fall0042.png"),
            new Picture(x, y, Program.prefix + "fall0043.png"), new Picture(x, y, Program.prefix + "fall0044.png"),
            new Picture(x,y,Program.prefix + "fall0045.png"),new Picture(x,y,Program.prefix + "fall0046.png"),
            new Picture(x, y, Program.prefix + "fall0047.png"), new Picture(x, y, Program.prefix + "fall0048.png"),
            new Picture(x, y, Program.prefix + "fall0049.png"), new Picture(x, y, Program.prefix + "fall0050.png"),
            new Picture(x,y,Program.prefix + "fall0051.png"),new Picture(x,y,Program.prefix + "fall0052.png"),
            new Picture(x, y, Program.prefix + "fall0053.png"), new Picture(x, y, Program.prefix + "fall0054.png"),
            new Picture(x, y, Program.prefix + "fall0055.png"), new Picture(x, y, Program.prefix + "fall0056.png"),
            new Picture(x,y,Program.prefix + "fall0057.png"),new Picture(x,y,Program.prefix + "fall0058.png"),
            new Picture(x, y, Program.prefix + "fall0059.png"), new Picture(x, y, Program.prefix + "fall0060.png"),
            new Picture(x, y, Program.prefix + "fall0061.png"), new Picture(x, y, Program.prefix + "fall0062.png"),
            new Picture(x,y,Program.prefix + "fall0063.png"),new Picture(x,y,Program.prefix + "fall0064.png"),
            new Picture(x, y, Program.prefix + "fall0065.png"), new Picture(x, y, Program.prefix + "fall0066.png"),
            new Picture(x, y, Program.prefix + "fall0067.png"), new Picture(x, y, Program.prefix + "fall0068.png"),
            new Picture(x,y,Program.prefix + "fall0069.png"),new Picture(x,y,Program.prefix + "fall0070.png"),
            new Picture(x, y, Program.prefix + "fall0071.png"), new Picture(x, y, Program.prefix + "fall0072.png"),
            new Picture(x, y, Program.prefix + "fall0073.png"), new Picture(x, y, Program.prefix + "fall0074.png"),
            new Picture(x,y,Program.prefix + "fall0075.png"),new Picture(x,y,Program.prefix + "fall0076.png"),
            new Picture(x, y, Program.prefix + "fall0077.png"), new Picture(x, y, Program.prefix + "fall0078.png"),
            new Picture(x, y, Program.prefix + "fall0079.png"), new Picture(x, y, Program.prefix + "fall0080.png"),
            new Picture(x,y,Program.prefix + "fall0081.png"),new Picture(x,y,Program.prefix + "fall0082.png"),
            new Picture(x, y, Program.prefix + "fall0083.png"), new Picture(x, y, Program.prefix + "fall0084.png"),
            new Picture(x, y, Program.prefix + "fall0085.png"), new Picture(x, y, Program.prefix + "fall0086.png"),
            new Picture(x,y,Program.prefix + "fall0087.png"),new Picture(x,y,Program.prefix + "fall0088.png"),
            new Picture(x, y, Program.prefix + "fall0089.png"), new Picture(x, y, Program.prefix + "fall0090.png"),
            new Picture(x, y, Program.prefix + "fall0091.png"), new Picture(x, y, Program.prefix + "fall0092.png"),
            new Picture(x,y,Program.prefix + "fall0093.png"),new Picture(x,y,Program.prefix + "fall0094.png"),
            new Picture(x, y, Program.prefix + "fall0095.png"), new Picture(x, y, Program.prefix + "fall0096.png"),
            new Picture(x, y, Program.prefix + "fall0097.png"), new Picture(x, y, Program.prefix + "fall0098.png"),
            new Picture(x,y,Program.prefix + "fall0099.png"),new Picture(x,y,Program.prefix + "fall0100.png"),
            new Picture(x, y, Program.prefix + "fall0101.png"), new Picture(x, y, Program.prefix + "fall0102.png"),
            new Picture(x, y, Program.prefix + "fall0103.png"), new Picture(x, y, Program.prefix + "fall0104.png"),
            new Picture(x,y,Program.prefix + "fall0105.png"),new Picture(x,y,Program.prefix + "fall0106.png"),
            new Picture(x, y, Program.prefix + "fall0107.png"), new Picture(x, y, Program.prefix + "fall0108.png"),
            new Picture(x, y, Program.prefix + "fall0109.png"), new Picture(x, y, Program.prefix + "fall0110.png"),
            new Picture(x, y, Program.prefix + "fall0111.png"), new Picture(x, y, Program.prefix + "fall0112.png")};

    //-------------------------------------------------------------------------------------------------------------
    public Player (){
        //System.out.println("player created");

        position = new Position(minCol,minCol+widthInCols,minRow,minRow+heightInRows);

        rectangle = new Rectangle(x,y,widthInPixels,heigthInPixels); // Tentar não dar hard code nisto

        picture = new Picture(x,y,filepath);


        rectangle.draw();
        picture.draw();
        liveFull.draw();




        



        //crouched = new Picture(minCol*gridSize, ((minRow - bottomToFloor - (heightInRows/2)) * gridSize) ,crouchedFilePath);


    }
    public void movePlayerImage() {

        counterRun++;
        picture.delete();
        picture = run[counterRun];


        if (counterRun == run.length - 1) counterRun = 0;
    }
    public boolean isColided (Obstacle obstacle){

        if(position.getMinCol() <= obstacle.getPosition().getMaxCol() &&
                position.getMaxCol() >= obstacle.getPosition().getMinCol() &&
                position.getMaxRow() >= obstacle.getPosition().getMinRow() &&
                position.getMinRow() <= obstacle.getPosition().getMaxRow() )
        {
            hit();
            return true;
        }
        return false;
    }
    public int getHighScore(){

        return highScore;
    }
    public void incHighScore(){

        highScore++;
    }
    public void incHighScore(int plus){

        highScore += plus;
    }
    public int getLives(){
        return lives;
    }
    public void hit(){


            if(getLives()>1) {

                Sounds sound = new Sounds();
                sound.playSoundEffect(hitSound);
                lives--;
                if(lives == 2){
                    liveFull.delete();
                    live2.draw();
                }

                //System.out.println("You have " + getLives() + " left.");
                if(lives == 1){
                    live2.delete();
                    live1.draw();
                }
            }else
            if(getLives() == 1){

                Sounds sound = new Sounds();
                sound.playSoundEffect(hitSound);
                //System.out.println("You just lost your last life");
                lives--;
                live1.delete();
                live0.draw();
            }else {
                Sounds sound = new Sounds();
                sound.playSoundEffect(gameOverSound);
                //System.out.println("You didn't have any lives to lose");

            }

        }


    public Picture[] getFall() {
        return fall;
    }

    public void jump() {

        if (goingUp) {

            if (jumpCounter < maxJump) {
                for (int i = 0; i < jumpingIndex; i++) {
                    System.out.println("MinRow " + position.getMinRow());
                    System.out.println("MaxRow " + position.getMaxRow());
                    if (jumpCounter % 10 == 0) {

                        position.setMinRow(position.getMinRow() - 1);
                        position.setMaxRow(position.getMaxRow() - 1);
                    }
                    rectangle.translate(0, -1);
                    picture.translate(0, -1);

                    jumpCounter++;
                    if (jumpCounter == maxJump) {
                        goingUp = false;
                    }
                }

            }
        }


                if (!goingUp) {

                        if (jumpCounter != 0) {
                            for (int i = 0; i < jumpingIndex; i++) {

                                System.out.println("MinRow " + position.getMinRow());
                                System.out.println("MaxRow " + position.getMaxRow());
                                if (jumpCounter % 10 == 0) {

                                    position.setMinRow(position.getMinRow() + 1);
                                    position.setMaxRow(position.getMaxRow() + 1);
                                }
                                rectangle.translate(0, +1);
                                picture.translate(0, +1);

                                jumpCounter--;
                                if (jumpCounter == 0) {
                                    goingUp = true;
                                    jumpCounter = -1;
                                    break;

                                }

                            }

                        }


                }
            }


    public void crouch(){
        position.setMinRow(position.getMinRow()+(heightInRows/2));
        picture.delete();
        crouched.draw();
    }
    public void unCrouch(){
        System.out.println("unc :" + position.getMinRow());
        System.out.println("unc :" + position.getMaxRow());
        position.setMinRow(position.getMinRow()-(heightInRows/2));
        crouched.delete();
        picture.draw();

    }
    public int getJumpCounter() {
        return jumpCounter;
    }

    public void setJumpCounter() {
        this.jumpCounter = 0;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Position getPosition() {
        return position;
    }

    public int getBottomToFloorPixel() {
        return bottomToFloorPixel;
    }

    public int getBottomToFloor() {
        return bottomToFloor;
    }

}
