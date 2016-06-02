package com.ac.commons;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;

import com.ac.pojo.ColumnDetail;
import com.ac.pojo.TempUserDetail;
import com.ac.pojo.UserDetail;
import com.ac.pojo.UserDetails;

@Component
public class UiCommons {
	
	public List<TextField> drawTextFields(int textFieldNumber,
			int passwordFieldNumber) {

		TextField field;
		PasswordField passwordField;
		List<TextField> textFields = new ArrayList<>();
		for (int i = 0; i < textFieldNumber; i++) {
			field = new TextField();
			field.setId("textField-" + i);
			textFields.add(field);
		}
		for (int i = 0; i < passwordFieldNumber; i++) {
			passwordField = new PasswordField();
			textFields.add(passwordField);
		}
		return textFields;
	}

	public List<Label> drawLabel(int n, List<String> data) {

		Label label;
		List<Label> labels = new ArrayList<Label>();
		for (int i = 0; i < n; i++) {
			label = new Label(data.get(i));
			labels.add(label);
		}
		return labels;
	}

	public List<Button> drawButton(int n, List<String> data) {

		Button button;
		List<Button> buttons = new ArrayList<Button>();
		for (int i = 0; i < n; i++) {
			button = new Button(data.get(i));
			button.setId("button-" + data.get(i));
			buttons.add(button);
		}
		return buttons;
	}

	public void attachCode(Button btn, Stage primaryStage,
			List<TextField> textFields) {
		// have each button run BTNCODE when clicked
		btn.setOnAction(e -> btncode(btn, e, primaryStage, textFields));
	}

	public void btncode(Button btn, ActionEvent e, Stage primaryStage,
			List<TextField> textFields) {

		if (btn.getId().contains("Submit")) {
			for (TextField txt : textFields) {
				System.out.println(txt.getText());
			}
		} else {
			System.exit(0);
		}

	}

	public static <E> void tableCreation(GridPane gridPane, String tableTitle,
			Stage stage, ObservableList tabObservableList,
			List<ColumnDetail> columnDetails) {
		TableView table = new TableView();
		Scene scene = new Scene(new Group());
		stage.setTitle(tableTitle);
		stage.setWidth(450);
		stage.setHeight(550);

		final Label label = new Label(tableTitle);
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		// if(tabObservableList instanceof TableViewPojo) {
		// ObservableList<UserDetails> observableList =
		// (ObservableList<UserDetails>) tabObservableList ;
		if (tabObservableList != null && !tabObservableList.isEmpty()
				&& tabObservableList.get(0) instanceof UserDetails) {
			TableColumn<UserDetails, String> username = new TableColumn<UserDetails, String>(
					"Username");
			username.setMinWidth(50);
			username.setCellValueFactory(new PropertyValueFactory<UserDetails, String>(
					"username"));

			TableColumn<UserDetails, String> name = new TableColumn<UserDetails, String>(
					"Name");
			name.setMinWidth(50);
			name.setCellValueFactory(new PropertyValueFactory<UserDetails, String>(
					"name"));

			TableColumn<UserDetails, String> emailCol = new TableColumn<UserDetails, String>(
					"Email");
			emailCol.setMinWidth(180);
			emailCol.setCellValueFactory(new PropertyValueFactory<UserDetails, String>(
					"email"));

			TableColumn<UserDetails, String> mobile = new TableColumn<UserDetails, String>(
					"Mobile");
			mobile.setMinWidth(50);
			mobile.setCellValueFactory(new PropertyValueFactory<UserDetails, String>(
					"mobile"));
			table.setItems(tabObservableList);
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			table.getColumns().addAll(username, name, emailCol, mobile);
		} else {
			// TEMP
			TableColumn<TempUserDetail, String> username = new TableColumn<TempUserDetail, String>(
					"Hello");
			username.setMinWidth(50);
			username.setCellValueFactory(new PropertyValueFactory<TempUserDetail, String>(
					"username"));

			TableColumn<TempUserDetail, String> name1 = new TableColumn<TempUserDetail, String>(
					"Jai");
			name1.setMinWidth(50);
			name1.setCellValueFactory(new PropertyValueFactory<TempUserDetail, String>(
					"name1"));

			TableColumn<TempUserDetail, String> emailCol = new TableColumn<TempUserDetail, String>(
					"Ho");
			emailCol.setMinWidth(180);
			emailCol.setCellValueFactory(new PropertyValueFactory<TempUserDetail, String>(
					"email"));

			TableColumn<TempUserDetail, String> mobile = new TableColumn<TempUserDetail, String>(
					"Demo");
			mobile.setMinWidth(50);
			mobile.setCellValueFactory(new PropertyValueFactory<TempUserDetail, String>(
					"mobile"));
			table.setItems(tabObservableList);
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			table.getColumns().addAll(username, name1, emailCol, mobile);
		}

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, gridPane);
		scene.getStylesheets().add("css/UI.css");
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setScene(scene);
		stage.show();

	}

	public static List<UserDetail> userDetailCommons(List<TextField> textFields) {
		List<UserDetail> userDetails = null;
		UserDetail userDetail = null;
		if (textFields != null && !textFields.isEmpty()) {
			userDetails = new ArrayList<UserDetail>();
			int i = 1;
			for (TextField txtField : textFields) {
				userDetail = new UserDetail(i, txtField.getText());
				userDetails.add(userDetail);
				i++;
			}
		}
		return userDetails;
	}
}
