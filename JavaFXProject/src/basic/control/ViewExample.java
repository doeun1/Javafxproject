package basic.control;
//ui : view.fxml
//control : viewcontroller.jave

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//ui : input.fxml
//controller : inputcontroller.java
public class ViewExample extends Application {

		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			AnchorPane root = FXMLLoader.load(getClass().getResource("View.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}
		public static void main(String[] args) {
			Application.launch(args);
		}
}

