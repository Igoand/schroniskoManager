package pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Manager {

	public static void main(String[] args) throws IOException {
		Schronisko schronisko = new Schronisko(2);
		System.out.printf("Witaj w schronisku!\n" + "Co chcesz zrobiæ?\n" + "d - dodaj zwierze\n" + "u - uzun zwierze\n"
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