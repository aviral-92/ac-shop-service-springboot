package com.ac.global.focus;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FocusUI {

	public static void textFieldFocusLost(TextField textField) {

		textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (oldValue.booleanValue()) {
					textFieldAlert(textField);
				} else {
					textField.requestFocus();
				}
			}

		});

	}

	public static void textFieldAlert(TextField textField) {
		if (StringUtils.isEmpty(textField.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please Enter Name");
//			alert.showAndWait();
			alert.show();
			// textField.requestFocus();
		}
	}
}
