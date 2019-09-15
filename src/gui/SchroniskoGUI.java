package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mail.ObslugaMail;
import pl.Schronisko;
import pl.Zwierze;

/**
 * @author igoreczek Klasa obsługi aplikacji poprzez GUI
 */
public class SchroniskoGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7844781897347264130L;
	private JButton dodaj;
	private JButton usun;
	private JButton edytuj;
	private Schronisko schronisko;
	private JList<String> listZwierzatGUI;
	private JTextField nazwaZwierzakaTF;
	private JTextField gatunekZwierzakaTF;
	private JTextField plecZwierzakaTF;
	private JPanel panelDodawaniaZwierzaka;

	// Konstruktor Klasy SchroniskoGUI
	public SchroniskoGUI(Schronisko skronisko) {
		// super("Hello World!");
		this.schronisko = skronisko;

		// Definicja elementów GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		setSize(300, 300);
		setLocation(300, 300);
		setLayout(new FlowLayout());

		// Inicjacja przycisków i list na GUI
		add(dodaj = new JButton("Dodaj"));
		panelDodawaniaZwierzaka = new JPanel();
		panelDodawaniaZwierzaka.add(new JLabel("Podaj nazwę"));
		panelDodawaniaZwierzaka.add(nazwaZwierzakaTF = new JTextField(10));
		panelDodawaniaZwierzaka.add(Box.createHorizontalStrut(15));
		panelDodawaniaZwierzaka.add(new JLabel("Podaj gatunek"));
		panelDodawaniaZwierzaka.add(gatunekZwierzakaTF = new JTextField(10));
		panelDodawaniaZwierzaka.add(Box.createHorizontalStrut(15));
		panelDodawaniaZwierzaka.add(new JLabel("Podaj pleć"));
		panelDodawaniaZwierzaka.add(plecZwierzakaTF = new JTextField(10));

		add(usun = new JButton("Usuń"));

		add(edytuj = new JButton("Edytuj"));

		add(listZwierzatGUI = new JList<String>());
		listZwierzatGUI.setVisible(true);
		listZwierzatGUI.setSize(150, 150);
		listZwierzatGUI.setListData(dajTabliceZwierzat());

		dodaj.addActionListener(this);
		usun.addActionListener(this);
		edytuj.addActionListener(this);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.print("Zamykam program i zapisuję stan danych.\n");
				// schronisko.zapiszStanSchroniska(dajElemenyListyGiu(listZwierzatGUI));
				schronisko.zapiszStanSchronisa(schronisko.getLista_zwierzat());
				System.out.print("Zapisane. Zamykam.");
			}
		}));
	}

	/**
	 * Obsługa elementów GUI. Obsługa przycisków i wysyłania maila w przypadku
	 * niewielkiej ilości miejsca.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		// Obsługa dodawania zwierzaka
		if (source == dodaj) {
			if (schronisko.czyMogeDodacZwierze() == true) {

				// Wyświetl panel do wprowadzenia danych zwierzaka
				int resultOfJPanelinput = JOptionPane.showConfirmDialog(null, panelDodawaniaZwierzaka,
						"Podaj dane zwierzaka", JOptionPane.OK_CANCEL_OPTION);
				if (resultOfJPanelinput == JOptionPane.OK_OPTION) {
					schronisko.dodajZwierze(new Zwierze(nazwaZwierzakaTF.getText(), gatunekZwierzakaTF.getText(),
							plecZwierzakaTF.getText()));
				}
			} else {
				JOptionPane.showMessageDialog(null, "Brak wolnych miejsc w schronisku!");
			}
		}

		// Obsługa usuwania zwierzaka wybranego z listy
		else if (source == usun) {
			schronisko.usunZwierze(new Zwierze(listZwierzatGUI.getSelectedValue()));
			// Obsługa edytowania zwierzaka wybranego z listy
		} else if (source == edytuj) {
			Zwierze wybranyZwierzak = schronisko.dajZwierzakaPoNazwie(listZwierzatGUI.getSelectedValue());
			nazwaZwierzakaTF.setText(wybranyZwierzak.getNazwa());
			gatunekZwierzakaTF.setText(wybranyZwierzak.getStadZdrowia());
			plecZwierzakaTF.setText(wybranyZwierzak.getPlec());
			String zwierzeDoZmiany = nazwaZwierzakaTF.getText();
			int resultOfJPanelInput = JOptionPane.showConfirmDialog(null, panelDodawaniaZwierzaka,
					"Zmień dane zwierzaka", JOptionPane.OK_CANCEL_OPTION);

			if (resultOfJPanelInput == JOptionPane.OK_OPTION) {
				schronisko.edytujZwierze(zwierzeDoZmiany, new Zwierze(nazwaZwierzakaTF.getText(),
						gatunekZwierzakaTF.getText(), plecZwierzakaTF.getText()));
			}
		}

		listZwierzatGUI.setListData(dajTabliceZwierzat());
		listZwierzatGUI.setSize(getPreferredSize());
		listZwierzatGUI.setVisible(true);

		// Obsługa wysyłania maila jeśli jest mało miejsca
		if (schronisko.getLiczbaMiejsc() - schronisko.getIloscZajetychMiejsc() < 5) {

			// host smtp.wp.pl
			// port 587
			// login ad.grzyb03
			// pass Zaq-1234
			// new ObslugaMail("smtp.wp.pl", 587, "ad.grzyb03", "Zaq-1234",
			// dajTabliceZwierzat());
		}
	}

	public DefaultListModel<Zwierze> defList() {
		DefaultListModel<Zwierze> zwierzakiList = new DefaultListModel<>();
		for (Zwierze zwierzak : this.schronisko.listaZwierzatObj()) {
			zwierzakiList.addElement(zwierzak);
		}
		return zwierzakiList;
	}

	public String[] dajTabliceZwierzat() {
		String[] zwierze = new String[this.schronisko.listaZwierzatObj().size()];
		int i = 0;
		for (Zwierze zwierzak : this.schronisko.listaZwierzatObj()) {
			zwierze[i] = zwierzak.getNazwa();
			i += 1;
		}
		return zwierze;
	}

	public ArrayList<String> dajElemenyListyGiu(JList<String> listaGui) {
		ArrayList<String> lista = new ArrayList<>();
		for (int i = 0; i < listaGui.getModel().getSize(); i++) {
			lista.add(listaGui.getModel().getElementAt(i));
		}
		return lista;
	}
}