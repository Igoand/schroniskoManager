package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import pl.Schronisko;
import pl.Zwierze;

public class MyFrame extends JFrame implements ActionListener {

	private JButton dodaj;
	private JButton usun;
	private JButton status;
	private JTextField nazwaInput;
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
		setLayout(new FlowLayout());

		add(dodaj = new JButton("Dodaj"));
		add(usun = new JButton("Usuñ"));
		add(status = new JButton("Status"));
		add(nazwaInput = new JTextField("Nazwa zwierzaka"));

		add(listZwierzatGUI = new JList());
		listZwierzatGUI.setVisible(false);
		listZwierzatGUI.setSize(150, 150);

		dodaj.addActionListener(this);
		usun.addActionListener(this);
		status.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == dodaj) {
			// System.out.print(nazwaInput.getText());
			schronisko.dodajZwierze(new Zwierze(nazwaInput.getText()));

		} else if (source == usun) {
			setBackground(Color.RED);

		} else if (source == status) {
			System.out.print("Stats");
			listZwierzatGUI.setListData(dajTabliceZwierzat());
			listZwierzatGUI.setVisible(true);
			listZwierzatGUI.setSize(150, 150);
		}
	}

	@Override
	public synchronized void addWindowListener(WindowListener l) {
		// TODO Auto-generated method stub
		super.addWindowListener(l);

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
}