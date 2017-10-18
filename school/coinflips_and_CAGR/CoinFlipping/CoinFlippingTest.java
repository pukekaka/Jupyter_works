package CoinFlipping;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.SwingWrapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CoinFlippingTest {
    public static void main(String args[]){

       //HeadsCount hc = new HeadsCount();
       Draw draw = new Draw();
       //List<Integer> HeadsCountList = hc.getList(1000, 10000);
       HeadsProbability hp = new HeadsProbability();
       //List<BigDecimal> HeadProbabilityList = hp.getList(1000);
        Map<BigDecimal, Integer> HeadProbabilityMap = hp.getList2(1000, 10000);

       //CategoryChart chart = draw.getHeadsCountChart(HeadsCountList.size(), HeadsCountList);
       //new SwingWrapper<CategoryChart>(chart).displayChart();

        //CategoryChart chart2 = draw.getHeadsProbabilityChart(HeadProbabilityList.size(), HeadProbabilityList);
        //new SwingWrapper<CategoryChart>(chart2).displayChart();

        CategoryChart chart = draw.getHeadsProbabilityChart2(HeadProbabilityMap);
        new SwingWrapper<CategoryChart>(chart).displayChart();

    }
}
