package CoinFlipping;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;

import java.math.BigDecimal;
import java.util.*;

public class Draw {

    public CategoryChart getHeadsCountChart(Integer x, List<Integer> value){

        List<Integer> xData = new ArrayList<Integer>();
        List<Integer> yData = new ArrayList<Integer>();

        for (Integer i=0; i< x; i++){
            if(value.get(i)!=0){
                xData.add(i);
                yData.add(value.get(i));
            }
        }

        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Coin Homework")
                .xAxisTitle("FlipCount")
                .yAxisTitle("Count").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        //chart.getStyler().setHasAnnotations(true);

        chart.addSeries("Heads Count", xData, yData);

        return chart;
    }

    public CategoryChart getHeadsProbabilityChart(int x, List<BigDecimal> value){

        List<Integer> xData = new ArrayList<Integer>();
        List<BigDecimal> yData = new ArrayList<BigDecimal>();

        BigDecimal cn = new BigDecimal (1/x);

        for (int i=0; i< x; i++){
            if(value.get(i).compareTo(cn) > 0){
                xData.add(i);
                //yData.add(Double.parseDouble(value.get(i).toString())/Double.parseDouble(x.toString()));
                //yData.add(value.get(i));
                //Integer ix = x;
                //Double probability = value.get(i).doubleValue() / ix.doubleValue();
                yData.add(value.get(i));

            }
        }

        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Coin Homework")
                .xAxisTitle("FlipCount")
                .yAxisTitle("Probability").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        //chart.getStyler().setHasAnnotations(true);

        chart.addSeries("Heads Probability", xData, yData);

        return chart;
    }

    public CategoryChart getHeadsProbabilityChart2(Map<BigDecimal, Integer> hashmap){

        List<BigDecimal> xData = new ArrayList<BigDecimal>();
        List<Integer> yData = new ArrayList<Integer>();

        //BigDecimal cn = new BigDecimal (1/x);
        TreeMap<BigDecimal,Integer> tm = new TreeMap<BigDecimal,Integer>(hashmap);
        Iterator<BigDecimal> iteratorKey = tm.keySet( ).iterator( );


        while(iteratorKey.hasNext()){
            BigDecimal key = iteratorKey.next();
            System.out.println("value:"+key);
            xData.add(key);
            yData.add(tm.get(key));
        }

        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Coin Homework")
                .xAxisTitle("FlipCount")
                .yAxisTitle("Probability").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);

        chart.addSeries("Heads Probability", xData, yData);

        return chart;
    }

}
