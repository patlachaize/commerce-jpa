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
                int num = rs.getInt("num");
                String description = rs.getString("description");
                BigDecimal prix = rs.getBigDecimal("prix");
                int numClient = rs.getInt("client");
                Client client = null;
                if (numClient != 0) {
                    String prenom = rs.getString("prenom");
                    BigDecimal solde = rs.getBigDecimal("solde");
                    client = new Client(numClient, prenom, solde);
                }
                Item item = new Item(num, description, prix, client);
                items.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CommerceException(ex);
        }
        return items;
    }

    // crée le client si prenom inconnu, achete l'item si existant et libre sinon exception
    // retourne le solde du client, eventuellement négatif.
    public void achete(String prenom, int numItem) throws CommerceException, ItemNotFoundException {
        Item item = getFreeItem(numItem);
        Client client = getClient(prenom);
        if (client == null) {
            client = insertClient(prenom);
        }
    }


    private Item getFreeItem(int numItem) throws CommerceException, ItemNotFoundException {
        Item item = null;
        try (
                PreparedStatement s = connection.prepareStatement(
                        "SELECT description, prix " +
                                "FROM items " +
                                "WHERE num = ? " +
                                "AND client is null");
        ) {
            s.setInt(1, numItem);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                String description = rs.getString(1);
                BigDecimal prix = rs.getBigDecimal(2);
                item = new Item(numItem, description, prix, null);
            } else {
                throw new ItemNotFoundException(numItem);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CommerceException(ex);
        }
        return item;
    }

    private Client getClient(String prenom) throws CommerceException {
        Client client = null;
        try (
                PreparedStatement s = connection.prepareStatement(
                        "SELECT num,solde " +
                                "FROM clients " +
                                "WHERE prenom = ?");
        ) {
            s.setString(1,prenom);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                int num = rs.getInt(1);
                BigDecimal solde = rs.getBigDecimal(2);
                client = new Client(num,prenom,solde);
            } else {
                client = insertClient(prenom);
            }
            rs.close();
        }  catch  (SQLException ex) {
            ex.printStackTrace();
            throw new CommerceException(ex);
        }
        return client;
    }

    private Client insertClient(String prenom) throws CommerceException {
        Client client  = null;
        try (
                PreparedStatement s0 = connection.prepareStatement(
                        "SELECT seq_clients.NEXTVAL from DUAL");
                PreparedStatement s1 = connection.prepareStatement(
                        "INSERT INTO CLIENTS (num,prenom) VALUES (?, ?)");
                PreparedStatement s2 = connection.prepareStatement(
                        "INSERT INTO CLIENTS (prenom) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
        ) {
            String subprotocol = connection.getMetaData().getURL().split(":")[1];
            int num = 0;
            if (subprotocol.equals("oracle")) {
                ResultSet rs = s0.executeQuery();
                if (rs.next()) {
                    num = rs.getInt(1);
                }
                s1.setInt(1,num);
                s1.setString(2,prenom);
                s1.executeUpdate();
            } else if (subprotocol.equals("mysql")) {
                s2.setString(1,prenom);
                s2.executeUpdate();
                ResultSet rs = s2.getGeneratedKeys();
                if (rs.next()) {
                    num = rs.getInt(1);
                }
            }
            client = new Client(num,prenom,new BigDecimal(50));
        } catch  (SQLException ex) {
            ex.printStackTrace();
            throw new CommerceException(ex);
        }
        return client;
    }


}

