import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewFunc {
    private static final double T = 10;
    private static final double h = 0.001;
    private static final double k = 7;
    private static final double T_MAX = 100;
    public static List<Double> f_x_arr = new ArrayList<Double>();

    //        public static void func() {
//        for (double t = 0; t < T_MAX; t++) {
//            if (t % T > T / 2 && t % T <= T) {
//                f_x_arr.add(k);
//            }// прямая линия
//            else {
//                f_x_arr.add(0.0);
//            }
//        }
//    }
    public static double func(double t) {
        if (t % T > T / 2 && t % T <= T) {
            return k;
        }// прямая линия
        else {
            return 0;
        }
    }

//    public static void showFunc() {
//        func();
//        XYSeries series = new XYSeries("График");
//        for (int i = 0; i < T_MAX; i++) {
//            series.add(i * h, f_x_arr.get(i));
//        }
//        XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(series);
//        JFreeChart chart = ChartFactory
//                .createXYLineChart("График решения ", "t", "f_x",
//                        dataset,
//                        PlotOrientation.VERTICAL,
//                        true, true, true);
//        JFrame frame =
//                new JFrame("Функция");
//
//        Component add = frame.getContentPane()
//                .add(new ChartPanel(chart));
//        frame.setSize(800, 600);
//        frame.setVisible(true);
//    }
}

