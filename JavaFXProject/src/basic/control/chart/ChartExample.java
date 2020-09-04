package basic.control.chart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//ui : Chart.fxml(p.925)
//control : chartController.java
public class ChartExample extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		HBox ap = FXMLLoader.load(this.getClass().getResource("Chart.fxml"));// 
//this.getclass하는게 ???
		Scene scene = new Scene(ap);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}