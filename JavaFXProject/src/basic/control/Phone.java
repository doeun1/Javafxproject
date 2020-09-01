package basic.control;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Phone {
	StringProperty medel;
	IntegerProperty price;
	
	public void setModel(String model) {
		this.model.set(model);
	}
	
}
