package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame implements ActionListener {

	private JButton dodaj;
	private JButton usun;
	private JButton status;

	public MyFrame() {
		super("Hello World!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// setBackground("DARK_GRAY");
		setResizable(true);
		setSize(300, 300);
		setLocation(100, 100);
		setLayout(new FlowLayout());
		
		add(dodaj = new JButton("Dodaj"));
		add(usun = new JButton("Usuñ"));
		add(status = new JButton("Status"));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == dodaj) {
			setBackground(Color.BLUE);
		} else if (source == usun) {
			setBackground(Color.RED);

		} else if (source == status) {
			setBackground(Color.GREEN);
		}
	}
}