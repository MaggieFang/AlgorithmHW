package com.hw.simulation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 10/12/18
 * Talk is Cheap,Show me the Code.
 **/

public class BatchChart {

    // 保存为文件
    public static void saveAsFile(JFreeChart chart, String outputPath,
                                  int weight, int height) throws FileNotFoundException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            // ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
            // 保存为JPEG
            ChartUtilities.writeChartAsJPEG(out, chart, 600, 400);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    // 根据CategoryDataset创建JFreeChart对象
    public static JFreeChart createChart(CategoryDataset categoryDataset,String title ) {
        // 创建JFreeChart对象：ChartFactory.createLineChart
        JFreeChart jfreechart = ChartFactory.createLineChart(title, // 标题
                "Service Time", // categoryAxisLabel （category轴，横轴，X轴标签）
                "Time", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
        plot.setBackgroundAlpha(0.5f);
        plot.setForegroundAlpha(0.5f);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setUseSeriesOffset(true); // 设置偏移量
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        return jfreechart;
    }

    /**
     * 创建CategoryDataset对象
     */
    public static CategoryDataset createDataset(Task3.Result[] task) {
        String[] rowKeys = {"MEAN", "CI LOW", " CI HIGH"};
        String[] colKeys = new String[task.length];
        for (int i = 0; i < task.length; i++) {
            colKeys[i] = String.valueOf(task[i].serviceTime);
        }

        for (int i = 0; i < task.length; i++) {
            colKeys[i] = String.valueOf(task[i].serviceTime);
        }
        double[][] data = new double[3][task.length];

        for (int i = 0; i < task.length; i++) {
            data[0][i] = task[i].mean;
        }

        for (int i = 0; i < task.length; i++) {
            data[1][i] = task[i].low;
        }

        for (int i = 0; i < task.length; i++) {
            data[2][i] = task[i].hight;
        }

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }
}
