import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Method_Runge_Kutta {
    private static final double k = 1;
    private static final double m = 0.5;
    private static final double c = 300;
    private static double x0 = 0.1;
    private static final double v0 = 0;
    private static final double a = k / m; //0
    private static final double μ = 0.15;
    private static final double g = 9.8;
    private static final double alpha = 0.23;
    private static final double beta = alpha / (2 * m);
    private static final double w2 = c / m;
    private static final double w = Math.sqrt(w2);
    private static final double omega = 2.2;

//    private static final double w1 = Math.sqrt(w2 - beta * beta);
//    private static final double c1 = 0.01; // x0
//    private static final double v0 = 0.02;
//    private static final double c2 = (v0 * (w2 - omega * omega) - a * omega) / w * (w - omega * omega);

    private static final double start_time = 0;
    private static final double end_time = 10;
    private static final double n = 1000;
    private static final double h = (end_time - start_time) / n;

    private static final List<List<Double>> result = new ArrayList<List<Double>>();
    private static final List<Double> result_x = new ArrayList<Double>();
    private static final List<Double> result_y = new ArrayList<Double>();

    private static double func(double x, double y) {
        return y;
    }

//  private static double func_t(double x, double y, double t) {
//        return -w2 * x - μ * g * Math.signum(y) + a * Math.sin(omega * t);
//    }

    private static double func_t(double x, double y, double t) {
        return -w2 * x - 2 * beta * Math.signum(y) + a * Math.sin(omega * t);
    }

    private static void RungeKutt(double h) {

        double y0 = v0;
        result_x.add(x0);
        result_y.add(y0);
        for (int i = 0; i <= n - 1; i++) {
            double t0 = h * i;

            double k11 = h * func(x0, y0);
            double k12 = h * func_t(x0, y0, t0);

            double k21 = h * func(x0 + k11 / 2, y0 + k12 / 2);
            double k22 = h * func_t(x0 + k11 / 2, y0 + k12 / 2, t0 + h / 2);

            double k31 = h * func(x0 + k21 / 2, y0 + k22 / 2);
            double k32 = h * func_t(x0 + k21 / 2, y0 + k22 / 2, t0 + h / 2);

            double k41 = h * func(x0 + k31, y0 + k32);
            double k42 = h * func_t(x0 + k31, y0 + k32, t0 + h);

            double dx = (k11 + 2 * k21 + 2 * k31 + k41) / 6;
            double dy = (k12 + 2 * k22 + 2 * k32 + k42) / 6;

            double xi = x0 + dx;
            double yi = y0 + dy;

            result_x.add(xi);
            result_y.add(yi);
            y0 = yi;
            x0 = xi;
        }
        result.add(result_x);
        result.add(result_y);
    }

    public static void minMax() {

        System.out.println(Collections.min(result_x));
        System.out.println(Collections.max(result_x));
    }

    public static void showFunc() {
        RungeKutt(h);
        minMax();
//        XYSeries series1 = new XYSeries("График точного решения");
        XYSeries series2 = new XYSeries("График");
        for (int i = 0; i <= n; i++) {
//            final double v = Math.pow((w2 - omega * omega), 2)
//                    + 4 * beta * beta * omega * omega;
//            double x_t = Math.exp(-beta * i/10)
//                    * (c1 * Math.cos(w1 * i/10) + c2 * Math.sin(w1 * i/10))
//                    - (2 * beta * omega) / v * Math.cos(omega * i/10)
//                    + (Math.pow((w2 - omega * omega), 2) * a) / v * Math.sin(omega * i/10);
//            series1.add(i* h, x_t/5);
            series2.add(i * h, result_x.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(series1);
        dataset.addSeries(series2);
        JFreeChart chart = ChartFactory
                .createXYLineChart("График решения Рунге-Кутты 4 порядка", "t", "x",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame frame =
                new JFrame("Функция Рунге-Кутты 4 порядка");

        Component add = frame.getContentPane()
                .add(new ChartPanel(chart));
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
//    public static void showFuncTest() {
//        RungeKutt(h);
//        XYSeries series1 = new XYSeries("График точного решения");
//        XYSeries series2 = new XYSeries("График");
//        for (int i = 0; i <= n; i++) {
//            final double v = Math.pow((w2 - omega * omega), 2)
//                    + 4 * beta * beta * omega * omega;
//            double x_t = Math.exp(-beta * i)
//                    * (c1 * Math.cos(w1 * i) + c2 * Math.sin(w1 * i))
//                    - (2 * beta * omega) / v * Math.cos(omega * i)
//                    + (Math.pow((w2 - omega * omega), 2) * a) / v * Math.sin(omega * i);
//            series1.add(i * h, x_t);
//            series2.add(i * h, result_x.get(i));
//        }
//        XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(series1);
//        dataset.addSeries(series2);
//        JFreeChart chart = ChartFactory
//                .createXYLineChart("График решения Рунге-Кутты 4 порядка", "t", "x",
//                        dataset,
//                        PlotOrientation.VERTICAL,
//                        true, true, true);
//        JFrame frame =
//                new JFrame("Функция Рунге-Кутты 4 порядка");
//
//        Component add = frame.getContentPane()
//                .add(new ChartPanel(chart));
//        frame.setSize(800, 600);
//        frame.setVisible(true);
//    }

}