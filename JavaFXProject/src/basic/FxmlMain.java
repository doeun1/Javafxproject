package basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("AnchorRoot.fxml"));//메인파일에,, root파일에 ,,,,,,,,,걔를 hbox에 담겠다
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("FXML 화면");
		
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
