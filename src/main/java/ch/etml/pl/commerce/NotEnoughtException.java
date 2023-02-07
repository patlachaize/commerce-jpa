package ch.etml.pl.commerce;

public class NotEnoughtException extends Exception {
    public NotEnoughtException(Client client) {
        super(client.getPrenom() + " n'a pas assez avec le solde " + client.getSolde());
    }
}
