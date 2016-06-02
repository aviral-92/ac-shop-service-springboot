package com.ac.main.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
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
import com.ac.global.focus.FocusUI;
import com.ac.main.connection.SqlConnections;
import com.ac.pojo.LoginPagePojo;
import com.ac.pojo.UserDetail;
public class AddUser extends Application {

	Button btnSubmit, btnCancel;

	@Override
	public void start(Stage primaryStage) throws Exception {

		LoginPagePojo readEnglishProperties = new LoginPagePojo(
				AcShopServiceApplication.test());

		btnSubmit = new Button(readEnglishProperties.getSubmitbutton());
		btnCancel = new Button(readEnglishProperties.getCancel());

		GridPane setTitle = new GridPane();

		GridPane setComponents = new GridPane();
		setComponents.setHgap(10);
		setComponents.setVgap(10);
		setComponents.setPadding(new Insets(5, 10, 0, 10));

		UiCommons commons = new UiCommons();
		List<TextField> textFields = commons.drawTextFields(4, 2);
		int i = 0;
		for (TextField textField : textFields) {
			setComponents.add(textField, 1, 3 + i);
			i++;
		}

		for (TextField textField : textFields) {
			FocusUI.textFieldFocusLost(textField);
		}

		List<String> data = new ArrayList<String>();
		data.add(readEnglishProperties.getAddUser());
		data.add(readEnglishProperties.getName());
		data.add(readEnglishProperties.getEmail());
		data.add(readEnglishProperties.getMobile());
		data.add(readEnglishProperties.getUsername());
		data.add(readEnglishProperties.getPswd());
		data.add(readEnglishProperties.getConfirmPswd());
		List<Label> labelList = commons.drawLabel(7, data);

		setTitle.add(labelList.get(0), 0, 0);

		for (int j = 1; j < labelList.size(); j++) {
			setComponents.add(labelList.get(j), 0, j + 2);
		}
		setComponents.add(btnSubmit, 0, 9);
		setComponents.add(btnCancel, 1, 9);

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().addAll(setTitle, setComponents);

		attachCode(primaryStage, textFields);

		Scene scene = new Scene(anchorPane, 350, 350);
		scene.getStylesheets().add("css/UI.css");
		primaryStage.setTitle("Welcome Admin");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void attachCode(Stage primaryStage, List<TextField> textFields) {
		// have each button run BTNCODE when clicked
		btnSubmit.setOnAction(e -> btncode(e, primaryStage, textFields));
		btnCancel.setOnAction(e -> btncode(e, primaryStage, textFields));
	}

	public void btncode(ActionEvent e, Stage primaryStage,
			List<TextField> textFields) {

		if (btnCancel == e.getSource()) {
			System.exit(0);
		}
		if (btnSubmit == e.getSource()) {

			List<UserDetail> listDetails = UiCommons
					.userDetailCommons(textFields);
			SqlConnections sqlConnections = new SqlConnections();
			String query = sqlConnections.addUser(listDetails);
			System.out.println(query);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
