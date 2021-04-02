package application;
	
import controller.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import repository.RepoCarti;
import repository.RepoPersoane;
import service.Service;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		RepoCarti rc = new RepoCarti();
		RepoPersoane rp = new RepoPersoane();
		
		Service s = new Service(rc, rp);
		try {
			 	FXMLLoader loader=new FXMLLoader();
		        loader.setLocation(getClass().getResource("/application/Sample.fxml"));
		        AnchorPane root=loader.load();
		        MenuController ctrl=loader.getController();
		        ctrl.setServices(s);
		        ctrl.setPrimaryStage(primaryStage);
		        primaryStage.setScene(new Scene(root, 900, 700));
		        primaryStage.setTitle("Biblioteca");
		        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
