package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import pl.Schronisko;
import pl.Zwierze;

public class MyFrame extends JFrame implements ActionListener {

	private JButton dodaj;
	private JButton usun;
	private JButton status;
	private JTextField nazwaInput;
	private Schronisko schronisko;

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
		add(usun = new JButton("Usuń"));
		add(status = new JButton("Status"));

		add(nazwaInput = new JTextField("Nazwa zwierzaka"));

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
			setBackground(Color.GREEN);
		}
	}
}