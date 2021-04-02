package domain;
import java.util.List;

public class Persoana {
	public int id;
	public String nume;
	public String CNP;
	public List<Integer> id_carti;
	public String lista_c;
	
	public Persoana() {
		id = 0;
		nume = "";
		CNP = "";
		id_carti = null;
	}
	
	public Persoana(int id1, String nume1, String CNP1, List<Integer> carti1) {
		id = id1;
		nume = nume1;
		CNP = CNP1;
		id_carti = carti1;
		lista_c = CartiToString();
	}

	public List<Integer> getId_carti() {
		return id_carti;
	}

	public void setId_carti(List<Integer> id_carti) {
		this.id_carti = id_carti;
	}

	public String getLista_c() {
		return lista_c;
	}

	public void setLista_c(String lista_c) {
		this.lista_c = lista_c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

	public List<Integer> getId_Carti() {
		return id_carti;
	}

	public void setId_Carti(List<Integer> carti) {
		this.id_carti = carti;
	}
	
	public String CartiToString() {
		String rez = "";
		int l = id_carti.size();
		rez += Integer.toString(id_carti.get(0));
		for(int j = 1; j < l; j++) {
			rez += " " + Integer.toString(id_carti.get(j));
		}
		return rez;
	}
	
	public String toString() {
		return Integer.toString(id) + "," + nume + "," + CNP + "," + CartiToString() + "\n";
	}
}
