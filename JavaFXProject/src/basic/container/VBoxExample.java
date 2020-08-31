package basic.container;

//책에 있는 내용을 자바의 클래스와 메소드로 만들어봄
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {//start라는 추상메소드
		VBox root = new VBox();//javafx에 .....?
		root.setPadding(new Insets(10,10,10,10));
		
		ImageView iv = new ImageView();
		iv.setFitWidth(200);//더블타입어쩌구
		iv.setPreserveRatio(true);//비율맞추기
		iv.setImage(new Image("/basic/images/fruit1.jpg"));

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		
		Button btnPrev = new Button();
		btnPrev.setText("이전");
		Button btnNext = new Button("다음");
		HBox.setHgrow(btnNext, Priority.ALWAYS);
		btnNext.setMaxWidth(Double.MAX_VALUE);
		hbox.getChildren().add(btnPrev);
		hbox.getChildren().add(btnNext);
		VBox.setMargin(hbox, new Insets(10));
		
		//이벤트 핸들러를 해당 컨트롤에 등록
		btnNext.setOnAction(new EventHandler<ActionEvent>() {//이벤트 핸들러라는 제네릭 타입을 받음
			int loc = 1;
			
			@Override
			public void handle(ActionEvent ae) {//이벤트 핸들러 구현
				if(loc == 9)
					loc =1;
				iv.setImage(new Image("/basic/images/fruit"+ loc++ +".jpg"));
				
			}
			
		});//액션이 발생했을때 이벤트를 실행하겠다
		
		root.getChildren().add(iv);
		root.getChildren().add(hbox);

		Scene scene = new Scene(root);//컨테이너를 담고 있는게 scene, 신을 담고 있는게 stage
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("VBox 예제");
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
