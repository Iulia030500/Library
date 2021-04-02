package domain;

public class Carte {
	private int id;
	private String titlu;
	private String autor;
	private int an;
	private double pret;
	private String editura;
	private String cota;
	private boolean imprumutata;
	
	public Carte() {
		id = 0;
		titlu = "";
		autor = "";
		an = 0;
		pret = 0;
		editura = "";
		cota = "";
		imprumutata = false;
	}
	
	public Carte(int id1, String titlu1, String autor1, int an1, double pret1,
			String editura1, String cota1, boolean imprumutata1) {
		id = id1;
		titlu = titlu1;
		autor = autor1;
		an = an1;
		pret = pret1;
		editura = editura1;
		cota = cota1;
		imprumutata = imprumutata1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitlu() {
		return titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAn() {
		return an;
	}

	public void setAn(int an) {
		this.an = an;
	}

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

	public String getEditura() {
		return editura;
	}

	public void setEditura(String editura) {
		this.editura = editura;
	}

	public String getCota() {
		return cota;
	}

	public void setCota(String cota) {
		this.cota = cota;
	}
	
	public boolean getImprumutata() {
		return imprumutata;
	}

	public void setImprumutata(boolean imprumutata) {
		this.imprumutata = imprumutata;
	}
	
	public String toString() {
		return Integer.toString(id)+","+titlu+","+autor+","+Integer.toString(an)
		+","+Double.toString(pret)+","+editura+","+cota+","+imprumutata+"\n";
	}
}
