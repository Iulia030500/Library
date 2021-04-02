package controller;

import java.io.IOException;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import domain.Carte;
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

public class CartiController {
	private Stage primaryStage;
	private Service service;
	 ObservableList<Carte> model = FXCollections.observableArrayList();
	@FXML 
	TextField id_field;
	@FXML 
	TextField titlu_field;
	@FXML 
	TextField autor_field;
	@FXML 
	TextField an_field;
	@FXML 
	TextField pret_field;
	@FXML 
	TextField editura_field;
	@FXML 
	TextField cota_field;
	@FXML 
	TextField imprumutata_field;
	@FXML 
	TableView<Carte> carti;
	@FXML
	TableColumn<Carte,Integer> id;
	@FXML
	TableColumn<Carte, String> titlu;
	@FXML
	TableColumn <Carte, String> autor;
	@FXML
	TableColumn <Carte, Integer> an;
	@FXML
	TableColumn <Carte, Double> pret;
	@FXML
	TableColumn <Carte, String> editura;
	@FXML
	TableColumn <Carte, String> cota;
	@FXML
	TableColumn <Carte, Boolean> imprumutata;
	@FXML 
	TextField search_autor;
	@FXML 
	TextField search_titlu;

	public void setServices(Service service) {
		this.service = service;
		model.setAll(getCarti());
	}

	public List<Carte> getCarti(){
		List<Carte> c = service.getCarti();
		return c;
	}
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
	}
	 public void initialize() throws FileNotFoundException {
		 	//search.depthTestProperty().addListener(x->handlerSearch());
	        id.setCellValueFactory(new PropertyValueFactory<Carte, Integer>("id"));
	        titlu.setCellValueFactory(new PropertyValueFactory<Carte, String>("titlu"));
	        autor.setCellValueFactory(new PropertyValueFactory<Carte,String>("autor"));
	        an.setCellValueFactory(new PropertyValueFactory<Carte,Integer>("an"));
	        pret.setCellValueFactory(new PropertyValueFactory<Carte,Double>("pret"));
	        editura.setCellValueFactory(new PropertyValueFactory<Carte,String>("editura"));
	        cota.setCellValueFactory(new PropertyValueFactory<Carte,String>("cota"));
	        imprumutata.setCellValueFactory(new PropertyValueFactory<Carte,Boolean>("imprumutata"));
	        
	        carti.setItems(model);
	    }
	
	public void handlerCreate() {
		int id = Integer.valueOf(id_field.getText());
		String titlu = titlu_field.getText();
		String autor = autor_field.getText();
		int an = Integer.valueOf(an_field.getText());
		Double pret = Double.valueOf(pret_field.getText());
		String editura = editura_field.getText();
		String cota = cota_field.getText();
		Boolean imprumutata = Boolean.valueOf(imprumutata_field.getText());
		try {
		service.adauga_carte(id, titlu, autor, an, pret, editura, cota, imprumutata);
		}catch (RepoException ex) {
			AlertBox alertbox = new AlertBox("Prostule", ex.getMessage());
			alertbox.display();
		}
		model.setAll(getCarti());
	}
	public void handlerUpdate() {
		int id = Integer.valueOf(id_field.getText());
		String titlu = titlu_field.getText();
		String autor = autor_field.getText();
		int an = Integer.valueOf(an_field.getText());
		Double pret = Double.valueOf(pret_field.getText());
		String editura = editura_field.getText();
		String cota = cota_field.getText();
		Boolean imprumutata = Boolean.valueOf(imprumutata_field.getText());
		try {
		service.modificare_carte(id, titlu, autor, an, pret, editura, cota, imprumutata);
		}catch (RepoException ex) {
			AlertBox alertbox = new AlertBox("Prostule", ex.getMessage());
			alertbox.display();
		}
		model.setAll(getCarti());
	}
	
	public void handlerDelete() {
		Carte carte = carti.getSelectionModel().getSelectedItem();
		service.stergere_carte(carte.getId());
		model.setAll(getCarti());
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
	public void handlerSearchTitlu() {
		String c = search_titlu.getText();
		Pattern pattern = Pattern.compile(Pattern.quote(c), Pattern.CASE_INSENSITIVE);
		model.setAll(StreamSupport.stream(getCarti().spliterator(),false)
			.filter(x->pattern.matcher(x.getTitlu()).find())
			.collect(Collectors.toList()));
	}
	
	public void handlerSearchAutor() {
		String c = search_autor.getText();
		Pattern pattern = Pattern.compile(Pattern.quote(c), Pattern.CASE_INSENSITIVE);
		model.setAll(StreamSupport.stream(getCarti().spliterator(),false)
			.filter(x->pattern.matcher(x.getAutor()).find())
			.collect(Collectors.toList()));
	}
	
	public void handlerSortareImprumutatori() {
		service.sortare_dupa_imprumutatori();
		model.setAll(getCarti());
	}
	
	public void handlerSortareId() {
		service.sortare_carti_id();
		model.setAll(getCarti());
	}
	
	
}