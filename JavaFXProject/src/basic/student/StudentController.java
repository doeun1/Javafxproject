package basic.student;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.student.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentController implements Initializable {
	@FXML TableView<Student> tableView;
	@FXML Button btnAdd, btnBarChart;
	
	ObservableList<Student> list; 
	Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) { // 컨트롤러에서 primary stage에서 사용하는 값들을 전달해주기 위해서
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		   TableColumn<Student,?> tc = tableView.getColumns().get(0);
		   tc.setCellValueFactory(new PropertyValueFactory<>("id"));
		   tc = tableView.getColumns().get(1);
		   tc.setCellValueFactory(new PropertyValueFactory<>("name")); 
		   tc = tableView.getColumns().get(2);
		   tc.setCellValueFactory(new PropertyValueFactory<>("korean")); 
		   tc = tableView.getColumns().get(3);
		   tc.setCellValueFactory(new PropertyValueFactory<>("math")); 
		   tc = tableView.getColumns().get(4);
		   tc.setCellValueFactory(new PropertyValueFactory<>("english")); 
		   
		   list = FXCollections.observableArrayList();
		   list = getStudentList();
		   tableView.setItems(list);//

		   btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleBtnAddAction();
			}
			   
		   });
	}
	
	public ObservableList<Student> getStudentList() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr", passwd = "hr";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String sql = "select * from student order by 1";
		ObservableList<Student> list = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Student st = new Student(rs.getString("id"),rs.getString("name")
						,rs.getInt("korean"),rs.getInt("math"),rs.getInt("english"));
				list.add(st);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
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
					TextField txtId = (TextField) parent.lookup("#txtId");

					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");

					Student student = new Student(txtId.getText(),txtName.getText(), Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()), Integer.parseInt(txtEnglish.getText()));

					list.add(student);
					insertStudent(student);
					stage.close();

				}

			});
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e -> {
				TextField txtId = (TextField) parent.lookup("#txtId");
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtKorean = (TextField) parent.lookup("#txtKorean");
				TextField txtMath = (TextField) parent.lookup("#txtMath");
				TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");

				txtId.clear();
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
	public void insertStudent(Student st ){
		//sql db연결하여 정보 넣기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "hr", passwd ="hr";
				Connection conn = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection(url, user, passwd);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
						
				String sql="insert into student values (?,?,?,?,?)";
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, st.getId());
					pstmt.setString(2, st.getName());
					pstmt.setInt(3, st.getKorean());
					pstmt.setInt(4, st.getMath());
					pstmt.setInt(5, st.getEnglish());
					
					int r = pstmt.executeUpdate();
					System.out.println(r+"건 입력됨");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		
	}
	
}
