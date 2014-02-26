
package hr.analemma.graph;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {

	public static class AxisData {

		private List<Integer> xAxis;
		private List<BigDecimal> yAxis;

		public AxisData(List<Integer> x, List<BigDecimal> y) {

			this.setxAxis(x);
			this.setyAxis(y);
		}

		public List<Integer> getxAxis() {

			return xAxis;
		}

		public void setxAxis(List<Integer> xAxis) {

			this.xAxis = xAxis;
		}

		public List<BigDecimal> getyAxis() {

			return yAxis;
		}

		public void setyAxis(List<BigDecimal> yAxis) {

			this.yAxis = yAxis;
		}
	}

	public static class ParseData {

		private AxisData axisData;
		private String summary;

		public ParseData(AxisData data, String sum) {

			this.setAxisData(data);
			this.setSummary(sum);
		}

		public AxisData getAxisData() {

			return axisData;
		}

		public void setAxisData(AxisData axisData) {

			this.axisData = axisData;
		}

		public String getSummary() {

			return summary;
		}

		public void setSummary(String summary) {

			this.summary = summary;
		}

	}

	public static ParseData parseFile(InputStream file)
		throws ParseException {

		Scanner scanner = new Scanner(file);

		findDataStart(scanner);

		AxisData points = getAllDataPoints(scanner);
		String summery = getDataSummery(scanner);

		ParseData pd = new ParseData(points, summery);

		return pd;
	}

	private static String getDataSummery(Scanner scanner) {

		StringBuilder sb = new StringBuilder();

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.length() == 0) {
				break;
			}
			else {
				sb.append(line + "\n");
			}
		}

		return sb.toString();
	}

	private static AxisData getAllDataPoints(Scanner scanner) {
		List<Integer> x = new ArrayList<Integer>();
		List<BigDecimal> y = new ArrayList<BigDecimal>();
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.length() == 0) {
				break;
			}
			else {
				x.add(Integer.parseInt(line.substring(0, 8).trim())); // SIMNO
				y.add(new BigDecimal(line.substring(160, 167).trim())); // Recovery time			
			}
		}
		
		AxisData data =  new AxisData(x, y);
		return data;
	}

	private static void findDataStart(Scanner scanner)
		throws ParseException {

		while (scanner.hasNextLine()) {
			if (scanner.nextLine().startsWith("SIMNO")) {
				return;
			}
		}

		throw new ParseException("Coudnt find data in file", 0);
	}
}
