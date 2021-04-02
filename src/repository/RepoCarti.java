package repository;
import domain.Carte;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import exceptions.RepoException;

public class RepoCarti {
	private List<Carte> carti; 
	
	public RepoCarti() {
		carti = new ArrayList<>();
		citire();
	}
	
	private void citire() {
		try { BufferedReader fisier = 
			  new BufferedReader(new FileReader("C:\\Users\\Iulia\\Desktop\\MAP\\carti.txt"));
		      String s;						
		      while((s = fisier.readLine())!= null){
			    String sir[]=s.split(",");
			    int id = Integer.parseInt(sir[0]);
			    String titlu = sir[1];
			    String autor = sir[2];
			    int an = Integer.parseInt(sir[3]);
			    double pret = Double.parseDouble(sir[4]);
			    String editura = sir[5];
			    String cota = sir[6];
			    boolean imprumutata = Boolean.parseBoolean(sir[7]);
			    Carte c = new Carte(id, titlu, autor, an, pret, editura, 
			    		cota, imprumutata);
			    
			    carti.add(c);
		     }
		  fisier.close();
		  
		} 
	   catch(Exception e) {
	     System.out.println(e.getMessage());
	     e.printStackTrace();
	   } 
	}
	
	private void writeToFile() {
		try { 
			FileWriter fisierOut = new FileWriter("C:\\Users\\Iulia\\Desktop\\MAP\\carti.txt");
			
			for(Carte c : carti)
				fisierOut.write(c.toString());
		
			fisierOut.close();
			  
			} // try
		   catch(Exception e) {
		     System.out.println(e.getMessage());
		     e.printStackTrace();
		   } // catch 
	}
	
	public void stergere(int id) {
		for(Carte c: carti)
			if(c.getId() == id) {
				carti.remove(c);
				writeToFile();
				return;
			}
		throw new RepoException("ID-ul nu exista!");
	}
	
	public void modificare(Carte cart) {
		int idx = 0;
		for(Carte c: carti) {
			if(c.getId() == cart.getId()) {
				carti.set(idx, cart);
				writeToFile();
				return;
			}
			idx++;
		}	
		throw new RepoException("ID-ul nu exista!");
	}
	
	public void adaugare(Carte cart) {
		for(Carte c : carti)
			if(c.getId() == cart.getId())
				throw new RepoException("ID-ul exista deja!");
		carti.add(cart);
		writeToFile();
	}
	
	public List<Carte> getAll(){
		return carti;
	}
}
