package CAGR;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CAGRTest {
    public static void main(String args[]){

        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Annual Sales ").xAxisTitle("Year").yAxisTitle("Amount").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setAvailableSpaceFill(.96);
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setHasAnnotations(true);


        String[] year = new String[]{"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017"};
        Double[] salesData = new Double[]{980.0, 1100.0, 1400.0, 1500.0, 1400.0, 1400.0, 1400.0, 2000.0};

        List<Date> xData = new ArrayList<Date>();
        DateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = null;
        for (int i = 1; i <= 8; i++) {
            try {
                date = sdf.parse("" + (2010 + i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            xData.add(date);
        }

        //caculate groth rate
        List<Double> grData = new ArrayList<Double>();
        for(int i=0; i< salesData.length; i++){
            if(i==0){
                grData.add(new Double(0));
            }else{
                grData.add(new Double((salesData[i]-salesData[i-1])/salesData[i-1]*100));
                //System.out.println(new Double((salesData[i]-salesData[i-1])/salesData[i-1]*100));
            }
        }

        //cagr
        List<Double> cagrData = new ArrayList<Double>();
        int yearCount = salesData.length-1;

        Double cagr = (Math.pow(salesData[yearCount] / salesData[0], 1.0 / yearCount)- 1.0) *100;
        //Double mcagr = Math.round(cagr*100)/100.0;


        //System.out.println(cagr);
        for(int i=0; i< salesData.length; i++){
            cagrData.add(cagr);
        }

        chart.addSeries("Sales", new ArrayList<String>(Arrays.asList(year)), new ArrayList<Double>(Arrays.asList(salesData)));


        XYChart lineChart = new XYChartBuilder().width(600).height(400).title("Rate").xAxisTitle("Year").yAxisTitle("Percentage").build();
        lineChart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        lineChart.getStyler().setYAxisGroupPosition(1, Styler.YAxisPosition.Right);


        lineChart.addSeries("Growth Rate", xData, grData).setYAxisGroup(1);
        lineChart.addSeries("CAGR", xData, cagrData);

        new SwingWrapper<CategoryChart>(chart).displayChart();
        new SwingWrapper<XYChart>(lineChart).displayChart();
    }

}
