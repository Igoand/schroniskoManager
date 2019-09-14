package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import mail.ObslugaMail;
import pl.Schronisko;
import pl.Zwierze;

public class SchroniskoGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7844781897347264130L;
	private JButton dodaj;
	private JButton usun;
	private JButton status;
	// private JTextField nazwaInput;
	private Schronisko schronisko;
	private JList<String> listZwierzatGUI;

	public SchroniskoGUI(Schronisko skronisko) {
		super("Hello World!");
		this.schronisko = skronisko;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		setSize(300, 300);
		setLocation(100, 100);
		setLayout(new BorderLayout());

		add(dodaj = new JButton("Dodaj"), BorderLayout.LINE_START);
		add(usun = new JButton("Usuń"), BorderLayout.PAGE_END);
		// add(status = new JButton("Status"));
		// add(nazwaInput = new JTextField("Nazwa zwierzaka"));

		add(listZwierzatGUI = new JList<String>(), BorderLayout.CENTER);
		listZwierzatGUI.setVisible(true);
		listZwierzatGUI.setSize(150, 150);
		listZwierzatGUI.setListData(dajTabliceZwierzat());

		dodaj.addActionListener(this);
		usun.addActionListener(this);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.print("Zamykam program i zapisuję stan danych.\n");
				schronisko.zapiszStanSchroniska(dajElemenyListyGiu(listZwierzatGUI));
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
				String nazwaZwierzaka = JOptionPane.showInputDialog("Podaj nazwę zwierzaka.");
				schronisko.dodajZwierze(new Zwierze(nazwaZwierzaka));
				listZwierzatGUI.setListData(dajTabliceZwierzat());
				listZwierzatGUI.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Brak wolnych miejsc w schronisku!");
			}
		}
//		Obsługa usuwania zwierzaka wybranego z listy
		else if (source == usun) {
			schronisko.usunZwierze(new Zwierze(listZwierzatGUI.getSelectedValue()));
			listZwierzatGUI.setListData(dajTabliceZwierzat());
		} else if (source == status) {
			listZwierzatGUI.setListData(dajTabliceZwierzat());
			listZwierzatGUI.setVisible(true);
			listZwierzatGUI.setSize(150, 150);
		}
		// Obsługa wysyłania maila jeśli jest mało miejsca
		if (schronisko.getLiczbaMiejsc() - schronisko.getIloscZajetychMiejsc() < 5) {

			// host smtp.wp.pl
			// port 587
			// login ad.grzyb03
			// pass Zaq-1234
			new ObslugaMail("smtp.wp.pl", 587, "ad.grzyb03", "1234", dajTabliceZwierzat());
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