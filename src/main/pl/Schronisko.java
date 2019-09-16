package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import pliki.ObslugaTxtZapis;

public class Schronisko {
	// Atrybuty klasy
	private int iloscZajetychMiejsc;
	private int liczbaMiejsc;
	private ArrayList<Zwierze> lista_zwierzat = new ArrayList<Zwierze>();

	// Konstruktor
	public Schronisko(int liczbaMiejsc) {
		super();
		this.liczbaMiejsc = liczbaMiejsc;
	}

	public boolean czyMogeDodacZwierze() {
		if (getLiczbaMiejsc() - getIloscZajetychMiejsc() > 0) {
			return true;
		} else
			return false;
	};

	// Metody klasy
	public void dodajZwierze(Zwierze zwierze) {
		if (getLiczbaMiejsc() - getIloscZajetychMiejsc() > 0) {
			this.lista_zwierzat.add(zwierze);
			// zmniejszIloscMiejsc();
			zwiekszIloscZajetychMiejsc();
		} else {
			System.out.println("Nie można dodać zwierzaka. Schronisko jest pełne.");
		}
	}

	public int getIloscZajetychMiejsc() {
		return iloscZajetychMiejsc;
	}

	public int getLiczbaMiejsc() {
		return liczbaMiejsc;
	}

	public ArrayList<Zwierze> getLista_zwierzat() {
		return lista_zwierzat;
	}

	public void listaZwierzat() {
		int pozycja = 0;
		for (Zwierze zwierze : lista_zwierzat) {
			pozycja += 1;
			System.out.printf("%d: %s\n", pozycja, zwierze.getNazwa());
		}
	}

	public ArrayList<Zwierze> listaZwierzatObj() {
		// ArrayList<Zwierze> listaZwierzatString = new ArrayList<>();
		return this.lista_zwierzat;
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

	public void setIloscZajetychMiejsc(int iloscZajetychMiejsc) {
		this.iloscZajetychMiejsc = iloscZajetychMiejsc;
	}

	public void setLiczbaMiejsc(int liczbaMiejsc) {
		this.liczbaMiejsc = liczbaMiejsc;
	}

	public void setLista_zwierzat(ArrayList<Zwierze> lista_zwierzat) {
		this.lista_zwierzat = lista_zwierzat;
	}

	public void sprawdzLiczbeMiejsc() {
		this.listaZwierzat();
		if (getLiczbaMiejsc() > 0) {
			System.out.printf("Wolne miejsca: %d\n", getLiczbaMiejsc() - getIloscZajetychMiejsc());
		} else {
			System.out.println("Wszystkie miejsca zajęte");
		}
	}

	public void usunZwierze(Zwierze zwierze) {
		for (int i = 0; i < this.lista_zwierzat.size(); i++) {
			if (lista_zwierzat.get(i).getNazwa().equals(zwierze.getNazwa())) {
				System.out.printf("Usunięto zwierzę %s\n", lista_zwierzat.get(i).getNazwa());
				lista_zwierzat.remove(i);
				zmniejszIloscZajetychMiejsc();
			}
		}
	}

	public boolean zapiszStanSchroniska(ArrayList<String> daneWejsciwe) {
		try {
			ObslugaTxtZapis zapisPliku = new ObslugaTxtZapis(Manager.nazwaPliku);
			zapisPliku.zapisDoPliku(daneWejsciwe);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean zapiszStanSchronisa(ArrayList<Zwierze> daneWejsciowe) {
		try {
			ObslugaTxtZapis zapisPliku = new ObslugaTxtZapis(Manager.nazwaPliku);
			zapisPliku.zapisDoPlikuJSON(daneWejsciowe);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void zmniejszIloscMiejsc() {
		setLiczbaMiejsc(getLiczbaMiejsc() - 1);
	}

	private void zmniejszIloscZajetychMiejsc() {
		this.iloscZajetychMiejsc -= 1;
	}

	public void zwiekszIloscMiejsc() {
		setLiczbaMiejsc(getLiczbaMiejsc() + 1);
	}

	private void zwiekszIloscZajetychMiejsc() {
		this.iloscZajetychMiejsc += 1;
	}

	public void edytujZwierze(String zwierzeDoZmiany, Zwierze zwierzeZmienione) {
		for (Zwierze zwierze : getLista_zwierzat()) {
			if (zwierze.getNazwa().equals(zwierzeDoZmiany)) {
				zwierze.setNazwa(zwierzeZmienione.getNazwa());
				zwierze.setStadZdrowia(zwierzeZmienione.getStadZdrowia());
				zwierze.setPlec(zwierzeZmienione.getPlec());
			}
		}
	}

	public Zwierze dajZwierzakaPoNazwie(String selectedValue) {
		for (Zwierze zwierze : getLista_zwierzat()) {
			if (zwierze.getNazwa().equals(selectedValue)) {
				return zwierze;
			}
		}
		return null;
	}
}