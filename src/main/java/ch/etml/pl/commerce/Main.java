package ch.etml.pl.commerce;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            CommerceService commerceService = new CommerceService();
        } catch (IOException  | SQLException e) {
            e.printStackTrace();
        }
    }

}
