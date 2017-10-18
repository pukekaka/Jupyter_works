package CoinFlipping;

import java.util.ArrayList;
import java.util.List;

public class HeadsCount {

    public List<Integer> getList(int totalCount, int tryCount){
        List<Integer> HeadsCountList = new ArrayList<Integer>();

        Coin coin = new Coin();

        for (int j=0; j<= totalCount; j++){

            Integer Heads = 0;

            for(int i =0; i < tryCount; i++){
                if(coin.countCoinFlips(totalCount, j)){
                    Heads++;
                }
            }
            if(j%10==0){
                System.out.println("progress: "+j + " / " + totalCount);
            }
            HeadsCountList.add(Heads);
        }

        return HeadsCountList;
    }
}
