package ch.etml.pl.commerce;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CommerceService {

    private Connection connection;

    public CommerceService() throws IOException, SQLException {
        Properties prop = new Properties();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("commerce.properties");
        prop.load(input);

        connection = DriverManager.getConnection(
                            prop.getProperty("url"),
                            prop.getProperty("user"),
                            prop.getProperty("password")
                    );

    }

    public ArrayList<Item> getItems() throws CommerceException {
        ArrayList<Item> items = new ArrayList<>();
        try (
                PreparedStatement s = connection.prepareStatement(
                        "SELECT i.num,i.description,i.prix," +
                                "i.client, c.prenom, c.solde " +
                                "FROM items i " +
                                "LEFT OUTER JOIN clients c ON c.num = i.client " +
                                "ORDER BY i.num");
                ResultSet rs = s.executeQuery();
        ) {
            while (rs.next()) {
                int num =rs.getInt("num");
                String description = rs.getString("description");
                BigDecimal prix = rs.getBigDecimal("prix");
                int numClient = rs.getInt("client");
                Client client = null;
                if (numClient != 0) {
                    String prenom = rs.getString("prenom");
                    BigDecimal solde = rs.getBigDecimal("solde");
                    client = new Client(numClient,prenom,solde);
                }
                Item item = new Item(num,description,prix,client);
                items.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CommerceException(ex);
        }
        return items;
    }
}
