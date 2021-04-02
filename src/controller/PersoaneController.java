package controller;

import java.io.IOException;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import domain.Persoana;
import exceptions.RepoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

public class PersoaneController {
	private Stage primaryStage;
	private Service service;
	ObservableList<Persoana> model = FXCollections.observableArrayList();
	@FXML 
	TextField id_field;
	@FXML 
	TextField nume_field;
	@FXML 
	TextField cnp_field;
	@FXML 
	TextField carti_field;
	@FXML 
	TableView<Persoana> persoane;
	@FXML
	TableColumn<Persoana,Integer> id;
	@FXML
	TableColumn<Persoana, String> nume;
	@FXML
	TableColumn <Persoana, String> cnp;
	@FXML
	TableColumn <Persoana, String> carti;
	@FXML 
	TextField search_nume;

	public void setServices(Service service) {
		this.service = service;
		model.setAll(getPersoane());
	}

	public List<Persoana> getPersoane(){
		return service.getPersoane();
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
	}
	 public void initialize() throws FileNotFoundException {
	        id.setCellValueFactory(new PropertyValueFactory<Persoana, Integer>("id"));
	        nume.setCellValueFactory(new PropertyValueFactory<Persoana, String>("nume"));
	        cnp.setCellValueFactory(new PropertyValueFactory<Persoana, String>("CNP"));
	        carti.setCellValueFactory(new PropertyValueFactory<Persoana, String>("lista_c"));
	        persoane.setItems(model);

	    }
	
	public void handlerCreate() {
		int id = Integer.valueOf(id_field.getText());
		String nume = nume_field.getText();
		String cnp = cnp_field.getText();
		String carti = carti_field.getText();
		List<Integer> lista = new ArrayList<Integer>();
		String sir[] = carti.split(" ");
	    for (String ss: sir) 
	    	lista.add(Integer.parseInt(ss));
		try {
		service.adaugare_persoana(id, nume, cnp, lista);
		}catch (RepoException ex) {
			AlertBox alertbox = new AlertBox("Prostule", ex.getMessage());
			alertbox.display();
		}
		model.setAll(getPersoane());
		
	}
	public List<Persoana> getPiese(){
		return service.getPersoane();
	}
	public void handlerUpdate() {
		int id = Integer.valueOf(id_field.getText());
		String nume = nume_field.getText();
		String cnp = cnp_field.getText();
		String carti = carti_field.getText();
		List<Integer> lista = new ArrayList<Integer>();
		String sir[] = carti.split(" ");
	    for (String ss: sir) 
	    	lista.add(Integer.parseInt(ss));
		try {
		service.modificare_persoana(id, nume, cnp, lista);
		}catch (RepoException ex) {
			AlertBox alertbox = new AlertBox("Prostule", ex.getMessage());
			alertbox.display();
		}
		model.setAll(getPiese());
	}
	
	public void handlerDelete() {
		Persoana persoana = persoane.getSelectionModel().getSelectedItem();
		service.stergere_persoana(persoana.getId());
		model.setAll(getPiese());
	}

	public void handlerExit() throws IOException {
		FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/application/Sample.fxml"));
        AnchorPane root=loader.load();
        MenuController ctrl=loader.getController();
        ctrl.setServices(service);
        ctrl.setPrimaryStage(primaryStage);
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.setTitle("Biblioteca");
        primaryStage.show();
	}
	
	public void handlerSearchNume() {
		String c = search_nume.getText();
		Pattern pattern = Pattern.compile(Pattern.quote(c), Pattern.CASE_INSENSITIVE);
		model.setAll(StreamSupport.stream(getPersoane().spliterator(),false)
			.filter(x->pattern.matcher(x.getNume()).find())
			.collect(Collectors.toList()));
	}
	
	public void handlerSortare() {
		service.sortare_dupa_carti_imprumutate();
		model.setAll(getPiese());
	}
	
	public void handlerSortareId() {
		service.sortare_persoane_id();
		model.setAll(getPersoane());
	}
	
}