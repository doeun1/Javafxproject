package basic.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	@FXML
	TableView<Student> tableView;
	@FXML
	Button btnAdd, btnBarChart;

	ObservableList<Student> list;

	Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) { // 컨트롤러에서 primary stage에서 사용하는 값들을 전달해주기 위해서
		this.primaryStage = primaryStage;
	}

	@Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      TableColumn<Student,?> tc = tableView.getColumns().get(0); //첫번째칼럼
      tc.setCellValueFactory(new PropertyValueFactory<>("name"));
      
      tc = tableView.getColumns().get(1);
      tc.setCellValueFactory(new PropertyValueFactory<>("korean"));
      
      tc = tableView.getColumns().get(2);
      tc.setCellValueFactory(new PropertyValueFactory<>("math"));

      tc = tableView.getColumns().get(3);
      tc.setCellValueFactory(new PropertyValueFactory<>("english"));
      
      //성적저장
      list = FXCollections.observableArrayList();
      
      tableView.setItems(list);
      
      //추가버튼
      btnAdd.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent arg0) {
            handleBtnAddAction() ;
         }
         
      });
      //차트버튼
      btnBarChart.setOnAction(e -> handleBtnChartAction());
		
     
      tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	  @Override
          public void handle(MouseEvent event) {
               System.out.println(event);
               	if(event.getClickCount()==2) {
               	String selectedName =tableView.getSelectionModel().getSelectedItem().getName();
               	handleDoubleClickedAction(selectedName);	
               	}
          }					

      });
   }

	public void handleDoubleClickedAction(String name) {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage); 
		
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(210,230);
		
		Label lKorean, lMath, lEnglish;
		TextField tName, tKorean, tMath, tEnglish;

		//위치지정	
		lKorean = new Label("국어");
		lKorean.setLayoutX(35);
		lKorean.setLayoutY(73);
		
		lMath = new Label("수학");
		lMath.setLayoutX(35);
		lMath.setLayoutY(99);
		
		lEnglish = new Label("영어");
		lEnglish.setLayoutX(35);
		lEnglish.setLayoutY(132);
		
		tName = new TextField();
		tName.setPrefWidth(110);
		tName.setLayoutX(72);
		tName.setLayoutY(30);
		
		//네임 수정불가하도록 수정함
		tName.setText(name);
		tName.setEditable(false);
		
		tKorean = new TextField();
		tKorean.setPrefWidth(110);
		tKorean.setLayoutX(72);
		tKorean.setLayoutY(69);
		
		tMath = new TextField();
		tMath.setPrefWidth(110);
		tMath.setLayoutX(72);
		tMath.setLayoutY(95);
		
		tEnglish = new TextField();
		tEnglish.setPrefWidth(110);
		tEnglish.setLayoutX(72);
		tEnglish.setLayoutY(128);
		
		//수정에 버튼 만들어주기
		Button btnUpdate = new Button("수정");
		btnUpdate.setLayoutX(85);
		btnUpdate.setLayoutY(184);
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for(int i=0; i<list.size(); i++) {
					if(list.get(i).getName().equals(name)) {
						Student student = new Student(name, Integer.parseInt(tKorean.getText()),
								Integer.parseInt(tMath.getText()),
								Integer.parseInt(tEnglish.getText())
								);//바깥 필드가 스트링타입이라서 인트타입으로 바꿔주려고 Integer.parseInt()를 씀
						list.set(i, student);
						stage.close();

					}
				}
			}
		});
		
		//이름기준으로 국어, 수학, 영어점수 화면에 입력하기
		for(Student stu : list) {
			if(stu.getName().equals(name)) {
				tMath.setText(String.valueOf(stu.getMath()));//settext가 스트링타입이라서 스트링타입을 받아와야함
				tKorean.setText(String.valueOf(stu.getKorean()));
				tEnglish.setText(String.valueOf(stu.getEnglish()));
			}
		}
		
		
		
		ap.getChildren().addAll(btnUpdate, tName, tKorean, tMath, tEnglish, lKorean, lMath, lEnglish);
		
		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
	}
	
	public void handleBtnChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);// setPrimaryStage함수가 있기 떄문에 stage값을 전달할수있다.

		try {
			Parent chart = FXMLLoader.load(getClass().getResource("BarChart.fxml"));
			Scene scene = new Scene(chart);
			stage.setScene(scene);
			stage.show();

			// chart가지고 와서 series를 추가해야한다
			BarChart barChart = (BarChart) chart.lookup("#barChart");
			// 국어
			XYChart.Series<String, Integer> seriesK = new XYChart.Series<String, Integer>();
			seriesK.setName("국어");

			ObservableList koreanList = FXCollections.observableArrayList();
			for (int i = 0; i < list.size(); i++) {
				koreanList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getKorean()));
			}

			// 수학
			XYChart.Series<String, Integer> seriesM = new XYChart.Series<String, Integer>();
			seriesM.setName("수학");

			ObservableList mathList = FXCollections.observableArrayList();
			for (int i = 0; i < list.size(); i++) {
				mathList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getKorean()));
			}

//영어         
			XYChart.Series<String, Integer> seriesE = new XYChart.Series<String, Integer>();
			seriesE.setName("영어");

			ObservableList englishList = FXCollections.observableArrayList();
			for (int i = 0; i < list.size(); i++) {
				englishList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getKorean()));
			}

			// 국어
			seriesK.setData(koreanList);
			barChart.getData().add(seriesK);

			// 수학
			seriesM.setData(mathList);
			barChart.getData().add(seriesM);

			// 영어
			seriesE.setData(englishList);
			barChart.getData().add(seriesE);
			
			Button btnClose = (Button) chart.lookup("#btnClose");
			btnClose.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					stage.close();
					
				}
				
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 추가화면 보여주는 작업
	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());// 나중에 추가

		try {
			// BorderPane BP = FXMLLoader.load(getClass().getResource("AddForm.fxml")); 같다
			// 밑에와
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));

			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();

			// 추가화면의 컨트롤 사용하기
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");

					Student student = new Student(txtName.getText(), Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()), Integer.parseInt(txtEnglish.getText()));

					list.add(student);

					stage.close();

				}

			});

			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e -> {
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtKorean = (TextField) parent.lookup("#txtKorean");
				TextField txtMath = (TextField) parent.lookup("#txtMath");
				TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");

				txtName.clear();
				txtKorean.clear();
				txtMath.clear();
				txtEnglish.clear();
				stage.close();

			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}