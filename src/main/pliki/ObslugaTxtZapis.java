package pliki;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.Zwierze;

public class ObslugaTxtZapis {

	PrintWriter plikZ;

	public ObslugaTxtZapis(String plik) throws FileNotFoundException {
		super();
		this.plikZ = new PrintWriter(plik);

	}

	public ObslugaTxtZapis() throws FileNotFoundException {
		this.plikZ = new PrintWriter("pliczek.txt");
	}

	public PrintWriter getPlikZapisu() {
		return plikZ;
	}

	public void setPlikZapisu(PrintWriter plikZapisu) {
		this.plikZ = plikZapisu;
	}

	public void zapisDoPliku(ArrayList<String> daneWejsciwe) {
		for (String linia : daneWejsciwe) {
			getPlikZapisu().println(linia);
		}
		getPlikZapisu().close();
	}

	public void zapisDoPlikuJSON(ArrayList<Zwierze> daneWejsciwe) {
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonZwierzaki = gsonBuilder.toJson(daneWejsciwe);
		getPlikZapisu().println(jsonZwierzaki);

		getPlikZapisu().close();
	}

	public PrintWriter getPlikZ() {
		return plikZ;
	}

	public void setPlikZ(PrintWriter plikZ) {
		this.plikZ = plikZ;
	}
}