package main;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gui.SchroniskoGUI;
import pliki.ObslugaTxtOdczyt;

/**
 * @author igoreczek Test commita do etap 2 dodatek do commita
 */
@SpringBootApplication

public class Manager {

	public static String nazwaPliku = "daneSchorniska.txt";

	public static void main(String[] args) throws IOException {

		SpringApplication.run(Manager.class, args);

		// Inicjacja schroniska
		// String nazwaPliku = "daneSchorniska.txt";
		Schronisko schronisko = new Schronisko(15);

		// Inicjacja danych schroniska z pliku
		ObslugaTxtOdczyt odczytPliku = new ObslugaTxtOdczyt(nazwaPliku);
		// schronisko.setLista_zwierzat(odczytPliku.odczytajPlik());
		schronisko.setLista_zwierzat(odczytPliku.odczytajPlikJSON());
		schronisko.setIloscZajetychMiejsc(schronisko.getLista_zwierzat().size());

		// Start aplikacji GUI
		/*
		 * EventQueue.invokeLater(new Runnable() {
		 * 
		 * @Override public void run() { new SchroniskoGUI(schronisko); } });
		 */

		/*
		 * System.out.printf("Witaj w schronisku!\n" + "Co chcesz zrobić?\n" +
		 * "d - dodaj zwierze\n" + "u - uzun zwierze\n" +
		 * "s - stan schroniska\nk - koniec pracy\n\n"); String opcja = "p";
		 * InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		 * BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		 * 
		 * while (opcja != "k") { opcja = bufferedReader.readLine(); switch (opcja) {
		 * case "s": schronisko.sprawdzLiczbeMiejsc(); break; case "d": //
		 * schronisko.dodajZwierze(new Zwierze(bufferedReader.readLine())); break; case
		 * "u": // schronisko.usunZwierze(new Zwierze(bufferedReader.readLine()));
		 * break; case "k": {
		 * System.out.print("Koniec pracy. Zamykam program.ąśćęółńćżż\n");
		 * schronisko.zapiszStanSchroniska(schronisko.listaZwierzatString());
		 * System.exit(0); break; } default:
		 * System.out.printf("Brak takiej opcji jak %s\n", opcja); break; } }
		 */
	}
}