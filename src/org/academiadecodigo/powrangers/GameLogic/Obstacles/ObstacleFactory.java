package org.academiadecodigo.powrangers.GameLogic.Obstacles;

import org.academiadecodigo.powrangers.GameLogic.StaticResources.NumberGenerator;

public class ObstacleFactory {

    private static final int ObstacleTypes = 2; // 1:Puff, 2:Plushie, 3:Table, 4:Cristina

    public static Obstacle obstacleCreator(){

        switch(NumberGenerator.NumberGeneratorMaxMin(ObstacleTypes, 0)){

            case 0: //Puff
                System.out.println("Created a Puff");
                return new Puff();
            case 1: //Plushie
                System.out.println("Created a Plushie");
                return new Plushie(); /*
            case 2: //Table
                System.out.println("Created a Table");
                return new Table();
            case 3: //Cristina
                System.out.println("Created a Cristina");
                return new Cristina(); */
            default:
                System.out.println("Created a default");
                return new Puff();
        }
    }

}


