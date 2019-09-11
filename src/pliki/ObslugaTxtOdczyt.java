package pliki;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.Zwierze;

public class ObslugaTxtOdczyt {

	Scanner plikO;

	public ObslugaTxtOdczyt(String plikO) {
		try {
			this.plikO = new Scanner(new File(plikO));
		} catch (FileNotFoundException e) {
			try {
				ObslugaTxtZapis utworzPlik;
				utworzPlik = new ObslugaTxtZapis(plikO);
				utworzPlik.zapisDoPliku(new ArrayList<String>() {
					{
						add("");
					}
				});
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public ObslugaTxtOdczyt() throws FileNotFoundException {
		this.plikO = new Scanner(new File("pliczek.txt"));
	}

	public Scanner getPlikO() {
		return plikO;
	}

	public void setPlikO(Scanner plikO) {
		this.plikO = plikO;
	}

	public ArrayList<Zwierze> odczytajPlik() {
		ArrayList<Zwierze> odczytaneZwierzeta = new ArrayList<>();
		while (getPlikO().hasNext()) {
			odczytaneZwierzeta.add(new Zwierze(getPlikO().nextLine()));
		}
		return odczytaneZwierzeta;
	}
}