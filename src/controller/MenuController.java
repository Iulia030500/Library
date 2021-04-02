package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

public class MenuController {
	private Stage primaryStage;
	private Service service;

	public void setServices(Service service) {
		this.service = service;
		
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
	}
	
	public void handlerCarti () throws IOException {
		FXMLLoader loader;
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/application/Carti.fxml"));
        AnchorPane root = loader.load();
        Scene userScene = new Scene(root, 900, 700);
        CartiController cartiController = loader.getController();
        cartiController.setPrimaryStage(primaryStage);
        cartiController.setServices(service);
        primaryStage.setTitle("Carti");
        primaryStage.setScene(userScene);
        primaryStage.show();
	}
	
	public void handlerPersoane () throws IOException {
		FXMLLoader loader;
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/application/Persoane.fxml"));
        AnchorPane root = loader.load();
        Scene userScene = new Scene(root, 900, 700);
        PersoaneController persoaneController = loader.getController();
        persoaneController.setPrimaryStage(primaryStage);
        persoaneController.setServices(service);
        primaryStage.setTitle("Persoane");
        primaryStage.setScene(userScene);
        primaryStage.show();
		
	}
	
	public void handlerExit() throws IOException {
		primaryStage.close();
	}
}
