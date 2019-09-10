package pl;

import java.util.ArrayList;

public class Schronisko {
	private int liczbaMiejsc;
	private ArrayList<Zwierze> lista_zwierzat = new ArrayList<Zwierze>();

	public ArrayList<Zwierze> getLista_zwierzat() {
		return lista_zwierzat;
	}

	public void setLista_zwierzat(ArrayList<Zwierze> lista_zwierzat) {
		this.lista_zwierzat = lista_zwierzat;
	}

	/**
	 * @param liczbaMiejsc
	 */
	public Schronisko(int liczbaMiejsc) {
		super();
		this.liczbaMiejsc = liczbaMiejsc;
	}
	/*
	 * public Schronisko() { this.liczbaMiejsc = 3; }
	 */

	public void sprawdzLiczbeMiejsc() {
		this.listaZwierzat();
		if (getLiczbaMiejsc() > 0) {
			System.out.printf("Wolne miejsca: %d\n", getLiczbaMiejsc());
		} else {
			System.out.println("Wszystkie miejsca zaj�te");
		}
	}

	public void setLiczbaMiejsc(int liczbaMiejsc) {
		this.liczbaMiejsc = liczbaMiejsc;
	}

	public void dodajZwierze(Zwierze zwierze) {
		if (getLiczbaMiejsc() > 0) {
			this.lista_zwierzat.add(zwierze);
			zmniejszIloscMiejsc();
		} else {
			System.out.println("Nie mo�na doda� zwierzaka. Schronisko jest pe�ne.");
		}
	};

	public void usunZwierze(Zwierze zwierze) {
		for (int i = 0; i < this.lista_zwierzat.size(); i++) {
			if (lista_zwierzat.get(i).getNazwa().equals(zwierze.getNazwa())) {
				System.out.printf("Usuni�to zwierz� %s\n", lista_zwierzat.get(i).getNazwa());
				lista_zwierzat.remove(i);
				zwiekszIloscMiejsc();
			} else {
				// System.out.printf("Brak %s w schronisku.\n", zwierze.getNazwa());
			}
		}
	}

	public void listaZwierzat() {
		int pozycja = 0;
		for (Zwierze zwierze : lista_zwierzat) {
			pozycja += 1;
			System.out.printf("%d: %s\n", pozycja, zwierze.getNazwa());
		}
	}

	public ArrayList<String> listaZwierzatString() {
		ArrayList<String> listaZwierzatString = new ArrayList<>();
		// int pozycja = 0;
		for (Zwierze zwierze : getLista_zwierzat()) {
			// pozycja += 1;
			// listaZwierzatString.add(pozycja + " " + zwierze.getNazwa());
			listaZwierzatString.add(zwierze.getNazwa());
		}
		return listaZwierzatString;
	}

	public void zmniejszIloscMiejsc() {
		setLiczbaMiejsc(getLiczbaMiejsc() - 1);
	}

	public void zwiekszIloscMiejsc() {
		setLiczbaMiejsc(getLiczbaMiejsc() + 1);
	}

	public int getLiczbaMiejsc() {
		return liczbaMiejsc;
	}
}