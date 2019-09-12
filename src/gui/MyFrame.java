package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.sun.glass.events.WindowEvent;

import pl.Schronisko;
import pl.Zwierze;
import pliki.ObslugaTxtZapis;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener {

	private JButton dodaj;
	private JButton usun;
	private JButton status;
	// private JTextField nazwaInput;
	private Schronisko schronisko;
	private JList<String> listZwierzatGUI;

	public MyFrame(Schronisko skronisko) {
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
/*		addWindowListener(new WindowAdapter() {
			@SuppressWarnings("unused")
			public void windowClosing(WindowEvent e) {
				System.out.print("Zamykam program i zapisuję stan danych.");
				schronisko.zapiszStanSchroniska(dajElemenyListyGiu(listZwierzatGUI));
				System.exit(0);
			}
		});*/

//		status.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == dodaj) {
			if (schronisko.czyMogeDodacZwierze() == true) {
				String nazwaZwierzaka = JOptionPane.showInputDialog("Podaj nazwę zwierzaka.");
				schronisko.dodajZwierze(new Zwierze(nazwaZwierzaka));
				listZwierzatGUI.setListData(dajTabliceZwierzat());
				listZwierzatGUI.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Brak wolnych miejsc w schronisku!");
			}
		} else if (source == usun) {
			// TODO Dodać obsługę usuwania zwierzaków z listy.
		} else if (source == status) {
			listZwierzatGUI.setListData(dajTabliceZwierzat());
			listZwierzatGUI.setVisible(true);
			listZwierzatGUI.setSize(150, 150);
		}
	}

//	@Override
//	public synchronized void addWindowListener(WindowListener l) {
//		super.addWindowListener(l);
//	}

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