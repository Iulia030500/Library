package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import domain.Carte;
import domain.Persoana;
import exceptions.RepoException;

public class RepoPersoane {
private List<Persoana> persoane; 
	
	public RepoPersoane() {
		persoane = new ArrayList<>();
		citire();
	}
	
	private void citire() {
		try { BufferedReader fisier = 
			  new BufferedReader(new FileReader("C:\\Users\\Iulia\\Desktop\\MAP\\persoane.txt"));
		      String s;						
		      while((s = fisier.readLine())!= null){
			    String sir[]=s.split(",");
			    int id = Integer.parseInt(sir[0]);
			    String nume = sir[1];
			    String CNP = sir[2];
			    String sir2[] = sir[3].split(" ");
			    List<Integer> l = new ArrayList<>();
			    for (String ss: sir2) 
			    	l.add(Integer.parseInt(ss));
			    Persoana pers = new Persoana(id, nume, CNP, l);
			    persoane.add(pers);
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
			FileWriter fisierOut = new FileWriter("C:\\Users\\Iulia\\Desktop\\MAP\\persoane.txt");
			
			for(Persoana p : persoane)
				fisierOut.write(p.toString());
		
			fisierOut.close();
			  
			} // try
		   catch(Exception e) {
		     System.out.println(e.getMessage());
		     e.printStackTrace();
		   } // catch 
	}
	
	public void stergere(int id) {
		for(Persoana p: persoane)
			if(p.getId() == id) {
				persoane.remove(p);
				writeToFile();
				return;
			}
		throw new RepoException("ID-ul nu exista!");
	}
	
	public void modificare(Persoana pers) {
		int idx = 0;
		for(Persoana c: persoane) {
			if(c.getId() == pers.getId()) {
				persoane.set(idx, pers);
				writeToFile();
				return;
			}
			idx++;
		}			
		throw new RepoException("ID-ul nu exista!");
	}
	
	public void adaugare(Persoana pers) {
		for(Persoana p : persoane)
			if(p.getId() == pers.getId())
				throw new RepoException("ID-ul exista deja!");
		persoane.add(pers);
		writeToFile();
	}
	
	public List<Persoana> getAll(){
		return persoane;
	}
}
