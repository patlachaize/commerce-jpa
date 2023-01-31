package ch.etml.pl.commerce;

import java.math.BigDecimal;

public class Item {
    private int num ;
    private String description;
    private BigDecimal prix;
    private Client client;

    public Item(int num, String description, BigDecimal prix, Client client) {
        this.num = num;
        this.description = description;
        this.prix = prix;
        this.client = client;
    }

    public int getNum() {
        return num;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        String desc =  num +
                ", " + description +
                ", " + prix;
        if (client != null) {
            desc += ", " + client.getPrenom();
        }
        return desc;
    }
}
