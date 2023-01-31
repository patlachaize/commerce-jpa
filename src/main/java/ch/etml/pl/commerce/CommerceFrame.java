package ch.etml.pl.commerce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CommerceFrame extends JFrame {
	
	private JTextArea display;
	private CommerceService commerceService;
	private JTextField tfPrenom;
	private JTextField tfItem;
	private JButton tfAchat;
	private JTextField tfMessage;
	
	public CommerceFrame(CommerceService commerceService) {
		this.commerceService = commerceService;
		display=new JTextArea();
		add(display);
		Box nord = Box.createHorizontalBox();
		add(nord, BorderLayout.NORTH);
		nord.add(new JLabel("Item: "));
		tfItem = new JTextField(2);
		nord.add(tfItem);
		nord.add(new JLabel("Prenom: "));
		tfPrenom = new JTextField(10);
		nord.add(tfPrenom);
		tfAchat = new JButton("Achete");
		nord.add(tfAchat);
		tfMessage = new JTextField();
		add(tfMessage,BorderLayout.SOUTH);

		tfAchat.addActionListener(e-> {
				String prenom = tfPrenom.getText();
				int numItem = Integer.parseInt(tfItem.getText());

		});
	}

}
