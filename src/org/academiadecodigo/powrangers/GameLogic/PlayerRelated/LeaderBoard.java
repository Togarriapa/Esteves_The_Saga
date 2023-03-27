package org.academiadecodigo.powrangers.GameLogic.PlayerRelated;

public class LeaderBoard { //LIST OF TOP SCORES - HOW TO ADD AND CONSULT

    private String[] names;
    private int[] scores;

    public LeaderBoard(int maxPlayers) {

        names = new String[maxPlayers];
        scores = new int[maxPlayers];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = -1;
        }

    }
    public String getName(int index){ // For the graphic part

        return names[index-1];
    }
    public int getScore(int index){ // For the graphic part

        return scores[index-1];
    }
    public void addScore(String name, int highScore) { // Add name to LeaderBoard

        int slotToOccupy = -1;

        for (int i = 0; i < scores.length; i++) {

            if (scores[i] < highScore) { // If the score we want to add is superior to the one stored we replace it :)
                slotToOccupy = i;
                break;
            } else { // If the above is false we go forward
                slotToOccupy = i;
                i++;
            }
        }
            if (scores[slotToOccupy] < highScore) { // Store in the slotToOcCupy
                scores[slotToOccupy] = highScore;
                names[slotToOccupy] = name;
                System.out.println("You got into the Leaderboard - In spot nº"+ (slotToOccupy+1));
            }

        PrintScores(); // Prints BillBoard to confirm method
    }

    public void PrintScores(){ // Prints Top Scores for checks

        for (int i = 0; i < scores.length; i++) {

            System.out.println("Nº" + (i+1) + " Position:");
            System.out.println(names[i]);
            System.out.println(scores[i]);

        }
    }
}
