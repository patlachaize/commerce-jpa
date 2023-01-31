package ch.etml.pl.commerce;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            CommerceService commerceService = new CommerceService();
            CommerceFrame commerceFrame = new CommerceFrame(commerceService);
            commerceFrame.setTitle("Commerce");
            commerceFrame.setSize(300, 200);
            commerceFrame.setVisible(true);
            commerceFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        } catch (IOException  | SQLException e) {
            e.printStackTrace();
        }
    }

}
