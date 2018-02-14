import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphPanel extends Application{

    public GraphPanel(){
        List<Double> list = new ArrayList<>();
        list = TradingGame.NYappleprices;
    }

    @Override public void start(Stage stage){

        stage.setTitle("Trading Game Graph");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Day of Trading");

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Trading Game Graph");

        final XYChart.Series<Number,Number> series = new XYChart.Series<>();

        series.setName("New York Apple Prices");

        series.getData().add(new XYChart.Data<>(1, list.get(0)));
        series.getData().add(new XYChart.Data<>(2, list.get(1)));
        series.getData().add(new XYChart.Data<>(3, list.get(2)));
        series.getData().add(new XYChart.Data<>(4, list.get(3)));
        series.getData().add(new XYChart.Data<>(5, list.get(4)));
        series.getData().add(new XYChart.Data<>(6, list.get(5)));
        series.getData().add(new XYChart.Data<>(7, list.get(6)));
        series.getData().add(new XYChart.Data<>(8, list.get(7)));
        series.getData().add(new XYChart.Data<>(9, list.get(8)));
        series.getData().add(new XYChart.Data<>(10, list.get(9)));

        Scene scene = new Scene(lineChart, 800, 600);

        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
