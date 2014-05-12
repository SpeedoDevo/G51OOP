/*
 * one-player darts game
 * @author: Barnabas Forgo (bxf03u, 4211949)
 */

public class DartsGame {
	final static int DARTS_PER_ROUND = 3;
    final static int MAX_THROW = 60;
    public static void main(String[] args) {
        int target,                              //target score
        score = 0,                               //accumulated score
        round = 0,                               //round counter
        roundScore,                              //score accumulated in a round
        throwScore,                              //score thrown
        darts = 0,                               //darts counter
        highestRound = 0,                        //maximum scoring round
        lowestRound = DARTS_PER_ROUND*MAX_THROW, //minimum scoring round
        highestThrow = 0,                        //maximum scoring throw
        lowestThrow = MAX_THROW;                 //minimum scoring throw
        Boolean bust;                            //was the round bust?
        
        
        //initialising and checking target score
        G51OOPInput.prompt("Please enter a target score:");
        target = G51OOPInput.readInt(); 
        if (target < 0) {
            System.out.println("Invalid target score");
            System.exit(1);
        }
        
        
        //game loop
        while (score != target) {
            bust = false;
            roundScore = 0;
            System.out.println("Next round is " + ++round);
            
            //throw prompt loop
            for (int i = 1; i <= DARTS_PER_ROUND; i++) {
                //keep prompting until value is valid
                do{
                    G51OOPInput.prompt("Please enter a throw (remaining score is: " + (target-score-roundScore) + "): ");
                    throwScore = G51OOPInput.readInt();
                    if (throwScore > MAX_THROW || throwScore < 0) {
                        System.out.println("Invalid input! Try again"); 
                    }
                } while (throwScore > MAX_THROW || throwScore < 0);
                darts++;
                
                //find min/max throw
                if (highestThrow <= throwScore) {
                    highestThrow = throwScore;
                }
                if (lowestThrow >= throwScore) {
                    lowestThrow = throwScore;
                }
                
                //checking for bust or win
                if (target < score+roundScore+throwScore) {
                    bust = true;
                    System.out.println("BUST!");
                    darts += DARTS_PER_ROUND-i; //adding unused darts in busted turn
                    i += DARTS_PER_ROUND-i; //break; without break;
                    roundScore = 0; //resetting round score as it would add up to the overall score otherwise
                } else if (target == score+roundScore+throwScore) {
                    roundScore += throwScore;
                    i += DARTS_PER_ROUND-i; //break; without break;
                } else {
                    roundScore += throwScore;
                }
            }
            
            //find min/max round if it wasn't busted
            if (highestRound <= roundScore && bust == false) {
                highestRound = roundScore;
            }
            if (lowestRound >= roundScore && bust == false) {
                lowestRound = roundScore;
            }
            
            score += roundScore;
        }
        
        
        //printing stats after the game ends
        System.out.println("=== Statistics===");
        System.out.println("Total Rounds: " + round);
        System.out.println("Total Darts: " + darts);
        System.out.println("Highest Scoring Round: " + highestRound);
        System.out.println("Min Scoring Round: " + lowestRound);
        System.out.println("Best Throw: " + highestThrow);
        System.out.println("Worst Throw: " + lowestThrow);
        System.out.format("Average Per Dart: %.2f", ((float) target / darts));
    }
}
