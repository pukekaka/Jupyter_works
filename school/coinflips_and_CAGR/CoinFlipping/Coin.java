package CoinFlipping;

import java.util.Random;

public class Coin {

    public String flip(){

        Random random = new Random();

        int randomNumber = random.nextInt(2);
        if (randomNumber == 0){
            return "Heads";
        }else{
            return "Tails";
        }
    }



    public boolean countCoinFlips(int totalCount, int headCount){
        Random rand = new Random();
        int numberOfTosses = totalCount;
        int numberOfHeads = 0;

        for(int i = 1; i <= numberOfTosses; i++){
            int value = rand.nextInt(2);
            if(value == 0)  numberOfHeads++;
        }

        if(numberOfHeads == headCount){
            return true;
        }
        else{
            return false;
        }
    }

}
