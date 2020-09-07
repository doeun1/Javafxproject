package basic.doeun;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookController implements Initializable {
	@FXML
	TableView<Book> tableView;
	@FXML
	Button btnAdd, btnFormModify;

	ObservableList<Book> list;

	Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) { // 컨트롤러에서 primary stage에서 사용하는 값들을 전달해주기 위해서
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Book, ?> tc = tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("writer"));
		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("company"));
		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("price"));

		list = FXCollections.observableArrayList();
		tableView.setItems(list);

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				handleBtnAddAction();
			}

		});

		tableView.setOnMouseClicked(event -> {

			if (event.getClickCount() == 2) { // 더블클릭이면
				String selectedName = tableView.getSelectionModel().getSelectedItem().getName();
				handleDoubleClickAction(selectedName);
			}
		});
	}

	public void handleDoubleClickAction(String name) {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BookUpdate.fxml"));

			Scene scene = new Scene(parent);
			stage.setTitle("도서수정");
			stage.setScene(scene);
			stage.show();

			Button btnFormModify = (Button) parent.lookup("#btnFormModify");
			TextField txtName = (TextField) parent.lookup("#txtName");
			TextField txtWriter = (TextField) parent.lookup("#txtWriter");
			TextField txtCompany = (TextField) parent.lookup("#txtCompany");
			TextField txtPrice = (TextField) parent.lookup("#txtPrice");

			for (Book bookList : list) {
				if (bookList.getName().equals(name)) {
					txtName.setText(String.valueOf(bookList.getName()));
					txtWriter.setText(String.valueOf(bookList.getWriter()));
					txtCompany.setText(String.valueOf(bookList.getCompany()));
					txtPrice.setText(String.valueOf(bookList.getPrice()));
				}
			}
			
			  btnFormModify.setOnAction(new EventHandler<ActionEvent>() {

		            @Override
		            public void handle(ActionEvent event) {
		               for(int i = 0; i< list.size(); i++) {
		                  if(list.get(i).getName().equals(name)) {
		                     Book book = new Book(txtName.getText(), 
		                           txtWriter.getText(), 
		                           txtCompany.getText(), 
		                           Integer.parseInt(txtPrice.getText()));
		                     list.set(i, book);
		                     stage.close();
		                  }
		               }
		            }
		         });

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());// 나중에 추가
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));

			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtWriter = (TextField) parent.lookup("#txtWriter");
					TextField txtCompany = (TextField) parent.lookup("#txtCompany");
					TextField txtPrice = (TextField) parent.lookup("#txtPrice");

					Book book = new Book(txtName.getText(), txtWriter.getText(), txtCompany.getText(),
							Integer.parseInt(txtPrice.getText()));
					list.add(book);

					stage.close();

				}
			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
