import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphPanel extends Application{

    @Override public void start(Stage stage){

        stage.setTitle("Trading Game Graph");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Day of Trading");

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Trading Game Graph");

        final XYChart.Series series = new XYChart.Series<>();

        series.setName("New York Apple Prices");

        series.getData().add(new XYChart.Data<>(1, 20));
        series.getData().add(new XYChart.Data<>(2, 14));
        series.getData().add(new XYChart.Data<>(3, 15));
        series.getData().add(new XYChart.Data<>(4, 24));
        series.getData().add(new XYChart.Data<>(5, 34));
        series.getData().add(new XYChart.Data<>(6, 36));
        series.getData().add(new XYChart.Data<>(7, 22));
        series.getData().add(new XYChart.Data<>(8, 45));
        series.getData().add(new XYChart.Data<>(9, 43));
        series.getData().add(new XYChart.Data<>(10, 17));

        Scene scene = new Scene(lineChart, 800, 600);

        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }
    /*
    public GraphPanel(){
        launch();
    }
    */
    public static void main(String[] args) {
        launch(args);
    }
}
