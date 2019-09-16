package main;

public class Zwierze {

	private String nazwa;
	private String stadZdrowia;
	private String plec;

	/**
	 * @param nazwa
	 */
	public Zwierze(String nazwa) {
		super();
		this.nazwa = nazwa;
	}

	/**
	 * @param nazwa
	 * @param stadZdrowia
	 * @param plec
	 */
	public Zwierze(String nazwa, String stadZdrowia, String plec) {
		super();
		this.nazwa = nazwa;
		this.stadZdrowia = stadZdrowia;
		this.plec = plec;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getStadZdrowia() {
		return stadZdrowia;
	}

	public void setStadZdrowia(String stadZdrowia) {
		this.stadZdrowia = stadZdrowia;
	}

	public String getPlec() {
		return plec;
	}

	public void setPlec(String plec) {
		this.plec = plec;
	}

}