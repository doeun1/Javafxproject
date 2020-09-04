package basic.control.chart;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class ChartController implements Initializable {
	@FXML
	PieChart pieChart;
	@FXML
	BarChart barChart;
	@FXML
	AreaChart areaChart;

	@Override
	public void initialize(URL location, ResourceBundle arg1) {// chartcontroller가 실행되면 젤 먼저 실행되는게 initialize
//piechart
		ObservableList<javafx.scene.chart.PieChart.Data> list = FXCollections.observableArrayList();

		list.add(new PieChart.Data("AWT", 10));
		list.add(new PieChart.Data("Swing", 30));
		list.add(new PieChart.Data("SWT", 25));
		list.add(new PieChart.Data("JavaFX", 35));
//		pieChart.setData(FXCollections.observableArrayList(//FXCollections.observableArrDatast인스턴스 생성해서 바로 값을 받음
//				new PieChart.Data("AWT", 10),
//				new PieChart.Data("Swing", 30),
//				new PieChart.Data("SWT", 25),
//				new PieChart.Data("JavaFX", 35)
//				));
		pieChart.setData(list);
//barchart
		XYChart.Series<String, Integer> s1 = new XYChart.Series<String, Integer>();
		s1.setData(getSeries1());
		s1.setName("남자");

		XYChart.Series<String, Integer> s2 = new XYChart.Series<String, Integer>();
		s2.setData(getSeries2());
		s2.setName("여자");
		barChart.getData().add(s1);
		barChart.getData().add(s2);
//areachart
		XYChart.Series<String, Integer> s3 = new XYChart.Series<String, Integer>();
		s3.setData(getSeries3());
		s3.setName("온도");

		XYChart.Series<String, Integer> s4 = new XYChart.Series<String, Integer>();
		s4.setData(getSeries4());
		s4.setName("Covid19");
		areaChart.getData().add(s3);
		areaChart.getData().add(s4);
	}

	
	public ObservableList<XYChart.Data<String, Integer>> getSeries1() {
		ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("2015", 70));
		list.add(new XYChart.Data<String, Integer>("2016", 40));
		list.add(new XYChart.Data<String, Integer>("2017", 50));
		list.add(new XYChart.Data<String, Integer>("2018", 30));
		return list;

	}

	public ObservableList<XYChart.Data<String, Integer>> getSeries2() {
		ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("2015", 70));
		list.add(new XYChart.Data<String, Integer>("2016", 40));
		list.add(new XYChart.Data<String, Integer>("2017", 50));
		list.add(new XYChart.Data<String, Integer>("2018", 30));
		return list;

	}
	public ObservableList<XYChart.Data<String, Integer>> getSeries3(){
	      Connection conn = ConnectionDB.getDB();
	      String sql = "select * from receipt";
	      ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
	      
	      try {
	         PreparedStatement pstmt=conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	            list.add(new XYChart.Data<>(rs.getString("receipt_month"),rs.getInt("receipt_qty")));
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
//	      ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
	      list.add(new XYChart.Data<String, Integer>("9",13));
	      list.add(new XYChart.Data<String, Integer>("10",14));
	      list.add(new XYChart.Data<String, Integer>("11",30));
	      list.add(new XYChart.Data<String, Integer>("12",20));
	      
	      return list;
	   }

	public ObservableList<XYChart.Data<String, Integer>> getSeries4() {
		ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("09", 5));
		list.add(new XYChart.Data<String, Integer>("10", 12));
		list.add(new XYChart.Data<String, Integer>("11", 15));
		list.add(new XYChart.Data<String, Integer>("12", 20));
		return list;

	}
}
