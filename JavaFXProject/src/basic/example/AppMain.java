package basic.example;
//ui : root.fxml(기본), addform.fxml(추가), barchart.fxml(차트)
//controller : rootcontroller.java
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppMain extends Application {

	   

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader =	new FXMLLoader(getClass().getResource("Root.fxml"));
		BorderPane root = loader.load();
		
		basic.example.RootController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
