package com.ac.main.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.ac.AcShopServiceApplication;
import com.ac.commons.UiCommons;
import com.ac.main.connection.SqlConnections;
import com.ac.pojo.LoginPagePojo;
import com.ac.pojo.UserDetail;
import com.ac.pojo.UserDetails;

public class GetUserDetail extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(5, 10, 0, 40));
		GridPane pane = new GridPane();
		LoginPagePojo readEnglishProperties = new LoginPagePojo(
				AcShopServiceApplication.test());
		UiCommons commons = new UiCommons();
		List<String> data = new ArrayList<String>();
		data.add(readEnglishProperties.getGetUserDetails());
		data.add(readEnglishProperties.getName());
		data.add(readEnglishProperties.getMobile());
		data.add(readEnglishProperties.getEmail());
		data.add(readEnglishProperties.getUsername());
		List<Label> labelList = commons.drawLabel(5, data);
		pane.add(labelList.get(0), 0, 0);
		for (int i = 1; i < labelList.size(); i++) {
			gridPane.add(labelList.get(i), 0, i + 2);
		}
		List<TextField> textFieldList = commons.drawTextFields(4,0);
		for (int i = 0; i < textFieldList.size(); i++) {
			gridPane.add(textFieldList.get(i), 1, i + 3);
		}
		List<String> buttonText = new ArrayList<String>();
		buttonText.add(readEnglishProperties.getSubmitbutton());
		buttonText.add(readEnglishProperties.getCancel());
		List<Button> buttonList = commons.drawButton(2, buttonText);
		for (int i = 0; i < buttonList.size(); i++) {
			gridPane.add(buttonList.get(i), i, 7);
			attachCode(buttonList.get(i), primaryStage, textFieldList);
		}

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().addAll(pane, gridPane);

		Scene scene = new Scene(anchorPane, 350, 250);
		scene.getStylesheets().add("css/UI.css");
		primaryStage.setTitle("Get User Details");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void attachCode(Button btn, Stage primaryStage,
			List<TextField> textFields) {
		// have each button run BTNCODE when clicked
		btn.setOnAction(e -> btncode(btn, e, primaryStage, textFields));
	}

	public void btncode(Button btn, ActionEvent e, Stage primaryStage,
			List<TextField> textFields) {

		if (btn.getId().contains("Submit")) {
			List<UserDetail> userDetails = UiCommons
					.userDetailCommons(textFields);
			SqlConnections connections = new SqlConnections();
			List<UserDetails> userDetailsList = connections
					.getUser(userDetails);

			if (userDetailsList != null && userDetailsList.size() > 0) {
				GetUserDetailTable getUserDetailTable = new GetUserDetailTable(
						FXCollections.observableArrayList(), userDetailsList);
				getUserDetailTable.callTable(primaryStage);
			}
		} else {
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		launch(args);

	}
}
