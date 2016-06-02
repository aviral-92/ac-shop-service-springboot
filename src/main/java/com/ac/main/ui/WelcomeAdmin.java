package com.ac.main.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import com.ac.AcShopServiceApplication;
import com.ac.pojo.LoginPagePojo;

public class WelcomeAdmin extends Application {

	Label lblWelcome;
	MenuBar menuBar;
	Menu menuUser, menuCustomer;
	MenuItem menuGetUser, menuAddUser, menuDeleteUser;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// String style = "-fx-border-color: #385; -fx-padding: 5px;";
		LoginPagePojo readEnglishProperties = new LoginPagePojo(AcShopServiceApplication.test());
		lblWelcome = new Label(readEnglishProperties.getWelcome());
		lblWelcome.setStyle(
				"-fx-border-color: #985; -fx-padding: 5px; -fx-font: 16px Serif; -fx-background-color: #CCFF99");
		lblWelcome.setAlignment(Pos.CENTER);

		menuUser = new Menu(readEnglishProperties.getUser());
		// menuUser.setStyle(style);

		menuCustomer = new Menu(readEnglishProperties.getCustomer());
		// menuCustomer.setStyle(style);

		menuAddUser = new MenuItem(readEnglishProperties.getAddUser());
		// menuAddUser.setStyle(style);
		menuGetUser = new MenuItem(readEnglishProperties.getGetUserDetails());
		// menuGetUser.setStyle(style);
		menuDeleteUser = new MenuItem(readEnglishProperties.getDeleteUser());
		// menuDeleteUser.setStyle(style);

		menuUser.getItems().addAll(menuAddUser, menuGetUser, menuDeleteUser);

		menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuUser, menuCustomer);

		// GridPane gridPane = new GridPane();
		// GridPane gridPane1 = new GridPane();
		FlowPane flowPane = new FlowPane();
		FlowPane flowPane1 = new FlowPane();
		flowPane1.setCenterShape(true);
		flowPane1.setLayoutX(40);
		flowPane1.setLayoutY(75);
		AnchorPane anchorPane = new AnchorPane();

		// gridPane.setVgap(10);
		// gridPane.setHgap(10);
		// gridPane.setPadding(new Insets(10, 50, 0, 10));

		flowPane.getChildren().addAll(menuBar);
		flowPane1.getChildren().add(lblWelcome);
		// gridPane.add(lblWelcome, 0, 4);

		anchorPane.getChildren().addAll(flowPane, flowPane1);
		Scene scene = new Scene(anchorPane, 250, 150);
		scene.getStylesheets().add("css/UI.css");
		primaryStage.setTitle("Welcome Admin");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
