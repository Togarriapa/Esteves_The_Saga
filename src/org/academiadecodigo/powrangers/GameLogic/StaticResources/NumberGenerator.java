package org.academiadecodigo.powrangers.GameLogic.StaticResources;

public class NumberGenerator {

    public static int NumberGenerator(int maxNumber){

        return (int) (Math.random() * maxNumber);

    }

    public static int NumberGeneratorMaxMin(int max, int min){

        return (int) ((Math.random()* (max-min))+min);

    }

}
