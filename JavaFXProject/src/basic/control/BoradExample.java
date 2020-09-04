package basic.control;
//ui : boardcontrol.fxml

//controller : boardcontroller.java

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BoradExample extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane ap = FXMLLoader.load(getClass().getResource("BoardControl.fxml"));// 현재 클래스의 같은 경로에 있는 fxml을 불러오겠다

		Scene scene = new Scene(ap);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
