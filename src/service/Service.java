package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import domain.Carte;
import domain.Persoana;
import repository.RepoCarti;
import repository.RepoPersoane;

public class Service {
	private RepoCarti repo_carti;
	private RepoPersoane repo_persoane;
	
	public Service(RepoCarti rc, RepoPersoane rp) {
		repo_carti = rc;
		repo_persoane = rp;
	}
	
	public void stergere_carte(int id) {
		repo_carti.stergere(id);
	}
	
	public void stergere_persoana(int id) {
		repo_persoane.stergere(id);
	}
	
	public void modificare_carte(int id, String titlu, String autor, int an, 
			double pret, String editura, String cota, boolean imprumutata) {
		Carte c = new Carte(id, titlu, autor, an, pret, editura, cota, imprumutata);
		repo_carti.modificare(c);
	}
	
	public void modificare_persoana(int id, String nume, String CNP, List<Integer> id_carti) {
		Persoana p = new Persoana(id, nume, CNP, id_carti);
		repo_persoane.modificare(p);
	}
	
	public void adauga_carte(int id, String titlu, String autor, int an, 
			double pret, String editura, String cota, boolean imprumutata) {
		Carte c = new Carte(id, titlu, autor, an, pret, editura, cota, imprumutata);
		repo_carti.adaugare(c);
	}
	
	public void adaugare_persoana(int id, String nume, String CNP, List<Integer> id_carti) {
		Persoana p = new Persoana(id, nume, CNP, id_carti);
		repo_persoane.adaugare(p);
	}
	
	public List<Carte> getCarti() {
		return repo_carti.getAll();
	}
	
	public List<Persoana> getPersoane(){
		return repo_persoane.getAll();
	}
	
	public int imprumutatori_carte(int id_carte){
		int nr = 0;
		for(Persoana pers:repo_persoane.getAll()) {
			for(int id: pers.getId_Carti())
				if(id == id_carte) {
					nr++;
					break;
				}
		}
		return nr;
	}
	
	public int nr_carti_imprumutate(int id_pers) {
		int nr = 0;
		for(Persoana pers:repo_persoane.getAll()) 
			if(id_pers == pers.getId()) {
				nr = pers.getId_Carti().size();
				return nr;
			}
		return 0;
	}
	
	public void sortare_dupa_imprumutatori() {
		Collections.sort(repo_carti.getAll(), new Comparator<Carte>(){
			public int compare(Carte c1, Carte c2) {
				if(imprumutatori_carte(c1.getId()) > imprumutatori_carte(c2.getId()))
					return 1;
				else
					if(imprumutatori_carte(c1.getId()) < imprumutatori_carte(c2.getId()))
						return -1;
				return 0;
			}
		});	
	}
	
	public void sortare_dupa_carti_imprumutate() {
		Collections.sort(repo_persoane.getAll(), new Comparator<Persoana>(){
			public int compare(Persoana p1, Persoana p2) {
				if(nr_carti_imprumutate(p1.getId()) > nr_carti_imprumutate(p2.getId()))
					return 1;
				else
					if(nr_carti_imprumutate(p1.getId()) < nr_carti_imprumutate(p2.getId()))
						return -1;
				return 0;
			}
		});
	}
	
	public void sortare_persoane_id() {
		Collections.sort(repo_persoane.getAll(), new Comparator<Persoana>(){
			public int compare(Persoana p1, Persoana p2) {
				if(p1.getId() > p2.getId())
					return 1;
				else
					if(p1.getId() < p2.getId())
						return -1;
				return 0;
			}
		});
	}
	
	public void sortare_carti_id() {
		Collections.sort(repo_carti.getAll(), new Comparator<Carte>(){
			public int compare(Carte c1, Carte c2) {
				if(c1.getId() > c2.getId())
					return 1;
				else
					if(c1.getId() < c2.getId())
						return -1;
				return 0;
			}
		});	
	}
}
