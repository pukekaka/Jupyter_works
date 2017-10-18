package CoinFlipping;


import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadsProbability {

    public List<BigDecimal> getList(int totalCount){
        List<BigDecimal> HeadsProbabilityList = new ArrayList<BigDecimal>();

        //Coin coin = new Coin();

        for(int i =0; i < totalCount; i++){

            int scale = (int)(Math.log10(totalCount));
            BigDecimal preNum = new BigDecimal (CombinatoricsUtils.binomialCoefficientDouble(totalCount, i));
            BigDecimal postNum = new BigDecimal(Math.pow(2,totalCount));
            BigDecimal e = preNum.divide(postNum, scale, BigDecimal.ROUND_DOWN);
            HeadsProbabilityList.add(e);
        }

        //BinomialDistribution bd = new BinomialDistribution(1000, 0.5);
        //System.out.println(bd.getProbabilityOfSuccess());

        //Combinations c = new Combinations (5, 3);
        //System.out.println(CombinatoricsUtils.binomialCoefficientDouble(1000,100));
        //System.out.println(Math.pow(2, 1000));


        return HeadsProbabilityList;
    }

    public Map<BigDecimal, Integer> getList2(int totalCount, int tryCount){


        Map HeadsProbabilityMap = new HashMap<BigDecimal, Integer>();

        //List<BigDecimal> HeadsProbabilityList2 = new ArrayList<BigDecimal>();

        Coin coin = new Coin();
        BigDecimal tc = new BigDecimal (totalCount);


        for(int j = 0; j<tryCount; j++){

            Integer count = 0;

            for (int i=0; i< totalCount; i++){
                if( coin.flip() == "Heads" ){
                    count++;
                }

            }

            BigDecimal c = new BigDecimal (count);

            BigDecimal hp = c.divide(tc);

            if (HeadsProbabilityMap.get(hp) == null)
                HeadsProbabilityMap.put(hp, 0);
            else
                HeadsProbabilityMap.put(hp, Integer.parseInt(HeadsProbabilityMap.get(hp).toString())+1);

            //HeadsProbabilityList2.add(hp);



            //System.out.println(hp);
        }

        return HeadsProbabilityMap;
    }

}
