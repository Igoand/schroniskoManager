package pl;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gui.MyFrame;
import pliki.ObslugaTxtOdczyt;
import pliki.ObslugaTxtZapis;

public class Manager {

	public static void main(String[] args) throws IOException {

		String nazwaPliku = "daneSchorniska.txt";
		Schronisko schronisko = new Schronisko(3);
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MyFrame(schronisko);

			}
		});
		ObslugaTxtOdczyt odczytPliku = new ObslugaTxtOdczyt(nazwaPliku);
		schronisko.setLista_zwierzat(odczytPliku.odczytajPlik());

		System.out.printf("Witaj w schronisku!\n" + "Co chcesz zrobi�?\n" + "d - dodaj zwierze\n" + "u - uzun zwierze\n"
				+ "s - stan schroniska\nk - koniec pracy\n\n");
		String opcja = "p";
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		while (opcja != "k") {
			opcja = bufferedReader.readLine();
			switch (opcja) {
			case "s":
				schronisko.sprawdzLiczbeMiejsc();
				break;
			case "d":
				schronisko.dodajZwierze(new Zwierze(bufferedReader.readLine()));
				break;
			case "u":
				schronisko.usunZwierze(new Zwierze(bufferedReader.readLine()));
				break;
			case "k": {
				System.out.print("Koniec pracy. Zamykam program.\n");
				ObslugaTxtZapis zapisPliku = new ObslugaTxtZapis(nazwaPliku);
				zapisPliku.zapisDoPliku(schronisko.listaZwierzatString());
				System.exit(0);
				break;
			}
			default:
				System.out.printf("Brak takiej opcji jak %s\n", opcja);
				break;
			}
		}
	}
}