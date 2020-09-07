package basic.doeun;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private SimpleStringProperty name;
	private SimpleStringProperty writer;
	private SimpleStringProperty company;
	private SimpleIntegerProperty price;
	

	public Book(String name, String writer, String company, int price) {
		
		this.name = new SimpleStringProperty(name);
		this.writer = new SimpleStringProperty(writer);
		this.company = new SimpleStringProperty(company);
		this.price = new SimpleIntegerProperty(price);
	}


	public String getName() {
		return this.name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getWriter() {
		return this.writer.get();
	}

	public void setWriter(String writer) {
		this.writer.set(writer);
	}

	public String getCompany() {
		return this.company.get();
	}

	public void setCompany(String company) {
		this.company.set(company);
	}
	public int getPrice() {
		return this.price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}


}
	