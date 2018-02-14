import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphPanel extends Application{
    @Override public void start(Stage stage, list){
        stage.setTitle("Trading Game Graph");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price");
        xAxis.setLabel("Day of Trading");
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Trading Game Graph");
        final XYChart.Series<Number,Number> series = new XYChart.Series<>();
        series.setName("My portfolio");
        series.getData().add(new XYChart.Data<>(1, NYappleprices.get(0)));
        series.getData().add(new XYChart.Data<>(2, NYappleprices.get(1)));
        series.getData().add(new XYChart.Data<>(3, NYappleprices.get(2)));
        series.getData().add(new XYChart.Data<>(4, NYappleprices.get(3)));
        series.getData().add(new XYChart.Data<>(5, NYappleprices.get(4)));
        series.getData().add(new XYChart.Data<>(6, NYappleprices.get(5)));
        series.getData().add(new XYChart.Data<>(7, NYappleprices.get(6)));
        series.getData().add(new XYChart.Data<>(8, NYappleprices.get(7)));
        series.getData().add(new XYChart.Data<>(9, NYappleprices.get(8)));
        series.getData().add(new XYChart.Data<>(10, NYappleprices.get(9)));

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
