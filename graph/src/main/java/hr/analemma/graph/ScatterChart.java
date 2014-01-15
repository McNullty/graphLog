
package hr.analemma.graph;

import hr.analemma.graph.FileParser.AxisData;
import hr.analemma.graph.FileParser.ParseData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

public class ScatterChart extends JFrame {

	private static final long serialVersionUID = 1L;

	public ScatterChart(
		String applicationTitle, String chartTitle, String filename) {

		super(applicationTitle);

		try {
			InputStream is = new FileInputStream(filename);

			ParseData pd = FileParser.parseFile(is);

			// This will create the dataset
			XYSeriesCollection dataset = createDataset(pd.getAxisData());
			// based on the dataset we create the chart
			JFreeChart chart = createChart(dataset, chartTitle);
			
			
			TextTitle text = new TextTitle(pd.getSummary());
			text.setPosition(RectangleEdge.BOTTOM);
			chart.addSubtitle(text);
			
			// we put the chart into a panel
			ChartPanel chartPanel = new ChartPanel(chart);
			// default size
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
			// add it to our application
			setContentPane(chartPanel);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Creates a sample dataset
	 * 
	 * @param axisData
	 */

	private XYSeriesCollection createDataset(AxisData axisData) {

		XYSeriesCollection dataset = new XYSeriesCollection();

		List<Integer> x = axisData.getxAxis();
		List<BigDecimal> y = axisData.getyAxis();

		XYSeries data = new XYSeries("data");
		for (int i = 0; i < x.size(); i++) {
			data.add(x.get(i), y.get(i));
		}

		dataset.addSeries(data);
		return dataset;

	}

	/**
	 * Creates a chart
	 */

	private JFreeChart createChart(XYSeriesCollection dataset, String title) {

		JFreeChart chart =
			ChartFactory.createScatterPlot(
				title, "SIMNO", "Recovery time", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		// PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// plot.setStartAngle(290);
		// plot.setDirection(Rotation.CLOCKWISE);
		// plot.setForegroundAlpha(0.5f);

		// XYPlot plot = (XYPlot) chart.getPlot();

		return chart;

	}

	public static void main(String[] args) {
		if(args.length < 1){
			System.out.println("Treba specificirati datoteku");
			
			return;
		}

		ScatterChart demo = new ScatterChart("Plot Data", "", args[0]);
		demo.pack();
		demo.setVisible(true);
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
