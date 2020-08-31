package basic.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		TilePane tile = new TilePane();
		tile.setPrefTileHeight(100);
		tile.setPrefTileWidth(100);
		
		ImageView iv = new ImageView();
		iv.setImage(new Image("/basic/images/fruit1.jpg"));
		
		ImageView iv2 = new ImageView();
		iv2.setImage(new Image("/basic/images/fruit2.jpg"));
		
		ImageView iv3 = new ImageView();
		iv3.setImage(new Image("/basic/images/fruit3.jpg"));
		
		ImageView iv4 = new ImageView();
		iv4.setImage(new Image("/basic/images/fruit4.jpg"));
		
		ImageView iv5 = new ImageView();
		iv5.setImage(new Image("/basic/images/fruit5.jpg"));
		
		
		tile.getChildren().add(iv);
		tile.getChildren().add(iv2);
		tile.getChildren().add(iv3);
		tile.getChildren().add(iv4);
		tile.getChildren().add(iv5);
		
		Scene scene = new Scene(tile);//컨테이너를 담고 있는게 scene, 신을 담고 있는게 stage
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("tile 예제");
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
