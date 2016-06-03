package com.ac.main.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.ac.AcShopServiceApplication;
import com.ac.commons.UiCommons;
import com.ac.pojo.LoginPagePojo;

public class UpdateUserDetail extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(5, 0, 0, 120));

		LoginPagePojo readEnglishProperties = new LoginPagePojo(
				AcShopServiceApplication.test());

		UiCommons commons = new UiCommons();
		List<String> data = new ArrayList<String>();
		// data.add(readEnglishProperties.getModifyUser());
		data.add(readEnglishProperties.getUsername());
		data.add(readEnglishProperties.getEmail());
		data.add(readEnglishProperties.getMobile());
		data.add(readEnglishProperties.getUserId());
		data.add(readEnglishProperties.getUsername());
		data.add(readEnglishProperties.getName());
		data.add(readEnglishProperties.getEmail());
		data.add(readEnglishProperties.getMobile());

		List<Label> labelList = commons.drawLabel(8, data);
		// From Here

		Text text = new Text(85, 25, readEnglishProperties.getModifyUser());
		text.setY(25);
		text.setFill(Color.CHOCOLATE);
		// text.setFont(Font.font(java.awt.Font.MONOSPACED, 35));
		Font monoFont = Font.font("Dialog", 30);
		text.setFont(monoFont);
		text.setFill(Color.BLACK);
		// For label username
		gridPane.add(labelList.get(0), 0, 4);
		// For label Email
		gridPane.add(labelList.get(1), 0, 5);
		// For label Mobile
		gridPane.add(labelList.get(2), 0, 6);

		List<TextField> textFieldList = commons.drawTextFields(8, 0);
		gridPane.add(textFieldList.get(0), 1, 4);
		gridPane.add(textFieldList.get(1), 1, 5);
		gridPane.add(textFieldList.get(2), 1, 6);

		List<String> data1 = new ArrayList<String>();
		data1.add(readEnglishProperties.getFind());
		data1.add(readEnglishProperties.getBack());
		data1.add(readEnglishProperties.getSubmitbutton());
		data1.add(readEnglishProperties.getCancel());
		List<Button> buttonList = commons.drawButton(4, data1);
		gridPane.add(buttonList.get(0), 0, 7);
		gridPane.add(buttonList.get(1), 1, 7);
		// Till Here Get UI is set

		// Now, after get data should display in respective UI will begin from
		// here.

		gridPane.add(labelList.get(3), 0, 8);
		gridPane.add(labelList.get(4), 0, 9);
		gridPane.add(labelList.get(5), 0, 10);
		gridPane.add(labelList.get(6), 0, 11);
		gridPane.add(labelList.get(7), 0, 12);

		gridPane.add(textFieldList.get(3), 1, 8);
		gridPane.add(textFieldList.get(4), 1, 9);
		gridPane.add(textFieldList.get(5), 1, 10);
		gridPane.add(textFieldList.get(6), 1, 11);
		gridPane.add(textFieldList.get(7), 1, 12);

		gridPane.add(buttonList.get(2), 0, 14);
		gridPane.add(buttonList.get(3), 1, 14);

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().addAll(gridPane, text);

		Scene scene = new Scene(anchorPane, 500, 400);
		scene.getStylesheets().add("css/UI.css");
		stage.setTitle("Get User Details");
		// stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		// stage.setFullScreen(false);
		stage.setResizable(false);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
