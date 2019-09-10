package pl;

import java.util.ArrayList;

public class Schronisko {
	private int liczbaMiejsc;
	private ArrayList<Zwierze> lista_zwierzat = new ArrayList<Zwierze>();

	/**
	 * @param liczbaMiejsc
	 */
	public Schronisko(int liczbaMiejsc) {
		super();
		this.liczbaMiejsc = liczbaMiejsc;
	}

	public Schronisko() {
		this.liczbaMiejsc = 3;
	}

	public void sprawdzLiczbeMiejsc() {
		this.listaZwierzat();
		if (getLiczbaMiejsc() > 0) {
			System.out.printf("Wolne miejsca: %d\n", getLiczbaMiejsc());
		} else {
			System.out.println("Wszystkie miejsca zajête");
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
			System.out.println("Nie mo¿na dodaæ zwierzaka. Schronisko jest pe³ne.");
		}
	};

	public void usunZwierze(Zwierze zwierze) {
		for (int i = 0; i < this.lista_zwierzat.size(); i++) {
			if (lista_zwierzat.get(i).getNazwa().equals(zwierze.getNazwa())) {
				System.out.printf("Usuniêto zwierzê %s\n", lista_zwierzat.get(i).getNazwa());
				lista_zwierzat.remove(i);
				zwiekszIloscMiejsc();
			} else {
				//System.out.printf("Brak %s w schronisku.\n", zwierze.getNazwa());
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