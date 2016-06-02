package com.ac.main.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.ac.AcShopServiceApplication;
import com.ac.commons.UiCommons;
import com.ac.pojo.ColumnDetail;
import com.ac.pojo.LoginPagePojo;
import com.ac.pojo.UserDetails;

public class GetUserDetailTable extends Application {

	ObservableList tabObservableList = null;
	List<UserDetails> userDetailsList;

	public ObservableList getTabObservableList() {
		return tabObservableList;
	}

	public void setTabObservableList(ObservableList tabObservableList) {
		this.tabObservableList = tabObservableList;
	}

	public List<UserDetails> getUserDetailsList() {
		return userDetailsList;
	}

	public void setUserDetailsList(List<UserDetails> userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

	@Override
	public void start(Stage stage) {

		LoginPagePojo readEnglishProperties = new LoginPagePojo(
				AcShopServiceApplication.test());
		String tableTitle = "User Details";

		UiCommons commons = new UiCommons();
		List<String> data = new ArrayList<String>();
		data.add(readEnglishProperties.getSubmitbutton());
		data.add(readEnglishProperties.getCancel());
		List<Button> buttonList = commons.drawButton(2, data);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(0, 0, 0, 80));

		gridPane.add(buttonList.get(0), 0, 1);
		gridPane.add(buttonList.get(1), 1, 1);

		ColumnDetail columnDetail = new ColumnDetail(1, "username");
		ColumnDetail columnDetail1 = new ColumnDetail(1, "name");
		ColumnDetail columnDetail2 = new ColumnDetail(1, "email");
		ColumnDetail columnDetail3 = new ColumnDetail(1, "mobile");

		List<ColumnDetail> columnDetails = new ArrayList<ColumnDetail>();
		columnDetails.add(columnDetail);
		columnDetails.add(columnDetail1);
		columnDetails.add(columnDetail2);
		columnDetails.add(columnDetail3);

		UiCommons.tableCreation(gridPane, tableTitle, stage, tabObservableList,
				columnDetails);

	}

	public void callTable(Stage stage) {

		UserDetails userDetails = null;

		for (UserDetails uiUseDetails : userDetailsList) {
			userDetails = new UserDetails(uiUseDetails.getUsername(),
					uiUseDetails.getName(), uiUseDetails.getEmail(),
					uiUseDetails.getMobile());
			tabObservableList.add(userDetails);
		}
		start(stage);

	}

	public GetUserDetailTable() {
	}

	public GetUserDetailTable(ObservableList tabObservableList,
			List<UserDetails> userDetailsList) {
		super();
		this.tabObservableList = tabObservableList;
		this.userDetailsList = userDetailsList;
	}
}
