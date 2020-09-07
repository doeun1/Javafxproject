package basic.doeun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BookMain  extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		BorderPane bp = FXMLLoader.load(getClass().getResource("BookList.fxml"));
		
		Scene scene = new Scene(bp);
		arg0.setScene(scene);
		arg0.show();
	} 

	
	public static void main(String[] args) {
		launch(args);
	}

}
