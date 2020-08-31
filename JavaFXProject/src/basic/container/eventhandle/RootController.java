package basic.container.eventhandle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;

public class RootController implements Initializable {//Initializable라는 추상메소드 구현
	@FXML Label label;
	@FXML Slider slider;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number startvalue, Number endValue) {
				System.out.println("startValue : "+startvalue.doubleValue());
				System.out.println("endValue : " +endValue.doubleValue());
				label.setFont(new Font(endValue.doubleValue()));//endvalue는 number 타입
				
			}
			
		});
		
	}

}
