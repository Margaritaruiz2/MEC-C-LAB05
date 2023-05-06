import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficaabortos {

    public static void main(String[] args) {

        // conjunto de datos de la gráfica
        XYSeriesCollection dataset = new XYSeriesCollection();

        try {
            // Lee los datos 
            BufferedReader reader = new BufferedReader(new FileReader("abortos.csv"));
            String line = null;
            List<String[]> data = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }

            //  nueva serie para los datos de la gráfica
            XYSeries series = new XYSeries("Abortos");

            // Agrega los datos a la serie
            for (String[] row : data) {
                double x = Double.parseDouble(row[0]);
                double y = Double.parseDouble(row[1]);
                series.add(x, y);
            }

            //  serie al conjunto de datos
            dataset.addSeries(series);

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //  gráfica de línea
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Abortos por año",  
                "Año",             
                "Abortos",          
                dataset            
        );

        // Muestra grafica
        ChartFrame frame = new ChartFrame("Mi gráfica", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
