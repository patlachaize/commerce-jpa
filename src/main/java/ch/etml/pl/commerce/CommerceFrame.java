package ch.etml.pl;

import ch.etml.pl.commerce.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommerceFrame extends JFrame {

	public static void main(String[] args) throws  SQLException, IOException {
		CommerceService commerceService = new CommerceService();
		CommerceFrame commerceFrame = new CommerceFrame(commerceService);
		commerceFrame.setTitle("Commerce");
		commerceFrame.setSize(250, 165);
		commerceFrame.setVisible(true);
		commerceFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

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
		liste();
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
			try {
				String prenom = tfPrenom.getText();
				int numItem = Integer.parseInt(tfItem.getText());
				commerceService.achete(prenom,numItem);
				liste();
				tfMessage.setText("");
			} catch (ItemNotFoundException | CommerceException  ex) {
				tfMessage.setText(ex.getMessage());
			}

		});
	}

	public void liste() {
		ArrayList<Client> clients;
		display.setText("");
		try {
			for (Item item : commerceService.getItems()) {
				display.append(item.toString() + "\n");
			}
		} catch (CommerceException e) {
			display.append(e.getMessage());
		}



	}

}
